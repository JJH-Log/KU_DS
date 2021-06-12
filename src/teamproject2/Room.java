package teamproject2;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;

import teamproject.TimerGraphic;
import teamproject2.CMWinServer.MyActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	public void checkWord(String tmp) {
		String pretmp;
		TimerGraphic g = new TimerGraphic();
		
		class action implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (g.i <= 180) {
					g.repaint();
				}
			}
		}
		
		Timer t = new Timer(150, new action());
		
		if(tmp.length()==0) {
			JOptionPane.showMessageDialog(null, "빈 입력입니다.");
			return ;
		}
		if(Answer.contains(tmp)){
			JOptionPane.showMessageDialog(null, "중복된 단어가 있음");
			return ;
		}
		if (Answer.size() > 0) {
			pretmp = Answer.get(Answer.size() - 1);
			if (pretmp.charAt(pretmp.length() - 1) != tmp.charAt(0)) {
				JOptionPane.showMessageDialog(null, "이전 단어의 끝글자와 첫글자가 다름");
				return ;
			}
		}
		if(!xmlParsing.search(tmp)){
			JOptionPane.showMessageDialog(null, "국어사전에 등록된 단어가 아님");
			return ;
		}
		else {
			prevAnswer.setText(tmp);
			if(Answer.isEmpty()) {
				Answer.add(tmp);
				FirstAnswer.setText(tmp);
			}
			else if(Answer.size()==1) {
				Answer.add(tmp);
				SecondAnswer.setText(tmp);
			}
			else if(Answer.size()==2){
				Answer.add(tmp);
				ThirdAnswer.setText(tmp);
			}
			else {
				Answer.add(tmp);
				FirstAnswer.setText(SecondAnswer.getText());
				SecondAnswer.setText(ThirdAnswer.getText());
				ThirdAnswer.setText(tmp);
			}
			g.i = 0;
			t.start();
		}
		return;
	}
	
	public Room() {
		this.setBounds(0,0,1200,675);
		Answer = new ArrayList<String>();
		xmlParsing = new XmlParsing();
		
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
		
		TimerGraphic g = new TimerGraphic();
		g.setBounds(0, 0, 1200, 675);
		
		setLayout(null);
		this.add(FirstAnswer);
		this.add(SecondAnswer);
		this.add(ThirdAnswer);
		this.add(btnNewButton);
		this.add(prevAnswer);
	}
}

class TimerGraphic extends JLabel {
	int i;
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(489, 141, 180, 10);
		g.setColor(Color.WHITE);
		g.fillRect(180 - i, 141, i, 10);
		i++;
		if (i > 180) {
			i = 181;
			JOptionPane.showMessageDialog(null, "제한 시간 초과");
		}
	}
}