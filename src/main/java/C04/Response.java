package C04;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

public class Response implements ServletResponse {

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
        File file = new File(HttpServer.WEB_ROOT, uri);

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
            printWriter.close();
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        try {
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
        } catch (Exception e) {
            return "Content-Type: text/html;charset=UTF-8";
        }
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: " + getContentType());
        printWriter.println();
        return printWriter;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
