package dto;

import java.io.Serializable;

public class Event implements Serializable {
	private String eventDate; // イベントの日
	private String eventStart; // イベントの開始時間
	private String eventEnd; // イベントの終了時間
	private int typeId; // イベントの内容
	
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventStart() {
		return eventStart;
	}
	public void setEventStart(String eventStart) {
		this.eventStart = eventStart;
	}
	public String getEventEnd() {
		return eventEnd;
	}
	public void setEventEnd(String eventEnd) {
		this.eventEnd = eventEnd;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public Event(String eventDate, String eventStart, String eventEnd, int typeId) {
		this.eventDate = eventDate;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.typeId = typeId;
	}
}
