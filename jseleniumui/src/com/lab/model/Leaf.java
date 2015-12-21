package com.lab.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.lab.util.ApplicationUtil.LeafType;

public class Leaf{

	public static final Leaf PROJECT_LEAF = new Leaf(LeafType.PROJECT);
	public static final Leaf STEPS_LEAF = new Leaf(LeafType.STEPS);
	public static final Leaf STEPGRP_LEAF = new Leaf(LeafType.STEP_GROUPS);
	public static final Leaf MAINFLW_LEAF = new Leaf(LeafType.MAIN_FLOWS);
	
	private LeafType leafType;
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

	@Override
	public String toString() {
		return this.leafName;
	}	
}
