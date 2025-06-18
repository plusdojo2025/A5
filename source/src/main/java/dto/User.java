package dto;

import java.io.Serializable;

public class User implements Serializable {
	private int id; // ユーザーID
	private String pw; // パスワード
	private String name; // 名前
	private int flag; // 店長フラグ
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
