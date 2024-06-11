package eu.overhue.event.Utils.Event;

import eu.overhue.event.Core;
import eu.overhue.event.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.logging.Level;

public class EventUtil2 implements Listener {
    private eu.overhue.event.Utils.ConfigUtil config;
    private Location spawn;

    public EventUtil2(Core plugin){

        config = new eu.overhue.event.Utils.ConfigUtil(plugin, "event2.yml");

        String worldName = config.getConfig().getString("world");
        double x = config.getConfig().getDouble("x");
        double y = config.getConfig().getDouble("y");
        double z = config.getConfig().getDouble("z");
        float yaw = (float)  config.getConfig().getDouble("yaw");
        float pitch = (float)  config.getConfig().getDouble("pitch");

        if (worldName != null) {
            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                Bukkit.getLogger().log(Level.SEVERE, "the world \"" + worldName + "\" does not exist.");
                return;
            }

            spawn = new Location(world, x, y, z, yaw, pitch);
        }
    }


    @EventHandler
    public void Respawn(PlayerRespawnEvent event) {
        if (spawn != null) {
            event.setRespawnLocation(spawn);
        }
    }
    public void teleport(Player player) {
        if (spawn == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe spawn is not set!"));
            return;
        }
        player.teleport(spawn);
    }

    public void teleport2(Player player) {
        if (spawn == null) {
            return;
        }
        player.teleport(spawn);
    }
    public void set(Location spawn) {
        this.spawn = spawn;

        String worldName = spawn.getWorld().getName();
        double x = spawn.getX();
        double y = spawn.getY();
        double z = spawn.getZ();
        float yaw =spawn.getYaw();
        float pitch = spawn.getPitch();

        config.getConfig().set("world", worldName);
        config.getConfig().set("x", x);
        config.getConfig().set("y", y);
        config.getConfig().set("z", z);
        config.getConfig().set("yaw", yaw);
        config.getConfig().set("pitch", pitch);
        config.save();
    }
    public void deleteSpawnLocation() {
        spawn = null;
        config.getConfig().set("world", null);
        config.getConfig().set("x", null);
        config.getConfig().set("y", null);
        config.getConfig().set("z", null);
        config.getConfig().set("yaw", null);
        config.getConfig().set("pitch", null);
        config.save();
    }
}
