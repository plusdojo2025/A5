package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventDao;
import dao.ShiftDAO;
import dto.CalEvent;
import dto.CalShift;

/**
 * Servlet implementation class CalendarServlet
 */

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	//userテーブルのtenchou_flagが1の場合tencho_calendar.jspへ。0の場合baito_calnedar.jspへ。
    	
    	
//        Map<String, Integer> shiftData = new HashMap<>();
//        Map<String, Integer> eventData = new HashMap<>();

        	//シフト件数を日付ごとに取得
//            String shiftQuery = "SELECT shift_date, COUNT(shift_id) AS shift_count FROM shift GROUP BY shift_date";
//            //データベースにクエリを送るためのStatementを作成し、shiftQueryを実行、結果はResultSetに返ってくる
//            try (Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(shiftQuery)) {
//            	//ResultSetに次の行がある限りループ
//                while (rs.next()) {
//                	//Map<String, Integer> 型のshiftDataに保存
//                    shiftData.put(rs.getString("shift_date"), rs.getInt("shift_count"));
//                }
//            }
            ShiftDAO sdao = new ShiftDAO();
            List<CalShift> slist = sdao.getShift();
            
            //consoleにslistの中身を表示
            for (CalShift cs : slist) {
                System.out.println("日付" + cs.getShiftData() + ", 件数" + cs.getCount());
            }
            Map<String, Integer> shiftMap = new TreeMap<>(); // TreeMapで日付順にもできて便利
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            int sum = 0;
            for (CalShift cs : slist) {
                sum += cs.getCount();
                String dateStr = sdf.format(cs.getShiftData());
                shiftMap.put(dateStr, sum);
            }
            
            //確認
            
            
            //jspが見えるところにセット
            request.setAttribute("shiftMap", shiftMap);
            request.setAttribute("calShift", slist);

//            //イベント件数を日付ごとに取得
//            String eventQuery = "SELECT event_date, COUNT(event_id) AS event_count FROM event GROUP BY event_date";
//            try (Statement stmt = conn.createStatement();
//                 ResultSet rs = stmt.executeQuery(eventQuery)) {
//                while (rs.next()) {
//                    eventData.put(rs.getString("event_date"), rs.getInt("event_count"));
//                }
//            }
            EventDao edao = new EventDao();
            List<CalEvent> elist = edao.getEvent();
            
            //consoleにelistの中身を表示
            for (CalEvent ce : elist) {
                System.out.println("日付" + ce.getEventData() + ", 件数" + ce.getCount());
            }
            
            Map<String, Integer> eventMap = new TreeMap<>();
           

            int eSum = 0;
            for (CalEvent ce : elist) {
                eSum += ce.getCount();
                String dateStr = sdf.format(ce.getEventData());
                eventMap.put(dateStr, eSum);
            }
            
            //jspが見えるところにセット
            request.setAttribute("eventMap", eventMap);
            request.setAttribute("calEvent", elist);
            
            

        
        //取得したデータをjspに渡す
            
          //consoleにshiftDataの中身を表示
//            for(Entry<String, Integer> list : shiftData.entrySet()) {
//            	System.out.println("shiftDataの中身"+list);
//            }
           
            
//        request.setAttribute("shiftData", shiftData);
//        request.setAttribute("eventData", eventData);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_calendar.jsp");
        dispatcher.forward(request, response);
    }
}
