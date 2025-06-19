package dto;

import java.io.Serializable;
import java.sql.Date;

public class Shift implements Serializable {
	private Date shiftDate; // シフトの日
	private String shiftStart; // シフト開始時間
	private String shiftEnd; // シフト終了時間
	private int userId; // ユーザーID
	
	public Date getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(String shiftDate) {
		this.shiftDate = Date.valueOf(shiftDate); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
	}
	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate; // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
	}
	public String getShiftStart() {
		return shiftStart;
	}
	public void setShiftStart(String shiftStart) {
		this.shiftStart = shiftStart;
	}
	public String getShiftEnd() {
		return shiftEnd;
	}
	public void setShiftEnd(String shiftEnd) {
		this.shiftEnd = shiftEnd;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Shift() {
		
	}
	
	public Shift(String shiftDate, String shiftStart, String shiftEnd, int userId) {
		this.shiftDate = Date.valueOf(shiftDate); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.userId = userId;
	}
	
	public Shift(Date shiftDate, String shiftStart, String shiftEnd, int userId) {
		this.shiftDate = shiftDate;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.userId = userId;
	}

}
