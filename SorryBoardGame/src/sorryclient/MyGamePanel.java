package sorryclient;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MyGamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final  String  defaultBgSrc="Images/images/panels/grey_panel.png";
	String bgImageSrc;
	BufferedImage bgImage;
	
	public MyGamePanel()
	{
		super();
		this.bgImageSrc=defaultBgSrc;
		this.bgImage=Utils.makeImage(bgImageSrc);
		repaint();
	}
	
	public MyGamePanel(String bgImage){
		super();
		this.bgImageSrc=bgImage;
		this.bgImage=Utils.makeImage(bgImageSrc);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);		
		g.drawImage(bgImage,0, 0,this.getWidth(),this.getHeight() , null);
	}
	public void setBgImage(String newSrc){
		this.bgImageSrc=newSrc;
		this.bgImage=Utils.makeImage(bgImageSrc);
		repaint();
	}
}
