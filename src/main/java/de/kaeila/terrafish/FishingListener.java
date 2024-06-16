package de.kaeila.terrafish;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FishingListener implements Listener {
    private TerraFish plugin;
    private ItemCreator itemCreator;

    public FishingListener(TerraFish plugin) {
        this.plugin = plugin;
        this.itemCreator = new ItemCreator(plugin);
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        // Generate a random number between 1 and 10
        int randomChance = new Random().nextInt(10) + 1;

        // If the random number is not 1, return early (90% chance to not get a crate)
        if (randomChance != 1) {
            return;
        }
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            plugin.getLogger().info("Player caught a fish!");

            // Create a new Crate
            Crate crate;
            double random = Math.random();
            if (random < 0.33) {
                crate = new Crate("Wooden Crate", Material.CHEST, null);
            } else if (random < 0.66) {
                crate = new Crate("Iron Crate", Material.IRON_BLOCK, null);
            } else {
                crate = new Crate("Diamond Crate", Material.DIAMOND_BLOCK, null);
            }

            // Log the type of crate
            plugin.getLogger().info("Player fished a " + crate.getName());

            // Create an ItemStack representing the crate
            ItemStack crateItem = itemCreator.createCrate(crate);

            // Drop the crate at the player's location
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), crateItem);
        }
    }
}
