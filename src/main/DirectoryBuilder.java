package main;

import java.io.File;

public class DirectoryBuilder {
    public static String build(String route, String path) {
        StringBuilder body = new StringBuilder();
        File directory;

        if (path.equals("/")) {
            directory = new File(Server.getDirectory()).getAbsoluteFile();
        } else {
            directory = new File(Server.getDirectory() + path).getAbsoluteFile();
        }

        File[] listOfFiles = directory.listFiles();

        for(File file : listOfFiles) {
            if (file.isFile() || file.isDirectory()) {
                String relativeFilePath = file.toString().substring(Server.getDirectory().length());
                body.append("<html><body>");
                body.append("<a href=" + (route + relativeFilePath) + ">" + relativeFilePath + "</a>").append("<br>");
                body.append("</body></html>");
            }
        }

        return body.toString();
    }
}
