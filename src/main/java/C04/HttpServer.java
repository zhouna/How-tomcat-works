package C04;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static final String WEB_ROOT = "/Users/nazhou/workspace/java/How-tomcat-works/target/classes";

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    public void await() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7077);
        while (true) {
            try {
                Socket client = serverSocket.accept();

                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                Request request = new Request(in);
                Response response = new Response(out);
                response.setRequest(request);

                if (request.isServletRequest()) {
                    ServletProcessor.process(request, response);
                } else {
                    StaticResourceProcessor.process(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
