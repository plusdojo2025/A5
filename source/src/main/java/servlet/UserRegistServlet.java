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

import dao.BcDAO;
import dto.Bc;

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
	//画面表示：ユーザー登録画面へ
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
		String role = request.getParameter("employees");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//リクエストパラメータのチェック
		if(role.equals("0")) {role="店長";}
		else if(role.equals("1")) {role="店員";}

		// 登録処理を行う
				BcDAO bDao = new BcDAO(); 
				Bc newUser =new Bc(role,name,password);
				List<Bc> userList = bDao.select(newUser); //↓短縮バージョン

				//List<Bc> cardList = bDao.select(new Bc(0, company,department,position ,name,phone,email, zipcode,address));

				// 登録結果をリクエストスコープに格納する
				request.setAttribute("userList", userList);

				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_user_edit.jsp");
				dispatcher.forward(request, response);
			}
		}

