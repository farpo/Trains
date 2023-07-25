package eu.ansquare.trains;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemManager {
    public void giveItem(String item, Player player){
        switch (item){
            case "manager":
                giveManager(player);
                break;
            case "linker":
                giveLinker(player);
                break;
            default:
                player.sendMessage("Unknown item");
                break;
        }
    }
    public void giveLinker(Player player){
        NamespacedKey key = new NamespacedKey("trains", "is-linker");
        ItemStack item = new ItemStack(Material.IRON_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "yes");
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }
    public boolean isLinker(ItemStack item){
        NamespacedKey key = new NamespacedKey("trains", "is-linker");
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)){
            String valueString = container.get(key, PersistentDataType.STRING);
            if(valueString.equalsIgnoreCase("yes")){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    public void giveManager(Player player){
        NamespacedKey key = new NamespacedKey("trains", "is-manager");
        ItemStack item = new ItemStack(Material.SCUTE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "yes");
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }
    public boolean isManager(ItemStack item){
        NamespacedKey key = new NamespacedKey("trains", "is-manager");
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)){
            String valueString = container.get(key, PersistentDataType.STRING);
            if(valueString.equalsIgnoreCase("yes")){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
