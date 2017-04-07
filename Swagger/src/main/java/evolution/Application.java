package evolution;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evolution.dto.RequestMappingDto;
import evolution.dto.Swagger;
import evolution.util.Ref;
import evolution.util.Sys;

public class Application {
	public static Object requestBodyDto(Method method) {
		List<Parameter> parameters = Arrays.asList(method.getParameters()).stream().filter(x -> x.getAnnotation(RequestBody.class) != null).collect(Collectors.toList());
		int parameterCount = parameters.size();
		if (parameterCount > 1) {
			Sys.println("There should be no more than one request bodies.");
			return null;
		} else if (parameterCount == 1) {
			Parameter parameter = parameters.get(0);
			try {
				return Ref.defaultObject(parameter.getType().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else  {
			return null;
		}
	}
	
	public static Object responseBodyDto(Method method) {
		try {
			return Ref.defaultObject(method.getReturnType().newInstance());
		} catch (Exception e) {
			return null;
		}
	}
	
	// The object can either be a method or class object. 
	public static RequestMappingDto requestMappingDto(Object object) {
		try {
			RequestMapping requestMapping = Ref.annotation(object, RequestMapping.class);
			if (requestMapping == null) {
				return null;
			}
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static String run(Class clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		List<Swagger> swaggers = new LinkedList<>();
		String baseUri = requestMappingDto(clazz).getUri();
		for (Method method : methods) {
			// Create Swagger
			Swagger swagger = new Swagger();
			// Set Request Mapping
			RequestMappingDto requestMappingDto = requestMappingDto(method);
			swagger.setUri(baseUri + requestMappingDto.getUri());
			swagger.setRequestMethod(requestMappingDto.getRequestMethod());
			// Set Request Body
			swagger.setRequestBodyDto(requestBodyDto(method));
			// Set Response Body
			swagger.setResponseBodyDto(responseBodyDto(method));
			// Add Swagger
			Sys.println(swagger);
			swaggers.add(swagger);
		}
		return null;
	}
}
