package teamproject2;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9076369777156348457L;
	private JLabel IdLabel;
	private JLabel PasswordLabel;
	private JPasswordField PasswordField;
	private JTextField IdField;
	private JButton LogInBtn;
	
	public void setLabel(JLabel Component,int a,int b,int c,int d,String LabelText) {
		Component.setBounds(a,b,c,d);
		Component.setText(LabelText);
	}
	
	public void setTextField(JTextField Component,int a,int b,int c, int d,String text) {
		Component.setBounds(a,b,c,d);
		Component.setToolTipText(text);
	}
	
	public JButton getButton() {
		return LogInBtn;
	}
	
	public void setButton(JButton Component,int a,int b,int c, int d, String text) {
		Component.setBounds(a,b,c,d);
		Component.setText(text);
	}
	public LogIn() {		
		IdLabel = new JLabel();
		this.setLabel(IdLabel,406, 304, 57, 15,"ID : ");
		
		PasswordLabel = new JLabel();
		this.setLabel(PasswordLabel,406, 333, 57, 15,"PW : ");
		
		PasswordField = new JPasswordField();
		this.setTextField(PasswordField, 442, 326, 190, 34,"Insert your Password");
		
		IdField=new JTextField();
		this.setTextField(IdField, 442, 291, 190, 34,"Insert your Password");
		
		LogInBtn=new JButton();
		this.setButton(LogInBtn, 631, 290, 133, 71, " LogIn ");
		
		this.add(IdLabel);
		this.add(PasswordLabel);
		this.add(PasswordField);
		this.add(IdField);
		this.add(LogInBtn);
	}

}
