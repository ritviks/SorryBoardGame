package sorryclient;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CardDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private CardMessagePanel container;
	private String cardMessage;
	private final static Dimension minSize = new Dimension(200,180);
	private final static Dimension maxSize = new Dimension(560,280);
	
	private final String cursorSrc="Images/images/cursors/cursorHand_grey.png";
	public CardDialog(String cardName){
		super( );		
		this.setModal(true);
		this.cardMessage=cardName;
		this.container=new CardMessagePanel(cardName);
		setTitle("Sorry!");
		setSize(minSize);
		
		
		setMinimumSize(minSize);
		setMaximumSize(maxSize);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(container);

	}





	class CardMessagePanel extends JPanel{
		private static final long serialVersionUID = 1L;

		private BufferedImage img;
		private JTextPane displayMessage;
		private JLabel cardType;
		private String message;
		private  final Font font=Utils.makeFont(true, 11f);
		public CardMessagePanel(String message){
			super();
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = toolkit.getImage(cursorSrc);
			Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(), 
			           this.getY()), "greyCursor");
			this.setCursor (c);
			this.message=message;
			String[] strings =this.message.split("-");
			displayMessage=new JTextPane();
			this.displayMessage.setFont(font);
			this.displayMessage.setEditable(false);
			StyledDocument doc = displayMessage.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
			try {
				doc.insertString(doc.getLength(), strings[1], null);
				doc.setParagraphAttributes(doc.getLength()+1, 1, center, false);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}


			this.setLayout(new GridLayout(2,1));
			this.img=Utils.makeImage("Images/images/cards/card_brown.png");	
			displayMessage.setOpaque(false);
			cardType=new JLabel(strings[0]);
			cardType.setFont(font.deriveFont(16f));
			cardType.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(cardType);
			this.add(displayMessage);
		}

		public void paintComponent(Graphics g){
			g.drawImage(img,0,0,this.getWidth(),this.getHeight(),null);
			super.setOpaque(false);
			super.paintComponent(g);

		}
		public void setMessage(String message){
			this.displayMessage.setText(message);
		}
		public String getMessage(){
			return this.displayMessage.getText();
		}

	}

}
