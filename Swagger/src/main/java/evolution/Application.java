package evolution;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evolution.dto.AdditionalProperties;
import evolution.dto.Contact;
import evolution.dto.Definition;
import evolution.dto.Delete;
import evolution.dto.ExternalDocs;
import evolution.dto.Get;
import evolution.dto.Http;
import evolution.dto.HttpBody;
import evolution.dto.Info;
import evolution.dto.Items;
import evolution.dto.License;
import evolution.dto.Parameter;
import evolution.dto.Patch;
import evolution.dto.Post;
import evolution.dto.Property;
import evolution.dto.Put;
import evolution.dto.RequestMappingDto;
import evolution.dto.Response;
import evolution.dto.Schema;
import evolution.dto.Swagger;
import evolution.dto.Tag;

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
	
	public static void addDefinition(Object object, Map<String, Definition> definitions, Method method, Boolean isRequestBody) {
		addDefinition(object.getClass(), definitions, method, isRequestBody);
	}
	
	public static void addDefinition(Class<?> clazz, Map<String, Definition> definitions, Method method, Boolean isRequestBody) {
		String className = Ref.simpleClassName(clazz);
		if (definitions.containsKey(className)) {
			return;
		} else if (Ref.isList(clazz)) {// TODO For the time being, the value of List should be neither List nor Map. 
			if (isRequestBody) {
				addDefinition(Ref.genericClass(method, RequestBody.class, 0), definitions, null, null);
			} else {
				addDefinition(Ref.genericClass(method, 0), definitions, null, null);
			}
		} else if (Ref.isMap(clazz)) {// TODO For the time being, the value of Map should be neither List nor Map.
			if (isRequestBody) {
				addDefinition(Ref.genericClass(method, RequestBody.class, 1), definitions, null, null);
			} else {
				addDefinition(Ref.genericClass(method, 1), definitions, null, null);
			}
		} else {
			Map<String, Property> properties = new LinkedHashMap<>();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Property property = new Property();
				if (Ref.isBasic(field)) {
					property.setType(type(field));
				} else if (Ref.isList(field)) {
					property.setType("array");
					property.setItems(listItems(field));
				} else if (Ref.isMap(field)) {
					addDefinition(Ref.genericClass(field, 1), definitions, null, null);
				} else {// POJO
					property.set$ref(DEFINITIONS + Ref.simpleClassName(field));
					addDefinition(field.getType(), definitions, null, null);
				}
				properties.put(field.getName(), property);
			}
			Definition definition = new Definition();
			definition.setType("object");
			definition.setProperties(properties);
			definitions.put(className, definition);
		}
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
	
	public static Schema mapSchema(Method method, boolean isRequestBody) {
		Class<?> mapValueClass = null;
		if (isRequestBody) {
			mapValueClass = Ref.genericClass(method, RequestBody.class, 1);
		} else {// ResponseBody
			mapValueClass = Ref.genericClass(method, 1);
		}
		AdditionalProperties additionalProperties = new AdditionalProperties();
		if (Ref.isBasic(mapValueClass)) {
			additionalProperties.setType(type(mapValueClass));
		} else {// TODO List and Map can also be the value of a map.
			additionalProperties.set$ref(DEFINITIONS + mapValueClass.getSimpleName());
		}
		Schema schema = new Schema();
		schema.setAdditionalProperties(additionalProperties);
		return schema;
	}
	
	public static Schema listSchema(Method method, boolean isRequestBody) {
		Schema schema = new Schema();
		schema.setType("array");
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
		String className;
		if (object instanceof Class) {
			className = Ref.simpleClassName((Class<?>) object).toLowerCase();
		} else if (object instanceof Field) {
			className = Ref.simpleClassName((Field) object).toLowerCase();
		} else {
			className = Ref.simpleClassName(object).toLowerCase();
		}
		switch (className) {
		case "int":
			return "integer";
		case "double":
			return "number";
		default:
			return className;
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static void swagger(Class controllerClass, String filePath) {
		Map<String, Http> paths = new LinkedHashMap<>();
		Map<String, Definition> definitions = new LinkedHashMap<>();
		List<Method> methods = Arrays.asList(controllerClass.getDeclaredMethods())
				.stream().filter(x -> x.getAnnotation(RequestMapping.class) != null)
				.collect(Collectors.toList());
		for (Method method : methods) {
			HttpBody httpBody = new HttpBody();
			httpBody.setConsumes(Arrays.asList("application/json"));
			httpBody.setProduces(Arrays.asList("application/json"));
			// Request Body
			Object requestBodyDto = requestBodyDto(method);
			addDefinition(requestBodyDto, definitions, method, true);
			Parameter parameter = new Parameter();
			if (Ref.isBasic(requestBodyDto)) {
				parameter.setType(type(requestBodyDto));
			} else if (Ref.isList(requestBodyDto)) {
				parameter.setSchema(listSchema(method, true));
			} else if (Ref.isMap(requestBodyDto)) {
				parameter.setSchema(mapSchema(method, true));
			} else {// POJO
				parameter.setSchema(refSchema(requestBodyDto));
			}
			parameter.setName("requestBody");
			parameter.setIn("body");
			httpBody.setParameters(Arrays.asList(parameter));// TODO There can be more than one parameters.
			// Response Body
			Object responseBodyDto = responseBodyDto(method);
			addDefinition(responseBodyDto, definitions, method, false);
			Response response = new Response();
			response.setDescription("Success");
			if (Ref.isBasic(responseBodyDto)) {
				response.setSchema(typeSchema(responseBodyDto));
			} else if (Ref.isList(responseBodyDto)) {
				response.setSchema(listSchema(method, false));
			} else if (Ref.isMap(responseBodyDto)) {
				response.setSchema(mapSchema(method, false));
			} else {// POJO
				response.setSchema(refSchema(responseBodyDto));
			}
			Map<Integer, Response> responses = new LinkedHashMap<>();
			responses.put(200, response);// TODO Response code is not limited to 200.
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
		swagger.setSwagger("2.0");
		swagger.setInfo(info());
		swagger.setHost("Unknown");
		swagger.setBasePath(requestMappingDto(controllerClass).getUri());
		swagger.setTags(Arrays.asList(tag()));
		swagger.setSchemes(Arrays.asList("http"));
		swagger.setPaths(paths);
		swagger.setDefinitions(definitions);
		swagger.setExternalDocs(externalDocs());
		Yml.write(swagger, filePath, true, new MyRepresenter(true, true, null), true);
	}
	
	public static ExternalDocs externalDocs() {
		ExternalDocs externalDocs = new ExternalDocs();
		externalDocs.setDescription("Unknown");
		externalDocs.setUrl("Unknown");
		return externalDocs;
	}
	
	public static Tag tag() {
		Tag tag = new Tag();
		tag.setName("Unknown");
		tag.setDescription("Unknown");
		tag.setExternalDocs(externalDocs());
		return tag;
	}
	
	public static Info info() {
		Info info = new Info();
		info.setDescription("Unknown");
		info.setVersion("Unknown");
		info.setTitle("Unknown");
		info.setTermsOfService("Unknown");
		Contact contact = new Contact();
		contact.setEmail("Unknown");
		info.setContact(contact);
		License license = new License();
		license.setUrl("Unknown");
		license.setName("Unknown");
		return info;
	}
}
