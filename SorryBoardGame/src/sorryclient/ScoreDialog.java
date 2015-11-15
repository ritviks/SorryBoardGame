package sorryclient;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sorryclient.HelpPanel.HelpInfo;

public class ScoreDialog extends JDialog{
	 private int finalScore;
	 private final String cursorSrc="Images/images/cursors/cursorHand_grey.png";
		
	
public ScoreDialog(int score){
	finalScore=score;
	setModal(true);
	ScorePanel sPanel=new ScorePanel(score);
	add(sPanel);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	pack();
	setSize(sPanel.preferredSize());
	setVisible(true);
	
}
public void hideThis(){
	this.hide();
}


class ScorePanel extends JPanel {
	String message;
	public ScorePanel(int score){
		
		message = "Congratulations! Your Score is "+score+".\n"
				+ "Please Enter Your Name For The ScoreBoard";
		setLayout (new GridLayout(2,1));
		add(new JLabel(message));
		JTextField tf=new JTextField("",20);
		JButton bt=new JButton("Submit");
		bt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(!tf.getText().isEmpty())	{
					ScoreBoardPanel.addName(tf.getText(),score);
					hideThis();
				}
			}
		});
		JPanel jp2=new JPanel();
		jp2.setLayout(new GridLayout(1,2));
		jp2.add(tf);
		jp2.add(bt);
		add(jp2);
		
		
	}
}
}
