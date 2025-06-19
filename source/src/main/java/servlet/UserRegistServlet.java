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
 * Servlet implementation class RegistServlet
 */
@WebServlet("/UserRegistServlet")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//画面表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインしていなかったらログインサーブレットにリダイレクトする（ログイン画面に戻る）
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect(request.getContextPath() + "/LoginServlet");
//			return;
//		}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_user_reg.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		Integer tencho_flag =Integer.parseInt(request.getParameter("tencho_flag"));
		
		

		// 登録処理を行う
				UserDAO dao = new UserDAO(); 
				User newUser =new User();
				newUser.setName(user_name);
				newUser.setPw(password);
				newUser.setFlag(tencho_flag);
				
				boolean result = dao.insert(newUser); //↓短縮バージョン

				if(result==true) {
					List<User> userList = dao.selectAll();
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_user_edit.jsp");
					dispatcher.forward(request, response);
					
				}else {
					request.setAttribute("errMsg","登録できませんでした。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_user_edit.jsp");
					dispatcher.forward(request, response);
				}
				//List<Bc> cardList = bDao.select(new Bc(0, company,department,position ,name,phone,email, zipcode,address));

				
				
			}
		}

