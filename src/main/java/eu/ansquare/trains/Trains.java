package eu.ansquare.trains;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Trains extends JavaPlugin {
    static Trains instance;
    ItemManager im;
    PlayerInteractListener pil;
    CommonGuiManager gm;
    CartListener cl;
    List<Train> trainsList;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("trains")){
            if(args[0].equalsIgnoreCase("give")){
                im.giveItem(args[1], (Player) sender);
                return true;
            } else if (args[0].equalsIgnoreCase("list")) {
                list((Player) sender);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void onEnable(){
        instance = this;
        trainsList = new ArrayList<>();
        im = new ItemManager();
        gm = new CommonGuiManager(trainsList);
        pil = new PlayerInteractListener(gm);
        cl = new CartListener();
        getServer().getPluginManager().registerEvents(cl, this);
        getServer().getPluginManager().registerEvents(pil, this);
        getServer().getPluginManager().registerEvents(gm, this);
    }
    public void list(Player player){
        for(Train train : trainsList){
            player.sendMessage("Train:");
            player.sendMessage("Main: " + train.mainCart.getLocation());
            for(Minecart minecart : train.carts){
                if(train.isMain(minecart)){
                    player.sendMessage("Cart: " + minecart.getLocation() + " main");
                }
                else {
                    player.sendMessage("Cart: " + minecart.getLocation());
                }
            }
        }
    }

}
