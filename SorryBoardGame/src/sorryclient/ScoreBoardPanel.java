package sorryclient;

import game.GameHelpers;

import java.awt.BorderLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import sorryclient.HelpPanel.HelpInfo;

public class ScoreBoardPanel extends JDialog{

	static ScoreBoardInfo scoreBoardInfo;
	public ScoreBoardPanel(){
		super();
		this.setTitle("ScoreBoard");
		this.setModal(true);	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		scoreBoardInfo=new ScoreBoardInfo();
		pack();
		setSize(150,200);
//		this.setResizable(false);
		add(scoreBoardInfo);
	}


	class ScoreBoardInfo extends JPanel{
		private JTable tableContainer;
		private DefaultTableModel tableInfo;
		private final String[] columNames={"Name", "Score"};
		private Object[][] tableData;
		private JScrollPane scrollPane;
		private TableRowSorter<TableModel> sorter;
		public ScoreBoardInfo(){
			setLayout(new BorderLayout());
			tableData=GameHelpers.getScoreData();
			tableInfo=new DefaultTableModel(tableData,columNames);
			tableContainer = new JTable(tableInfo);
			tableContainer.setAutoCreateRowSorter(true);
			 sorter = new TableRowSorter<>(tableContainer.getModel());
			tableContainer.setRowSorter(sorter);
			ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
			 
			int columnIndexToSort = 1;
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			 
			sorter.setSortKeys(sortKeys);
			sorter.sort();
			
			
			scrollPane= new JScrollPane(tableContainer);
			add(scrollPane,BorderLayout.CENTER);
			setResizable(false);
		}
		public void addName(String name, int score) {
			tableInfo.addRow(new Object[]{name,score});
			///NEEED TO Store it Sorted ;
			sorter.sort();
			GameHelpers.writeScore(tableInfo);
		}


	}
	static void addName(String name, int Score){
		scoreBoardInfo.addName(name,Score);
	}
}
