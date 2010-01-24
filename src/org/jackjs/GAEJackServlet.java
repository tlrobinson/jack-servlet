package org.jackjs;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.TimeZone;

@SuppressWarnings("serial")
public class GAEJackServlet extends JackServlet {

    public void init(ServletConfig config) throws ServletException {
        // Set the timezone
        String tz = config.getInitParameter("timezone");
        if (tz != null) {
            TimeZone.setDefault(TimeZone.getTimeZone(tz));
        }
    
        super.init(config);
    }

}
