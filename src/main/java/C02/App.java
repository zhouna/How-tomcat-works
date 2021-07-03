package C02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try (ServerSocket serverSocket = new ServerSocket(7077)) {
            while (true) {
                Socket client = serverSocket.accept();
                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                // 请求
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    if (line.isEmpty()) break; // 注意！请求体以什么标志结束？isEmpty和null区别？
                }

                // 响应
                PrintWriter printWriter = new PrintWriter(out);
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type: text/html;charset=UTF-8");
                printWriter.println();
                printWriter.println("Hello Socket!");

                printWriter.close();
                in.close();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
