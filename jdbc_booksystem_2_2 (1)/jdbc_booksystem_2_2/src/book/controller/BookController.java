package book.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import book.dao.BookDAO;
import book.dao.JDBCConnector;
import book.view.BookInsertView;
import book.view.BookSearchView;
import book.vo.BookVO;

public class BookController extends JFrame {
	Connection con;
	BookDAO dao;
	BookSearchView searchPan;
	ArrayList<BookVO> bookVOList;
	JComboBox<String> comboSearch;
	BookInsertView insertPan;
	JPanel panel;
	public BookController() {
		JTabbedPane tab = new JTabbedPane();
//		DB 연결
		con = JDBCConnector.getCon();
		dao = new BookDAO();
		
		// 도서 검색
		searchPan = new BookSearchView();
		bookVOList = dao.select(con, searchPan.getSearchWord(), 0);
		comboSearch = searchPan.getComboSearch();
		searchPan.setBookVOList(bookVOList);
		searchPan.initView(); // 호출
		
		JButton btnSearch = searchPan.getBtnSearch();
		btnSearch.addActionListener(btnL);
		
		// 도서추가
		insertPan = new BookInsertView();
		bookVOList = dao.select(con, "", 0);
		insertPan.setBookVOList(bookVOList);
		insertPan.initView(); // 호출
		JButton btnAdd = insertPan.getBtnAdd();
		btnAdd.addActionListener(btnAddL);
		add(searchPan);
		tab.add("도서검색", searchPan);
		tab.add("도서추가", insertPan);
		
		add(tab);
		
		// JFrame
		setTitle("도서관");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 500, 600, 500);
		setVisible(true);
	}
	ActionListener btnAddL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			BookVO vo = insertPan.neededInsertData();
			dao.insert(con, vo);
			bookVOList = dao.select(con, "", 0);
			insertPan.setBookVOList(bookVOList);
			insertPan.putSearchResult();
			insertPan.initData();
		}
	};
	
	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("is called");
			bookVOList = dao.select(con, searchPan.getSearchWord(), comboSearch.getSelectedIndex());
			for(BookVO bk:bookVOList) {
				System.out.println(bk.getName());
			}
			searchPan.setBookVOList(bookVOList);
			searchPan.putSearchResult();
		}
	};
	
	public static void main(String[] args) {	
		new BookController();
	}

}
