package evolution.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ApiExamples {
	public String[] keys() default "";
	public String[] stringValues() default "";
	public int[] intValues() default Integer.MIN_VALUE;
	// TODO Add support for other data types.
}
