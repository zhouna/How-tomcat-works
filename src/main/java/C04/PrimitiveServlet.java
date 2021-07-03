package C04;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zz
 * @ClassName: MyServlet
 * @Description:
 * @date 2021/7/2 14:59
 */
public class PrimitiveServlet implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service");
        PrintWriter out = servletResponse.getWriter();
        out.println("Hello. Roses are red");
        out.print("Violets are blue.");
        out.close();
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("destory");
    }
}
