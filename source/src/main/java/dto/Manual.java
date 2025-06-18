package dto;

import java.io.Serializable;

public class Manual implements Serializable {
	private String manualFile; // マニュアルファイル名
	private int importance; // 重要度
	private String date; // アップロード日
	private int fileId; // ファイルID
	
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
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
}
