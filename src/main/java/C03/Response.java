package C03;

import java.io.*;

/**
 * @author zz
 * @ClassName: Response
 * @Description:
 * @date 2021/7/2 11:45
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    private OutputStream out;
    private Request request;

    public Response(OutputStream out) {
        this.out = out;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaicResource() throws IOException {
        String uri = request.getUri();
        File file = new File(SimpleStaticHttpServer.WEB_ROOT, uri);

        if (file.exists()) {
            StringBuffer sb = new StringBuffer();
            sb.append("HTTP/1.1 200 OK\r\n");
            sb.append("Content-Type: " + getContentType() + "\r\n");
            sb.append("\r\n");

            out.write(sb.toString().getBytes());

            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] b = new byte[BUFFER_SIZE];
            int n = fileInputStream.read(b);
            while (n != -1) {
                out.write(b, 0, n);
                n = fileInputStream.read(b);
            }
            out.close();
        } else {
            PrintWriter printWriter = new PrintWriter(out);
            printWriter.println("HTTP/1.1 400 OK");
            printWriter.println("Content-Type: text/html;charset=UTF-8");
            printWriter.println();
            printWriter.println(uri + " File not found!");
        }
    }

    private String getContentType() {
        String uri = request.getUri();
        String suffix = uri.substring(uri.lastIndexOf('.'));
        switch (suffix) {
            case ".html":
                return "Content-Type: text/html;charset=UTF-8";
            case ".ico":
                return "Content-Type: image/x-icon";
            default:
                return "Content-Type: text/plain";
        }
    }
}
