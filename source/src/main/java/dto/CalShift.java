package dto;

import java.sql.Date;

public class CalShift {

    // シフトの日付
    private Date shiftData;
    // シフト開始時間（例："09:00"）
    private String shiftStart;
    // シフト終了時間（例："17:00"）
    private String shiftEnd;
    // ユーザーID
    private int userId;
    // 件数（オプション、複数シフトの件数を扱う場合）
    private int count;

    // getter/setter

    public Date getShiftData() {
        return shiftData;
    }

    public void setShiftData(Date shiftData) {
        this.shiftData = shiftData;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}