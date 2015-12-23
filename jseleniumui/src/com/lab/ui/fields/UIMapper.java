/**
 * 
 */
package com.lab.ui.fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 334049
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UIMapper {	
	String id();
	FieldType type();
	Class<? extends ComponentValueHandler>[] handler() default {};
}
