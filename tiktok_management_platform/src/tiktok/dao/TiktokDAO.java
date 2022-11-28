package tiktok.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tiktok.vo.UserVO;
import tiktok.vo.MusicVO;
import tiktok.vo.MovieVO;

public class TiktokDAO {
    public UserVO GetUserData(Connection con, int uid) {
        UserVO user = new UserVO();
        
        try {
            String query = "SELECT uid, nickname, password, age, star FROM user WHERE uid LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + uid + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                user.setUid(rs.getInt("uid"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getString("age"));
                user.setStar(rs.getString("star"));
            }
        } catch (SQLException e) {
            e.printfStackTrace();
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
            e.printfStackTrace();
        }
        
        return music;
    }
    
    public ArrayList<MovieVO> GetMovieDatas(Connection con, int movieID) {
        ArrayList<MovieVO> movieList = new ArrayList<MovieVO>();
        
        try {
            String query = "SELECT movie_id, user, len, song FROM movie WHERE movie_id LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + movieID + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                MovieVO movie = new MovieVO();
                movie.setMusicId(rs.getInt("movie_id"));
                movie.setUser(rs.getInt("user"));
                movie.setLen(rs.getString("len"));
                movie.setSong(rs.getInt("song"));
                movieList.add(movie);
            }
        } catch (SQLException e) {
            e.printfStackTrace();
        }
        
        return movieList;
    }
}