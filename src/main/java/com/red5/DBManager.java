package com.red5;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import org.hsqldb.persist.HsqlProperties;

/**
 *
 * @author olivier
 */
public class DBManager {

    String dbLocation; // change it to your db location
    org.hsqldb.server.Server sonicServer;
    Connection dbConn = null;
    private String db;

    public void startDBServer(String location, String db) {
        this.dbLocation = location;
        this.db = db;
        HsqlProperties props = new HsqlProperties();
        props.setProperty("server.database.0", "file:" + dbLocation + File.separator + this.db+";");
        props.setProperty("server.dbname.0", "xdb");
        sonicServer = new org.hsqldb.Server();
        try {
            sonicServer.setProperties(props);
        } catch (Exception e) {
            return;
        }
        sonicServer.start();
    }

    public void stopDBServer() {
        sonicServer.shutdown();
    }

    public Connection getDBConn() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            dbConn = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}
