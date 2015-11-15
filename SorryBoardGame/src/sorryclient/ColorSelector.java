package sorryclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * ColorSelector
 * Menu to select Red,Blue,Green,Yellow
 * */
public class ColorSelector extends MyGamePanel {
	
	private static final long serialVersionUID = 1900724217285760485L;
	private final String cursorSrc="Images/images/cursors/cursorHand_grey.png";
	
	//The options for color selection
	private Color selection;
	private final int numOptions = 4;
	private final ColorButton[] optionButtons;
	
	private final NextButton confirmButton;
	
	private final static String selectColorString = "Select your color";
	
	private final static String[] colorNames = {"Red", "Blue", "Green", "Yellow"};
	private final static Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
	
	//For the Font
	private Font titleFont;
	private Font buttonFont;
	private Font nextFont;
	
	//For spacing to the borders
	private final static Insets spacing = new Insets(20,20,20,20);
	
	public Color getPlayerColor() {
		return selection;
	}
	
	public ColorSelector(ActionListener confirmAction) {
		super();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(cursorSrc);
		Cursor cursor = toolkit.createCustomCursor(image , new Point(this.getX(), 
		           this.getY()), "greyCursor");
		this.setCursor (cursor);
//		setOpaque(true);
//		this.set
//		setBackground(null);
		//Creates the 3 fonts;
		titleFont=Utils.makeFont(true,25f);
		buttonFont=Utils.makeFont(true, 20f);
		nextFont=Utils.makeFont(true, 12f);
		
		//set up the button so we can proceed
		confirmButton = new NextButton("Confirm",12f);
		confirmButton.addActionListener(confirmAction);
		confirmButton.setEnabled(false);
		confirmButton.setFont(nextFont);
		
//		new GameButtons
		//The top of the panel, the label
		JLabel selectPlayerLabel = new JLabel(selectColorString);
		selectPlayerLabel.setFont(titleFont);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		topPanel.setOpaque(false);
		topPanel.add(selectPlayerLabel,new GridBagConstraints());
		
		//The middle of the panel, the color buttons
		JPanel centerPanel = new JPanel(new GridLayout(2,2,20,20));
	
		optionButtons = new ColorButton[numOptions];
		for(int i = 0; i < numOptions; ++i) {
			optionButtons[i] = new ColorButton(colorNames[i],20f,colors[i],i);
			optionButtons[i].setOpaque(true);
			optionButtons[i].setBorderPainted(false);
			final int buttonSelection = i;
			optionButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					selection = colors[buttonSelection];
					for(JButton button : optionButtons) button.setEnabled(true);
					optionButtons[buttonSelection].setEnabled(false);
					optionButtons[buttonSelection].setImagePressed();
					for(int i=0;i<4;i++){
						if(i!=buttonSelection)
							optionButtons[i].setImageReleased();
					}
					confirmButton.setEnabled(true);
				}
			});
//			ColorButton c=optionButtons[i];
//			optionButtons[i].addMouseListener(new MouseAdapter() {
//
//				@Override
//				public void mousePressed(MouseEvent e) {
//					c.setImagePressed();
//					
//				}
//
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					c.setImageReleased();
//					
//				}
//				
//			});
			optionButtons[i].setFont(buttonFont);
			centerPanel.add(optionButtons[i]);
		}
		centerPanel.setBorder(new EmptyBorder(spacing));
		
		//The bottom of the panel, the confirm button
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.X_AXIS));
		bottomPanel.setBorder(new EmptyBorder(spacing));
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(confirmButton);
		
		setLayout(new GridLayout(3,1));
		this.setBorder(new EmptyBorder(new Insets(20,20,20,20)));
		add(topPanel);
		centerPanel.setOpaque(false);
		add(centerPanel);
		add(bottomPanel);
		bottomPanel.setOpaque(false);
	}

}
