package C03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zz
 * @ClassName: App
 * @Description:
 * @date 2021/7/2 11:43
 */
public class SimpleStaticHttpServer {

    public static final String WEB_ROOT = "D:\\study\\tomcat\\02\\src\\main\\resources";

    public static void main(String[] args) {
        SimpleStaticHttpServer server = new SimpleStaticHttpServer();
        server.await();
    }

    public void await() {
        try (ServerSocket serverSocket = new ServerSocket(7077)) {
            while (true) {
                Socket client = serverSocket.accept();
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                try {
                    // 请求
                    Request request = new Request(in);
                    request.parse();
                    // 响应
                    Response response = new Response(out);
                    response.setRequest(request);
                    response.sendStaicResource();

                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
