package com.lab.util;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.lab.model.Leaf;

public class CTreeCellRenderer  extends DefaultTreeCellRenderer {
	
	 private Border border = BorderFactory.createEmptyBorder ( 3,3,3,3 );

	    @Override
	    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

	    	DefaultMutableTreeNode node= (DefaultMutableTreeNode) value;
	    	Leaf leafObj = (Leaf)node.getUserObject();

	    	setClosedIcon(ApplicationUtil.CLOSE_ICON);
	    	setOpenIcon(ApplicationUtil.OPEN_ICON);
	    	
            setLeafIcon(leafObj.getIcon());
            
	        JLabel label = (JLabel)super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
	        label.setBorder(border);

	        return this;
	    }
	}