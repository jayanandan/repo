package com.lab.actions;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.apache.commons.lang3.StringUtils;

import jseleniumui.ApplicationUI;

import com.lab.model.Leaf;
import com.lab.ui.panels.LeftNavPanel;
import com.lab.util.ApplicationUtil;
import com.lab.util.ApplicationUtil.LeafAction;
import com.lab.util.ApplicationUtil.LeafType;
import com.lab.util.ApplicationUtil.ScriptType;
import com.lab.util.Constants;

public class MenuAction extends JMenuItem{
	
	private LeafAction action;
	
	public MenuAction(final LeafAction action){
		super(action.getValue());
		this.action = action;
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(action.getValue()+" is performed.");
				
				switch (action) {
				case NEW_PROJECT:{
					
					String projName = JOptionPane.showInputDialog(getParent(e),ApplicationUtil.properties.getProperty(Constants.NEW_PROJ_MSG),StringUtils.EMPTY,JOptionPane.PLAIN_MESSAGE);
					if(StringUtils.isNotBlank(projName) && StringUtils.isAlphaSpace(projName)){
						ApplicationUI.leftNanPanel.intialize(projName);						
					}
					break;
				}
				case NEW_STEP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),ApplicationUtil.properties.getProperty(Constants.NEW_STEP_MSG),StringUtils.EMPTY,JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEPS,ScriptType.STEP, stepName);
					break;
				}
				case NEW_SUB_STEP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),ApplicationUtil.properties.getProperty(Constants.NEW_SUBSTEP_MSG),StringUtils.EMPTY,JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEPS,ScriptType.SUB_STEP, stepName);
					break;
				}
				case NEW_REC_STEP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),ApplicationUtil.properties.getProperty(Constants.NEW_RECSTEP_MSG),StringUtils.EMPTY,JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEPS,ScriptType.REC_STEP,stepName);
					break;
				}
				case NEW_STEP_GRP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),ApplicationUtil.properties.getProperty(Constants.NEW_STEPGRP_MSG),StringUtils.EMPTY,JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEP_GROUPS,ScriptType.STEP_GRP,stepName);
					break;
				}
				case NEW_MAIN_FLW:{
					String stepName = JOptionPane.showInputDialog(getParent(e),ApplicationUtil.properties.getProperty(Constants.NEW_MAINFLW_MSG),StringUtils.EMPTY,JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.MAIN_FLOWS,ScriptType.MAIN_FLW,stepName);
					break;
				}
				default:
					break;
				}
				
			}
		});
	}	
	
	private boolean addLeaf(LeafType lType,ScriptType scriptType,String name){
		if (null != name && !"".equals(name) && name.trim().length() > 0) {
			Leaf leaf = new Leaf(lType, name);
			leaf.setScriptType(scriptType);
			return LeftNavPanel.addLeaf(leaf);			
		}
		return Boolean.FALSE;
	}
	
	private Component getParent(ActionEvent e){
		JMenuItem menuItem = (JMenuItem) e.getSource();
        JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
        Component invoker = popupMenu.getInvoker(); //this is the JMenu (in my code)
        JComponent invokerAsJComponent = (JComponent) invoker;
        return invokerAsJComponent.getTopLevelAncestor();
	}
	
	
	
	
}