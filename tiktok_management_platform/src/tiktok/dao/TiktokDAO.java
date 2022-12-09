package tiktok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import tiktok.dao.JDBCConnector;
import tiktok.vo.MovieVO;
import tiktok.vo.UserVO;
import tiktok.vo.MusicVO;


public class TiktokDAO {
	PreparedStatement ps;
    public UserVO GetUserData(Connection con, int uid) {
        UserVO user = new UserVO();
        
        try {
            String query = "SELECT uid, nickname, password, age, star FROM user WHERE uid LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + uid + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                user.setStar(rs.getInt("star"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public MusicVO GetMusicDatas(Connection con, int musicID) {
        MusicVO music = new MusicVO();
        
        try {
            String query = "SELECT music_id, title FROM music WHERE music_id LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + musicID + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                music.setMusic_id(rs.getInt("music_id"));
                music.setTitle(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return music;
    }
    
    public ArrayList<MovieVO> GetMovieDatas(Connection con, int movieId) {
        ArrayList<MovieVO> movieList = new ArrayList<MovieVO>();
        String query;
        
        try {
        	
        	if(movieId==0) {
        		
        		query = "SELECT movie_id, user, len, song FROM movie";	
        	}else {
        		query = "SELECT movie_id, user, len, song FROM movie WHERE movie_id = ?";		
        	}
        	
        	PreparedStatement ps = con.prepareStatement(query);
            if(movieId!=0)
            	ps.setInt(1,movieId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                MovieVO movie = new MovieVO();
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setUser(rs.getInt("user"));
                movie.setLen(rs.getString("len"));
                movie.setSong(rs.getInt("song"));
                movieList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return movieList;
    }
    public void insert(MovieVO vo){
		Connection con = JDBCConnector.getCon();
		PreparedStatement pstmt = null;
		//INSERT INTO `tiktok_management`.`movie` (`user`, `len`, `song`) VALUES ('1', '2:22', '3');
		String sql = "insert into movie(user,len,song) values(?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getUser());
			pstmt.setString(2, vo.getLen());
			pstmt.setInt(3, vo.getSong());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    public void Update(MovieVO vo){
		Connection con = JDBCConnector.getCon();
		PreparedStatement pstmt = null;
		String sql = "update movie set user=?, len=?, song=? where movie_id=?;"; 
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getUser());
			pstmt.setString(2, vo.getLen());
			pstmt.setInt(3, vo.getSong());
			pstmt.setInt(4, vo.getMovieId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    public void Delete(MovieVO vo){
		Connection con = JDBCConnector.getCon();
		PreparedStatement pstmt = null;
		String sql = "delete from movie where movie_id=?;"; 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMovieId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    public String TransferStrToInt(int num,int SELECTOR) {
    	String sql;
    	PreparedStatement pstmt=null;
    	Connection con = JDBCConnector.getCon();
    	ResultSet rs;
    	String rt,result="";
    	if(SELECTOR==0) {
    		sql = "select nickname from user where uid=?";
    		rt = "nickname";
    	}else {
    		sql = "select title from music where music_id=?";
    		rt = "title";
    	}
    	try {
            pstmt = con.prepareStatement(sql);
            ps.setInt(1, num);
             rs = ps.executeQuery();
         	while(rs.next()) {
        		result = rs.getString(rt);
        	}
    	}catch (Exception e) {
    		System.out.println("Failed");
    	}
    	return result;
    }
    
    public int TransferIntToStr(String str,int SELECTOR) {
    	String sql;
    	PreparedStatement pstmt=null;
    	Connection con = JDBCConnector.getCon();
    	ResultSet rs;
    	String rt;
    	int result = 0;
    	if(SELECTOR==0) {
    		sql = "select uid from user where nickname=?";
    		rt = "uid";
    	}else {
    		sql = "select music_id from music where title=?";
    		rt = "music_id";
    	}
    	try {
            pstmt = con.prepareStatement(sql);
            ps.setString(1, str);
             rs = ps.executeQuery();
         	while(rs.next()) {
        		result = rs.getInt(rt);
        	}
    	}catch (Exception e) {
    		System.out.println("Failed");
    	}
    	return result;
    }
    
    

	
}