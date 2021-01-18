package de.alfigeralf.pvptraining.reportsystem;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import de.alfigeralf.pvptraining.reportsystem.commands.CMD_Jump;
import de.alfigeralf.pvptraining.reportsystem.commands.CMD_Login;
import de.alfigeralf.pvptraining.reportsystem.commands.CMD_Report;

import de.alfigeralf.pvptraining.reportsystem.file.ConfigYML;
import de.alfigeralf.pvptraining.reportsystem.mysql.MySQL;
import net.md_5.bungee.BungeeCord;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;



import java.util.ArrayList;

public class ReportSystem extends Plugin {

    public static ArrayList<String> reports;
    public static ReportSystem instance;


    public void onEnable(){
        ConfigYML.createFile();
        MySQL.connect();
        MySQL.createTables();



        final PluginManager pm = BungeeCord.getInstance().getPluginManager();
        pm.registerCommand((Plugin)this, (Command)new CMD_Login("login"));
        pm.registerCommand((Plugin)this, (Command)new CMD_Jump("Jump"));
        pm.registerCommand((Plugin)this, (Command)new CMD_Report("report"));



    }

   public ReportSystem(){
        instance = this;
   }

   public static ReportSystem getInstance(){
       return instance;
    }

}
