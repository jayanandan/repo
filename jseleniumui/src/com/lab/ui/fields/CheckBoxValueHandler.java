package com.lab.ui.fields;

import javax.swing.JCheckBox;

public class CheckBoxValueHandler implements ComponentValueHandler<JCheckBox, Boolean> {

	@Override
	public void setValue(JCheckBox p, Boolean q) {
		p.setSelected(q);
	}

	@Override
	public Boolean getValue(JCheckBox p) {
		return p.isSelected();
	}
	
}
