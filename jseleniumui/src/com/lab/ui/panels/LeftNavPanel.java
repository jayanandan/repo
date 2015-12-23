package com.lab.ui.panels;

import static com.lab.model.Leaf.MAINFLW_LEAF;
import static com.lab.model.Leaf.PROJECT_LEAF;
import static com.lab.model.Leaf.STEPGRP_LEAF;
import static com.lab.model.Leaf.STEPS_LEAF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.lab.actions.MenuAction;
import com.lab.model.Leaf;
import com.lab.util.ApplicationUtil.LeafAction;
import com.lab.util.ApplicationUtil.LeafType;
import com.lab.util.ApplicationUtil.ScriptType;
import com.lab.util.CTreeCellRenderer;

public class LeftNavPanel extends JPanel {

	private static final JPopupMenu STEPS_POPUP_MENU = new JPopupMenu();
	private static final JPopupMenu STEPGRP_POPUP_MENU = new JPopupMenu();
	private static final JPopupMenu MF_POPUP_MENU = new JPopupMenu();
	public static JTree leftNavTree = null;
	static {

		STEPS_POPUP_MENU.add(new MenuAction(LeafAction.NEW_STEP));
		STEPS_POPUP_MENU.add(new MenuAction(LeafAction.NEW_SUB_STEP));
		STEPS_POPUP_MENU.add(new MenuAction(LeafAction.NEW_REC_STEP));

		STEPGRP_POPUP_MENU.add(new MenuAction(LeafAction.NEW_STEP_GRP));

		MF_POPUP_MENU.add(new MenuAction(LeafAction.NEW_MAIN_FLW));
		
		((DefaultMutableTreeNode) PROJECT_LEAF.getNode()).add((DefaultMutableTreeNode) STEPS_LEAF.getNode());
		((DefaultMutableTreeNode) PROJECT_LEAF.getNode()).add((DefaultMutableTreeNode) STEPGRP_LEAF.getNode());
		((DefaultMutableTreeNode) PROJECT_LEAF.getNode()).add((DefaultMutableTreeNode) MAINFLW_LEAF.getNode());
		
		leftNavTree = new JTree(PROJECT_LEAF.getNode());
		leftNavTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				
			}
		});
		leftNavTree.setVisible(false);
		leftNavTree.setLayout(new GridLayout(0, 1));
		leftNavTree.setExpandsSelectedPaths(true);
		leftNavTree.setCellRenderer(new CTreeCellRenderer());
	}

	/**
	 * Create the panel.
	 */
	public LeftNavPanel() {
		setLayout(new BorderLayout(1, 1));
		JScrollPane scrollPane = new JScrollPane(leftNavTree);
		add(scrollPane, BorderLayout.CENTER);
		//intialize(null);
	}
	
	public void intialize(String projName){
		
		((DefaultMutableTreeNode)STEPS_LEAF.getNode()).removeAllChildren();
		((DefaultMutableTreeNode)STEPGRP_LEAF.getNode()).removeAllChildren();
		((DefaultMutableTreeNode)MAINFLW_LEAF.getNode()).removeAllChildren();
		
		leftNavTree.setVisible(true);
		PROJECT_LEAF.setLeafName(projName);
		// leftNavTree.setEditable(true);
		leftNavTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		leftNavTree.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		leftNavTree.setShowsRootHandles(true);
		leftNavTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int index = leftNavTree.getRowForLocation(e.getX(), e.getY());
				if(index != -1){
					TreePath path = leftNavTree.getPathForLocation(e.getX(), e.getY());
					leftNavTree.setSelectionPath(path);
					
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
					Leaf leaf = (Leaf) selectedNode.getUserObject();
					
					if (SwingUtilities.isRightMouseButton(e)) {
						if (leaf.getLeafType() != LeafType.PROJECT  
								&& selectedNode.getParent() == PROJECT_LEAF.getNode()) {
							
							switch (leaf.getLeafType()) {
								case STEPS: {
									STEPS_POPUP_MENU.show(leftNavTree, e.getX(), e.getY());
									break;
								}
								case STEP_GROUPS: {
									STEPGRP_POPUP_MENU.show(leftNavTree, e.getX(), e.getY());
									break;
								}
								case MAIN_FLOWS: {
									MF_POPUP_MENU.show(leftNavTree, e.getX(), e.getY());
									break;
								}
							}
						}
					}else{
						ScriptType scriptType = leaf.getScriptType();
						switch (scriptType) {
						case STEP:{
							System.out.println("step");
							break;							
						}case SUB_STEP:{
							System.out.println("sub step");
							break;							
						}case REC_STEP:{
							System.out.println("rec step");
							break;							
						}case STEP_GRP:{
							System.out.println("step group ");
							break;							
						}case MAIN_FLW:{
							System.out.println("main flow");
							break;							
						}
						default:
							break;
						}
					}//if-else
				}//if
			}
		});
		
		DefaultTreeModel root = (DefaultTreeModel )leftNavTree.getModel();
		root.reload();
		
	}
	
	public static boolean addLeaf(Leaf leaf){
		
		LeafType type = leaf.getLeafType();
		
		DefaultMutableTreeNode newNode = (DefaultMutableTreeNode)leaf.getNode();
		
		switch (type) {
		case STEPS:{
			
			((DefaultMutableTreeNode)STEPS_LEAF.getNode()).insert(newNode,((DefaultMutableTreeNode)STEPS_LEAF.getNode()).getChildCount());
			break;			
		}
		case STEP_GROUPS:{
			((DefaultMutableTreeNode)STEPGRP_LEAF.getNode()).add((DefaultMutableTreeNode)leaf.getNode());
			break;			
		}
		case MAIN_FLOWS:{
			((DefaultMutableTreeNode)MAINFLW_LEAF.getNode()).add((DefaultMutableTreeNode)leaf.getNode());
			break;			
		}
		default:
			return Boolean.FALSE;
		}
		
		DefaultTreeModel root = (DefaultTreeModel )leftNavTree.getModel();
		root.reload();
		
		TreePath path = new TreePath(newNode.getPath());		
		leftNavTree.setSelectionPath(path);
		leftNavTree.scrollPathToVisible(path);
		
		return Boolean.TRUE;
		
	}
	
}
