package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import dao.ImageDAO;

/**
 * Servlet implementation class UploadServlet
 */
@MultipartConfig(
  //アップロードされたファイルが一時的に保存されるディレクトリのパス。
  location="/tmp",
  //このサイズを超えるファイルは一時的にディスクに保存
  fileSizeThreshold=1024*1024,
  //アップロード可能なファイルの最大サイズ(byte単位）
  maxFileSize=1024*1024*5,
  //マルチパートリクエスト全体の最大サイズ(byte単位）
  maxRequestSize=1024*1024*5*5
)
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの指定
		request.setCharacterEncoding("UTF-8");
		//一番最初に起動した際のパス
		String url ="/WEB-INF/jsp/fileInput.jsp";

		if(request.getParameter("result")!=null) {
			String resutlNo = request.getParameter("result");
			if(resutlNo.equals("1")) {
				//DBから画像を全件取得してくる
				ImageDAO dao = new ImageDAO();
				ArrayList<byte[]> imageDataList =dao.getImageData();
		        //上記のデータを加工して下のArrayListに入れ替える
		        ArrayList<String> imageDataListString = new ArrayList<>();
		        for(byte[] image:imageDataList) {
		        	imageDataListString.add(Base64.getEncoder().encodeToString((byte[])image));
		        }
		        //いくつあるか確認（デバッグ）
		        System.out.println(imageDataListString.size());

		        //入れ替えたデータをリクエストスコープへセットする
			    request.setAttribute("imageDataListString", imageDataListString);
				url = "/WEB-INF/jsp/showImage.jsp";
			}
		}

    	// 検索ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストがmultipart/form-dataかどうかを確認
        if (!ServletFileUpload.isMultipartContent(request)) {
          throw new ServletException("Content type is not multipart/form-data");
        }

        try {
          // ServletFileUploadインスタンスを作成
          ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

          // リクエストからFileItemインスタンスのマップを取得
          Map<String, List<FileItem>> itemsMap = upload.parseParameterMap(request);

          for (List<FileItem> items : itemsMap.values()) {
            for (FileItem item : items) {
              // フォームフィールドではないもの（つまりファイル）だけを処理
              if (!item.isFormField()) {
                // ファイルをバイト配列に変換
                byte[] bytes = item.get();

                    // UDaoインスタンスを作成し、uploadPhotoメソッドにバイト配列を渡す
                    ImageDAO dao = new ImageDAO();
                    dao.saveImage(bytes);
                }
              }
          }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

	}

}
