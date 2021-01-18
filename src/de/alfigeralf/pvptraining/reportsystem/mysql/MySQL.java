package de.alfigeralf.pvptraining.reportsystem.mysql;

import de.alfigeralf.pvptraining.reportsystem.file.ConfigYML;
import net.md_5.bungee.BungeeCord;

import java.sql.*;

public class MySQL {



    public static Connection con;

    @SuppressWarnings("deprecation")
    public static void connect() {
        if (!MySQL.isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + ConfigYML.getStringFromConfig("URL") + ":" +  ConfigYML.getStringFromConfig("Port") + "/" + ConfigYML.getStringFromConfig("Database") +"?autoReconnect=true", ConfigYML.getStringFromConfig("User"), ConfigYML.getStringFromConfig("Password"));
                BungeeCord.getInstance().getConsole().sendMessage("§aDie MySQL Verbindung wurde erfolgreich aufgebaut§8.");
            }
            catch (SQLException e) {
                BungeeCord.getInstance().getConsole().sendMessage("§cDie MySQL-Verbindung konnte nicht aufgebaut werden§8.");
            }
        }
    }

    @SuppressWarnings("deprecation")
    public static void close() {
        if (MySQL.isConnected()) {
            try {
                con.close();
                BungeeCord.getInstance().getConsole().sendMessage("§cDie MySQL Verbindung wurde erfolgreich geschlossen§8.");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        if (con != null) {
            return true;
        }
        return false;
    }

    public static void update(String query) {
        PreparedStatement ps = null;
        try {
            ps = MySQL.con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResult(String query) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MySQL.con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createTables() {
        if (MySQL.isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Notify (UUID varchar(200));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Report (Spielername VARCHAR(100), UUID VARCHAR(100), Reports INT(100))");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet query(String string) {
        // TODO Auto-generated method stub
        return null;
    }



}

