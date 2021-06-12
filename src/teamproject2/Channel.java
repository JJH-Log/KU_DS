package teamproject2;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Channel extends JPanel {

	private static final long serialVersionUID = 6361189082409594243L;
	public ChannelPanel[] ChannelPanel;
	private int[] capacity;
//	�ӽ÷� public
	
	private void setPanelBounds(JComponent Component,int a,int b,int c, int d) {
		Component.setBounds(a,b,c,d);
		Component.setLayout(null);
		this.add(Component);
	}
	
	public void updateCapacity(int c) {
		capacity[0] = c;
		ChannelPanel[0].setTextArea(12, 12, 213, 71, "capacity" + capacity[0] + "/5");
		return;
	}
	
	public Channel() {
		ChannelPanel=new ChannelPanel[4];
		capacity = new int[4];
		ChannelPanel[0]=new ChannelPanel();
		setPanelBounds(ChannelPanel[0],174, 73, 320, 113);
		ChannelPanel[0].setLabel(12, 88, 53, 15, "Channel1");
		ChannelPanel[0].setTextArea(12, 12, 213, 71, "capacity" + capacity[0] + "/5");
		ChannelPanel[0].setButton(223, 12, 97, 71, "Entrance");
		
		ChannelPanel[1]=new ChannelPanel();
		setPanelBounds(ChannelPanel[1],604, 73, 320, 113);
		ChannelPanel[1].setLabel(12, 88, 53, 15, "Channel2");
		ChannelPanel[1].setTextArea(12, 12, 213, 71, "capacity" + capacity[1] + "/5");
		ChannelPanel[1].setButton(223, 12, 97, 71, "Entrance");
		
		ChannelPanel[2]=new ChannelPanel();
		setPanelBounds(ChannelPanel[2],174, 228, 320, 113);
		ChannelPanel[2].setLabel(12, 88, 53, 15, "Channel3");
		ChannelPanel[2].setTextArea(12, 12, 213, 71, "capacity" + capacity[2] + "/5");
		ChannelPanel[2].setButton(223, 12, 97, 71, "Entrance");
		
		ChannelPanel[3]=new ChannelPanel();
		setPanelBounds(ChannelPanel[3],604, 228, 320, 113);
		ChannelPanel[3].setLabel(12, 88, 53, 15, "Channel4");
		ChannelPanel[3].setTextArea(12, 12, 213, 71, "capacity" + capacity[3] + "/5");
		ChannelPanel[3].setButton(223, 12, 97, 71, "Entrance");
		
		this.add(ChannelPanel[0]);
		this.add(ChannelPanel[1]);
		this.add(ChannelPanel[2]);
		this.add(ChannelPanel[3]);
	}

}