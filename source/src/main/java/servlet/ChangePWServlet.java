package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
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
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("user");
		int id = sessionUser.getId();
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		
		
		UserDAO dao = new UserDAO();
		User user =new User();
		user.setPw(currentPassword);
		user.setPw(newPassword);
		user.setId(id);
		
		boolean ans = dao.pwupdate(user);
		
		if(ans != true) {
			//変更できたときの処理
			response.sendRedirect("/webapp/LoginServlet");
		}else {
			//変更できなかったときの処理
			request.setAttribute("error", "パスワード変更に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_pw_change.jsp");
			dispatcher.forward(request, response);
		}

		
		
		
		
		
	
	
}
	
}

