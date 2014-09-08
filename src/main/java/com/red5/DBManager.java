package com.red5;

/**
 *
 * @author olivier
 */

public class DBManager {

final String dbLocation = "c:\\temp\\"; // change it to your db location
org.hsqldb.server.Server sonicServer;
Connection dbConn = null;

public void startDBServer() {
    HsqlProperties props = new HsqlProperties();
    props.setProperty("server.database.0", "file:" + dbLocation + "mydb;");
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