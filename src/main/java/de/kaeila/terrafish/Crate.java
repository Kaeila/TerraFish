package de.kaeila.terrafish;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import java.util.UUID;

public class Crate {
    private UUID id;
    private String name;
    private Material material;
    private List<ItemStack> contents;

    public Crate(String name, Material material, List<ItemStack> contents) {
        this.id = UUID.randomUUID(); // Generate a unique ID for each crate
        this.name = name;
        this.material = material;
        this.contents = contents;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public List<ItemStack> getContents() {
        return contents;
    }



    // You can add setters if needed
}