package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ManualDao;
import dto.Manual;


@MultipartConfig(location = "C:\\plusdojo2025\\A5\\source\\src\\main\\webapp\\mt" // アップロードファイルの一時的な保存先
//    fileSizeThreshold = 1024 * 1024, // 1MBまではメモリに保持
//    maxFileSize = 1024 * 1024 * 50,  // 50MBまでのファイルを許可
//    maxRequestSize = 1024 * 1024 * 100 // 100MBまでのリクエストを許可
)
@WebServlet("/ManualServlet")
public class ManualServlet extends HttpServlet {
//	これ↓は要らないのかも？
//	private static final long serialVersionUID = 1L;

	
    @Override
    	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	request.setCharacterEncoding("UTF-8");
//    	ここから↓
    	HttpSession session = request.getSession();
    	String loginUser = (String) session.getAttribute("name");//ログイン時にセッションスコープに預けた名前を持ってくる
    	System.out.println("ログインユーザー名："+loginUser);
    	if (loginUser == null) {//もしログインしていなければログイン画面へ飛ばす
    		response.sendRedirect(request.getContextPath() + "/LoginServlet");
    		return;
	    }
	    
	    int flag = (Integer)session.getAttribute("flag"); //セッションスコープから取り出すとObject型なのでIntegerにキャストする
//	    loginUser.getFlag();
	    System.out.println("ログインフラグ："+flag);
	    
	    if (flag == 0) {//もしflagが0つまり店員であれば店員用のマニュアル閲覧画面へ飛ばす
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_manual.jsp");
	    	dispatcher.forward(request, response);
	    	return;
	    }
	    else if (flag == 1){//もしflagが1つまり店長であれば店長用のマニュアル管理画面へ飛ばす
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_manual.jsp");
	        dispatcher.forward(request, response);}
//    	ここまで↑フラグ別にページを分ける試み、完了↑
	    
//	    ここから↓
	    
	    ManualDao dao = new ManualDao();
	    try {
	        List<Manual> manualList = dao.findAll();
	        request.setAttribute("manualList", manualList);
	        if (flag == 0) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_manual.jsp");
	        dispatcher.forward(request, response);}
	        else if(flag == 1) { 
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_manual.jsp");
	        dispatcher.forward(request, response);}
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    }
	    
//	    ここまで↑テーブル全取得からの一覧表示に必要,分岐も上記同様にこれで行けるはず
	    
//    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_manual.jsp");
//        dispatcher.forward(request, response);
        }
    	//店長用画面に遷移する↑。IfでFlagが0なら店員用に飛ばす必要がある、どうやってやるんだ←完了
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	request.setCharacterEncoding("UTF-8");
//		これら↑が無いと、アップロード後に文字化けが発生する！
    	System.out.println("ManualServlet doPost called");
//      ここから↓
    	Part part = request.getPart("file"); // getPartで取得
    	System.out.println(part+"aaaaaaaa");//
    	String image = this.getFileName(part);
    	request.setAttribute("image", image);
    	// サーバの指定のファイルパスへファイルを保存
        //場所はクラス名↑の上に指定してある（"C:\\pleiades\\workspace\\a5\\webapp\\img"）を参照している
    	part.write(image);
    	//ここでwebapp内のimgにimageをの中身自体を保存。
    	//名前の文字列をDBに保存するならこの後にDaoに画像を渡す処理が入る↓
//		ここまで↑先生からのやつ

//      		リクエストパラメータの取得？必要かな？これわっかんね～保留！
//           	request.setCharacterEncoding("UTF-8");
//           	String manual_file = request.getParameter("manual_file");
           	String manual_file = image;
//           	request.setCharacterEncoding("UTF-8");
           	int importance = Integer.parseInt(request.getParameter("importance"));
           	Date date_up = new Date(System.currentTimeMillis());
//           	Date date_up = new Date () ;request.getParameter("date_up");// ←jspにdate_upのname属性のものがないからnullになっちゃうかも
//送る側なのでオートインクリメントのファイルIDはここで書く必要ないかも→int file_id = Integer.parseInt(request.getParameter("file_id"));
//            
//            登録の処理を行いたい、名刺管理をベースにやってみるなう
           	ManualDao MDao = new ManualDao();

            try { if (MDao.insert(new Manual(manual_file, importance, date_up, 0))) {    
                	response.getWriter().write("ファイルアップロード成功");
                	}
        		} catch (Exception e) {
        			System.out.println("例外発生時に出るメッセージだぞ");
        			e.printStackTrace(); // サーバーログには詳細を出すが、クライアント側には出さないようにしたい
        			response.getWriter().write("アップロードに失敗しました。管理者にお問い合わせください。");
        			
        		}
            HttpSession session = request.getSession();
            int flag = (Integer)session.getAttribute("flag"); //セッションスコープから取り出すとObject型なのでIntegerにキャストする
//    	    loginUser.getFlag();
    	    System.out.println("ログインフラグ："+flag);
    	    
            ManualDao dao = new ManualDao();
    	    try {
    	        List<Manual> manualList = dao.findAll();
    	        request.setAttribute("manualList", manualList);
    	        if (flag == 0) {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/baito_manual.jsp");
    	        dispatcher.forward(request, response);}
    	        else if(flag == 1) { 
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_manual.jsp");
    	        dispatcher.forward(request, response);}
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    	    }
    		}

//ここから↓
    private String getFileName(Part part) {
    	String cd = part.getHeader("content-disposition");
    	if (cd == null) return "unknown(ヘッダーが見つからなかったよ)";
    	for (String content : cd.split(";")) {
    		if (content.trim().startsWith("filename")) {
    			String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
    			return fileName.substring(fileName.lastIndexOf(File.separator) + 1);
    		}
    	}
    	return "unknown(ファイルが見つからなかったデフォ値だよ)";
    	}
    
	}
//ここまで↑もらったやつの一番下の部分のコードとやってることは同じっぽい