package com.lab.util;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.apache.commons.beanutils.BeanUtils;

import com.lab.ui.fields.CheckBoxValueHandler;
import com.lab.ui.fields.ComboValueHandler;
import com.lab.ui.fields.ComponentValueHandler;
import com.lab.ui.fields.FieldType;
import com.lab.ui.fields.TextFieldValueHandler;
import com.lab.ui.fields.UIMapper;

public class UIModelBinder {

	private static UIModelBinder instance = new UIModelBinder();
	private UIModelBinder(){}
	public static UIModelBinder getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[]  buildModel(Class<T>[] t, Container c) throws InstantiationException, IllegalAccessException, InvocationTargetException{
		Map<Field, UIMapper> fieldMappers=null;
		List<T> instances_ = new ArrayList<>();
		
		for (Class<T> class_ : t) {
			fieldMappers = getComponentIds(class_);
			
			T instance_ = class_.newInstance();
			instances_.add(instance_);
			
			for(Field field_ : fieldMappers.keySet()){
				UIMapper uiMapper = fieldMappers.get(field_);
				Component component = UIBuilder.getComponentByName(uiMapper.id());
				if(component != null) {
					Object uiValue = getUIValue(uiMapper,component);
					BeanUtils.setProperty(instance_, field_.getName(), uiValue);
				}
			}
		}
		return (T[])instances_.toArray();
	}
	
	private static Map<Field,UIMapper> getComponentIds(Class<?> t){
		final Map<Field,UIMapper> mappers = new HashMap<>();
		
		Field[] fields = t.getFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(UIMapper.class)){
				mappers.put(field,field.getAnnotation(UIMapper.class));				
			}
		}
		return mappers;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object getUIValue(UIMapper mapper,Component c) throws InstantiationException, IllegalAccessException{
		
		ComponentValueHandler handler = null;
		Object fieldValue = null;
		if(mapper.handler() != null && mapper.handler().length>0){
			handler = mapper.handler()[0].newInstance();
		}
		
		fieldValue = handler.getValue(c);
		
		FieldType fieldType = mapper.type();
		switch (fieldType) {
			case TEXT_FIELD:{
				TextFieldValueHandler valueHandler = new TextFieldValueHandler();
				fieldValue = valueHandler.getValue((JTextField)c);
				break;
			}case CHECKBOX:{
				fieldValue = new CheckBoxValueHandler().getValue((JCheckBox)c);
				break;			
			}case COMBO:{
				fieldValue = new ComboValueHandler().getValue((JComboBox)c);
				break;			
			}
		}
		return fieldValue;
		
	}
	
}
