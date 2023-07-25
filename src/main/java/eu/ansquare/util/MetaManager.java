package eu.ansquare.util;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MetaManager {
    public void addLore(ItemStack item, String lore){
        ItemMeta meta = item.getItemMeta();
        List<Component> list = new ArrayList<>();
        list.add(Component.text(lore));
        meta.lore(list);
        item.setItemMeta(meta);
    }
}
