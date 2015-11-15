package sorryclient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class NextButton extends GameButtons{
	private static final long serialVersionUID = 1;

	private final String buttonImageSource="Images/images/buttons/grey_button00.png";

	BufferedImage img=Utils.makeImage(buttonImageSource);
	public NextButton(String name,float size){
		super(name,size+2);
		this.font=Utils.makeFont(true,this.fontSize);
		this.setBorder(new LineBorder(Color.GRAY, 1));
		Dimension dim=new Dimension(150,40);
		setPreferredSize(dim);
		setIcon(new ImageIcon(img.getScaledInstance(dim.width,dim.height, Image.SCALE_SMOOTH)));
		
	}
	public void paintComponent(Graphics g){
		
		
		g.drawImage(img,0, 0,img.getWidth(),img.getHeight() , null);
		g.setFont(this.font);
		int strWidth=g.getFontMetrics(font).stringWidth(name);
		int strHeight=g.getFontMetrics(font).getHeight();
		strHeight=strHeight/2;///GET THIS PART TO WORK
		g.drawString(name,(this.getWidth()-strWidth)/2,(this.getHeight())/2);

	}
	
}
