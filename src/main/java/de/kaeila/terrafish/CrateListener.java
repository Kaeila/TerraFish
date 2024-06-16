package de.kaeila.terrafish;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class CrateListener implements Listener {
    private TerraFish plugin;

    public CrateListener(TerraFish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null && event.getItem().hasItemMeta()) {
                NamespacedKey key = new NamespacedKey(plugin, "crateId");
                String id = event.getItem().getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);

                if (id != null) {
                    // The player is right-clicking with a crate item
                    getLogger().info("Player right-clicked with a crate!");

                    // Define the loot table
                    ItemStack[] lootTable;
                    switch (event.getItem().getType()) { // Check the material of the crate
                        case CHEST:
                            lootTable = new ItemStack[]{
                                    new ItemStack(Material.WOODEN_SWORD),
                                    new ItemStack(Material.STONE_SWORD)
                            };
                            break;
                        case IRON_BLOCK:
                            lootTable = new ItemStack[]{
                                    new ItemStack(Material.IRON_SWORD),
                                    new ItemStack(Material.GOLDEN_SWORD)
                            };
                            break;
                        case DIAMOND_BLOCK:
                            lootTable = new ItemStack[]{
                                    new ItemStack(Material.DIAMOND_SWORD),
                                    new ItemStack(Material.NETHERITE_SWORD)
                            };
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + event.getItem().getType());
                    }

                    // Randomly select an item from the loot table
                    ItemStack loot = lootTable[new Random().nextInt(lootTable.length)];

                    // Give the loot to the player
                    Player player = event.getPlayer();
                    player.getInventory().addItem(loot);

                    // Consume the crate
                    ItemStack crateItem = event.getItem();
                    crateItem.setAmount(crateItem.getAmount() - 1);
                    player.getInventory().setItemInMainHand(crateItem.getAmount() > 0 ? crateItem : null);
                }
            }
        }
    }
}