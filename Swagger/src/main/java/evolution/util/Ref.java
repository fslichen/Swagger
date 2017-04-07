package evolution.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ref {
	public static Boolean isMap(Class<?> clazz) {
		return clazz == Map.class;
	}
	
	public static Boolean isMap(Object object) {
		return isMap(object.getClass());
	}
	
	public static Boolean isMap(Field field) {
		return isMap(field.getType());
	}
	
	// Get the annotation of a class, method or field object.
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

	public static Object defaultBasicObject(Class<?> clazz) {
		// TODO Add more criteria.
		if (clazz == int.class || clazz == Integer.class) {
			return 0;
		} else if (clazz == double.class || clazz == Double.class) {
			return 0d;
		} else if (clazz == String.class) {
			return "anyString";
		} else if (clazz == Date.class) {
			return new Date();
		}
		return null;
	}

	public static Object defaultBasicObject(Field field) {
		return defaultBasicObject(field.getType());
	}

	public static Object defaultBasicObject(Object object) {
		return defaultBasicObject(object.getClass());
	}

	public static Object defaultObject(Class<?> clazz) {
		try {
			return defaultObject(clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object defaultObject(Field field) {
		try {
			return defaultObject(field.getType().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object defaultObject(Object object) {
		try {
			if (isBasic(object)) {
				return defaultBasicObject(object);
			}
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (isBasic(field)) {
					field.set(object, defaultBasicObject(field));
				} else if (isList(field)) {
					List list = new LinkedList();
					list.add(defaultObject(genericClass(field, 0)));
					field.set(object, list);
				} else if (isMap(field)) {
					Map map = new LinkedHashMap();
					List<Class<?>> genericClasses = genericClasses(field);
					map.put(defaultObject(genericClasses.get(0)), defaultObject(genericClasses.get(1)));
					field.set(object, map);
				} else {
					field.set(object, defaultObject(field));
				}
			}
			return object;
		} catch (Exception e) {
			Sys.println(object + " is invalid.");
			return null;
		}
	}

	public static Type[] actualTypeArguments(Field field) {
		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		return parameterizedType.getActualTypeArguments();
	}
	
	public static List<Class<?>> genericClasses(Field field) {
		List<Class<?>> genericClasses = new LinkedList<>();
		Type[] types = actualTypeArguments(field);
		for (Type type : types) {
			genericClasses.add((Class<?>) type);
		}
		return genericClasses;
	}
	
	public static Class<?> genericClass(Field field, int i) {
		return (Class<?>) actualTypeArguments(field)[i];
	}

	public static Boolean isBasic(Class<?> clazz) {
		// TODO Add more criteria.
		if (clazz == int.class || clazz == Integer.class
				|| clazz == double.class || clazz == Double.class
				|| clazz == String.class
				|| clazz == Date.class) {
			return true;
		}
		return false;
	}

	public static Boolean isBasic(Field field) {
		return isBasic(field.getType());
	}

	public static Boolean isBasic(Object object) {
		return isBasic(object.getClass());
	}

	public static Boolean isList(Class<?> clazz) {
		return clazz == List.class;
	}

	public static Boolean isList(Field field) {
		return isList(field.getType());
	}
	
	public static Boolean isList(Object object) {
		return isList(object.getClass());
	}
}
