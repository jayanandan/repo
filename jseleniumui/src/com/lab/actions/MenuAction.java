package com.lab.actions;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.lab.model.Leaf;
import com.lab.panels.LeftNavPanel;
import com.lab.util.ApplicationUtil.LeafAction;
import com.lab.util.ApplicationUtil.LeafType;


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
					JOptionPane.showInputDialog(getParent(e),"Please enter the project name","Name",JOptionPane.PLAIN_MESSAGE);
					break;
				}
				case NEW_STEP:{
					String stepName = JOptionPane.showInputDialog(getParent(e),"Please enter the step name","Name",JOptionPane.PLAIN_MESSAGE);
					Leaf leaf = new Leaf(LeafType.STEPS, stepName);
					LeftNavPanel.addLeaf(leaf);
					break;
				}
				case NEW_SUB_STEP:{
					JOptionPane.showInputDialog(getParent(e),"Please enter the substep name","Name",JOptionPane.PLAIN_MESSAGE);
					break;
				}
				case NEW_REC_STEP:{
					JOptionPane.showInputDialog(getParent(e),"Please enter the recursive name","Name",JOptionPane.PLAIN_MESSAGE);
					break;
				}
				case NEW_STEP_GRP:{
					JOptionPane.showInputDialog(getParent(e),"Please enter the stepgroup name","Name",JOptionPane.PLAIN_MESSAGE);
					break;
				}
				case NEW_MAIN_FLW:{
					JOptionPane.showInputDialog(getParent(e),"Please enter the mainflow name","Name",JOptionPane.PLAIN_MESSAGE);					
					break;
				}
				default:
					break;
				}
				
			}
		});
	}	
	
	private Component getParent(ActionEvent e){
		JMenuItem menuItem = (JMenuItem) e.getSource();
        JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
        Component invoker = popupMenu.getInvoker(); //this is the JMenu (in my code)
        JComponent invokerAsJComponent = (JComponent) invoker;
        return invokerAsJComponent.getTopLevelAncestor();
	}
	
	
	
	
}