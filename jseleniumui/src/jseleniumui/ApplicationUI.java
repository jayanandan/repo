package jseleniumui;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.lab.actions.MenuAction;
import com.lab.ui.panels.LeftNavPanel;

import static com.lab.util.ApplicationUtil.LeafAction;;

public class ApplicationUI {

	private JFrame frame;
	
	private static final JMenuBar menuBar = new JMenuBar();
	private static final JMenu mnFile = new JMenu("File");
	
	public static final LeftNavPanel leftNavPanel = new LeftNavPanel();

	static{
		MenuAction mntmNewProject = new MenuAction(LeafAction.NEW_PROJECT);
		mnFile.add(mntmNewProject);
		menuBar.add(mnFile);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationUI window = new ApplicationUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 753, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane);
		
		splitPane.setLeftComponent(leftNavPanel);
//		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		
	}
	
	public static Component getComponentById(Container container, String componentId){

        if(container.getComponents().length > 0)
        {
            for(Component c : container.getComponents())
            {
                if(componentId.equals(c.getName()))
                {
                    return c;
                }
                if(c instanceof Container)
                {
                    return getComponentById((Container) c, componentId);
                }
            }
        }

        return null;

    }

}
