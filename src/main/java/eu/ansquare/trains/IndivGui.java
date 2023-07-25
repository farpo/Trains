package eu.ansquare.trains;

import eu.ansquare.util.MetaManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class IndivGui implements Listener {
    private Inventory inv;
    Train train;
    MetaManager mm;
    public IndivGui(Train train){
        this.train = train;
        mm = new MetaManager();
    }
    public void showGui(Player player, int number){
        inv = Bukkit.createInventory(null, 54, Component.text("Train " + number));
        putCars();
        player.openInventory(inv);
    }
    void putCars(){
        for(int i = 0; i < train.carts.size(); i++){
            ItemStack is = new ItemStack(train.carts.get(i).getMinecartMaterial(), i+1);
            if(train.isMain(train.carts.get(i))){
                mm.addLore(is, "Main cart");
            }
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
                Minecart cart = train.carts.get(slot);
                if(!train.isMain(cart)){
                    train.setMain(cart);
                    putCars();
                }
            }
            e.setCancelled(true);
        }
    }
}
