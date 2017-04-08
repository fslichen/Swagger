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
	
	public static void addDefinition(Object dto, Map<String, Definition> definitions) {
		if (Ref.isBasic(dto)) {
			return;
		}
		String dtoClassName = Ref.simpleClassName(dto);
		if (!definitions.containsKey(dtoClassName)) {
			definitions.put(dtoClassName, definition(dto));
		}
	}
	
	public static Definition definition(Object dto) {
		if (Ref.isBasic(dto)) {
			return null;
		}
		Map<String, Property> properties = new LinkedHashMap<>();
		Field[] fields = dto.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Property property = new Property();
			String fieldClassName = Ref.simpleClassName(field);
			if (Ref.isBasic(field)) {
				property.setType(fieldClassName.toLowerCase());
			} else if (Ref.isList(field)) {
				property.setType("array");
				Items items = new Items();
				items.set$ref(DEFINITIONS + Ref.simpleClassName(Ref.genericClass(field, 0)));
				property.setItems(items);
			}  else {
				property.setType("object");
				property.set$ref(DEFINITIONS + fieldClassName);
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
			Schema requestSchema = new Schema();// TODO The request body is not necessarily a DTO since it can also be a List.
			requestSchema.set$ref(DEFINITIONS + Ref.simpleClassName(requestBodyDto));
			Parameter parameter = new Parameter();
			parameter.setSchema(requestSchema);
			httpBody.setParameters(Arrays.asList(parameter));// TODO There can be more than one parameters.
			// Response Body
			Object responseBodyDto = responseBodyDto(method);
			addDefinition(responseBodyDto, definitions);
			Schema responseSchema = new Schema();// TODO The response body is not necessarily a DTO since it can also be a List.
			responseSchema.set$ref(DEFINITIONS + Ref.simpleClassName(responseBodyDto));
			Response response = new Response();
			response.setSchema(responseSchema);
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
