package de.alfigeralf.pvptraining.reportsystem.commands;

import de.alfigeralf.pvptraining.reportsystem.mysql.NotifyManager;
import de.alfigeralf.pvptraining.reportsystem.utils.Manager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMD_Login extends Command
{
    public int SPIELERS;

    public CMD_Login(final String name) {
        super(name);
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            if (p.hasPermission("PVPpvptraining.report")) {
                if (!NotifyManager.isExists(p.getUniqueId().toString())) {
                    NotifyManager.add(p.getUniqueId().toString());
                    p.sendMessage(Manager.PREFIX + "Du hast die §aerfolgreich §7ausgeloggt");
                }
                else {
                    NotifyManager.remove(p.getUniqueId().toString());
                    p.sendMessage(Manager.PREFIX + "Du hast die §aerfolgreich §7in das §aReport §7Sytsem eingeloggt");
                }
            }
            else {
                p.sendMessage(Manager.NO_PERMS);
            }
        }
    }
}