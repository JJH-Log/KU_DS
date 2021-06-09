package teamproject2;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

public class UserGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4286945394785661101L;
	private JPanel contentPane;
	private LogIn LogInComponent;
	private Channel ChannelComponent;
	private Room[] RoomComponent;
	private static int numOfChannel=4;
	
	private void setPanelBounds(JComponent Component,int a,int b,int c, int d) {
		Component.setBounds(a,b,c,d);
		Component.setLayout(null);
	}
	private void addComponent(JComponent Component) {
		this.add(Component);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI();
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
	public UserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);
		
		ChannelComponent = new Channel();
			setPanelBounds(ChannelComponent,0,0,1200,675);
		
		LogInComponent = new LogIn();
		setPanelBounds(LogInComponent,0,0,1200,675);
		
		RoomComponent = new Room[4];
		for(int i=0;i<numOfChannel;i++) {
			RoomComponent[i]=new Room();
			setPanelBounds(RoomComponent[i],0,0,1200,675);
		}
		
		LogInComponent.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "You have logged in successfully");
					addComponent(ChannelComponent);
					LogInComponent.setVisible(false);
					ChannelComponent.setVisible(true);
				}

			});
		for(int i=0;i<numOfChannel;i++) {
		ChannelComponent.ChannelPanel[i].getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You have Entranced in successfully");
				addComponent(RoomComponent[0]);
				ChannelComponent.setVisible(false);
				RoomComponent[0].setVisible(true);
			}

		});
		}
		this.addComponent(LogInComponent);

	}
}
