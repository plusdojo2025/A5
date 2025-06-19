package dto;

import java.io.Serializable;
import java.sql.Date;

public class Manual implements Serializable {
	private String manualFile; // マニュアルファイル名
	private int importance; // 重要度
	private Date date; // アップロード日
	private int fileId;
	
	public String getManualFile() {
		return manualFile;
	}
	public void setManualFile(String manualFile) {
		this.manualFile = manualFile;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = Date.valueOf(date); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public Manual(String manualFile, int importance, String date, int fileId) {
		this.manualFile = manualFile;
		this.importance = importance;
		this.date = Date.valueOf(date); // String型「YYYY-MM-DD」をSQLのDATE型に入れられるように変換
		this.fileId = fileId;
	}
	
	
}
