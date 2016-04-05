package oracle.academy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShowImg extends HttpServlet {
    String uploadPath = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("id");
        byte[] imageBytes = getImgAsByteArray(getAbsPath(fileName));
        System.out.println(getAbsPath(fileName));

        response.setContentType("image/jpeg");
        response.setContentLength(imageBytes.length);

        BufferedOutputStream output = null;
        try {

            output = new BufferedOutputStream(response.getOutputStream(), imageBytes.length);
            output.write(imageBytes);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    private byte[] getImgAsByteArray(String imgName) {
        Path path = Paths.get(imgName);
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String getAbsPath(String fileName) {
        if (uploadPath == null) {
            uploadPath = getServletContext().getInitParameter("folder");
        }
        return uploadPath + File.separator + fileName;
    }
}
