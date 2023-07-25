package eu.ansquare.trains;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {
    long lastInteractionTime;
    Trains instance;
    ItemManager im;
    Linking linker;
    CommonGuiManager gm;
    public PlayerInteractListener(CommonGuiManager gm){
        lastInteractionTime = 0l;
        this.instance = Trains.instance;
        im = new ItemManager();
        linker = new Linking();
        this.gm = gm;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item.hasItemMeta()){
            if(im.isManager(item)){
                long now = System.currentTimeMillis();
                if(event.getAction().isRightClick() && (now - lastInteractionTime) > 100){
                    gm.showGui(player);
                    lastInteractionTime = System.currentTimeMillis();
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerEntityInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Entity entity = event.getRightClicked();
        if(item.hasItemMeta()){
            if(im.isLinker(item)){
                long now = System.currentTimeMillis();
                if((now - lastInteractionTime) > 100){
                    if(entity instanceof Minecart){
                        linker.addLink(player, (Minecart) entity);
                    }
                    lastInteractionTime = System.currentTimeMillis();
                    event.setCancelled(true);
                }
            }
        }
    }
}
