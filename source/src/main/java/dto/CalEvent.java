package dto;

import java.sql.Date;

public class CalEvent {
	
	//カレンダーに表示するイベントの日付と件数を格納する
	private Date eventData;
	private int count;
	
	public Date getEventData() {
		return eventData;
	}
	public void setEventData(Date eventData) {
		this.eventData = eventData;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
