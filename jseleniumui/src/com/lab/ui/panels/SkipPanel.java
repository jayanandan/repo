package com.lab.ui.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.java.accessibility.util.SwingEventMonitor;

import jseleniumui.UIBuilder;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;

public class SkipPanel extends JPanel {

	MigLayout layout = new MigLayout("","[][][][][][][][][]","[][][]");
	
	/**
	 * Create the panel.
	 */
	public SkipPanel() {
		setLayout(layout);
		initialize();
	}
	
	public void initialize(){
		
		
		add(UIBuilder.createSeperator(),"width 20");		
		add(UIBuilder.createLabel("Find & Match"),"split 2, span");
		add(UIBuilder.createSeperator(),"growx, wrap");
		
		add(UIBuilder.createLabel("Find"),"skip");
		add(UIBuilder.createTxtField(),"id skip.find, width 200");
		
		add(UIBuilder.createLabel("By"),"skip");
		add(UIBuilder.createComboBox(new String[]{"id","xpath","css","class","value"}),"id skip.by");
		
		add(UIBuilder.createLabel("index"));
		add(UIBuilder.createTxtField(),"id skip.index,width 20,growx");
		
		add(UIBuilder.createSeperator(JSeparator.VERTICAL),"width 5,height 50,span 3 3,split 3");
		add(UIBuilder.createLabel("match"));
		add(UIBuilder.createTxtField(),"id skip.match,width 200,wrap,growx");
		
		add(UIBuilder.createLabel("Id"),"skip");
		add(UIBuilder.createTxtField(),"id skip.id,span 2,growx");
		
		add(UIBuilder.createChkBox("iframe"),"id skip.iframe,skip");
		add(UIBuilder.createChkBox("invisible"),"id skip.invisible");
		add(UIBuilder.createChkBox("multiple"),"id skip.multiple");
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 753, 480);
		frame.add(new SkipPanel());
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
