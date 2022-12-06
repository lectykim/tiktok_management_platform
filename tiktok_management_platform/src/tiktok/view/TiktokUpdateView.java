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

public class TiktokUpdateView {

	JButton btnSearch;
	ArrayList<MovieVO> MovieVOList;
	JTable table;
	DefaultTableModel model;
	String searchWord;
	JPanel panS = new JPanel(new GridLayout(4,4));
	String [] header = {"영상 번호", "유저 이름", "영상 길이", "사용된 음악"};
	JLabel [] lbls = new JLabel[header.length];
	JTextField [] tf = new JTextField[lbls.length - 1];
	JButton btnUpdate = new JButton("영상 수정");
	
	public TiktokUpdateView() {
		setLayout(new BorderLayout(10,10));
		for(int i = 0; i < header.length; i++) {
			lbls[i] = new JLabel(header[i]);
			panS.add(lbls[i]);
			if(i < header.length - 1){
				tf[i] = new JTextField();
				panS.add(tf[i]);
			}
		}
		// 영상 번호 편집 불가.
		tf[0].setEditable(false);
				
		for(int i = 0; i < 3; i++)
		{
			panS.add(new JLabel(""));
		}
		panS.add(btnUpdate);
	}
	public void initData() {
		for(int i = 0; i < tf.length; i++)
		{
			tf[i].setText("");
		}
	}
	public void setTextField(int row) {
		for(int i = 0; i < tf.length; i++) {
			tf[i].setText(model.getValueAt(row, i)+""); 
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
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
	
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
			model.setValueAt(vo.getSong(), i, 2);
		}
	}
	
	public JButton getBtnUpdate() {
		return btnUpdate;
	}
	
	// setter method를 만든 것이다.
	public void setBookVOList(ArrayList<MovieVO> MovieVOList) {
		this.MovieVOList = MovieVOList;
	}
	public MovieVO neededUpdateData() {
		MovieVO vo = new MovieVO();
		vo.setMovieId(Integer.parseInt(tf[0].getText()));
		vo.setLen(tf[1].getText());
		vo.setSong(Integer.parseInt(tf[2].getText()));
		return vo;
	}
	
	public JTable getTable() {
		return table;
	}
}
	
	
	public static void main(String[] args) {
		

	}

}
