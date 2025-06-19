package dto;

import java.io.Serializable;
import java.sql.Date;

public class UserShift implements Serializable {
	private Date shiftDate; // シフトの日
	private String shiftStart; // シフト開始時間
	private String shiftEnd; // シフト終了時間
	private String userName; // ユーザーネーム
	
	public Date getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(String shiftDate) {
		this.shiftDate = Date.valueOf(shiftDate); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
	}
	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public UserShift() {
		
	}
	
	public UserShift(String shiftDate, String shiftStart, String shiftEnd, String userName) {
		this.shiftDate = Date.valueOf(shiftDate); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.userName = userName;
	}
	
	public UserShift(Date shiftDate, String shiftStart, String shiftEnd, String userName) {
		this.shiftDate = shiftDate;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.userName = userName;
	}

}
