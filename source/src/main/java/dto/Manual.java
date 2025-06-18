package dto;

import java.io.Serializable;

public class Manual implements Serializable {
	private String manualFile; // マニュアルファイル名
	private int importance; // 重要度
	private String date; // アップロード日
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Manual(String manualFile, int importance, String date) {
		this.manualFile = manualFile;
		this.importance = importance;
		this.date = date;
	}
}
