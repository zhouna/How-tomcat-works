package C03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zz
 * @ClassName: Request
 * @Description:
 * @date 2021/7/2 11:45
 */
public class Request {
    private InputStream in;
    private String uri;

    public Request(InputStream in) {
        this.in = in;
    }

    public void parse() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        this.uri = parseUri(line);
    }

    private String parseUri(String line) {
        if (line == null) return null;
        int index1, index2;
        index1 = line.indexOf(' ');
        if (index1 != -1) {
            index2 = line.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return line.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String line = "GET /index.html HTTP/1.1";
        int index1, index2;
        index1 = line.indexOf(' ');
        if (index1 != -1) {
            index2 = line.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                System.out.println(line.substring(index1 + 1, index2));
            }
        }
    }

    public String getUri() {
        return uri;
    }
}
