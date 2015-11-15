package sorryclient;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/*
 * NumPlayerSelector
 * Menu to select a number of players 2-3
 * */
public class NumPlayerSelector extends JPanel {
	private static final long serialVersionUID = -4510696620583889943L;
	private final String cursorSrc="Images/images/cursors/cursorHand_grey.png";
	
	private Font font;
	
	//The options for number of player selection
	private int selection = -1;
	private JRadioButton current;
	private final int numOptions = 3;
	private final JRadioButton[] optionButtons;
	private final ButtonGroup buttonGroup;
	
	private NextButton confirmButton;
	
	private final String selectNumPlayerString = "Select the number of players";
	private Image notClicked=null;
	private Image clicked=null;
	private ImageIcon notClickedIcon=null;
	private ImageIcon clickedIcon=null;
	private String notClickedSrc="Images/images/checkboxes/grey_circle.png";
	private String ClickedSrc="Images/images/checkboxes/grey_boxTick.png";
	//The spacing of the border
	private static final Insets spacing = new Insets(50,50,50,50);
	
	public int getNumberOfPlayers() {
		return selection;
	}
	
	public NumPlayerSelector(ActionListener confirmAction){
		super();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(cursorSrc);
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(), 
		           this.getY()), "greyCursor");
		this.setCursor (c);
		
		setOpaque(false);
		font=Utils.makeFont(true,25);
		notClicked=Utils.makeImage(notClickedSrc);
		clicked=Utils.makeImage(ClickedSrc);
		notClickedIcon=new ImageIcon (notClicked.getScaledInstance(notClicked.getWidth(null)/2, notClicked.getHeight(null)/2, Image.SCALE_SMOOTH));
		clickedIcon=new ImageIcon(clicked.getScaledInstance(clicked.getWidth(null)/2, clicked.getHeight(null)/2, Image.SCALE_SMOOTH));
		
		
		//set up the button so we can proceed
		confirmButton = new NextButton("Confirm",12f);
		confirmButton.addActionListener(confirmAction);
		confirmButton.setEnabled(false);
		
		//The top of the panel, the label
		JLabel selectPlayerLabel = new JLabel(selectNumPlayerString);
		selectPlayerLabel.setFont(font);
		
		JPanel topPanel = new JPanel();
		topPanel.add(selectPlayerLabel);
		topPanel.setOpaque(false);
		//The center panel to hold the button panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setOpaque(false);
		//The button panel to hold the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setOpaque(false);
		
		Font buttonFont=Utils.makeFont(true,13f);
		buttonGroup = new ButtonGroup();
		optionButtons = new JRadioButton[numOptions];
		for(int i = 0; i < numOptions; ++i) {
			JPanel numPanel = new JPanel();
			numPanel.setOpaque(false);
			optionButtons[i] = new JRadioButton(""+(i+2));
			optionButtons[i].setOpaque(false);
			optionButtons[i].setIconTextGap(10);
			optionButtons[i].setIcon(notClickedIcon);
			optionButtons[i].setFont(buttonFont);
			final int buttonSelection = i+2;
			optionButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					selection = buttonSelection;
					if(current!=null){
						current.setIcon(notClickedIcon);
					}
					current=optionButtons[selection-2];
					current.setIcon(clickedIcon);
					confirmButton.setEnabled(true);
				}
			});
			buttonGroup.add(optionButtons[i]);
			numPanel.add(optionButtons[i]);
			
			buttonPanel.add(Box.createGlue());
			buttonPanel.add(numPanel);
			buttonPanel.add(Box.createGlue());
		}
		
		centerPanel.add(Box.createGlue());
		centerPanel.add(buttonPanel);
		centerPanel.add(Box.createGlue());
		
		//The bottom panel to hold the confirm button
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.X_AXIS));
		bottomPanel.setBorder(new EmptyBorder(spacing));
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(confirmButton);
		bottomPanel.setOpaque(false);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	
		add(Box.createGlue());
		add(topPanel);
		add(Box.createGlue());
		add(centerPanel);
		add(Box.createGlue());
		add(bottomPanel);
		
	}
	public void paintComponent(Graphics g){
		this.setOpaque(false);
		g.drawImage(Utils.makeImage("background"),0,0,this.getWidth(),this.getHeight(),null);
		super.paintComponent(g);
		super.setOpaque(false);
		}
	

	
}
