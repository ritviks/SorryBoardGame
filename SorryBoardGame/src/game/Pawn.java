package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/*Pawn
 * Data for a pawn that belongs to a player
 * */
public class Pawn {

	//Each pawn has a color
	private final Color mColor;
//	private final BufferedImage pImage;
	
	//The current tile
	private Tile mTile;
	private boolean isGhost=false;
	private boolean isLeavingStart=false;
	private boolean isJumpingOpponent=false;
	private boolean isMovingForward=false;
	private boolean isMovingBackward=false;
	
	//The tile to return to start
	private Tile mStartTile;
	
	private int distFromStart=0;
	
	private boolean canMoveFromStart=false;
	
	public Pawn(Color inColor) {
		mColor = inColor;
		
	}

	public Image getIcon() {return GameHelpers.getPawnLabel(mColor);}
	public Color getColor() { return mColor;}

	public void setCurrentTile(Tile inTile) {
		mTile = inTile;
	}
	
	public Tile getCurrentTile() {
		return mTile;
	}

	public void setGhost(boolean b){
		isGhost=b;
	}
	public boolean getGhost(){
		return isGhost;
	}
	
	public void setCanJump(boolean b){
		isJumpingOpponent=b;
	}
	public boolean getCanJump(){
		return isJumpingOpponent;
	}
	
	public void setCanMoveForward(boolean b){
		isMovingForward=b;
	}
	public boolean getCanMoveForward(){
		return isMovingForward;
	}
	
	public void setCanMoveBackward(boolean b){
		isMovingBackward=b;
	}
	public boolean getCanMoveBackward(){
		return isMovingBackward;
	}
	
	public void setStartTile(Tile inTile) {
		mStartTile = inTile;
		mTile = inTile;
	}
	public Tile getStartTile(){
		return mStartTile;
	}
	
	public void returnToStart() {
		mTile.removePawn();
		mTile = mStartTile;
	}

	public void setDistance(int i){;
		this.distFromStart=i;
	}
	public  int getDistance(){;
	 return this.distFromStart;
	}
	
	public  void setCanMoveFromStart(boolean b){;
	  this.canMoveFromStart=b;
	}	
	public  boolean getCanMoveFromStart(){;
	 return  this.canMoveFromStart;
	}
}
