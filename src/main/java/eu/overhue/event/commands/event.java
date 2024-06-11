package eu.overhue.event.commands;

import eu.overhue.event.Utils.Event.EventUtil1;
import eu.overhue.event.Utils.Event.EventUtil2;
import eu.overhue.event.Utils.Event.EventUtil3;
import eu.overhue.event.Utils.Event.EventUtil4;
import eu.overhue.event.Utils.Event.EventUtil5;
import eu.overhue.event.Utils.SpawnUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class event implements CommandExecutor {

    private EventUtil1 eventUtil1;
    private SpawnUtil spawnUtil;
    private EventUtil2 eventUtil2;
    private EventUtil3 eventUtil3;
    private EventUtil4 eventUtil4;
    private EventUtil5 eventUtil5;

    public event(EventUtil1 eventUtil1, EventUtil2 eventUtil2, EventUtil3 eventUtil3, EventUtil4 eventUtil4, EventUtil5 eventUtil5, SpawnUtil spawnUtil) {
        this.eventUtil1 = eventUtil1;
        this.eventUtil2 = eventUtil2;
        this.eventUtil3 = eventUtil3;
        this.eventUtil4 = eventUtil4;
        this.eventUtil5 = eventUtil5;
        this.spawnUtil = spawnUtil;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be executed by a player.");
            return false;
        }

        Player player = (Player) commandSender;

        if (args.length < 1) {
            player.sendMessage("§e§lEVENT\n\n§7Commands:\n§b/event join <event_number> §9- §fJoin an event\n§b/event create <event_number> §9- §fCreate an event\n§b/event delete <event_number> §9- §fDelete an event\n\n§8- Created by overhue.eu");
            return true;
        }

        if (args[0].equalsIgnoreCase("create")) {
            if (!player.hasPermission("event.admin")) {
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cYou don't have permission to create events.".replaceAll("&", "§"));
                return true;
            }

            if (args.length != 2) {
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cUsage: /event create <event_number>".replaceAll("&", "§"));
                return true;
            }

            String eventNumber = args[1];

            if (eventNumber.equalsIgnoreCase("1")) {
                eventUtil1.set(player.getLocation());
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fYou have successfully created Event 1.");
            } else if (eventNumber.equalsIgnoreCase("2")) {
                eventUtil2.set(player.getLocation());
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fYou have successfully created Event 2.");
            } else if (eventNumber.equalsIgnoreCase("3")) {
                eventUtil3.set(player.getLocation());
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fYou have successfully created Event 3.");
            } else if (eventNumber.equalsIgnoreCase("4")) {
                eventUtil4.set(player.getLocation());
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fYou have successfully created Event 4.");
            } else if (eventNumber.equalsIgnoreCase("5")) {
                eventUtil5.set(player.getLocation());
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fYou have successfully created Event 5.");
            } else {
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cInvalid event number. Usage: /event create <event_number>".replaceAll("&", "§"));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("delete")) {
            String eventNumber = args[1];

            switch (eventNumber) {
                case "1":
                    eventUtil1.deleteSpawnLocation();
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fSpawn location for Event 1 has been successfully deleted.");
                    break;
                case "2":
                    eventUtil2.deleteSpawnLocation();
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fSpawn location for Event 2 has been successfully deleted.");
                    break;
                case "3":
                    eventUtil3.deleteSpawnLocation();
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fSpawn location for Event 3 has been successfully deleted.");
                    break;
                case "4":
                    eventUtil4.deleteSpawnLocation();
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fSpawn location for Event 4 has been successfully deleted.");
                    break;
                case "5":
                    eventUtil5.deleteSpawnLocation();
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §fSpawn location for Event 5 has been successfully deleted.");
                    break;
                default:
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cInvalid event number. Usage: /event delete <event_number>.replaceAll(\"&\", \"§\")");
                    break;
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("join")) {
            if (args.length != 2) {
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cUsage: /event join <event_number>".replaceAll("&", "§"));
                return true;
            }

            String eventNumber = args[1];

            if (eventNumber.equalsIgnoreCase("1")) {
                eventUtil1.teleport(player);
            } else if (eventNumber.equalsIgnoreCase("2")) {
                eventUtil2.teleport(player);
            } else if (eventNumber.equalsIgnoreCase("3")) {
                eventUtil3.teleport(player);
            } else if (eventNumber.equalsIgnoreCase("4")) {
                eventUtil4.teleport(player);
            } else if (eventNumber.equalsIgnoreCase("5")) {
                eventUtil5.teleport(player);
            } else {
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cInvalid event number. Usage: /event join <event_number>".replaceAll("&", "§"));
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("kick")) {
            if (args.length == 2) {
                String targetPlayerName = args[1];
                Player targetPlayer = Bukkit.getPlayer(targetPlayerName);

                if (targetPlayer != null) {
                    spawnUtil.teleport(targetPlayer);
                    targetPlayer.sendMessage("§x§5§9§c§7§e§aOverHue §8| &fByl jsi &cvyhozen&f z eventu!".replaceAll("&", "§"));
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| &fvyhodil jsi hrace §x§5§9§c§7§e§a".replaceAll("&", "§") + targetPlayerName + " z eventu".replaceAll("&", "§"));
                } else {
                    player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cPlayer not found: ".replaceAll("&", "§") + targetPlayerName);
                }
            } else {
                player.sendMessage("§x§5§9§c§7§e§aOverHue §8| §cUsage: /event kick <player>".replaceAll("&", "§"));
            }
        }
        return false;
    }
}
