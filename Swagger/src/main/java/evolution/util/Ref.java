package evolution.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Ref {
	public static Boolean isList(Object object) {
		return isList(object.getClass());
	}

	public static Boolean isList(Field field) {
		return isList(field.getType());
	}

	public static Boolean isList(Class<?> clazz) {
		if (clazz == List.class) {
			return true;
		}
		return false;
	}

	public static Boolean isBasic(Object object) {
		return isBasic(object.getClass());
	}

	public static Boolean isBasic(Field field) {
		return isBasic(field.getType());
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

	public static Object defaultBasicObject(Field field) {
		return defaultBasicObject(field.getType());
	}

	public static Object defaultBasicObject(Object object) {
		return defaultBasicObject(object.getClass());
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

	public static Class<?> genericClass(Field field, int i) {
		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		return (Class<?>) parameterizedType.getActualTypeArguments()[i];
	}

	public static Object defaultObject(Field field) {
		try {
			return defaultObject(field.getType().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object defaultObject(Class<?> clazz) {
		try {
			return defaultObject(clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object defaultObject(Object object) {
		try {
			if (Ref.isBasic(object)) {
				return defaultBasicObject(object);
			}
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (Ref.isBasic(field)) {
					field.set(object, defaultBasicObject(field));
				} else if (Ref.isList(field)) {
					List list = new LinkedList();
					list.add(defaultObject(genericClass(field, 0)));
					field.set(object, list);
				} else {
					field.set(object, defaultObject(field));
				}
			}
			return object;
		} catch (Exception e) {
			Sys.println("The invalid object is " + object);
			return object;
		}
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
}
