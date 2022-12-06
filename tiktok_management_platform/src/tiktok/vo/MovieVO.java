package tiktok.vo;

public class MovieVO {

	private int movieId; // 영상 번호
	private int user; // 유저 번호
	private String len; // 영상 길이
	private int song; // 사용된 음악
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public int getSong() {
		return song;
	}
	public void setSong(int song) {
		this.song = song;
	}
	
	
	
}
