package teamproject2;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChannelPanel extends JPanel {

	private static final long serialVersionUID = -1175413487614398007L;
	private JLabel ChannelLabel;
	private JTextArea ChannelInfo;
	private JButton EntranceBtn;
	
	public void setLabel(int a,int b,int c,int d,String LabelText) {
		ChannelLabel.setBounds(a,b,c,d);
		ChannelLabel.setText(LabelText);
	}

	public JButton getButton() {
		return EntranceBtn;
	}
	
	public void setTextArea(int a,int b, int c, int d,String text) {
		ChannelInfo.setEditable(false);
		ChannelInfo.setWrapStyleWord(true);
		ChannelInfo.setText(text);
		ChannelInfo.setBackground(Color.LIGHT_GRAY);
		ChannelInfo.setBounds(a,b,c,d);
	}
	
	public void setButton(int a,int b,int c, int d,String text) {
		EntranceBtn.setBounds(a, b, c, d);
		EntranceBtn.setText(text);
	}
	
	
	

	public ChannelPanel() {
		ChannelLabel = new JLabel();
		this.add(ChannelLabel);
		
		ChannelInfo = new JTextArea();
		this.add(ChannelInfo);
		
		EntranceBtn = new JButton();
		this.add(EntranceBtn);
	}
}