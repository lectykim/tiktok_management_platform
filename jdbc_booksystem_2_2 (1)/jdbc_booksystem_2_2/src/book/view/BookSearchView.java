package book.view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import book.vo.BookVO;

public class BookSearchView extends JPanel{
	
	JLabel lbl;
	JTextField tf;
	JButton btnSearch;
	ArrayList<BookVO> bookVOList;
	JPanel panN;
	JTable table;
	DefaultTableModel model;
	String searchWord;
	String [] comboStr = {"도서명", "출판사", "저자명"}; 
	JComboBox<String> comboSearch;
	
	
	public BookSearchView() {
		setLayout(new BorderLayout(10,10));
		comboSearch = new JComboBox<String>(comboStr);
		lbl = new JLabel("검색어 : ");
		tf = new JTextField(20);
		btnSearch = new JButton("검색");
		panN = new JPanel();
		panN.add(comboSearch);
		panN.add(lbl);
		panN.add(tf);
		panN.add(btnSearch);
		
	}
	public void initView() {
		String [] header = {"도서번호", "도서명", "저자명", "출판사", "가격", "분류명"};
		model = new DefaultTableModel(header, bookVOList.size()) {
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
		
		
	}
	
	public void putSearchResult() {
		model.setRowCount(bookVOList.size());
		BookVO vo = null;
		for (int i = 0; i < bookVOList.size(); i++) {
			vo = bookVOList.get(i);
			model.setValueAt(vo.getIsbn(), i, 0);
			
			model.setValueAt(vo.getName(), i, 1);
			model.setValueAt(vo.getAuthor(), i, 2);
			model.setValueAt(vo.getPublish(), i, 3);
			model.setValueAt(vo.getPrice(), i, 4);
			model.setValueAt(vo.getCategoryName(), i, 5);
		}
	}
	
	// setter method를 만든 것이다.
	public void setBookVOList(ArrayList<BookVO> bookVOList) {
		this.bookVOList = bookVOList;
	}
	
	// getter method
	public String getSearchWord() {
		searchWord = tf.getText();
		return searchWord;
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
	public JComboBox<String> getComboSearch() {
		return comboSearch;
	}
	
	
	
	
	
	
		
	
	

}
