package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ManualServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MBまではメモリに保持
    maxFileSize = 1024 * 1024 * 50,  // 50MBまでのファイルを許可
    maxRequestSize = 1024 * 1024 * 100 // 100MBまでのリクエストを許可
)
public class ManualServlet extends HttpServlet {

    // ファイル保存ディレクトリ（サーバーのフォルダをパスで指定する
    private static final String UPLOAD_DIR = "A5/src/main/TestYouAgeAge";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    	System.out.println("ManualServlet doPost called");  // ここをテスト用として追加,サーバー側のコンソールにデバッグ用として出力させる
        response.setContentType("text/plain; charset=UTF-8");

        try {
            // 複数ファイルを受け取る
            for (Part part : request.getParts()) {
                if (part.getName().equals("file") && part.getSize() > 0) {
                    // ファイル名取得
                    String submittedFileName = getFileName(part);

                    // 保存先のファイルを作成
                    File uploads = new File(UPLOAD_DIR);
                    if (!uploads.exists()) uploads.mkdirs();

                    File file = new File(uploads, submittedFileName);

                    // ファイルの書き込み
                    try (InputStream input = part.getInputStream()) {
                        Files.copy(input, file.toPath());
                    }
                }
            }

            response.getWriter().write("ファイルアップロード成功");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("ファイルアップロード失敗: " + e.getMessage());
        }
    }

    // ファイル名をパースするヘルパー（headerの内容から）
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        if (contentDisposition == null) return "unknown";

        for (String cdPart : contentDisposition.split(";")) {
            if (cdPart.trim().startsWith("filename")) {
                String fileName = cdPart.substring(cdPart.indexOf('=') + 1).trim().replace("\"", "");
                // IEの場合パスが含まれることがあるので最後の部分だけ取り出す
                return fileName.substring(fileName.lastIndexOf(File.separator) + 1);
            }
        }
        return "unknown";
    }
}