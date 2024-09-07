package org.razordevs.ascended_quark.datagen;

import com.aetherteam.nitrogen.data.providers.NitrogenLanguageProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.codehaus.plexus.util.StringUtils;
import org.razordevs.ascended_quark.AscendedQuark;
import org.razordevs.ascended_quark.blocks.AQBlocks;
import org.razordevs.ascended_quark.items.AQItems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AQLangData extends NitrogenLanguageProvider {

    public AQLangData(PackOutput output) {
        super(output, AscendedQuark.MODID);
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

        //TODO: Polishing lore descriptions

        this.add("lore." + AQItems.AMBROSIUM_TORCH_ARROW.get().getDescriptionId(), "An arrow tied to an Ambrosium Torch. It imbues the healing properties of Ambrosium, and places the torch on impact.");
        this.add("lore." + AQItems.BLUE_SWET_IN_A_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Blue Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.BLUE_SWET_IN_A_SKYROOT_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Blue Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.GOLDEN_SWET_IN_A_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Golden Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.GOLDEN_SWET_IN_A_SKYROOT_BUCKET_ITEM.get().getDescriptionId(), "Seems like you caught a Golden Swet! Not that useful, but it's an easy way to transport them. They might get excited too!");
        this.add("lore." + AQItems.QUICKSOIL_GLASS_SHARD.get().getDescriptionId(), "Apparently something broke. You can still use some of the shards to craft the glass again.");

        this.add("lore." + AQBlocks.HOLYSTONE_FURNACE.get().getDescriptionId(), "A versatile furnace that can also use Ambrosium as fuel. Also looks fancier in white.");
        this.add("lore." + AQBlocks.AMBROSIUM_LAMP.get().getDescriptionId(), "A lamp made out of Holystone and Ambrosium. Ambrosium Blocks can increase its light up to 4 times.");
        this.add("lore." + AQBlocks.BLUE_BERRY_CRATE.get().getDescriptionId(), "A handy crate for storing blue berries! A very good decoration block!");
        this.add("lore." + AQBlocks.QUICKSOIL_FRAMED_GLASS.get().getDescriptionId(), "Combine Iron Ingots and Quicksoil Glass, and you get yourself some fancy Quicksoil Framed Glass. Great for some more rustic or medieval builds.");
        this.add("lore." + AQBlocks.QUICKSOIL_FRAMED_GLASS_PANE.get().getDescriptionId(), "A thin decorative variant of Quicksoil Framed Glass. Great for some more rustic or medieval builds.");
        this.add("lore." + AQBlocks.SKYROOT_LADDER.get().getDescriptionId(), "Hey look! Now ladders can be made out of Skyroot!");
        this.add("lore." + AQBlocks.SKYROOT_CHEST.get().getDescriptionId(), "A nice looking chest made out of Skyroot. Perfect blend for your storage room.");
        this.add("lore." + AQBlocks.HOLLOW_SKYROOT_LOG.get().getDescriptionId(), "Can be crafted with four Skyroot Logs. You can sneak into them horizontally, anc climb inside of them vertically!");
        this.add("lore." + AQBlocks.SKYROOT_STOOL.get().getDescriptionId(), "Stools can be created the same way you'd make a Bed, but using slabs instead of full wood blocks. As you can expect, you can sit on them!");
        this.add("lore." + AQBlocks.SKYROOT_STICK_BLOCK.get().getDescriptionId(), "Cool looking pillar to store your sticks, or to integrate them into your builds!");

        addPostLore(AQBlocks.SKYROOT_POST.get());
        addPostLore(AQBlocks.STRIPPED_SKYROOT_POST.get());

        addHedgeLore(AQBlocks.SKYROOT_HEDGE.get());
        addHedgeLore(AQBlocks.GOLDEN_SKYROOT_HEDGE.get());
        addHedgeLore(AQBlocks.HOLIDAY_SKYROOT_HEDGE.get());
        addHedgeLore(AQBlocks.DECORATED_HOLIDAY_SKYROOT_HEDGE.get());
        addHedgeLore(AQBlocks.CRYSTAL_SKYROOT_HEDGE.get());
        addHedgeLore(AQBlocks.CRYSTAL_FRUIT_SKYROOT_HEDGE.get());

        addCarpetLore(AQBlocks.SKYROOT_LEAF_CARPET.get());
        addCarpetLore(AQBlocks.GOLDEN_OAK_LEAF_CARPET.get());
        addCarpetLore(AQBlocks.HOLIDAY_LEAF_CARPET.get());
        addCarpetLore(AQBlocks.DECORATED_HOLIDAY_LEAF_CARPET.get());
        addCarpetLore(AQBlocks.CRYSTAL_LEAF_CARPET.get());
        addCarpetLore(AQBlocks.CRYSTAL_FRUIT_LEAF_CARPET.get());

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

        addBricksLore(AQBlocks.POLISHED_ICESTONE.get());
        addSlabLore(AQBlocks.POLISHED_ICESTONE_SLAB.get());
        addVerticalSlabLore(AQBlocks.POLISHED_ICESTONE_VERTICAL_SLAB.get());
        addStairsLore(AQBlocks.POLISHED_ICESTONE_STAIRS.get());
        addWallLore(AQBlocks.POLISHED_ICESTONE_WALL.get());

        addBricksLore(AQBlocks.ICESTONE_BRICKS.get());
        addSlabLore(AQBlocks.ICESTONE_BRICK_SLAB.get());
        addVerticalSlabLore(AQBlocks.ICESTONE_BRICK_VERTICAL_SLAB.get());
        addStairsLore(AQBlocks.ICESTONE_BRICK_STAIRS.get());
        addWallLore(AQBlocks.ICESTONE_BRICK_WALL.get());

        addVerticalSlabLore(AQBlocks.HOLYSTONE_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.HOLYSTONE_BRICK_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.MOSSY_HOLYSTONE_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.HELLFIRE_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.AEROGEL_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.SKYROOT_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.ANGELIC_VERTICAL_SLAB.get());
        addVerticalSlabLore(AQBlocks.ICESTONE_VERTICAL_SLAB.get());

        addVerticalPlanksLore(AQBlocks.VERTICAL_SKYROOT_PLANKS.get());

        addMenuLangComponents();
    }

    private void addMenuLangComponents() {
        this.add(AscendedQuark.MODID + ".category.aether", "Aether");
        this.add(AscendedQuark.MODID + ".category.general", "General Settings");
        this.add(AscendedQuark.MODID + ".gui.config.social.discord", "RazorDevs Discord");
    }

    private void addBricksLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Used as a building material native to the Aether. It is made from " + clearBlockOrigin(block, 7) + " and is sturdier than it too.");
    }
    private void addSlabLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 5) + ". Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
    }
    private void addVerticalSlabLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 14) + ". Vertical Slabs are half blocks, versatile for decoration. Try adding some to a building's window!");
    }
    private void addStairsLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 7) + ". Stairs are useful for adding verticality to builds and are often used for decoration too!");
    }
    private void addWallLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 5) + ". Can be used for decorative enclosures and defenses. Great for keeping nasty intruders away!");
    }
    private void addHedgeLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 6) + ". They connect to each other like fences, and have the same bounding box. You can even put flowers on them!");
    }
    private void addPostLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 5) + ". These are slim log style blocks you can lay down vertically or horizontally. Chains and lanterns can also connect to them.");
    }
    private void addCarpetLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Crafted from " + clearBlockOrigin(block, 7) + ". Leaf Carpets are a great way to add natural elements to your builds!");
    }
    private void addVerticalPlanksLore(Block block){
        this.add("lore." + block.getDescriptionId(), "Simple vertical planks to spice up your creativity.");
    }


    /**
     * Returns the block of origin, cleaning the string and adding uppercase letters from the descriptionId of the passed block.
     * Used in the Lang datageneration for various Blocks lore.
     *
     * @param block
     * Block to elaborate.
     *
     * @param endIndex
     * Index position used to exclude the suffix depending on the type of block.
     */
    private String clearBlockOrigin(Block block, int endIndex){
        return StringUtils.capitaliseAllWords(block.getDescriptionId().substring(21, block.getDescriptionId().length() - endIndex).replace("_"," "));
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
