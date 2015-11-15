package game;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import sorryclient.Utils;

/*
 * GameHelpers
 * Various methods for translation
 * */
public class GameHelpers {
	private static final Color colors[] = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};

	private static final Map<String,Color> colorLookup = new HashMap<String,Color>();
	private static final Map<Color,BufferedImage> slideLabelLookup = new HashMap<Color,BufferedImage>();
	private static final Map<Color,Integer> colorIndexLookup = new HashMap<Color,Integer>();
	private static final Map<Color,Image> pawnLabelLookup=new HashMap<Color,Image>();
	private static final String scoreInfo="src/game/scoreBoard.txt";

	static {
		colorLookup.put("-", Color.BLACK);
		colorLookup.put("r", colors[0]);
		colorLookup.put("b", colors[1]);
		colorLookup.put("y", colors[2]);
		colorLookup.put("g", colors[3]);

		slideLabelLookup.put(colors[0], Utils.makeImage("Images/images/sliders/red_slide.png"));
		slideLabelLookup.put(colors[1], Utils.makeImage("Images/images/sliders/blue_slide.png"));
		slideLabelLookup.put(colors[2], Utils.makeImage("Images/images/sliders/yellow_slide.png"));
		slideLabelLookup.put(colors[3], Utils.makeImage("Images/images/sliders/green_slide.png"));

		BufferedImage i=Utils.makeImage("Images/images/pawns/red_pawn.png");
		pawnLabelLookup.put(colors[0], Utils.makeImage("Images/images/pawns/red_pawn.png").getScaledInstance(i.getWidth()/2, i.getHeight()/4,Image.SCALE_SMOOTH));
		pawnLabelLookup.put(colors[1], Utils.makeImage("Images/images/pawns/blue_pawn.png").getScaledInstance(i.getWidth()/2, i.getHeight()/4,Image.SCALE_SMOOTH));
		pawnLabelLookup.put(colors[2], Utils.makeImage("Images/images/pawns/yellow_pawn.png").getScaledInstance(i.getWidth()/2, i.getHeight()/4,Image.SCALE_SMOOTH));
		pawnLabelLookup.put(colors[3], Utils.makeImage("Images/images/pawns/green_pawn.png").getScaledInstance(i.getWidth()/2, i.getHeight()/4,Image.SCALE_SMOOTH));

		colorIndexLookup.put(colors[0], 0);
		colorIndexLookup.put(colors[1], 1);
		colorIndexLookup.put(colors[2], 2);
		colorIndexLookup.put(colors[3], 3);
	}

	public static Color getColorFromIndex(int index) {
		return colors[index];
	}

	public static Integer getIndexFromColor(Color color) {
		return colorIndexLookup.get(color);
	}

	public static Color getColorFromString(String color) {
		return colorLookup.get(color);
	}

	public static BufferedImage getSlideLabelFromColor(Color color) {
		return slideLabelLookup.get(color);
	}

	public static Image getPawnLabel(Color color){
		return pawnLabelLookup.get(color);
	}

	public static Object[][] getScoreData() {
		Object[][] data;
		Scanner sc = null;
		//Read in the scoreboard
		try {
			sc = new Scanner(new File(scoreInfo));
		} catch (FileNotFoundException e) {
			System.out.println("NO FILE");
		}

		int rows=sc.nextInt();
		data=new Object[rows][2];
		int x=0;
		while(sc.hasNext()) {
			String name=sc.next();
			data[x][0]=name;
			int score= sc.nextInt();
			data[x][1]=score;
			++x;
		}
		sc.close();
		return data;
	}

	public static void writeScore(DefaultTableModel tableInfo) {
		FileWriter fw=null;
		PrintWriter pW=null;
		try {
			fw = new FileWriter(scoreInfo);;
			pW=new PrintWriter(fw);

			int nRow = tableInfo.getRowCount();
			int nCol = tableInfo.getColumnCount();
			pW.println(nRow);
			for (int i = 0 ; i < nRow ; i++){
				for (int j = 0 ; j < nCol ; j++){
					pW.print(""+tableInfo.getValueAt(i, j)+" ");
				}
				pW.print('\n');
			}
			pW.flush();
		} catch (IOException e) {
			System.out.println("Nope");

		} finally {
			try {
				if (pW != null) {
					pW.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException ioe) {
				System.out.println("IOException closing file: " + ioe.getMessage());
			}
		}

	}
}
