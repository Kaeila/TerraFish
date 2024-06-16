package de.kaeila.terrafish;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemCreator {
    private TerraFish plugin;

    public ItemCreator(TerraFish plugin) {
        this.plugin = plugin;
    }

    public ItemStack createCrate(Crate crate) {
        ItemStack crateItem = new ItemStack(crate.getMaterial());
        ItemMeta meta = crateItem.getItemMeta();

        meta.setDisplayName(crate.getName());
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "crateId"), PersistentDataType.STRING, crate.getId().toString());

        crateItem.setItemMeta(meta);
        return crateItem;
    }
}