package tiktok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        
        try {
            String query = "SELECT movie_id, user, len, song FROM movie WHERE movie_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + movieId  + "%");
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
		String sql = "insert into movie values(?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMovieId());
			pstmt.setInt(2, vo.getUser());
			pstmt.setString(3, vo.getLen());
			pstmt.setInt(4, vo.getSong());
			
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
		String sql = "update book set movie_id=?, user=?, len=?, song=?;"; 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMovieId());
			pstmt.setInt(2, vo.getUser());
			pstmt.setString(3, vo.getLen());
			pstmt.setInt(4, vo.getSong());
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

	
}