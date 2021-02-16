package com.silva.chetax.spring.life.test.demo.reflection;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD, METHOD, CONSTRUCTOR })
@Inherited
public @interface Test001Annotation {
	AnnotationPosition value() default AnnotationPosition.DEFAULT;
}
