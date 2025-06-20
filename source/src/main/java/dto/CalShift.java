package dto;

import java.sql.Date;

public class CalShift {

	//カレンダーに表示するシフトの日付と件数を格納する
	private Date shiftData;
	private int count;
	
	public Date getShiftData() {
		return shiftData;
	}
	public void setShiftData(Date shiftData) {
		this.shiftData = shiftData;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
