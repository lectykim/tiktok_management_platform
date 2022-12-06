package tiktok.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import tiktok.dao.JDBCConnector;
import tiktok.dao.TiktokDAO;
import tiktok.view.TiktokInsertView;
import tiktok.view.TiktokSearchView;
import tiktok.view.TiktokUpdateView;
import tiktok.vo.MovieVO;

public class TiktokController extends JFrame{
	Connection con;
	TiktokDAO dao;
	ArrayList<MovieVO> MovieVOList;
	TiktokSearchView searchPan;
	TiktokInsertView insertPan;
	TiktokUpdateView updatePan;
	JPanel panel;
	JTable table;
	static final int YES = 0;
	public TiktokController() {
		JTabbedPane tab = new JTabbedPane();
//		DB 연결
		con = JDBCConnector.getCon();
		dao = new TiktokDAO();
		
		// 영상 검색
		searchPan = new TiktokSearchView();
		MovieVOList = dao.GetMovieDatas(searchPan.getSearchWord(), 0); 
		searchPan.setMovieVOList(MovieVOList);
		JButton btnSearch = searchPan.getBtnSearch();
		btnSearch.addActionListener(btnL);
		searchPan.initView(); // 호출
		
		// 도서추가
		insertPan = new TiktokInsertView();
		MovieVOList = dao.GetMovieDatas("", 0); 
		insertPan.setMovieVOList(MovieVOList);
		insertPan.initView(); // 호출 JButton btnAdd = insertPan.getBtnAdd();
		JButton btnAdd = insertPan.getBtnAdd();
		btnAdd.addActionListener(btnAddL);
		 
		
		// 도서수정
		updatePan = new TiktokUpdateView();
		MovieVOList = dao.GetMovieDatas("", 0); updatePan.setBookVOList(bookVOList);
		updatePan.initView(); // 호출 table = updatePan.getTable();
		table.addMouseListener(tableL); JButton btnUpdate = updatePan.getBtnUpdate();
		btnUpdate.addActionListener(btnUpdateL);
		 
				
		tab.add("제목 검색", searchPan);
		tab.add("영상 추가", insertPan);
		tab.add("영상 수정 및 삭제", updatePan);
		add(tab);
		
		// JFrame
		setTitle("도서관");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 500, 600, 500);
		setVisible(true);
	}
	// 영상 검색 리스너
	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("is called");
			
			 MovieVOList = dao.GetMovieDatas(searchPan.getSearchWord(),searchPan.get); for(MovieVO mk:MovieVOList) {
			 System.out.println(bk.getName()); } searchPan.setMovieVOList(MovieVOList);
			 searchPan.putSearchResult();
			 
			
		}
	};
	
	// 영상 추가 리스너
	ActionListener btnAdd = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	// 영상 수정 및 삭제 리스너
	ActionListener btnUpdateL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	public static void main(String[] args) {
		new TiktokController();
		
	}
}
