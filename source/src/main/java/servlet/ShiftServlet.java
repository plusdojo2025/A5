package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ShiftDao;
import dto.UserShift;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/ShiftServlet")
public class ShiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("name") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}
		
		// データベースに保存してあるシフトの情報を全部持ってくる
		/*
		ShiftDao sDao = new ShiftDao();
		List<UserShift> sList = sDao.selectAll();
		request.setAttribute("sList", sList);
		System.out.println("aiu");
		*/
		ShiftDao shiftDao = new ShiftDao();
        List<UserShift> shiftList = shiftDao.selectAll();
		
		
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(shiftList); // ← JSON文字列に変換
        request.setAttribute("shiftList", json);
		
		// 店長フラグによる移動先ページの切り替え
		int flag = (Integer)session.getAttribute("flag");
		if (flag == 1) {
			// シフト確認ページ（店長）にフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_shift.jsp");
			dispatcher.forward(request, response);
		} else {
			// シフト確認ページ（店長）にフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_shift.jsp");
			dispatcher.forward(request, response);
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("name") == null) {
			response.sendRedirect(request.getContextPath() +"/LoginServlet");
			return;
		}
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		ShiftDao shiftDao = new ShiftDao();
		//String startTime = "startTime" + 2;
		//System.out.println(startTime);
		/*
		System.out.println(request.getParameter("nissu"));
		int niss = Integer.parseInt(request.getParameter("nissu"));
		
		for(int i = 1; i <= niss; i++) {
			String sTime = "startTime" + i;
			sTime = request.getParameter(sTime);
			String eTime = "endTime" + i;
			eTime = request.getParameter(eTime);
			if(sTime != null && !sTime.isEmpty() && eTime != null && !eTime.isEmpty()) {
				java.util.Date utilDate = new java.util.Date();
				utilDate.setMonth(utilDate.getMonth() + 1);
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

				if (shiftDao.insert(new UserShift(date, sTime, eTime, name))) { // 登録成功
					System.out.println("OK");
				} else { // 登録失敗
					System.out.println("NG");
				}
			}
		}
		//String name = request.getParameter(startTime);
		//if(name.equals("")) System.out.println("aiu");
		 * */
		
		
		
		List<UserShift> shiftList = shiftDao.selectAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(shiftList); // ← JSON文字列に変換
        request.setAttribute("shiftList", json);
		int flag = (Integer)session.getAttribute("flag");
		if (flag == 1) {
			// シフト確認ページ（店長）にフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_shift.jsp");
			dispatcher.forward(request, response);
		} else {
			// シフト確認ページ（店長）にフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_shift.jsp");
			dispatcher.forward(request, response);
		}
	}
}