package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ManualDao;
import dto.Manual;

@WebServlet("/ManualServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MBまではメモリに保持
    maxFileSize = 1024 * 1024 * 50,  // 50MBまでのファイルを許可
    maxRequestSize = 1024 * 1024 * 100 // 100MBまでのリクエストを許可
)
public class ManualServlet extends HttpServlet {

    // ファイル保存ディレクトリ（サーバーのフォルダをパスで指定する
//    private static final String UPLOAD_DIR = "A5/src/main/TestYouAgeAge";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/tencho_manual.jsp");
        dispatcher.forward(request, response);

    }//店長用画面に遷移する↑。IfでFlagが0なら店員用に飛ばす必要がある、どうやってやるんだ
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        System.out.println("ManualServlet doPost called");
        response.setContentType("text/plain; charset=UTF-8");
        // 保存先は Tomcat内の /uploads にするやで
        String uploadPath = getServletContext().getRealPath("/uploads");
        File uploads = new File(uploadPath);
        if (!uploads.exists()) uploads.mkdirs();
        try {
            for (Part part : request.getParts()) {
                if ("file".equals(part.getName()) && part.getSize() > 0) {
                    String fileName = getFileName(part);
                    File file = new File(uploads, fileName);
                    try (InputStream input = part.getInputStream()) {
                        Files.copy(input, file.toPath());
                    }
                }
            }
        
//            リクエストパラメータの取得？必要かな？
            request.setCharacterEncoding("UTF-8");
            String manual_file = request.getParameter("manual_file");
            int importance = Integer.parseInt(request.getParameter("importance"));
            String date_up = request.getParameter("date_up");
//            送る側なので要らないかも→int file_id = Integer.parseInt(request.getParameter("file_id"));
//            
//            登録の処理を行いたい、名刺管理をベースにやってみるなう
            ManualDao MDao = new ManualDao();

                if (MDao.insert(new Manual(manual_file, importance, date_up, 0))) {    
                    response.getWriter().write("ファイルアップロード成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("ファイルアップロード失敗: " + e.getMessage());}
}

        
            
    
  
    private String getFileName(Part part) {
        String cd = part.getHeader("content-disposition");
        if (cd == null) return "unknown";
        for (String content : cd.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf(File.separator) + 1);
            }
        }
        return "unknown";
    }
}
