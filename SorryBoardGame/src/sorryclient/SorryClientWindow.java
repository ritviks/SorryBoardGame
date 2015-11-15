package sorryclient;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/*
 * The main window for Sorry!
 * */
public class SorryClientWindow extends JFrame{
	private static final long serialVersionUID = 5147395078473323173L;

	//Dimensions for the game
	private final static Dimension minSize = new Dimension(640,480);
	private final static Dimension maxSize = new Dimension(960,640);

	public static Window game;
	private JMenuBar jmb;
	private JMenuItem helpMenu;
	private JMenuItem scoreBoard;
	private HelpPanel helpPanel;
	private ScoreBoardPanel scoreBoardPanel;
	
	{ //Construct
		setTitle("Sorry!");
		setSize(minSize);
		setMinimumSize(minSize);
		setMaximumSize(maxSize);
		add(new ClientPanel());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeMenuBar();

		setVisible(true);

	}
	public static void main(String[] args) {
		//Create a new SorryClient Window
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				game=new SorryClientWindow();
			}
		});
	}

	private void makeMenuBar() {
		jmb = new JMenuBar();
		jmb.setLayout(new GridLayout(1,2));
		helpMenu = new JMenuItem("Help");
		scoreBoard = new JMenuItem("Top Scores");
		
		//the Help Panel
		helpPanel=new HelpPanel();
		helpMenu.setMnemonic('H');
		helpMenu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				helpPanel.setVisible(true);					
			}
		});
		helpMenu.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_H,
						ActionEvent.CTRL_MASK));
		helpMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				helpPanel.setVisible(true);			
			}
		});
		
		//the Score board
		scoreBoardPanel=new ScoreBoardPanel();
		scoreBoard.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				scoreBoardPanel.setVisible(true);					
			}
		});
		scoreBoard.setMnemonic('S');
		scoreBoard.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S,
						ActionEvent.CTRL_MASK));
		scoreBoard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				scoreBoardPanel.setVisible(true);			
			}
		});	

		jmb.add(helpMenu);
		jmb.add(scoreBoard);
		setJMenuBar(jmb);
	}


}
