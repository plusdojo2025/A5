package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dto.User;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
				//入力値の文字コードをutf-8にする
				request.setCharacterEncoding("UTF-8");
				
				
				response.setCharacterEncoding("UTF-8");
				HttpSession session =request.getSession();

//				if (session.getAttribute("id") == null) {
//					request.setAttribute("message", "ログインしてください。");
//					request.getRequestDispatcher("login.jsp").forward(request, response);
//					return;
//				}
				

				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String pw = request.getParameter("pw");
				String flag = request.getParameter("flag");
				String action = request.getParameter("action"); // "更新" or "削除"

				System.out.println(id+","+name+","+pw+","+flag+","+action);
				UserDao dao = new UserDao();
				boolean result = false;

				if ("更新".equals(action)) {
					result = dao.update(new User(Integer.parseInt(id), pw, name, Integer.parseInt(flag)));
				} else if ("削除".equals(action)) {
					result = dao.delete(new User(Integer.parseInt(id), pw, name, Integer.parseInt(flag)));
				}
				if (result) {
					request.setAttribute("message", action + "しました。");
				
				} else {
					request.setAttribute("message", action + "に失敗しました。");
				}
				// 一覧取得してJSPへ渡す
				request.setAttribute("userList", dao.selectAll());

				// JSPにフォワード
				request.getRequestDispatcher("/WEB-INF/jsp/tencho_user_edit.jsp").forward(request, response);
				}
	
				
		}



