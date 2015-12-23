package com.lab.model;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.lab.util.ApplicationUtil;
import com.lab.util.ApplicationUtil.LeafType;
import com.lab.util.ApplicationUtil.ScriptType;

public class Leaf{

	public static final Leaf PROJECT_LEAF = new Leaf(LeafType.PROJECT);
	public static final Leaf STEPS_LEAF = new Leaf(LeafType.STEPS);
	public static final Leaf STEPGRP_LEAF = new Leaf(LeafType.STEP_GROUPS);
	public static final Leaf MAINFLW_LEAF = new Leaf(LeafType.MAIN_FLOWS);
	
	private LeafType leafType;
	private ScriptType scriptType=ScriptType.TOP;
	private String leafName;
	private TreeNode node;
	
	
	public Leaf(LeafType leafType,String leafName,boolean allowChildren){
		this.node = new DefaultMutableTreeNode(this,allowChildren);
		this.leafType = leafType;
		this.leafName = leafName;
	}
	
	public Leaf(LeafType leafType,String leafName){
		this(leafType,leafName,true);
	}
	
	private Leaf(LeafType leafType){
		this(leafType,leafType.getValue());
	}
	
	public String getLeafName() {
		return leafName;
	}

	public void setLeafName(String leafName) {
		this.leafName = leafName;
	}

	public LeafType getLeafType() {
		return leafType;
	}

	public void setLeafType(LeafType leafType) {
		this.leafType = leafType;
	}

	public TreeNode getNode() {
		return node;
	}

	public ScriptType getScriptType() {
		return scriptType;
	}

	public void setScriptType(ScriptType scriptType) {
		this.scriptType = scriptType;
	}
	public ImageIcon getIcon(){
		switch(scriptType){
			case TOP:{
				return ApplicationUtil.CLOSE_ICON;
			}case STEP:{
				return ApplicationUtil.STEP_ICON;
			}case SUB_STEP:{
				return ApplicationUtil.SUBSTEP_ICON;
			}case REC_STEP:{
				return ApplicationUtil.RECSTEP_ICON;
			}default:{
				return ApplicationUtil.LEAF_ICON;
			}
		}
	}

	@Override
	public String toString() {
		return this.leafName;
	}	
}
