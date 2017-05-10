package evolution.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DefinitionExample {
	public int intValue() default Integer.MIN_VALUE;
	public String stringValue() default "";
	public double doubleValue() default Double.MIN_VALUE;
}
