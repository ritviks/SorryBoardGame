package sorryclient;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import game.GameHelpers;
import game.GameManager;
import game.Tile;

/*
 * GamePanel
 * Panel to hold the graphical game
 * */
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1593344194657112518L;
	private final String cursorSrc="Images/images/cursors/cursorHand_grey.png";

	//A grid to hold all the tiles in the game
	private final static int boardSize = 16;
	private final TilePanel[][] tileGrid;
	
	public boolean mustRemoveHowers=false;
	//A map of color to respective tile Images
	private HashMap<Color,Vector<String> >colorMap;

	//The card button for the game
	private final CardButton cardButton;
	private final JLabel cardLabel;

	//The game manager that runs the actual logic
	private final GameManager mGameManager;

	//The way to exit the game menu
	private final ActionListener mQuitAction;

	//SORRY LOGO
	private BufferedImage imgLogo;
	{
		//Create and set-up the card button
		cardButton = new CardButton();
		cardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mGameManager.drawCard();
//				endGame(null)
				redraw();
			}
		});
		cardLabel = new JLabel("Cards:");
		imgLogo=Utils.makeImage("Images/images/sorry.png");
	}

	public GamePanel(ActionListener inQuitAction, GameManager inGameManager){
		mQuitAction = inQuitAction;
		mGameManager = inGameManager;

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(cursorSrc);
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(), 
				this.getY()), "greyCursor");
		this.setCursor (c);

		//Sets up colorMap.
		setUpColorMap();
		this.setOpaque(false);
		this.setBorder(null);
		//Create the GUI to be a grid for all the tiles
		setLayout(new GridLayout(boardSize,boardSize));
		tileGrid = new TilePanel[boardSize][boardSize];

		//Create each grid square
		//Either give it a Start/Home label panel, or a TilePanel
		for(int y = 0; y < boardSize; ++y) {
			for(int x = 0; x < boardSize; ++x) {
				if(x == 4 && y == 2) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.YELLOW),x,y);}
				else if (x == 2 && y == 7) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.YELLOW),x,y);}
				else if(x == 13 && y == 4) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.GREEN),x,y);}
				else if(x == 8 && y == 2) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.GREEN),x,y);}
				else if(x == 11 && y == 13) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.RED),x,y);}
				else if(x == 13 && y == 8) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.RED),x,y);}
				else if(x == 2 && y == 11) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.BLUE),x,y);}
				else if(x == 7 && y == 13) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.BLUE),x,y);}
				else {tileGrid[x][y] = new TilePanel(mGameManager.getTile(x,y),x,y);
				tileGrid[x][y].setOpaque(false);
				tileGrid[x][y].setBorder(null);}
				add(tileGrid[x][y]);
			}
		}

		//Set in the card
		TilePanel cardLabelTile = tileGrid[boardSize/2-1][boardSize/2-1];
		cardLabelTile.setLayout(new GridLayout(1,1));
		cardLabelTile.add(cardLabel);

		TilePanel cardButtonTile = tileGrid[boardSize/2][boardSize/2-1];
		cardButtonTile.setLayout(new GridLayout(1,1));
		cardButtonTile.add(cardButton);

		//This is used to make sure the GameManager can redraw the GUI
		inGameManager.setGamePanel(this);

		redraw();
	}

	//sets up colorMap
	private void setUpColorMap() {
		colorMap=new HashMap<Color,Vector<String>>();
		colorMap.put(Color.RED, new Vector<String>());
		Vector<String>temp=colorMap.get(Color.RED);
		temp.add("Images/images/panels/red_panel.png");
		temp.add("Images/images/tiles/red_tile.png");

		colorMap.put(Color.YELLOW,new Vector<String>());
		temp=colorMap.get(Color.YELLOW);
		temp.add("Images/images/panels/yellow_panel.png");
		temp.add("Images/images/tiles/yellow_tile.png");

		colorMap.put(Color.BLUE,new Vector<String>());
		temp=colorMap.get(Color.BLUE);
		temp.add("Images/images/panels/blue_panel.png");
		temp.add("Images/images/tiles/blue_tile.png");

		colorMap.put(Color.GREEN, new Vector<String>());
		temp=colorMap.get(Color.GREEN);
		temp.add("Images/images/panels/green_panel.png");
		temp.add("Images/images/tiles/green_tile.png");

		colorMap.put(Color.BLACK, new Vector<String>());
		temp=colorMap.get(Color.BLACK);
		temp.add("Images/images/tiles/grey_tile.png");
		temp.add("Images/images/tiles/grey_tile.png");

	}

	public void redraw() {
		for(TilePanel row[] : tileGrid) {
			for(TilePanel tp : row) {
				tp.update();
			}
		}
		
		revalidate();
		repaint();
	}

	//Each tile is a square in the grid, it can be null to hold a blank square
	class TilePanel extends JPanel {
		private static final long serialVersionUID = -9071191204545371340L;

		private final Tile mTile;
		private final Stack<Component> components;
		private BufferedImage img, regularImg; 
		private JLabel pawn = new JLabel();
		private boolean pawnDisplayed = false;
		private JLabel top;
		private int xCoordinate;
		private int yCoordinate;
		private ImageIcon sliderIcon;
		TilePanel(Tile tile, int x, int y) {
			mTile = tile;
			//Sets the Font
			Font font=Utils.makeFont(false, 10f);
			//Used to keep track what component should be displayed
			components = new Stack<Component>();


			if(mTile!=null){
				if(mTile.isHome()||mTile.isStart()){
					img=Utils.makeImage(colorMap.get(mTile.getColor()).get(0));
					regularImg=img;
				}
				else {
					if(mTile.hasColor()){

						img=Utils.makeImage(colorMap.get(mTile.getColor()).get(1));
						regularImg=img;
					}
				}
			}




			//If we are a meaningful tile in the game
			if(mTile != null) {
				top=new JLabel();
				//				setBorder(new LineBorder(mTile.getColor()));
				//Set any special looks based on the tiles properties

				//Makes Slider tiles
				if(mTile.doesSlide()) {
					BufferedImage bImg=GameHelpers.getSlideLabelFromColor(mTile.getColor());
					Dimension dim=this.getPreferredSize();
					sliderIcon=new ImageIcon(bImg.getScaledInstance((int)dim.getWidth(), (int)dim.getHeight(), Image.SCALE_SMOOTH));
					top=new JLabel(sliderIcon);
					top.setHorizontalAlignment(SwingConstants.CENTER);
					top.setVerticalAlignment(SwingConstants.CENTER);
				};

				//Makes start and home Tiles
				if(mTile.isStart()){ top=new JLabel("Start");}
				if(mTile.isHome()) top=new JLabel("Home");

				if(top!=null){
					top.setFont(font);
					components.push(top);
				}
				//If the tile is clicked by the user...
				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent me) {
						//Update this in the action manager
						repaint();
							if(mTile.enableHover)
						mGameManager.tileClicked(mTile,mGameManager.getMainPlayer());
					}
					public void mouseEntered(MouseEvent ae){
						if(mTile.getPawn()!=null)
							if(mTile.enableHover)
								img=Utils.makeImage(colorMap.get(mTile.getPawn().getColor()).get(0));
						repaint();
					}

					public void mouseExited(MouseEvent am){
						img=regularImg;
						repaint();
					}
				});
			}
		}
		//Update the tile based on its properties
		//				protected void update() {
		//					if(mTile == null) return;
		//					if(mTile.isOccupied() && !pawnDisplayed) {
		//						pawnDisplayed = true;
		//						components.push(pawn);
		//						if(mTile.getPawn().getIcon()!=null){
		//							if(mTile.doesSlide()) top.setIcon(null);
		//							Dimension dim=this.getPreferredSize();
		//							ImageIcon i=new ImageIcon(mTile.getPawn().getIcon().getScaledInstance((int)dim.getWidth(), (int)dim.getHeight(), Image.SCALE_SMOOTH));
		//							if(top!=null)top.setIcon(i);
		//						}
		//						this.repaint();
		//					}
		//					if(mTile.isOccupied() && pawnDisplayed) {
		//						pawn.setBackground(mTile.getPawnColor());
		//						pawn.setOpaque(false);
		//						
		//					}
		//					if(!mTile.isOccupied() && pawnDisplayed) {
		//						pawnDisplayed = false;
		//							components.pop();
		//		
		//						if(top!=null){
		//							top.setIcon(null);
		//							if(mTile.doesSlide())
		//								top.setIcon(sliderIcon);
		//						}
		//						this.repaint();
		//		
		//					}
		//					removeAll();
		//					if(!components.isEmpty()){
		//						if(top!=null)add(top);
		//					
		//					}
		//					repaint();
		//				}
		//UNCOMMENT ME IF NEEDED
		protected void update() {
			
			if(mTile == null) return;
			if(mTile.isOccupied() && !pawnDisplayed) {
				pawnDisplayed = true;
				components.push(pawn);
			}
			if(mTile.isOccupied() && pawnDisplayed) {
				Dimension dim=this.getPreferredSize();
				ImageIcon i=new ImageIcon();
				i.setImage(mTile.getPawn().getIcon());
				pawn.setIcon(i);
			}
			if(!mTile.isOccupied() && pawnDisplayed) {
				pawnDisplayed = false;
				components.pop();
			}
			removeAll();
			if(!components.isEmpty())add(components.peek());
			repaint();
		}

		protected void paintComponent(Graphics g){


			g.drawImage(img,0, 0,this.getWidth(),this.getHeight() , null);
			super.setOpaque(false);
			//super.paintComponent(g);
		}
	}

	//Used for the start counter display
	class StartLabelPanel extends TilePanel{
		private static final long serialVersionUID = -9166979703140637366L;
		private final int mPlayerNum;
		JLabel mLabel;
		{
			mLabel = new JLabel();
			add(mLabel);
		}
		StartLabelPanel(int numPlayer, int x, int y) {
			super(null, x ,y );
			mPlayerNum = numPlayer;
		}
		@Override
		protected void update() {
			mLabel.setText(mGameManager.getPlayerStartCount(mPlayerNum));
		}
	}

	//Used for the home counter display
	class HomeLabelPanel extends TilePanel{
		private static final long serialVersionUID = -9166979703540637366L;
		private final int mPlayerNum;
		JLabel mLabel;
		{
			mLabel = new JLabel();
			add(mLabel);
		}
		HomeLabelPanel(int numPlayer, int x, int y) {
			super(null,x,y);
			mPlayerNum = numPlayer;
		}
		@Override
		protected void update() {
			mLabel.setText(mGameManager.getPlayerHomeCount(mPlayerNum));
		}
	}

	public void endGame(String winnerName) {
		JOptionPane.showMessageDialog(
				null, 
				mGameManager.getWinner() + " player won!", 
				"Sorry!", 
				JOptionPane.NO_OPTION
				);
		//Quit out if over
		mGameManager.calculateScore();
		ScoreDialog sDialog=new ScoreDialog(mGameManager.getFinalScore());
		JButton exit = new JButton("");
		exit.addActionListener(mQuitAction);
		exit.doClick();
	}

	public void paintComponent(Graphics g){
		Dimension dim=this.getSize();
		double hieghtPerGrid=dim.getHeight()/boardSize;
		double widthPerGrid=dim.getWidth()/boardSize;

		this.setOpaque(false);
		super.paintComponent(g);
		g.drawImage(imgLogo.getScaledInstance((int)(6*widthPerGrid), (int)(3*hieghtPerGrid), Image.SCALE_SMOOTH),(int) (5*widthPerGrid),(int)(4*hieghtPerGrid), null);
	}

}
