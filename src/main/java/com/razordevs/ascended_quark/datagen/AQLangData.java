package com.razordevs.ascended_quark.datagen;

import com.aetherteam.nitrogen.data.providers.NitrogenLanguageProvider;
import com.razordevs.ascended_quark.AscendedQuarkMod;
import com.razordevs.ascended_quark.blocks.AQBlocks;
import com.razordevs.ascended_quark.items.AQItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AQLangData extends NitrogenLanguageProvider {

    public AQLangData(DataGenerator output) {
        super(output, AscendedQuarkMod.MODID);
    }

    @Override
    protected void addTranslations() {
        Collection<RegistryObject<Block>> blocks = AQBlocks.BLOCKS.getEntries();
        Collection<RegistryObject<Item>> itemsTemp = AQItems.ITEMS.getEntries();
        List<Item> items = new ArrayList<>();

        for (RegistryObject<Item> item : itemsTemp) {
            items.add(item.get());
        }
        // blocks.remove(Blocks.DIRT);
        for (RegistryObject<Block> block : blocks) {
            this.add(block.get(), this.getName(block.getId().getPath()));

            items.remove(block.get().asItem());
        }

        for (Item item : items)
        {
            this.add(item, this.getName(item.toString()));
        }

        this.add("quark.category.the_aether", "The Aether");

        //TODO: Finish missing blocks lore entries

        this.add("lore." + AQItems.AMBROSIUM_TORCH_ARROW.get().getDescriptionId(), "An arrow tied to an Ambrosium Torch. It imbues the healing properties of Ambrosium, and places the torch on impact.");
        this.add("lore." + AQItems.BLUE_SWET_IN_A_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Blue Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.BLUE_SWET_IN_A_SKYROOT_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Blue Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.GOLDEN_SWET_IN_A_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Golden Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.GOLDEN_SWET_IN_A_SKYROOT_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Golden Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.QUICKSOIL_GLASS_SHARD.get().getDescriptionId(), "Apparently something broke. You can still use some of the shards to craft the glass again.");

        this.add("lore." + AQBlocks.HOLYSTONE_FURNACE.get().getDescriptionId(), "A versatile furnace that can also use Ambrosium as fuel. Also looks fancier in white.");
        this.add("lore." + AQBlocks.AMBROSIUM_LAMP.get().getDescriptionId(), "A lamp made out of Holystone and Ambrosium. Ambrosium Blocks can increase its light up to 4 times.");
        this.add("lore." + AQBlocks.BLUE_BERRY_CRATE.get().getDescriptionId(), "A handy crate for storing blue berries! A very good decoration block!");
        this.add("lore." + AQBlocks.ZANITE_BARS.get().getDescriptionId(), "Bars made out of Zanite. A good substitute for the iron ones, and also more colorful.");
        this.add("lore." + AQBlocks.QUICKSOIL_FRAMED_GLASS.get().getDescriptionId(), "Combine Iron Ingots and Quicksoil Glass, and you get yourself some fancy Quicksoil Framed Glass. Great for some more rustic or medieval builds.");
        this.add("lore." + AQBlocks.QUICKSOIL_FRAMED_GLASS_PANE.get().getDescriptionId(), "A thin decorative variant of Quicksoil Framed Glass. Great for some more rustic or medieval builds.");

        addBricksLore(AQBlocks.QUICKSOIL_BRICKS.get());
        addSlabLore(AQBlocks.QUICKSOIL_BRICK_SLAB.get());
        addVerticalSlabLore(AQBlocks.QUICKSOIL_BRICK_VERTICAL_SLAB.get());
        addStairsLore(AQBlocks.QUICKSOIL_BRICK_STAIRS.get());
        addWallLore(AQBlocks.QUICKSOIL_BRICK_WALL.get());

        addBricksLore(AQBlocks.AETHER_DIRT_BRICKS.get());
        addSlabLore(AQBlocks.AETHER_DIRT_BRICK_SLAB.get());
        addVerticalSlabLore(AQBlocks.AETHER_DIRT_BRICK_VERTICAL_SLAB.get());
        addStairsLore(AQBlocks.AETHER_DIRT_BRICK_STAIRS.get());
        addWallLore(AQBlocks.AETHER_DIRT_BRICK_WALL.get());

        addVerticalSlabLore(AQBlocks.HOLYSTONE_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.HELLFIRE_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.AEROGEL_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.SKYROOT_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.ANGELIC_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.ICESTONE_VERTICAL_SLAB.get());
    }

    private void addBricksLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Used as a building material native to the Aether. It is made from " + block.getName() + " and is sturdier than it too.");
    }
    private void addSlabLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + block.getName() + ". Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
    }
    private void addVerticalSlabLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + block.getName() + ". Vertical Slabs are half blocks, versatile for decoration. Try adding some to a building's window!");
    }
    private void addStairsLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + block.getName() + ". Stairs are useful for adding verticality to builds and are often used for decoration too!");
    }
    private void addWallLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + block.getName() + ". Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
    }

    private String getName(String name) {
        char[] nameCharArray = name.toCharArray();
        String temp;
        temp = String.valueOf(nameCharArray[0]);
        nameCharArray[0] = temp.toUpperCase().toCharArray()[0];
        for(int i = 0; i < name.length(); i++) {
            if(name.charAt(i) == '_') {
                nameCharArray[i] = ' ';
                temp = String.valueOf(nameCharArray[i+1]);
                nameCharArray[i+1] =  temp.toUpperCase().toCharArray()[0];
            }
        }

        String name2 = "";
        for(int i = 0; i < nameCharArray.length; i++) {
            name2 = name2 + nameCharArray[i];
        }

        return name2;
    }

}
