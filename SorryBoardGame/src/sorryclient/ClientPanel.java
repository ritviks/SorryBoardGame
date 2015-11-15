package sorryclient;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import game.GameManager;

/*
 * ClientPanel
 * Holds the graphical menus for the Sorry! game
 * */
public class ClientPanel extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	//Elements of the client panel
	
	//Cursor source file
	private final String cursorSrc="Images/images/cursors/cursorHand_grey.png";
	
	
	

	
	//The panel will switch between these
	private MainMenu mainMenu;
	private NumPlayerSelector numPlayerSelect;
	private ColorSelector colorSelect;
	

	//Takes in input from the collected info from the other panels
	private GameManager gameManager;
	private GamePanel gamePanel;
	//default constructor
	{

		
		//Changes the cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(cursorSrc);
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(), 
		           this.getY()), "greyCursor");
		this.setCursor (c);
		
		
		//The start menu
		mainMenu = new MainMenu(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//Swap to the number of players menu
				ClientPanel.this.removeAll();
				ClientPanel.this.add(numPlayerSelect);
				ClientPanel.this.revalidate();
			}
		});

		//Set up the panel to display
		setLayout(new BorderLayout());
		add(mainMenu);
		refreshComponents();
	}

	private void refreshComponents() {
		numPlayerSelect = new NumPlayerSelector(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Swap to the color select menu
				ClientPanel.this.removeAll();
				ClientPanel.this.add(colorSelect);
				ClientPanel.this.revalidate();
			}
		});
		colorSelect = new ColorSelector(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientPanel.this.removeAll();
				//set up the game manager
				gameManager.setUp(
					colorSelect.getPlayerColor(), 
					numPlayerSelect.getNumberOfPlayers()
				);
				//set the panel to the game
				ClientPanel.this.add(gamePanel);
				ClientPanel.this.revalidate();
			}
		});
		gameManager = new GameManager();
		gamePanel = new GamePanel(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//go back to the main menu
				ClientPanel.this.removeAll();
				ClientPanel.this.add(mainMenu);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
				refreshComponents();
			}
		}, gameManager);
	}
	
	
}
