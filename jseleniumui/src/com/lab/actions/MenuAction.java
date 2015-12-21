package com.lab.actions;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import jseleniumui.ApplicationUI;

import com.lab.model.Leaf;
import com.lab.panels.LeftNavPanel;
import com.lab.util.ApplicationUtil.LeafAction;
import com.lab.util.ApplicationUtil.LeafType;
import com.lab.util.ApplicationUtil.ScriptType;

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
					String projName = JOptionPane.showInputDialog(getParent(e),"Please enter the project name","Name",JOptionPane.PLAIN_MESSAGE);
					ApplicationUI.leftNanPanel.intialize(projName);
					break;
				}
				case NEW_STEP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),"Please enter the step name","Name",JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEPS,ScriptType.STEP, stepName);
					break;
				}
				case NEW_SUB_STEP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),"Please enter the substep name","Name",JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEPS,ScriptType.STEP_GRP, stepName);
					break;
				}
				case NEW_REC_STEP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),"Please enter the recursive name","Name",JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEPS,ScriptType.REC_STEP,stepName);
					break;
				}
				case NEW_STEP_GRP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),"Please enter the stepgroup name","Name",JOptionPane.PLAIN_MESSAGE);
					addLeaf(LeafType.STEP_GROUPS,ScriptType.STEP_GRP,stepName);
					break;
				}
				case NEW_MAIN_FLW:{
					String stepName = JOptionPane.showInputDialog(getParent(e),"Please enter the mainflow name","Name",JOptionPane.PLAIN_MESSAGE);
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
		Leaf leaf = new Leaf(lType, name);
		leaf.setScriptType(scriptType);
		return LeftNavPanel.addLeaf(leaf);
	}
	
	private Component getParent(ActionEvent e){
		JMenuItem menuItem = (JMenuItem) e.getSource();
        JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
        Component invoker = popupMenu.getInvoker(); //this is the JMenu (in my code)
        JComponent invokerAsJComponent = (JComponent) invoker;
        return invokerAsJComponent.getTopLevelAncestor();
	}
	
	
	
	
}