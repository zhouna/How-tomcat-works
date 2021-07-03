package C04;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServletProcessor {

    private static String SERVLET_PATH = "file:/Users/nazhou/workspace/java/How-tomcat-works/target/classes";
    private static Map<String, Servlet> servletMap = new ConcurrentHashMap<>();
    private static URLClassLoader urlClassLoader;

    static {
        try {
            URL[] urls = new URL[1];
            urls[0] = new URL(SERVLET_PATH);
            urlClassLoader = new URLClassLoader(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void process(Request request, Response response) throws ServletException, IOException {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf('/') + 1);
        Servlet servlet = (Servlet) loadServlet("C04." + servletName);
        servlet.service(request, response);
    }

    private static Object loadServlet(String servletName) {
        if (!servletMap.containsKey(servletName)) {
            try {
                Class<?> aClass = urlClassLoader.loadClass(servletName);
                Servlet o = (Servlet) aClass.newInstance();
                o.init(null);
                servletMap.put(servletName, o);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return servletMap.get(servletName);
    }
}
