package com.lab.util;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.lab.ui.panels.SkipPanel;

public class UIBuilder {

	private static Map<String,Component> components;
	static{
		components = new HashMap<String, Component>();
		components.put(SkipPanel.NAME, SkipPanel.skipPanel);
	}
	
	public static Component getComponentByName(String name){
		return components.get(name);
	}
	
	private static void addComponent(String id,Component c){
		if(components.get(id) != null){
			System.err.print("Component with the same id ["+id+"] is already present.");
		}
		components.put(id, c);
	}
	
	public static JSeparator createSeperator(int orientation){
		JSeparator jSeparator = new JSeparator(orientation);
		return jSeparator;
	}
	
	public static JSeparator createSeperator(){
		return createSeperator(JSeparator.HORIZONTAL);
	}
	
	public static JLabel createLabel(String label){
		JLabel jLabel = new JLabel(label);
		return jLabel;		
	}
	
	public static JTextField createTxtField(final String id){
		JTextField textField = new JTextField(){
			@Override
			public String getName() {
				return id;
			}};
			addComponent(id, textField);
		return textField;
	}
	
	public static JComboBox<String> createComboBox(final String id,String... values ){
		JComboBox<String> jComboBox = new JComboBox<String>(values){
			@Override
			public String getName() {
				return id;
			}
		};
		addComponent(id, jComboBox);
		return jComboBox;
	}
	
	public static JCheckBox createChkBox(final String id,String label){
		JCheckBox jCheckBox = new JCheckBox(label){
			@Override
			public String getName() {
				return id;
			}
		};
		addComponent(id, jCheckBox);
		return jCheckBox;
	}
	
	public static JButton createButton(String label){
		return new JButton(label);
	}
	
}
