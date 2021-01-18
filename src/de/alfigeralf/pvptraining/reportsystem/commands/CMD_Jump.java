package de.alfigeralf.pvptraining.reportsystem.commands;

import de.alfigeralf.pvptraining.reportsystem.ReportSystem;
import de.alfigeralf.pvptraining.reportsystem.utils.Manager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMD_Jump extends Command
{
    public CMD_Jump(final String name) {
        super("jump");
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            if (p.hasPermission("PVPpvptraining.jump")) {
                if (args.length == 1) {
                    try {
                        p.connect(BungeeCord.getInstance().getServerInfo(args[0]));
                        p.sendMessage(Manager.PREFIX + "§7Du bist nun auf Server §e" + args[0] + "§8.");
                        ReportSystem.reports.remove(args[0]);
                    }
                    catch (Exception ex) {
                        p.sendMessage(Manager.PREFIX + "Der Server wurde nicht gefunden BZW. ist die runde schon zu ende!");
                    }
                }
            }
            else {
                p.sendMessage(Manager.NO_PERMS);
            }
        }
    }
}
