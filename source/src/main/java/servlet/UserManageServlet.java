package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import org.apache.tomcat.jni.User;*/

import dao.UserDAO;
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
		
		
				
				response.setCharacterEncoding("UTF-8");
				HttpSession session =request.getSession();

				if (session.getAttribute("id") == null) {
					request.setAttribute("message", "ログインしてください。");
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				

				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String pw = request.getParameter("pw");
				String flag = request.getParameter("flag");
				String action = request.getParameter("action"); // "更新" or "削除"

				UserDAO dao = new UserDAO();
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

				// JSPにフォワード（画面遷移）
				request.getRequestDispatcher("user_manage.jsp").forward(request, response);
			}
		}
