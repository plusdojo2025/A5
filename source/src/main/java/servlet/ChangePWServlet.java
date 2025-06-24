package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dto.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ChangePWServlet")
public class ChangePWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_pw_change.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false); // false：セッションがなければ null を返す

	    User sessionUser = (session != null) ? (User) session.getAttribute("user") : null;

	    if (sessionUser == null) {
	        // ログインしていない or セッション切れ → ログインページへリダイレクト
	        response.sendRedirect(request.getContextPath() + "/LoginServlet");
	        return;
	    }
		//HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int id = sessionUser.getId();
		String curPw = request.getParameter("curPw");
		String newPw1 = request.getParameter("newPw1");
		String newPw2 = request.getParameter("newPw2");

		if (newPw1 == null || !newPw1.equals(newPw2)) {
		    request.setAttribute("error", "新しいパスワードが一致しません。");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_pw_change.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		
		UserDao dao = new UserDao();
		//User user =new User();
		user.setPw(curPw);
		user.setPw(newPw1);
		user.setPw(newPw2);
		user.setId(id);
		
		boolean ans = dao.pwupdate(user);
		
		if(ans != true) {
			//変更できたときの処理
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
		}else {
			//変更できなかったときの処理
			request.setAttribute("error", "パスワード変更に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_pw_change.jsp");
			dispatcher.forward(request, response);
		}
		
		

		
		
		
		
		
	
	
}
	
}

