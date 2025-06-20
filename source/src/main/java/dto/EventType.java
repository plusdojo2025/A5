package dto;

import java.io.Serializable;
import java.sql.Date;

public class EventType implements Serializable {
	private Date eventDate; // イベントの日
	private String eventStart; // イベントの開始時間
	private String eventEnd; // イベントの終了時間
	private int eventId; // イベントID
	private String eventType; // イベントの内容
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = Date.valueOf(eventDate); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
	}
	public void setEventDate(Date eventDate) {
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
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public EventType() {
		
	}
	
	public EventType(String eventDate, String eventStart, String eventEnd, int eventId, String eventType) {
		this.eventDate = Date.valueOf(eventDate); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.eventId = eventId;
		this.eventType = eventType;
	}
	
	public EventType(Date eventDate, String eventStart, String eventEnd, int eventId, String eventType) {
		this.eventDate = eventDate;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.eventId = eventId;
		this.eventType = eventType;
	}

}
