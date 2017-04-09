package evolution;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evolution.controller.AnyController;
import evolution.controller.dto.AnyDto;
import evolution.dto.Definition;
import evolution.dto.Delete;
import evolution.dto.Get;
import evolution.dto.Http;
import evolution.dto.HttpBody;
import evolution.dto.Items;
import evolution.dto.Parameter;
import evolution.dto.Patch;
import evolution.dto.Post;
import evolution.dto.Property;
import evolution.dto.Put;
import evolution.dto.RequestMappingDto;
import evolution.dto.Response;
import evolution.dto.Schema;
import evolution.dto.Swagger;

public class Application {
	public static final String DEFINITIONS = "#/definitions/";
	
	public static Object requestBodyDto(Method method) {
		try {
			return Ref.defaultObject(Arrays.asList(method.getParameters())
					.stream().filter(x -> x.getAnnotation(RequestBody.class) != null)
					.collect(Collectors.toList()).get(0).getType());
		} catch (Exception e) {
			System.out.println("There is no request body or the request body count is greater than one.");
			return null;
		}
	}
	
	public static Object responseBodyDto(Method method) {
		return Ref.defaultObject(method.getReturnType());
	}
	
	// The object can either be a method or class object. 
	public static RequestMappingDto requestMappingDto(Object object) {
		RequestMapping requestMapping = Ref.annotation(object, RequestMapping.class);
		RequestMappingDto requestMappingDto = new RequestMappingDto();
		String[] uris = requestMapping.value();
		if (uris.length > 0) {
			requestMappingDto.setUri(uris[0]);
		}
		RequestMethod[] requestMethods = requestMapping.method();
		if (requestMethods.length > 0) {
			requestMappingDto.setRequestMethod(requestMethods[0].name());
		}
		return requestMappingDto;
	}
	
	public static void addDefinition(Object object, Map<String, Definition> definitions) {
		if (Ref.isBasic(object)) {
			return;
		}
		String dtoClassName = Ref.simpleClassName(object);
		if (!definitions.containsKey(dtoClassName)) {
			definitions.put(dtoClassName, definition(object));
		}
	}
	
	public static Definition definition(Object object) {
		if (Ref.isBasic(object)) {
			return null;
		}
		Map<String, Property> properties = new LinkedHashMap<>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Property property = new Property();
			if (Ref.isBasic(field)) {
				property.setType(type(field));
			} else if (Ref.isList(field)) {
				property.setType("array");
				property.setItems(listItems(field));
			}  else {
				property.setType("object");
				property.set$ref(DEFINITIONS + Ref.simpleClassName(field));
			}
			properties.put(field.getName(), property);
		}
		Definition definition = new Definition();
		definition.setType("object");
		definition.setProperties(properties);
		return definition;
	}
	
	@Test
	public void testDefinition() {
		AnyDto anyDto = new AnyDto();
		System.out.println(definition(anyDto));
	}
	
	public static Items listItems(Method method, boolean isRequestBody) {
		Items items = new Items();
		Class<?> clazz = null;
		if (isRequestBody) {
			clazz = Ref.genericClass(method, RequestBody.class, 0);
		} else {
			clazz = Ref.genericClass(method, 0);
		}
		if (Ref.isBasic(clazz)) {
			items.setType(clazz.getSimpleName().toLowerCase());
		} else {
			items.set$ref(DEFINITIONS + clazz.getSimpleName());
		}
		return items;
	}
	
	public static Items listItems(Field field) {
		Items items = new Items();
		Class<?> clazz = Ref.genericClass(field, 0);
		if (Ref.isBasic(clazz)) {
			items.setType(clazz.getSimpleName().toLowerCase());
		} else {
			items.set$ref(DEFINITIONS + clazz.getSimpleName());
		}
		return items;
	}
	
	public static Schema listSchema(Method method, boolean isRequestBody) {
		Schema schema = new Schema();
		schema.setItems(listItems(method, isRequestBody));
		return schema;
	}
	
	public static Schema refSchema(Object object) {
		Schema schema = new Schema();
		schema.set$ref(DEFINITIONS + Ref.simpleClassName(object));
		return schema;
	}
	
	public static Schema typeSchema(Object object) {
		Schema schema = new Schema();
		schema.setType(Ref.simpleClassName(object).toLowerCase());
		return schema;
	}
	
	public static String type(Object object) {
		if (object instanceof Class) {
			return Ref.simpleClassName((Class<?>) object).toLowerCase();
		} else if (object instanceof Field) {
			return Ref.simpleClassName((Field) object).toLowerCase();
		} else {
			return Ref.simpleClassName(object).toLowerCase();
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static void swagger(Class controllerClass) {
		Map<String, Http> paths = new LinkedHashMap<>();
		Map<String, Definition> definitions = new LinkedHashMap<>();
		List<Method> methods = Arrays.asList(controllerClass.getDeclaredMethods())
				.stream().filter(x -> x.getAnnotation(RequestMapping.class) != null)
				.collect(Collectors.toList());
		for (Method method : methods) {
			HttpBody httpBody = new HttpBody();
			// Request Body
			Object requestBodyDto = requestBodyDto(method);
			addDefinition(requestBodyDto, definitions);
			Parameter parameter = new Parameter();
			if (Ref.isBasic(requestBodyDto)) {
				parameter.setType(type(requestBodyDto));
			} else if (Ref.isList(requestBodyDto)) {
				parameter.setSchema(listSchema(method, true));
			} else {// POJO
				parameter.setSchema(refSchema(requestBodyDto));
			}
			httpBody.setParameters(Arrays.asList(parameter));// TODO There can be more than one parameters.
			// Response Body
			Object responseBodyDto = responseBodyDto(method);
			addDefinition(responseBodyDto, definitions);
			Response response = new Response();
			if (Ref.isBasic(responseBodyDto)) {
				response.setSchema(typeSchema(responseBodyDto));
			} else if (Ref.isList(responseBodyDto)) {
				response.setSchema(listSchema(method, false));
			} else {// POJO
				response.setSchema(refSchema(responseBodyDto));
			}
			Map<String, Response> responses = new LinkedHashMap<>();
			responses.put("200", response);// TODO Response code is not limited to 200.
			httpBody.setResponses(responses);
			// Request Mapping
			RequestMappingDto requestMappingDto = requestMappingDto(method);
			Http http = null;
			switch (requestMappingDto.getRequestMethod()) {
			case "GET":
				Get get = new Get();
				get.setGet(httpBody);
				http = get;
				break;
			case "POST":
				Post post = new Post();
				post.setPost(httpBody);
				http = post;
				break;
			case "PATCH":
				Patch patch = new Patch();
				patch.setPatch(httpBody);
				http = patch;
				break;
			case "DELETE":
				Delete delete = new Delete();
				delete.setDelete(httpBody);
				http = delete;
				break;
			case "PUT":
				Put put = new Put();
				put.setPut(httpBody);
				http = put;
				break;
			}
			paths.put(requestMappingDto.getUri(), http);
		}
		Swagger swagger = new Swagger();
		swagger.setPaths(paths);
		swagger.setDefinitions(definitions);
		swagger.setBasePath(requestMappingDto(controllerClass).getUri());
		Yml.write(swagger, "/home/chen/Desktop/Playground/Data/swagger.yml", true, new MyRepresenter(true, true, null), true);
	}
	
	@Test
	public void testSwagger() {
		Application.swagger(AnyController.class);
	}
}
