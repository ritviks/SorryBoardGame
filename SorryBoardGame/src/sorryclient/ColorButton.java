package sorryclient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ColorButton extends GameButtons {

	private static final long serialVersionUID = 1;
	protected Color color;
	protected int index;
	protected BufferedImage imgregular;
	protected BufferedImage imgpressed;
	protected BufferedImage img;
	
	protected final static String[] colorsSrc = {
		"Images/images/buttons/red_button00.png",
		"Images/images/buttons/blue_button00.png", 
		"Images/images/buttons/green_button00.png", 
		"Images/images/buttons/yellow_button00.png",
		"Images/images/buttons/red_button01.png",
		"Images/images/buttons/blue_button01.png", 
		"Images/images/buttons/green_button01.png", 
	"Images/images/buttons/yellow_button01.png"};


	public ColorButton(String name, float fontSize,Color color,int index) {
		super(name, fontSize);
		this.color=color;
		this.index=index;
		this.img=Utils.makeImage(colorsSrc[index]);
this.imgregular=Utils.makeImage(colorsSrc[index]);
this.imgpressed=Utils.makeImage(colorsSrc[index+4]);
		this.setSize(img.getWidth(),img.getHeight());
		revalidate();
	}



	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(this.img,0, 0,this.getWidth(),this.getHeight() , null);
		int strWidth=g.getFontMetrics(font).stringWidth(name);
		int strHeight=g.getFontMetrics(font).getHeight();
		strHeight=strHeight/2;///GET THIS PART TO WORK
		g.drawString(name,(this.getWidth()-strWidth)/2,(this.getHeight())/2);

	}
	public void setImagePressed(){
		this.img=this.imgpressed;
		repaint();
	}
	public void setImageReleased(){
		this.img=this.imgregular;
		repaint();
	}

}
