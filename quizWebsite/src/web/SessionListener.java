package web;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
    	arg0.getSession().setAttribute("isLoggedIn", new Boolean(false));
    	arg0.getSession().setAttribute("isLookingUp", new Boolean(false));
    	arg0.getSession().setAttribute("counterFriendRequestExists", new Boolean(false));
    	arg0.getSession().setAttribute("friendRequestExists", new Boolean(false));
    	arg0.getSession().setAttribute("goingToAdminPanel", true);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
