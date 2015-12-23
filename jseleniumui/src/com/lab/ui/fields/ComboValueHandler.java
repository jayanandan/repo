package com.lab.ui.fields;

import javax.swing.JComboBox;

public class ComboValueHandler implements ComponentValueHandler<JComboBox<String>, String>{

	@Override
	public void setValue(JComboBox<String> p, String q) {
		p.setSelectedItem(q);
	}

	@Override
	public String getValue(JComboBox<String> p) {		
		return (String)p.getSelectedItem();
	}	
	
}
