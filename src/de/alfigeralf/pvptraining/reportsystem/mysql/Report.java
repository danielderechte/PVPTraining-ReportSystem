package de.alfigeralf.pvptraining.reportsystem.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report {

    public static Integer addReport(String uuid, int zeit){
        if(zeit < 0){
            zeit = 0;
        }
        int amount = zeit + getReports(uuid);
        MySQL.update("UPDATE Report SET Reports='" + amount + "' WHERE UUID='" + uuid + "'");
        return 0;
    }

    public static Integer getReports(String uuid){
        ResultSet rs = MySQL.getResult("SELECT * FROM Report WHERE UUID='" + uuid + "'");
        try {
            while(rs.next()){
                return rs.getInt("Reports");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static boolean inList(String uuid) {
        ResultSet rs = MySQL.getResult("SELECT * FROM Report WHERE UUID='" + uuid + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void addPlayer(String player, String uuid){
        MySQL.update("INSERT INTO Report (Spielername, UUID, Reports) VALUES ('" + player + "', '" + uuid
                + "', '0')");
    }

}
