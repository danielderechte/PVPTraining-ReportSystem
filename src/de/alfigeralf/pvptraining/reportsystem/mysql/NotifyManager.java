package de.alfigeralf.pvptraining.reportsystem.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NotifyManager {
    public static void remove(final String uuid) {
        MySQL.update("DELETE FROM Notify WHERE UUID= '" + uuid + "';");
    }

    public static void add(final String uuid) {
        MySQL.update("INSERT INTO Notify (uuid) VALUES ('" + uuid + "')");
    }

    public static boolean isExists(final String uuid) {
        try {
            final ResultSet rs = MySQL.getResult("SELECT * FROM Notify WHERE UUID= '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
