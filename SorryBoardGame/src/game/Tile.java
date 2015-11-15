package game;

import java.awt.Color;
import java.util.Vector;

/*
 * Tile
 * List of attributes each tile holds
 * Used by the game to update game logic
 * */
public class Tile {
	//Each tile is a color
	private final Color mColor;
	
//	private TilePanel uiPanel=new TilePanel();
	
	//Each tile can have a property, slide, start, or home
	private final boolean bSlide;
	private final boolean bStart;
	private final boolean bHome;
	
	//Each tile contains a reference to a tile behind it
	private Tile mPrevious;
	//The tiles also holds next tiles based on color
	private Tile mNext;
	private Tile mNextSameColor;
	
	
	public boolean showWhenClicked=false;
	
	//Pawn on the tile
	private Pawn mPawn = null;
	
	public Vector<Tile> results=null;
	
	//Should the gui update this?
	boolean hasChanged = false;
	
	private int xPos;
	private int yPos;

	public Tile(Color color, boolean slide, boolean start, boolean home,int x, int y) {
		mColor = color;
		bSlide = slide;
		bStart = start;
		bHome = home;
		xPos = x;
		yPos = y;
	}
	
	
	public boolean enableHover=false;
	public Color getColor() {return mColor;}
	public boolean hasColor(){return mColor!=null;}
	public void setNext(Tile inNext) { mNext = inNext; }
	public void setNextSameColor(Tile inNextSameColor) {mNextSameColor = inNextSameColor;}
	public Tile getNext(Color c) {
		if(mNextSameColor == null) System.out.println("FUCKSHIT");
		if (c.equals(mColor)) return mNextSameColor;
		else return mNext;
	}
	
	public void setPrevious(Tile inPrevious) {mPrevious = inPrevious;}
	public Tile getPrevious() {return mPrevious;}
	public int maxForward;
	public int maxBackward;
	public boolean doesSlide() { return bSlide; }
	public boolean isHome() { return bHome; }
	public boolean isStart() { return bStart; }
	
	public void setHover(boolean b){
		enableHover=b;
	}
	public boolean getHover(){
		return enableHover;
	}
	
	public void setPawn(Pawn inPawn) {
		mPawn = inPawn;
		mPawn.setCurrentTile(this);
		
	}
	
	public void removePawn() {
		mPawn = null;
	}
	
	public Color getPawnColor() {
		return mPawn.getColor();
	}

	public boolean isOccupied() {return mPawn != null;}

	public boolean isOccupiedByColor(Color color) {
		if(mPawn == null) return false;
		return mPawn.getColor().equals(color);
	}

	public boolean isColor(Color color) {
		return mColor.equals(color);
	}

	public Pawn getPawn() {
		return mPawn;
	}

	public boolean isSafeZone() {
		if(!mColor.equals(Color.BLACK) && !bSlide) return true;
		else return false;
	}

	@Override
	public String toString() { return "Tile: "+xPos+"  "+ yPos;}
	
}
