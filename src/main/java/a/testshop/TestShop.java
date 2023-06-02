package a.testshop;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class TestShop extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        getServer().getPluginManager().registerEvents(new EventManager(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveDefaultConfig();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length != 0) {
            String title;
            String Configreload;
            String adminshop;
            if (args[0].equals("reload")) {
                reloadConfig();
                Configreload = getConfig().getString("Configreload");
                title = getConfig().getString("Title");
                sender.sendMessage(title + Configreload);
            } else if (args[0].equals("create")) {
                Location loc = ((Player) sender).getLocation();
                Block block = loc.getBlock();
                block.setType(Material.OAK_SIGN);
                if (args[1] != null){
                    if (args[2] != null) {
                        if (args[3] != null) {
                            adminshop = getConfig().getString("adminshop");
                            BlockState signState = block.getState();
                            Sign sign = (Sign) signState;
                            sign.setLine(0, adminshop);
                            sign.setLine(1, "§bアイテム:" + args[1]);
                            sign.setLine(2, "§6個数:" + args[2]);
                            sign.setLine(3, "§e値段:" + args[3]);
                            sign.update();
                            title = getConfig().getString("Title");
                            sender.sendMessage(title + "§e" + args[1] + "を" + args[2] + "個当たり" + args[3] + "円で作成しました");
                        } else {
                            sender.sendMessage("引数が見つかりませんでした");
                            return false;
                        }
                    } else {
                        sender.sendMessage("引数が見つかりませんでした");
                        return false;
                    }
                } else {
                    sender.sendMessage("アイテムが見つかりませんでした");
                    return false;
                }
            } else {
                sender.sendMessage("引数が見つかりませんでした");
            }
            return true;
        } else {
            sender.sendMessage("引数がありません");
            return false;
        }
    }
}
