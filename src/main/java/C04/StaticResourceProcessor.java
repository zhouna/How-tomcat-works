package C04;

import java.io.IOException;

public class StaticResourceProcessor {
    public static void process(Request request, Response response) throws IOException {
        response.sendStaicResource();
    }
}
