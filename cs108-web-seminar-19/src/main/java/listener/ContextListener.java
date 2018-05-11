package listener;

import db.dao.MetropolisDAO;
import db.MyDBInfo;
import db.pool.ConnectionPoolMeh;
import db.pool.DataSourceMeh;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is
           initialized(when the Web application is deployed).
           You can initialize servlet context related data here.
        */
        ServletContext sc = sce.getServletContext();

        try {
            DataSourceMeh mehPool = new ConnectionPoolMeh();
            mehPool.setDriver("com.mysql.cj.jdbc.Driver");
            mehPool.setUsername(MyDBInfo.MYSQL_USERNAME);
            mehPool.setPassword(MyDBInfo.MYSQL_PASSWORD);
            mehPool.setServer(MyDBInfo.MYSQL_DATABASE_SERVER);
            sc.setAttribute(ContextKey.CONNECTION_POOL, mehPool);

            MetropolisDAO dao = new MetropolisDAO(mehPool);
            sc.setAttribute(ContextKey.DAO, dao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is invoked when the Servlet Context
           (the Web application) is undeployed or
           Application Server shuts down.
        */
        ServletContext sc = sce.getServletContext();
        ConnectionPoolMeh mehPool = (ConnectionPoolMeh) sc.getAttribute(ContextKey.CONNECTION_POOL);
        mehPool.close();
    }

}
