package dto;

import java.io.Serializable;

public class UserShift implements Serializable {
	private int shiftId; // シフトID
	private String shiftDate; // シフトの日
	private String shiftStart; // シフト開始時間
	private String shiftEnd; // シフト終了時間
	private String userName; // ユーザーネーム
	
	public int getShiftId() {
		return shiftId;
	}
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
