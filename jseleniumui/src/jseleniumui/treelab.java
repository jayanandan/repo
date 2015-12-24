package jseleniumui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class treelab extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					treelab frame = new treelab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public treelab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Project");
		DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode("Steps");
		DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode("Step Groups");
		DefaultMutableTreeNode childNode3 = new DefaultMutableTreeNode("Main Flows");
		
		rootNode.add(childNode1);
		rootNode.add(childNode2);
		rootNode.add(childNode3);
		
		final JTree jTree = new JTree(rootNode);
		jTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				int row = jTree.getRowForLocation(e.getX(), e.getY());
				
				TreePath pathForLocation = jTree.getPathForLocation(e.getX(), e.getY());
				
				System.out.println(pathForLocation.getParentPath());
				
				if(SwingUtilities.isRightMouseButton(e) && row!=-1){
					JPopupMenu jPopupMenu = new JPopupMenu();
					jPopupMenu.add(new JMenuItem("New"));
					jPopupMenu.show(jTree, e.getX(), e.getY());
					
				}
				
			}
		});
		jTree.setShowsRootHandles(true);
		getContentPane().add(new JScrollPane(jTree));
		
	}

}
