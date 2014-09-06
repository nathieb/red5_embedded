package com.red5;


import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

/**
 * Welcome page servlet
 */
public class MediaServer extends HttpServlet  {

    @Override
	  public void doGet (HttpServletRequest hreq, HttpServletResponse hres)
	    throws ServletException, IOException {
            String page = "<html><head><title>Server mutimédia CISAD</title></head>"
                    + "<body>"
                    + "<script type='text/javascript'>"
                    + "function reallysure() { return confirm('Confirmer ?'); }"
                    + "</script>"
                    + "<h1>Démarrer le téléchargement !</h1>"
                    + "<form method='POST' action='/download'>"
                    + "To stop the server, click on stop<br/>"
                    + "<input type='submit' value='Download' onclick='return reallysure()'/>"
                    + "</form></body></html>";
            OutputStream out = hres.getOutputStream();
	    out.write(page.getBytes());
	    out.close();
	  }
}		

