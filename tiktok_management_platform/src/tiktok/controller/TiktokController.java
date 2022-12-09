package tiktok.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import tiktok.controller.TiktokController;
import tiktok.view.TiktokInsertView;
import tiktok.view.TiktokSearchView;
import tiktok.view.TiktokUpdateView;
import tiktok.vo.MovieVO;
import tiktok.dao.JDBCConnector;
import tiktok.dao.TiktokDAO;

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
		MovieVOList = dao.GetMovieDatas(con, 0); 
		searchPan.setMovieVOList(MovieVOList);
		searchPan.initView(); // 호출
		JButton btnSearch = searchPan.getBtnSearch();
		btnSearch.addActionListener(btnL);
		
		
		// 영상 추가
		insertPan = new TiktokInsertView();
		MovieVOList = dao.GetMovieDatas(con, 0); 
		insertPan.setMovieVOList(MovieVOList);
		JButton btnAdd = insertPan.getBtnAdd();
		btnAdd.addActionListener(btnAddL);
		insertPan.initView(); // 호출 JButton btnAdd = insertPan.getBtnAdd();
		 
		
		// 도서수정
		updatePan = new TiktokUpdateView();
		MovieVOList = dao.GetMovieDatas(con, 0); 
		updatePan.setMovieVOList(MovieVOList);
		JButton btnUpdate = updatePan.getBtnUpdate();
		btnUpdate.addActionListener(btnUpdateL);
		updatePan.initView(); // 호출 table = updatePan.getTable();
		table = updatePan.getTable();
		table.addMouseListener(tableL); 

		 
				
		tab.add("제목 검색", searchPan);
		tab.add("영상 추가", insertPan);
		tab.add("영상 수정 및 삭제", updatePan);
		add(tab);
		
		// JFrame
		setTitle("틱톡 영상관리 플랫폼");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 500, 600, 500);
		setVisible(true);
	}

	MouseAdapter tableL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) 
		{
			if(e.getClickCount() == 1) {
				int row = table.getSelectedRow(); // table의 행 번호가 반환
				updatePan.setTextField(row);
			}
			if(e.getClickCount() == 2) {
				int result = JOptionPane.showConfirmDialog(TiktokController.this, "정말로 삭제 하시겠습니까 ?", "삭제 여부",JOptionPane.WARNING_MESSAGE);
				if(result == YES) {
					MovieVO vo = updatePan.neededUpdateData();
					dao.Delete(vo);
					MovieVOList = dao.GetMovieDatas(con, 0);
					updatePan.setMovieVOList(MovieVOList);
					updatePan.putSearchResult();
				}
			}
		};
	};
	// 영상 검색 리스너
	ActionListener btnL = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("movieid : "+searchPan.getMovieId());
			MovieVOList = dao.GetMovieDatas(con, searchPan.getMovieId());
			for(MovieVO mv:MovieVOList) {
				System.out.println(mv.getMovieId());
			}
			searchPan.setMovieVOList(MovieVOList);
			searchPan.putSearchResult();
		}
	};
	
	// 영상 추가 리스너
	ActionListener btnAddL = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			MovieVO vo = insertPan.neededInsertData();
			dao.insert(vo);
			MovieVOList = dao.GetMovieDatas(con, 0);
			insertPan.setMovieVOList(MovieVOList);
			insertPan.putSearchResult();
			insertPan.initData();
		}
	};
	
	// 영상 수정 및 삭제 리스너
	ActionListener btnUpdateL = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			MovieVO vo = updatePan.neededUpdateData();
			dao.Update(vo);
			MovieVOList = dao.GetMovieDatas(con, 0);
			updatePan.setMovieVOList(MovieVOList);
			updatePan.putSearchResult();
		}
	};
	public static void main(String[] args) {
		new TiktokController();
		
	}
}
