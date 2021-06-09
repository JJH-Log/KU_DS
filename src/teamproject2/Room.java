package teamproject2;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.*;
import javax.swing.JTextArea;

public class Room extends JPanel {

	private static final long serialVersionUID = -230685077180076986L;
	private JTextField textField;
	private List<String> Answer;
	private XmlParsing xmlParsing;
	private JButton btnNewButton;
	private JTextArea prevAnswer;
	private JTextPane FirstAnswer;
	private JTextPane SecondAnswer;
	private JTextPane ThirdAnswer;

	public Room() {
		this.setBounds(0,0,1200,675);
		Answer = new ArrayList<String>();
		xmlParsing = new XmlParsing();
		
		textField = new JTextField();
		textField.setBounds(146, 599, 790, 47);
		
		textField.setColumns(10);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(935, 599, 97, 47);
		
		prevAnswer = new JTextArea();
		prevAnswer.setEditable(false);
		prevAnswer.setBounds(489, 41, 180, 100);
		
		FirstAnswer = new JTextPane();
		FirstAnswer.setBounds(223, 210, 180, 100);
		FirstAnswer.setEditable(false);
		
		SecondAnswer = new JTextPane();
		SecondAnswer.setBounds(489, 210, 180, 100);
		SecondAnswer.setEditable(false);
		
		ThirdAnswer = new JTextPane();
		ThirdAnswer.setBounds(756, 210, 180, 100);
		ThirdAnswer.setEditable(false);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmp=textField.getText();
				String pretmp;
				if(tmp.length()==0) {
					JOptionPane.showMessageDialog(null, "빈 입력입니다.");
					textField.setText("");
					return ;
				}
				if(Answer.contains(tmp)){
					JOptionPane.showMessageDialog(null, "중복된 단어가 있음");
					textField.setText("");
					return ;
				}
				if (Answer.size() > 0) {
					pretmp = Answer.get(Answer.size() - 1);
					if (pretmp.charAt(pretmp.length() - 1) != tmp.charAt(0)) {
						JOptionPane.showMessageDialog(null, "이전 단어의 끝글자와 첫글자가 다름");
						textField.setText("");
						return ;
					}
				}
				if(!xmlParsing.search(tmp)){
					JOptionPane.showMessageDialog(null, "국어사전에 등록된 단어가 아님");
					textField.setText("");
					return ;
				}
				else {
					prevAnswer.setText(tmp);
					if(Answer.isEmpty()) {
						Answer.add(tmp);
						FirstAnswer.setText(tmp);
						textField.setText("");
					}
					else if(Answer.size()==1) {
						Answer.add(tmp);
						SecondAnswer.setText(tmp);
						textField.setText("");
					}
					else if(Answer.size()==2){
						Answer.add(tmp);
						ThirdAnswer.setText(tmp);
						textField.setText("");
					}
					else {
						Answer.add(tmp);
						FirstAnswer.setText(SecondAnswer.getText());
						SecondAnswer.setText(ThirdAnswer.getText());
						ThirdAnswer.setText(tmp);
						textField.setText("");
					}	
				}

		}
		});
		setLayout(null);
		this.add(FirstAnswer);
		this.add(SecondAnswer);
		this.add(ThirdAnswer);
		this.add(btnNewButton);
		this.add(textField);
		this.add(prevAnswer);
	}
}
