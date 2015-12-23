package jseleniumui;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class UIBuilder {

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
	
	public static JTextField createTxtField(){
		JTextField textField = new JTextField();
		return textField;
	}
	
	public static JComboBox<String> createComboBox(String... values ){
		JComboBox<String> jComboBox = new JComboBox<>(values);
		return jComboBox;
	}
	
	public static JCheckBox createChkBox(String label){
		return new JCheckBox(label);
	}
	
}
