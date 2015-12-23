package jseleniumui;

import javax.el.ELProcessor;

public class ELProcessorLab {

	
	public static void main(String[] args) {
		
		ELProcessor processor = new ELProcessor();
		
		Employee employee = new Employee();
		employee.setId("jayanandan");
		
		processor.defineBean("employee", employee);
		
		System.out.println(processor.getValue("employee.id",String.class));
		
	}
	
}
