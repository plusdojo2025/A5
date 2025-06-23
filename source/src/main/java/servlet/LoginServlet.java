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

import dao.UserDAO;
import dto.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
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
		String userName = request.getParameter("user");
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
			session.setAttribute("name", loginUserName);
			session.setAttribute("flag", loginUserFlag);
			
			response.sendRedirect(request.getContextPath() + "/EventServlet");
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			// request.setAttribute("result", new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/webapp/LoginServlet"));
			
			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
