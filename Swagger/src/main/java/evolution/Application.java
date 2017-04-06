package evolution;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evolution.dto.RequestMappingDto;
import evolution.dto.Swagger;
import evolution.util.Sys;

public class Application {
	@SuppressWarnings("unchecked")
	public static <T> T annotation(Object object, Class<T> annotationClass) {
		try {
			T annotation = (T) object.getClass().getDeclaredMethod("getAnnotation", Class.class).invoke(object, annotationClass);
			return annotation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static RequestMappingDto requestMappingDto(Object object) {
		try {
			RequestMapping requestMapping = annotation(object, RequestMapping.class);
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
			
			// Add Swagger
			Sys.println(swagger);
			swaggers.add(swagger);
		}
		return null;
	}
}
