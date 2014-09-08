package com.red5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author olivier Thiébaut
 */

public class WFProcess extends Thread {

DBManager dbm = new DBManager();

public static void main(String[] args) {
    (new WFProcess()).start();
}

public void run() {
    String location = System.getProperty("java.io.tmpdir");
    String db = "LSELite";
    dbm.startDBServer(location,db);

    // some usefull server work here
    Connection conn = dbm.getDBConn();
    try {
        Statement stmt = conn.createStatement();
        stmt.executeQuery("CREATE TABLE IF NOT EXISTS answers (num INT IDENTITY, answer VARCHAR(250))");
        stmt.executeQuery("INSERT INTO answers (answer) values ('this is a new answer')");
        ResultSet rs = stmt.executeQuery("SELECT num, answer FROM answers");
        while (rs.next()) {
            System.out.println("Answer number: " + rs.getString("num")
                    + "; answer text: " + rs.getString("answer"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    // end of usefull server work

    dbm.stopDBServer();
}
}