package sorryclient;

import java.awt.Font;

import javax.swing.JButton;

public class GameButtons extends JButton {

	private static final long serialVersionUID = 1L;
	protected String name;
	protected Font font= null;
	protected float fontSize;
	
	public GameButtons(String name,float fontSize){
		super(name);
		this.fontSize=fontSize;
		this.font=Utils.makeFont(true,this.fontSize);
		this.name=name;
		this.setFont(font);
	}
	
	protected void changeFont(Font font){
		this.font=font;
		this.setFont(this.font);
		repaint();
	}
	
	protected void setFSize(float newSize){
		this.font=this.font.deriveFont(newSize);
		this.setFont(this.font);
		this.fontSize=newSize;
		repaint();
	}
	
}
