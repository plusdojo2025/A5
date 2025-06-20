package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 店長ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_event.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("username");
		String pw = request.getParameter("pw");

		// ログイン処理を行う
		UserDAO uDao = new UserDAO();
		User user = new User();
		user.setName(userName);
		user.setPw(pw);
		
		List<User> loginUser = uDao.login(user);
		if (loginUser != null) { // ログイン成功
			// セッションスコープにユーザーネーム・店長フラグを格納する
			HttpSession session = request.getSession();
			String loginUserName = (loginUser.get(0)).getName();
			int loginUserFlag = (loginUser.get(0)).getFlag();
			session.setAttribute("user", loginUserName);
			session.setAttribute("flag", loginUserFlag);
			
			// 日時を取得しセッションスコープに格納する
			Date now = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String formatDate = date.format(now);
			session.setAttribute("date", formatDate);
			
			
			response.sendRedirect("/webapp/Servlet");
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			// request.setAttribute("result", new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/webapp/LoginServlet"));
			
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		}
	}
	*/
}
