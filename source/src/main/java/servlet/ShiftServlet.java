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
		ShiftDao sDao = new ShiftDao();
		List<UserShift> sList = sDao.selectAll();
		request.setAttribute("sList", sList);
		System.out.println("aiu");
		
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
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("startTime2");
		if(name.equals("")) System.out.println("aiu");
		// ログイン処理を行う
		/*
		IdPwDAO iDao = new IdPwDAO();
		if (iDao.isLoginOK(new IdPw(id, pw))) { // ログイン成功
			// セッションスコープにIDを格納する
			HttpSession session = request.getSession();
			session.setAttribute("id", new LoginUser(id));
			session.setAttribute("user", iDao.holdName(new LoginUser(id)));
			
			// メニューサーブレットにリダイレクトする
			response.sendRedirect("/webapp/home");
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			request.setAttribute("errMes", "IDかPASSWORDが間違っています！");
			
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}*/
	}
}