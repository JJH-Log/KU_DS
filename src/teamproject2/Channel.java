package teamproject2;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Channel extends JPanel {

	private static final long serialVersionUID = 6361189082409594243L;
	public ChannelPanel ChannelPanel1;
	public ChannelPanel ChannelPanel2;
	public ChannelPanel ChannelPanel3;
	public ChannelPanel ChannelPanel4;
//	юс╫ц╥н public
	
	private void setPanelBounds(JComponent Component,int a,int b,int c, int d) {
		Component.setBounds(a,b,c,d);
		Component.setLayout(null);
		this.add(Component);
	}
	
	public Channel() {
		ChannelPanel1=new ChannelPanel();
		setPanelBounds(ChannelPanel1,174, 173, 320, 113);
		ChannelPanel1.setLabel(12, 88, 53, 15, "Channel1");
		ChannelPanel1.setTextArea(12, 12, 213, 71, "capacity 0/5");
		ChannelPanel1.setButton(223, 12, 97, 71, "Entrance");
		
		ChannelPanel2=new ChannelPanel();
		setPanelBounds(ChannelPanel2,604, 173, 320, 113);
		ChannelPanel2.setLabel(12, 88, 53, 15, "Channel2");
		ChannelPanel2.setTextArea(12, 12, 213, 71, "capacity 0/5");
		ChannelPanel2.setButton(223, 12, 97, 71, "Entrance");
		
		ChannelPanel3=new ChannelPanel();
		setPanelBounds(ChannelPanel3,174, 328, 320, 113);
		ChannelPanel3.setLabel(12, 88, 53, 15, "Channel3");
		ChannelPanel3.setTextArea(12, 12, 213, 71, "capacity 0/5");
		ChannelPanel3.setButton(223, 12, 97, 71, "Entrance");
		
		ChannelPanel4=new ChannelPanel();
		setPanelBounds(ChannelPanel4,604, 328, 320, 113);
		ChannelPanel4.setLabel(12, 88, 53, 15, "Channel4");
		ChannelPanel4.setTextArea(12, 12, 213, 71, "capacity 0/5");
		ChannelPanel4.setButton(223, 12, 97, 71, "Entrance");
		
		this.add(ChannelPanel1);
		this.add(ChannelPanel2);
		this.add(ChannelPanel3);
		this.add(ChannelPanel4);
	}

}
