package de.alfigeralf.pvptraining.reportsystem.commands;

import de.alfigeralf.pvptraining.reportsystem.mysql.NotifyManager;
import de.alfigeralf.pvptraining.reportsystem.mysql.Report;
import de.alfigeralf.pvptraining.reportsystem.utils.Manager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMD_Report extends Command
{
    public static String PREFIX;

    public CMD_Report(final String name) {
        super(name);
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            if (args.length == 0) {
                p.sendMessage(CMD_Report.PREFIX + "Benutze§8» §a/report §8(§aSPIELER§8) (§aGRUND§8)");
            }
            else if (args.length == 1) {
                p.sendMessage(CMD_Report.PREFIX + "Benutze§8» §a/report §8(§aSPIELER§8) (§aGRUND§8)");
                p.sendMessage(CMD_Report.PREFIX + "Gründe§8» §aHacking§8, §aTrolling§8,§aTeaming§8, §aSkin§8, §aName§8, §aBeleidigung§8, §aProvokation§8, §aChatverhalten§8, §aWerbung§8, §aNationalsozialismus§8, §aSpamming§8, §aDrohung");
            }
            else if (args[1].equalsIgnoreCase("Hacking") || args[1].equalsIgnoreCase("Bugusing") || args[1].equalsIgnoreCase("Trolling") || args[1].equalsIgnoreCase("Teaming") || args[1].equalsIgnoreCase("Skin") || args[1].equalsIgnoreCase("Name") || args[1].equalsIgnoreCase("Beleidigung") || args[1].equalsIgnoreCase("Provokation") || args[1].equalsIgnoreCase("Chatverhalten") || args[1].equalsIgnoreCase("Werbung") || args[1].equalsIgnoreCase("Nationalsozialismus") || args[1].equalsIgnoreCase("Spamming") || args[1].equalsIgnoreCase("Drohung")) {
                final ProxiedPlayer p2 = ProxyServer.getInstance().getPlayer(args[0]);
                if (p2 == null) {
                    p.sendMessage(CMD_Report.PREFIX + "Dieser Spieler ist nicht Online");
                    return;
                }
                p.sendMessage(CMD_Report.PREFIX + "§6Dein Report wird gleich bearbeitet");
                if (p2.getName().equalsIgnoreCase(p.getName())) {
                    p.sendMessage(CMD_Report.PREFIX + "Du kannst dich nicht selber reporten");
                    return;
                }
                for (final ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                    if (all.hasPermission("PVPpvptraining.report") && !NotifyManager.isExists(all.getUniqueId().toString())) {
                        final TextComponent msg2 = new TextComponent(CMD_Report.PREFIX + "§aAnnehmen");
                        msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Klicke hier um den Report §aanzunehmen§8.").create()));
                        msg2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Jump " + p2.getServer().getInfo().getName()));
                        all.sendMessage(CMD_Report.PREFIX + p2.getName() + " §8| §a" + args[1]);
                        all.sendMessage((BaseComponent)msg2);
                    }
                    if (Report.inList(p.getUniqueId().toString())) {
                        Report.addReport(p.getUniqueId().toString(), 1);
                    }
                    else {
                        Report.addPlayer(p.getName(), p.getUniqueId().toString());
                        Report.addReport(p.getUniqueId().toString(), 1);
                    }
                }
            }
            else {
                p.sendMessage(CMD_Report.PREFIX + "Benutze§8» §a/report §8(§aSPIELER§8) (§aGRUND§8)");
                p.sendMessage(CMD_Report.PREFIX + "Gründe§8» §aHacking§8, §aTrolling§8,§aTeaming§8, §aSkin§8, §aName§8, §aBeleidigung§8, §aProvokation§8, §aChatverhalten§8, §aWerbung§8, §aNationalsozialismus§8, §aSpamming§8, §aDrohung");
            }
        }
    }
    static {
        CMD_Report.PREFIX = Manager.PREFIX;
    }

}

