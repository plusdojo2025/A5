package dto;

import java.io.Serializable;

public class Type implements Serializable {
	// フィールド
	private int typeId;
	private String typeName;
	
	// コンストラクタ
	public Type() {
		
	}
	
	public Type(int typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}
	
	// ゲッタ・セッタ
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
