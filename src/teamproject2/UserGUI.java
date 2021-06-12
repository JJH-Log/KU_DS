package teamproject2;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import kr.ac.konkuk.ccslab.cm.entity.CMGroup;
import kr.ac.konkuk.ccslab.cm.entity.CMGroupInfo;
import kr.ac.konkuk.ccslab.cm.entity.CMSession;
import kr.ac.konkuk.ccslab.cm.entity.CMUser;
import kr.ac.konkuk.ccslab.cm.event.CMInterestEvent;
import kr.ac.konkuk.ccslab.cm.info.CMConfigurationInfo;
import kr.ac.konkuk.ccslab.cm.info.CMInfo;
import kr.ac.konkuk.ccslab.cm.info.CMInteractionInfo;
import kr.ac.konkuk.ccslab.cm.sns.CMSNSUserAccessSimulator;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;
import teamproject2.CMWinServer.MyKeyListener;

public class UserGUI extends JFrame {

	/**
	 * 
	 */
	private String strUserName;
	private String strPassword;
	
	private static final long serialVersionUID = 4286945394785661101L;
	private JPanel contentPane;
	private LogIn LogInComponent;
	private Channel ChannelComponent;
	private Room[] RoomComponent;
	private static int numOfChannel=4;
	
	private CMClientStub m_clientStub;
	private ClientEventHandler m_eventHandler;
	
	private JScrollPane m_outScrollPane;
	private JTextField m_inTextField;
	private JTextPane m_outTextPane;
	
	private int m_gameState;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI();
					CMClientStub cmStub = frame.getClientStub();
					cmStub.setAppEventHandler(frame.getClientEventHandler());
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CMClientStub getClientStub()
	{
		return m_clientStub;
	}
	public ClientEventHandler getClientEventHandler()
	{
		return m_eventHandler;
	}
	
	
	/**
	 * Create the frame.
	 */
	
	public UserGUI() {
		
		MyKeyListener cmKeyListener = new MyKeyListener();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 675);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);
		
		ChannelComponent = new Channel();
			setPanelBounds(ChannelComponent,0,0,1200,470);
		
		LogInComponent = new LogIn();
		setPanelBounds(LogInComponent,0,0,1200,470);
		
		RoomComponent = new Room[4];
		for(int i=0;i<numOfChannel;i++) {
			RoomComponent[i]=new Room();
			setPanelBounds(RoomComponent[i],0,0,1200,470);
		}
		
		LogInComponent.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(testLoginDS() == 1) {
						JOptionPane.showMessageDialog(null, "You have Entranced in successfully");
						testSessionInfoDS();
						addComponent(ChannelComponent);
						LogInComponent.setVisible(false);
						ChannelComponent.setVisible(true);						
					}
				}
			});
		
		for(int i=0;i<numOfChannel;i++) {
		ChannelComponent.ChannelPanel[i].getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You have Entranced in successfully");
				m_gameState = 1;
				addComponent(RoomComponent[0]);
				ChannelComponent.setVisible(false);
				RoomComponent[0].setVisible(true);
			}

		});
		}
		this.addComponent(LogInComponent);
		
		m_inTextField = new JTextField();
		m_inTextField.addKeyListener(cmKeyListener);
		m_inTextField.setBounds(0, 610, 1200, 25);
		add(m_inTextField);
		
		m_clientStub = new CMClientStub();
		m_eventHandler = new ClientEventHandler(m_clientStub, this);
		
		m_outTextPane = new JTextPane();
		m_outTextPane.setBackground(new Color(245,245,245));
		m_outTextPane.setEditable(false);

		StyledDocument doc = m_outTextPane.getStyledDocument();
		addStylesToDocument(doc);

		m_outScrollPane = new JScrollPane(m_outTextPane, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		m_outScrollPane.setBounds(0, 470, 1185, 140);
		add(m_outScrollPane);
		m_outScrollPane.setVisible(true);
		
		m_gameState = 0;
		
		// start CM
		testStartCM();
		
		m_inTextField.requestFocus();
	}
	
	public int getClientState() {
		return  m_clientStub.getCMInfo().getInteractionInfo().getMyself().getState();
	}

	public void testStartCM()
	{
		boolean bRet = false;
		
		// get current server info from the server configuration file
		String strCurServerAddress = null;
		int nCurServerPort = -1;
		
		strCurServerAddress = m_clientStub.getServerAddress();
		nCurServerPort = m_clientStub.getServerPort();
		
		bRet = m_clientStub.startCM();
		if(!bRet)
		{
			printStyledMessage("CM initialization error!\n", "bold");
			LogInComponent.getButton().setEnabled(false);
		}
		else
		{
			//m_loginLogoutButton.setEnabled(true);
			printStyledMessage("Client CM starts.\n", "bold");
			// change the appearance of buttons in the client window frame
			//setButtonsAccordingToClientState();
		}
	}
	
	private void setPanelBounds(JComponent Component,int a,int b,int c, int d) {
		Component.setBounds(a,b,c,d);
		Component.setLayout(null);
	}
	private void addComponent(JComponent Component) {
		this.add(Component);
	}
	
	public int getGameState() {
		return m_gameState;
	}
	
	public void checkWord(String s) {
		RoomComponent[0].checkWord(s);
	}
	
	public class MyKeyListener implements KeyListener {
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_ENTER)
			{
				JTextField input = (JTextField)e.getSource();
				String strText = input.getText();
				
				// parse and call CM API
				//processInput(strText);
				if(getClientState() == 4) {
					m_clientStub.chat("/g", strText);
				}

				input.setText("");
				input.requestFocus();
			}
		}
		
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
	
	
	public void printMessage(String strText)
	{
		/*
		m_outTextArea.append(strText);
		m_outTextArea.setCaretPosition(m_outTextArea.getDocument().getLength());
		*/
		StyledDocument doc = m_outTextPane.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), strText, null);
			m_outTextPane.setCaretPosition(m_outTextPane.getDocument().getLength());

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	public void updateSessionInfo(int tClient) {
		ChannelComponent.updateCapacity(tClient);
	}
	
	public void testSessionInfoDS()
	{
		boolean bRequestResult = false;

		m_eventHandler.setStartTime(System.currentTimeMillis());
		bRequestResult = m_clientStub.requestSessionInfo();
		long lDelay = System.currentTimeMillis() - m_eventHandler.getStartTime();
		if(!bRequestResult)
		{
			printStyledMessage("failed the session-info request!\n", "bold");
		}
	}
	
	public void printStyledMessage(String strText, String strStyleName)
	{
		StyledDocument doc = m_outTextPane.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), strText, doc.getStyle(strStyleName));
			m_outTextPane.setCaretPosition(m_outTextPane.getDocument().getLength());

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	
	private void addStylesToDocument(StyledDocument doc)
	{
		Style defStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		Style regularStyle = doc.addStyle("regular", defStyle);
		StyleConstants.setFontFamily(regularStyle, "SansSerif");
		
		Style boldStyle = doc.addStyle("bold", defStyle);
		StyleConstants.setBold(boldStyle, true);
		
		Style linkStyle = doc.addStyle("link", defStyle);
		StyleConstants.setForeground(linkStyle, Color.BLUE);
		StyleConstants.setUnderline(linkStyle, true);
	}
	
	public String getUserName() {
		return strUserName;
	}
	
	public int testLoginDS()
	{
		boolean bRequestResult = false;

		printMessage("====== login to default server ======\n");
		strUserName = LogInComponent.getUserName();
		strPassword = LogInComponent.getPassword(); // security problem?
		
		m_eventHandler.setStartTime(System.currentTimeMillis());
		bRequestResult = m_clientStub.loginCM(strUserName, strPassword);
		long lDelay = System.currentTimeMillis() - m_eventHandler.getStartTime();
		if(bRequestResult)
		{
			return 1;
		}
		else
		{
			printStyledMessage("failed the login request!\n", "bold");
			m_eventHandler.setStartTime(0);
			return 0;
		}
	}
	
	
}