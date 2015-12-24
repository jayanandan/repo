package com.lab.ui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.lab.model.FindMatchModel;
import com.lab.util.UIBuilder;
import com.lab.util.UIModelBinder;

import net.miginfocom.swing.MigLayout;

public class SkipPanel extends JPanel {

	public static final String NAME = "SkipPanel";  
	public static SkipPanel skipPanel = new SkipPanel();
	MigLayout layout = new MigLayout("","[][][][][][][][][]","[][][]");
	
	/**
	 * Create the panel.
	 */
	public SkipPanel() {
		setLayout(layout);
		System.out.println("constructor call...");
		initialize();
	}
	
	public void initialize(){
		
		
		add(UIBuilder.createSeperator(),"width 20");		
		add(UIBuilder.createLabel("Find & Match"),"split 2, span");
		add(UIBuilder.createSeperator(),"growx, wrap");
		
		add(UIBuilder.createLabel("Find"),"skip");
		add(UIBuilder.createTxtField("find.value"),", width 200");
		
		add(UIBuilder.createLabel("By"),"skip");
		add(UIBuilder.createComboBox("find.by",new String[]{"id","xpath","css","class","value"}),"");
		
		add(UIBuilder.createLabel("index"));
		add(UIBuilder.createTxtField("find.index"),"width 20,growx");
		
		add(UIBuilder.createSeperator(JSeparator.VERTICAL),"width 5,height 50,span 2 2,split 3");
		add(UIBuilder.createLabel("match"));
		add(UIBuilder.createTxtField("find.match"),"width 200,wrap,growx");
		
		add(UIBuilder.createLabel("Id"),"skip");
		add(UIBuilder.createTxtField("find.id"),"span 2,growx");
		
		add(UIBuilder.createChkBox("find.iframe","iframe"),"skip");
		add(UIBuilder.createChkBox("find.invisible","invisible"));
		
		add(UIBuilder.createChkBox("find.multiple","multiple"),"wrap");
		JButton saveBtn = UIBuilder.createButton("Save");
		add(saveBtn,"skip 1");
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("preparing model...");
					Object[] model = UIModelBinder.buildModel(new Class[]{FindMatchModel.class}, skipPanel);
					
					System.out.println(model[0]);
					
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 753, 480);
		
		frame.add(skipPanel);		
		frame.setVisible(true);
		
		/*Component[] components = skipPanel.getComponents();
		for (Component component : components) {
			if(StringUtils.isNotEmpty(component.getName()) 
					&& component.getName().equals("find.by")){
				((JComboBox<String>)component).setSelectedIndex(3);
			}
		}*/
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public String getName() {
		return NAME;
	}
}
