package sorryclient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



/*
 * MainMenu
 * Menu to hold the start button
 * */


public class MainMenu extends MyGamePanel{
	private static final long serialVersionUID = 3609831945869059312L;

	private final String buttonImageSource="Images/images/buttons/grey_button00.png";
	//Title Image
	private static BufferedImage titleImage;
	private final String titleImageSource="Images/images/sorry.png";
	//Button image
	private final NextButton start;
	//Custom Fonts
	
	private final static Insets spacing = new Insets(20,20,20,20);
	public MainMenu(ActionListener startAction){
		
		
		start = new NextButton("Start",20f);
		BufferedImage img= Utils.makeImage(buttonImageSource);
		Dimension dim=new Dimension(img.getWidth(),img.getHeight());
		start.setPreferredSize(dim);
		start.setIcon(new ImageIcon(img));
		start.addActionListener(startAction);

		titleImage=Utils.makeImage(titleImageSource);
		JLabel titleLabel = new JLabel();
		titleLabel.setIcon(new ImageIcon(titleImage));
		titleLabel.setFont(null);
		
		setLayout(new GridLayout(2,1));
		setBorder(new EmptyBorder(spacing));
		JPanel jp1=new JPanel();
		jp1.setOpaque(false);
		jp1.add(titleLabel,SwingConstants.CENTER);
		JPanel jp2=new JPanel();
		jp2.setOpaque(false);
		jp2.add(start,SwingConstants.CENTER);
		add(jp1);
		add(jp2);
		setOpaque(false);
	}

	
	
}
