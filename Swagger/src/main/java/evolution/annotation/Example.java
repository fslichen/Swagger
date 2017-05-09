package evolution.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Example {
	public String value() default "";
	@AliasFor("value")
	public String stringValue() default "";
	public int intValue() default Integer.MIN_VALUE;
	public double doubleValue() default Double.MIN_VALUE;
}
