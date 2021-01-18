package de.alfigeralf.pvptraining.reportsystem.file;

import de.alfigeralf.pvptraining.reportsystem.ReportSystem;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigYML {

    private static java.io.File file;
    private static Configuration configuration;


    public Configuration getConfiguration(){
        return configuration;
    }

    public static void saveConfiguration(){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            BungeeCord.getInstance().getConsole().sendMessage("[DEBUG] Beim Speichern der Config ist ein Fehler aufgetreten!");
        }
    }

    public static void createFile(){
        try {
            if (!ReportSystem.getInstance().getDataFolder().exists()) ReportSystem.getInstance().getDataFolder().mkdir();

            file = new java.io.File(ReportSystem.getInstance().getDataFolder().getPath(), "mysql.yml");

            if (!file.exists()){
                file.createNewFile();
            }

            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);


        }catch (IOException e){
            BungeeCord.getInstance().getConsole().sendMessage("[DEBUG] Beim Erstellen der Config ist ein Fehler aufgetreten!");
        }

    }

    public static String getStringFromConfig(String path){
        return configuration.getString(path);
    }


}