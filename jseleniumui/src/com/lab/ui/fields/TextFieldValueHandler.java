package com.lab.ui.fields;

import javax.swing.JTextField;

public class TextFieldValueHandler implements ComponentValueHandler<JTextField, String> {

	@Override
	public void setValue(JTextField p, String q) {
		p.setText(q);
	}

	@Override
	public String getValue(JTextField p) {
		return p.getText();
	}
	
}
