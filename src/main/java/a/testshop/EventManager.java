package a.testshop;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;
import org.bukkit.block.Sign;

public class EventManager implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        TestShop.getPlugin(TestShop.class).reloadConfig();
        String sides;
        String adminshop;
        adminshop = TestShop.getPlugin(TestShop.class).getConfig().getString("adminshop");
        if (!event.hasBlock()) return;
        if (!(event.getClickedBlock().getState() instanceof Sign)) return;
        Sign signboard = (Sign) event.getClickedBlock().getState();
        sides = signboard.getLine(0);
        if (sides == adminshop) return;{
            String title;
            String Configreload;
            if (signboard.getLine(1).equals("§bアイテム:reload")) {
                TestShop.getPlugin(TestShop.class).reloadConfig();
                title = TestShop.getPlugin(TestShop.class).getConfig().getString("Title");
                Configreload = TestShop.getPlugin(TestShop.class).getConfig().getString("Configreload");
                event.getPlayer().sendMessage(title + Configreload);
            }
        }
    }
}

