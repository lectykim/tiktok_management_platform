package tiktok.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tiktok.vo.MovieVO;

public class TiktokInsertView extends JPanel {
	JButton btnSearch;
	ArrayList<MovieVO> MovieVOList;
	JTable table; 
	DefaultTableModel model;
	String searchword;
	JPanel panS = new JPanel(new GridLayout(4,4));
	String [] header = {"영상 번호", "영상 길이", "유저 이름", "사용된 음악"};
	JLabel [] lbls = new JLabel[header.length];
	JTextField [] tf = new JTextField[lbls.length];
	JButton btnAdd = new JButton("영상 추가");
	public TiktokInsertView() {
		
		setLayout(new BorderLayout(10,10));
		for(int i = 0; i < header.length; i++) {
			lbls[i] = new JLabel(header[i]);
			panS.add(lbls[i]);
			if(i < header.length){
				tf[i] = new JTextField();
				panS.add(tf[i]);
			}
		}
		tf[0].setEditable(false);
		for(int i = 0; i < 4; i++)
		{
			panS.add(new JLabel(""));
		}
		panS.add(btnAdd);
	
	}
	public void initData() {
		for(int i = 0; i < tf.length; i++)
		{
			tf[i].setText("");
		}
	}
	public void initView() {
		model = new DefaultTableModel(header, MovieVOList.size()) {
			@Override
			public boolean isCellEditable(int row, int column) {		
				
				return false;
			}
		};
		putSearchResult();
		
		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		JScrollPane scroll = new JScrollPane(table);
		
		add("Center", scroll);
		add("South", panS);
	}
	
	public void putSearchResult() {
		model.setRowCount(MovieVOList.size());
		MovieVO vo = null;
		for (int i = 0; i < MovieVOList.size(); i++) {
			vo = MovieVOList.get(i);
			model.setValueAt(vo.getMovieId(), i, 0);
			model.setValueAt(vo.getLen(), i, 1);
			model.setValueAt(vo.getUser(), i, 2);
			model.setValueAt(vo.getSong(), i, 3);
		}
	}
	
	public JButton getBtnAdd() {
		return btnAdd;
	}
	
	// setter method를 만든 것이다.
	public void setMovieVOList(ArrayList<MovieVO> MovieVOList) {
		this.MovieVOList = MovieVOList;
	}
	public MovieVO neededInsertData() {
		MovieVO vo = new MovieVO();
		vo.setLen(tf[1].getText());
		vo.setSong(Integer.parseInt(tf[2].getText()));
		vo.setUser(Integer.parseInt(tf[3].getText()));
		return vo;
	}
	// getter method
		
//	public int getMovieId() {
//		searchword = tf[0].getText();
//		return Integer.parseInt(searchword);
//	}
}
