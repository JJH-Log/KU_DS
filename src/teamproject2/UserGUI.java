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

	private JPanel contentPane;
	private LogIn LogInComponent;
	private Channel ChannelComponent;
	private Room RoomComponent;
	private static final long serialVersionUID = 1L;
	
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
		
		RoomComponent = new Room();
		setPanelBounds(RoomComponent,0,0,1200,675);
		
		LogInComponent.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "You have logged in successfully");
					addComponent(ChannelComponent);
					LogInComponent.setVisible(false);
					ChannelComponent.setVisible(true);
				}

			});
		
		ChannelComponent.ChannelPanel1.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You have Entranced in successfully");
				addComponent(RoomComponent);
				ChannelComponent.setVisible(false);
				RoomComponent.setVisible(true);
			}

		});
		this.addComponent(LogInComponent);

	}
}
