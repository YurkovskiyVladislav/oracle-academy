package oracle.academy;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@MultipartConfig
public class Uploader extends HttpServlet {
    private String uploadPath;
    private boolean exception = false;
    private boolean info = false;
    private List<String> exceptionMessages;
    private List<String> infoMessages;
    private List<String> uploadedImg;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        findUploadedImg();
        request.setAttribute("uploadedImg", uploadedImg);
        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        exceptionMessages = new ArrayList<>();
        infoMessages = new ArrayList<>();
        Collection<Part> parts;

        try {
            parts = request.getParts();
            for (Part part : parts) {
                if (isPartAnImage(part)) {
                    saveFile(part);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        findUploadedImg();
        request.setAttribute("exception", exception);
        request.setAttribute("info", info);
        request.setAttribute("exceptionMessages", exceptionMessages);
        request.setAttribute("infoMessages", infoMessages);
        request.setAttribute("uploadedImg", uploadedImg);


        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }


    private boolean isPartAnImage(Part part) {
        if (part != null && part.getName().equals("file")) {
            switch (part.getContentType()) {
                case "image/jpeg":
                case "image/png":
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    private void saveFile(Part part) {
        String path = getUploadPath();
//        String fileName = part.getSubmittedFileName();
        String fileName = String.valueOf(System.currentTimeMillis());

        checkDir(path);

        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(
                             new File(
                                     path + File.separator + System.currentTimeMillis() + fileName));
             InputStream filecontent = part.getInputStream()) {

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
            info = true;
            infoMessages.add(fileName);
        } catch (IOException e) {
            exception = true;
            exceptionMessages.add(e.getMessage());
        }
    }


    private void checkDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void findUploadedImg() {
        uploadedImg = new ArrayList<>();
        File dir = new File(getUploadPath());
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                String imgPath = "show?id=" + file.getName();
                uploadedImg.add(imgPath);
            }
        }
    }

    private String getUploadPath() {
        if (uploadPath == null) {
            uploadPath = getServletContext().getInitParameter("folder");
        }
        return uploadPath;
    }

}