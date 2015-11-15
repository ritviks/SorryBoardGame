package sorryclient;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class CardButton extends GameButtons{

	private static final long serialVersionUID = 1L;
	BufferedImage bgImage;
	public CardButton() {
		super("", 12);
		
		bgImage=Utils.makeImage("Images/images/cards/cardBack_red.png");
		revalidate();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.bgImage,0, 0,this.getWidth(),this.getHeight() , null);
		int strWidth=g.getFontMetrics(font).stringWidth(name);
		int strHeight=g.getFontMetrics(font).getHeight();
		strHeight=strHeight/2;///GET THIS PART TO WORK
		g.drawString(name,(this.getWidth()-strWidth)/2,(this.getHeight())/2);
	}
}
