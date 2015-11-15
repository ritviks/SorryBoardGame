package sorryclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpPanel extends JDialog{
	private HelpInfo helpInfo;

	private final String instructions="Sorry! \n"
			+ "Starting Game \n"
			+ "    -Press Star\n"
			+ "    -Select Number Of Player\n"
			+ "    -Select Your Color\n"
			+ "    -Play\n"
			+ "Playing The Game\n"
			+ "    -Draw Card\n"
			+ "    -Select Valid Pawn\n"
			+ "    -Select Additional Info if Needed\n"
			+ "    -Wait For Bots To Finish";
	public HelpPanel(){
		super();
		this.setTitle("SORRY Help");
		this.setModal(true);	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		helpInfo=new HelpInfo(instructions);
		pack();
		setSize(helpInfo.preferredSize());
		this.setResizable(false);

		add(helpInfo);

	}
	class HelpInfo extends JPanel{
		private String info;
		private JTextArea tArea;
		private JScrollPane sPane;
		public HelpInfo(String message){
			super();
			this.info=message;
			this.setLayout(new BorderLayout());
			tArea = new JTextArea(message, 10, 15);
			tArea.setLineWrap(true);
			tArea.setWrapStyleWord(true);
			tArea.setFont(Utils.makeFont(true,12f));
		
			sPane=new JScrollPane(tArea);
			tArea.setOpaque(false);
			sPane.setOpaque(false);
			sPane.getViewport().setOpaque(false);
			this.setSize(tArea.getSize());
			this.setOpaque(false);
			add(sPane,BorderLayout.CENTER);
			pack();
		}
		
		public void paintComponent(Graphics g){

			super.setOpaque(false);
			super.paintComponent(g);
			g.drawImage(Utils.makeImage("Images/images/cards/card_beigeLight.png"), 0, 0,this.getWidth(),this.getHeight(),null);
			
		}

		public Dimension getTextAreaSize(){
			return tArea.getSize();

		}


	}
}
