package C01;

import java.io.*;
import java.net.Socket;

/**
 * @author zz
 * @ClassName: App
 * @Description:
 * @date 2021/7/2 11:35
 */
public class App {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("47.95.172.218", 80);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        PrintWriter writer = new PrintWriter(out);
        writer.println("GET /v100/indexlist.html HTTP/1.1");
        writer.println("Host: testxhnewsapi.xinhuaxmt.com");
        writer.println("Connection: keep-alive");
        writer.println("Cache-Control: no-cache");
        writer.println("Upgrade-Insecure-Requests: 1");
        writer.println("User-Agent: Mozilla/5.0 (Linux; U; Android 4.3; en-us; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
        writer.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        writer.println("Accept-Encoding: gzip, deflate"); // 是否压缩
        writer.println("Accept-Language: zh-CN,zh;q=0.9");
        writer.println();
        writer.flush();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"))) {
            String t;
            while ((t = reader.readLine()) != null) {
                System.out.println(t);
            }
        }
        out.close();
        in.close();
        socket.close();
    }
}
