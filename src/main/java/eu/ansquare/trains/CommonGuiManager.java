package eu.ansquare.trains;


import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CommonGuiManager implements Listener {

    List<Train> trainList;
    public CommonGuiManager(List<Train> list){
        this.trainList = list;
    }
    private Inventory inv;
    public void showGui(Player player){
        inv = Bukkit.createInventory(null, 54, Component.text("Trains"));
        putTrains();
        player.openInventory(inv);
    }
    void putTrains(){
        for(int i = 0; i < trainList.size(); i++){
            ItemStack is = new ItemStack(Material.MINECART, i+1);
            inv.setItem(i, is);
        }
    }
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inv)) {
            int slot = e.getSlot();
            if(e.getInventory().getItem(slot) != null){
                Train train = trainList.get(slot);
                IndivGui ingm = new IndivGui(train);
                Trains.instance.getServer().getPluginManager().registerEvents(ingm, Trains.instance);
                ingm.showGui((Player) e.getWhoClicked(), slot + 1);
            }
            e.setCancelled(true);
        }
    }
}
