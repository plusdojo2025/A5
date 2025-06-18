package servlet;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

/**
 * Servlet implementation class UpdateDeleteServlet
 */
@WebServlet("/UserManageServlet")
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	//画面表示：ユーザー登録画面へ
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// ログインしていなかったらログインサーブレットにリダイレクトする（ログイン画面に戻る）
			HttpSession session = request.getSession();
			if (session.getAttribute("id") == null) {
				response.sendRedirect("/webapp/LoginServlet");
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				
				String name = request.getParameter("name");
				String password = request.getParameter("password");

		// 更新または削除を行う
		UserDAO bDao = new UserDAO();
		if (request.getParameter("submit").equals("更新")) {
			if (bDao.update(new Bc(ID,name, password))) { // 更新成功
				request.setAttribute("result", new Result("更新が成功しました", "正常に動作しました", "/webapp/MenuServlet"));
			} else { // 更新失敗
				request.setAttribute("result", new Result("更新ができませんでした", "正常に動作しませんでした", "/webapp/MenuServlet"));
			}
		} else {
			if (bDao.delete(new Bc(ID,name,password))) { // 削除成功
				request.setAttribute("result", new Result("削除が成功しました", "正常に動作しました", "/webapp/MenuServlet"));
			} else { // 削除失敗
				request.setAttribute("result", new Result("削除が失敗しました", "正常に動作しませんでした", "/webapp/MenuServlet"));
			}
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_user_edit.jsp");
		dispatcher.forward(request, response);
	}
}
