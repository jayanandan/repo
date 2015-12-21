package com.lab.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationUtil {
	
	public static final Properties properties = new Properties();
	
	static{
		InputStream inputStream = ApplicationUtil.class.getResourceAsStream("/app.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static enum LeafType{
		PROJECT("menu.project"),STEPS("menu.steps"),STEP_GROUPS("menu.stepgroups"),MAIN_FLOWS("menu.mainflows");
		private String value;
		private String key;

		LeafType(String key){
			this.key = key;
		}
		
		public String getValue() {
			if(this.value==null){
				this.value=ApplicationUtil.properties.getProperty(key);
			}
			return value;
		}
		
	}
	
	public static enum LeafAction{
		NEW_PROJECT("actoin.create.project"),NEW_STEP("action.create.step"),NEW_SUB_STEP("action.create.substep"),NEW_REC_STEP("action.create.recstep"),NEW_STEP_GRP("action.create.stepgrp"),NEW_MAIN_FLW("action.create.mainflow");
		private String value;
		private String key;

		LeafAction(String key){
			this.key = key;
		}
		
		public String getValue() {
			if(this.value==null){
				this.value=ApplicationUtil.properties.getProperty(key);
			}
			return value;
		}
		
	}
	
	public static enum ScriptType{
		TOP,STEP,SUB_STEP,REC_STEP,STEP_GRP,MAIN_FLW;
	}
}