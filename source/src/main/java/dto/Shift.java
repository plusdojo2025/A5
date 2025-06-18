package dto;

import java.io.Serializable;

public class Shift implements Serializable {
	private String shiftDate; // シフトの日
	private String shiftStart; // シフト開始時間
	private String shiftEnd; // シフト終了時間
	private int userId; // ユーザーID
	
	public String getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(String shiftDate) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Shift(String shiftDate, String shiftStart, String shiftEnd, int userId) {
		this.shiftDate = shiftDate;
		this.shiftStart = shiftStart;
		this.shiftEnd = shiftEnd;
		this.userId = userId;
	}
}
