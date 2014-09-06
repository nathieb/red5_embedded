package com.red5;


import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

/**
 * Welcome page servlet
 */
public class Servlet1 extends HttpServlet  {

    @Override
	  public void doGet (HttpServletRequest hreq, HttpServletResponse hres)
	    throws ServletException, IOException {
            String page = "<html><head><title>Embedded Tomcat Example</title></head>"
                    + "<body>"
                    + "<script type='text/javascript'>"
                    + "function reallysure() { return confirm('Are you damn sure?'); }"
                    + "</script>"
                    + "<h1>Welcome to your embedded web server!</h1>"
                    + "<form method='POST' action='/shutdown'>"
                    + "To stop the server, click on stop<br/>"
                    + "<input type='submit' value='Stop' onclick='return reallysure()'/>"
                    + "</form></body></html>";
            OutputStream out = hres.getOutputStream();
	    out.write(page.getBytes());
	    out.close();
	  }
}		

