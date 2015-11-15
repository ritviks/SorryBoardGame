package sorryclient;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	
	//Background Grey Image
	private final static String bgImageSource="Images/images/panels/grey_panel.png";
	private final static String buttonImageSource="Images/images/buttons/grey_button00.png";
	
	//Custom Fonts
	public static  final String customFont="Images/fonts/kenvector_future.ttf";
	public static final String customFontThin="Images/fonts/kenvector_future_thin.ttf";
	
	
	public static Font makeFont(boolean b,float size){
		Font font=null;
		try {
			if(b) font=Font.createFont(Font.TRUETYPE_FONT, new File(customFont)).deriveFont(size);
			else font=Font.createFont(Font.TRUETYPE_FONT, new File(customFontThin)).deriveFont(size);
		} catch (FontFormatException e) {
			System.out.println("FONT FORMAT IS INCORRECT");
		}
		catch (IOException e) {
			System.out.println("FONT JUST DOESNT WANT TO WORK");
		}
		return font;
	}
	
	public static BufferedImage makeImage(String src){
		BufferedImage img=null;
		try {
			if(src!="background")	
		    img = ImageIO.read(new File(src));
			else  img = ImageIO.read(new File(bgImageSource));
		} catch (IOException e) {
			System.out.println("WELL IMAGE DOES WANT TO LOAD: "+src);
		}
		return img;
	}
//	public void makeFont(){
//		try {
//		     GraphicsEnvironment ge = 
//		         GraphicsEnvironment.getLocalGraphicsEnvironment();
//		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(customFont)));
//		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(customFontThin)));
//		} catch (FontFormatException e) {
//		     System.out.println("FONT FORMAT IS INCORRECT");
//		}
//		catch (IOException e) {
//			 System.out.println("FONT JUST DOESNT WANT TO WORK");
//		}
//	}
}
