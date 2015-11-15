package sorryclient;

import game.GameHelpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPawn extends JPanel{
	private BufferedImage img;
	Dimension dim;
	private JLabel icon;
public JPawn(){
	super();
	icon=new JLabel();
//	icon.setOpaque(false);
	setLayout(new GridLayout(1,1));
	setOpaque(false);
	//add(icon);
}
void setIcon(BufferedImage b, Dimension dim){
	img=b;
	this.dim=dim;
	this.setOpaque(false);
}

public void setBackGround(Color c){
	GameHelpers.getPawnLabel(c);
	icon.setIcon(new ImageIcon(GameHelpers.getPawnLabel(c)));
}

}
