package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ShiftDao;
import dto.UserShift;

@WebServlet("/ShiftCheckServlet")
public class ShiftCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ShiftDao shiftDao = new ShiftDao();
        List<UserShift> shiftList = shiftDao.selectAll();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(shiftList); // ← JSON文字列に変換
        request.setAttribute("shiftList", json);
		// シフト確認ページ（店長）にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_shift.jsp");
		dispatcher.forward(request, response);
	}
}
