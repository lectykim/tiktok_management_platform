package tiktok.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import tiktok.vo.MovieVO;

public class TiktokSearchView extends JPanel{
	
	/*--------- 알고리즘 ---------*/
	// 검색은 오로지 영상의 제목으로 만
	// DB (table)에 존재 유무 Check
	// 검색 결과는 영상의 제목, 길이, 사용된 음악정보 등을 표시
	// * 검색결과는 아래에 표시
	
	// 화면 구성
	// 탭 1 2 3
	// panN - label, textfield, btnSearch
	// table - DB
	// panS - 검색 결과
	
	JPanel panN;// label, textfield, button 등을 집어넣을 예정 
	JLabel lbl; // 제목 : 
	JTextField tf;// 검색어 입력
	JButton btnSearch; // 검색 버튼
	ArrayList<MovieVO> MovieVOList; // 
	JTable table; // DataBase
	DefaultTableModel model; 
	String searchWord; // 검색어 
	
	public TiktokSearchView() {
		setLayout(new BorderLayout(10,10));
		lbl = new JLabel("검색어 : ");
		tf = new JTextField(20);
		btnSearch = new JButton("검색");
		panN = new JPanel();
		
		// Panel 추가
		panN.add(lbl);
		panN.add(tf);
		panN.add(btnSearch);
	}
	public void initView() {
		String [] header = {"영상 번호", "유저 이름", "영상 길이", "사용된 음악"};
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
		
		add("North",panN);
		add("Center", scroll);
		
	} // initView();
	
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
	// setter method를 만든 것이다.
	public void setMovieVOList(ArrayList<MovieVO> MovieVOList) {
		this.MovieVOList = MovieVOList;
	}
		
	// getter method
	public String getMovieId() {
		searchWord = tf.getText();
		return searchWord;
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
}
