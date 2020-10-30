package com.karbonpowered.api.item

import com.karbonpowered.catalog.Catalog
import com.karbonpowered.catalog.CatalogRegistry

object ItemTypes : Catalog<ItemType> {
    override val type: Class<ItemType>
        get() = ItemType::class.java

    @JvmField
    val AIR = CatalogRegistry.getProvider(ItemType::class, "air").get()

    @JvmField
    val STONE = CatalogRegistry.getProvider(ItemType::class, "stone").get()

    @JvmField
    val GRANITE = CatalogRegistry.getProvider(ItemType::class, "granite").get()

    @JvmField
    val POLISHED_GRANITE = CatalogRegistry.getProvider(ItemType::class, "polished_granite").get()

    @JvmField
    val DIORITE = CatalogRegistry.getProvider(ItemType::class, "diorite").get()

    @JvmField
    val POLISHED_DIORITE = CatalogRegistry.getProvider(ItemType::class, "polished_diorite").get()

    @JvmField
    val ANDESITE = CatalogRegistry.getProvider(ItemType::class, "andesite").get()

    @JvmField
    val POLISHED_ANDESITE = CatalogRegistry.getProvider(ItemType::class, "polished_andesite").get()

    @JvmField
    val GRASS_BLOCK = CatalogRegistry.getProvider(ItemType::class, "grass_block").get()

    @JvmField
    val DIRT = CatalogRegistry.getProvider(ItemType::class, "dirt").get()

    @JvmField
    val COARSE_DIRT = CatalogRegistry.getProvider(ItemType::class, "coarse_dirt").get()

    @JvmField
    val PODZOL = CatalogRegistry.getProvider(ItemType::class, "podzol").get()

    @JvmField
    val CRIMSON_NYLIUM = CatalogRegistry.getProvider(ItemType::class, "crimson_nylium").get()

    @JvmField
    val WARPED_NYLIUM = CatalogRegistry.getProvider(ItemType::class, "warped_nylium").get()

    @JvmField
    val COBBLESTONE = CatalogRegistry.getProvider(ItemType::class, "cobblestone").get()

    @JvmField
    val OAK_PLANKS = CatalogRegistry.getProvider(ItemType::class, "oak_planks").get()

    @JvmField
    val SPRUCE_PLANKS = CatalogRegistry.getProvider(ItemType::class, "spruce_planks").get()

    @JvmField
    val BIRCH_PLANKS = CatalogRegistry.getProvider(ItemType::class, "birch_planks").get()

    @JvmField
    val JUNGLE_PLANKS = CatalogRegistry.getProvider(ItemType::class, "jungle_planks").get()

    @JvmField
    val ACACIA_PLANKS = CatalogRegistry.getProvider(ItemType::class, "acacia_planks").get()

    @JvmField
    val DARK_OAK_PLANKS = CatalogRegistry.getProvider(ItemType::class, "dark_oak_planks").get()

    @JvmField
    val CRIMSON_PLANKS = CatalogRegistry.getProvider(ItemType::class, "crimson_planks").get()

    @JvmField
    val WARPED_PLANKS = CatalogRegistry.getProvider(ItemType::class, "warped_planks").get()

    @JvmField
    val OAK_SAPLING = CatalogRegistry.getProvider(ItemType::class, "oak_sapling").get()

    @JvmField
    val SPRUCE_SAPLING = CatalogRegistry.getProvider(ItemType::class, "spruce_sapling").get()

    @JvmField
    val BIRCH_SAPLING = CatalogRegistry.getProvider(ItemType::class, "birch_sapling").get()

    @JvmField
    val JUNGLE_SAPLING = CatalogRegistry.getProvider(ItemType::class, "jungle_sapling").get()

    @JvmField
    val ACACIA_SAPLING = CatalogRegistry.getProvider(ItemType::class, "acacia_sapling").get()

    @JvmField
    val DARK_OAK_SAPLING = CatalogRegistry.getProvider(ItemType::class, "dark_oak_sapling").get()

    @JvmField
    val BEDROCK = CatalogRegistry.getProvider(ItemType::class, "bedrock").get()

    @JvmField
    val SAND = CatalogRegistry.getProvider(ItemType::class, "sand").get()

    @JvmField
    val RED_SAND = CatalogRegistry.getProvider(ItemType::class, "red_sand").get()

    @JvmField
    val GRAVEL = CatalogRegistry.getProvider(ItemType::class, "gravel").get()

    @JvmField
    val GOLD_ORE = CatalogRegistry.getProvider(ItemType::class, "gold_ore").get()

    @JvmField
    val IRON_ORE = CatalogRegistry.getProvider(ItemType::class, "iron_ore").get()

    @JvmField
    val COAL_ORE = CatalogRegistry.getProvider(ItemType::class, "coal_ore").get()

    @JvmField
    val NETHER_GOLD_ORE = CatalogRegistry.getProvider(ItemType::class, "nether_gold_ore").get()

    @JvmField
    val OAK_LOG = CatalogRegistry.getProvider(ItemType::class, "oak_log").get()

    @JvmField
    val SPRUCE_LOG = CatalogRegistry.getProvider(ItemType::class, "spruce_log").get()

    @JvmField
    val BIRCH_LOG = CatalogRegistry.getProvider(ItemType::class, "birch_log").get()

    @JvmField
    val JUNGLE_LOG = CatalogRegistry.getProvider(ItemType::class, "jungle_log").get()

    @JvmField
    val ACACIA_LOG = CatalogRegistry.getProvider(ItemType::class, "acacia_log").get()

    @JvmField
    val DARK_OAK_LOG = CatalogRegistry.getProvider(ItemType::class, "dark_oak_log").get()

    @JvmField
    val CRIMSON_STEM = CatalogRegistry.getProvider(ItemType::class, "crimson_stem").get()

    @JvmField
    val WARPED_STEM = CatalogRegistry.getProvider(ItemType::class, "warped_stem").get()

    @JvmField
    val STRIPPED_OAK_LOG = CatalogRegistry.getProvider(ItemType::class, "stripped_oak_log").get()

    @JvmField
    val STRIPPED_SPRUCE_LOG = CatalogRegistry.getProvider(ItemType::class, "stripped_spruce_log").get()

    @JvmField
    val STRIPPED_BIRCH_LOG = CatalogRegistry.getProvider(ItemType::class, "stripped_birch_log").get()

    @JvmField
    val STRIPPED_JUNGLE_LOG = CatalogRegistry.getProvider(ItemType::class, "stripped_jungle_log").get()

    @JvmField
    val STRIPPED_ACACIA_LOG = CatalogRegistry.getProvider(ItemType::class, "stripped_acacia_log").get()

    @JvmField
    val STRIPPED_DARK_OAK_LOG = CatalogRegistry.getProvider(ItemType::class, "stripped_dark_oak_log").get()

    @JvmField
    val STRIPPED_CRIMSON_STEM = CatalogRegistry.getProvider(ItemType::class, "stripped_crimson_stem").get()

    @JvmField
    val STRIPPED_WARPED_STEM = CatalogRegistry.getProvider(ItemType::class, "stripped_warped_stem").get()

    @JvmField
    val STRIPPED_OAK_WOOD = CatalogRegistry.getProvider(ItemType::class, "stripped_oak_wood").get()

    @JvmField
    val STRIPPED_SPRUCE_WOOD = CatalogRegistry.getProvider(ItemType::class, "stripped_spruce_wood").get()

    @JvmField
    val STRIPPED_BIRCH_WOOD = CatalogRegistry.getProvider(ItemType::class, "stripped_birch_wood").get()

    @JvmField
    val STRIPPED_JUNGLE_WOOD = CatalogRegistry.getProvider(ItemType::class, "stripped_jungle_wood").get()

    @JvmField
    val STRIPPED_ACACIA_WOOD = CatalogRegistry.getProvider(ItemType::class, "stripped_acacia_wood").get()

    @JvmField
    val STRIPPED_DARK_OAK_WOOD = CatalogRegistry.getProvider(ItemType::class, "stripped_dark_oak_wood").get()

    @JvmField
    val STRIPPED_CRIMSON_HYPHAE = CatalogRegistry.getProvider(ItemType::class, "stripped_crimson_hyphae").get()

    @JvmField
    val STRIPPED_WARPED_HYPHAE = CatalogRegistry.getProvider(ItemType::class, "stripped_warped_hyphae").get()

    @JvmField
    val OAK_WOOD = CatalogRegistry.getProvider(ItemType::class, "oak_wood").get()

    @JvmField
    val SPRUCE_WOOD = CatalogRegistry.getProvider(ItemType::class, "spruce_wood").get()

    @JvmField
    val BIRCH_WOOD = CatalogRegistry.getProvider(ItemType::class, "birch_wood").get()

    @JvmField
    val JUNGLE_WOOD = CatalogRegistry.getProvider(ItemType::class, "jungle_wood").get()

    @JvmField
    val ACACIA_WOOD = CatalogRegistry.getProvider(ItemType::class, "acacia_wood").get()

    @JvmField
    val DARK_OAK_WOOD = CatalogRegistry.getProvider(ItemType::class, "dark_oak_wood").get()

    @JvmField
    val CRIMSON_HYPHAE = CatalogRegistry.getProvider(ItemType::class, "crimson_hyphae").get()

    @JvmField
    val WARPED_HYPHAE = CatalogRegistry.getProvider(ItemType::class, "warped_hyphae").get()

    @JvmField
    val OAK_LEAVES = CatalogRegistry.getProvider(ItemType::class, "oak_leaves").get()

    @JvmField
    val SPRUCE_LEAVES = CatalogRegistry.getProvider(ItemType::class, "spruce_leaves").get()

    @JvmField
    val BIRCH_LEAVES = CatalogRegistry.getProvider(ItemType::class, "birch_leaves").get()

    @JvmField
    val JUNGLE_LEAVES = CatalogRegistry.getProvider(ItemType::class, "jungle_leaves").get()

    @JvmField
    val ACACIA_LEAVES = CatalogRegistry.getProvider(ItemType::class, "acacia_leaves").get()

    @JvmField
    val DARK_OAK_LEAVES = CatalogRegistry.getProvider(ItemType::class, "dark_oak_leaves").get()

    @JvmField
    val SPONGE = CatalogRegistry.getProvider(ItemType::class, "sponge").get()

    @JvmField
    val WET_SPONGE = CatalogRegistry.getProvider(ItemType::class, "wet_sponge").get()

    @JvmField
    val GLASS = CatalogRegistry.getProvider(ItemType::class, "glass").get()

    @JvmField
    val LAPIS_ORE = CatalogRegistry.getProvider(ItemType::class, "lapis_ore").get()

    @JvmField
    val LAPIS_BLOCK = CatalogRegistry.getProvider(ItemType::class, "lapis_block").get()

    @JvmField
    val DISPENSER = CatalogRegistry.getProvider(ItemType::class, "dispenser").get()

    @JvmField
    val SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "sandstone").get()

    @JvmField
    val CHISELED_SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "chiseled_sandstone").get()

    @JvmField
    val CUT_SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "cut_sandstone").get()

    @JvmField
    val NOTE_BLOCK = CatalogRegistry.getProvider(ItemType::class, "note_block").get()

    @JvmField
    val POWERED_RAIL = CatalogRegistry.getProvider(ItemType::class, "powered_rail").get()

    @JvmField
    val DETECTOR_RAIL = CatalogRegistry.getProvider(ItemType::class, "detector_rail").get()

    @JvmField
    val STICKY_PISTON = CatalogRegistry.getProvider(ItemType::class, "sticky_piston").get()

    @JvmField
    val COBWEB = CatalogRegistry.getProvider(ItemType::class, "cobweb").get()

    @JvmField
    val GRASS = CatalogRegistry.getProvider(ItemType::class, "grass").get()

    @JvmField
    val FERN = CatalogRegistry.getProvider(ItemType::class, "fern").get()

    @JvmField
    val DEAD_BUSH = CatalogRegistry.getProvider(ItemType::class, "dead_bush").get()

    @JvmField
    val SEAGRASS = CatalogRegistry.getProvider(ItemType::class, "seagrass").get()

    @JvmField
    val SEA_PICKLE = CatalogRegistry.getProvider(ItemType::class, "sea_pickle").get()

    @JvmField
    val PISTON = CatalogRegistry.getProvider(ItemType::class, "piston").get()

    @JvmField
    val WHITE_WOOL = CatalogRegistry.getProvider(ItemType::class, "white_wool").get()

    @JvmField
    val ORANGE_WOOL = CatalogRegistry.getProvider(ItemType::class, "orange_wool").get()

    @JvmField
    val MAGENTA_WOOL = CatalogRegistry.getProvider(ItemType::class, "magenta_wool").get()

    @JvmField
    val LIGHT_BLUE_WOOL = CatalogRegistry.getProvider(ItemType::class, "light_blue_wool").get()

    @JvmField
    val YELLOW_WOOL = CatalogRegistry.getProvider(ItemType::class, "yellow_wool").get()

    @JvmField
    val LIME_WOOL = CatalogRegistry.getProvider(ItemType::class, "lime_wool").get()

    @JvmField
    val PINK_WOOL = CatalogRegistry.getProvider(ItemType::class, "pink_wool").get()

    @JvmField
    val GRAY_WOOL = CatalogRegistry.getProvider(ItemType::class, "gray_wool").get()

    @JvmField
    val LIGHT_GRAY_WOOL = CatalogRegistry.getProvider(ItemType::class, "light_gray_wool").get()

    @JvmField
    val CYAN_WOOL = CatalogRegistry.getProvider(ItemType::class, "cyan_wool").get()

    @JvmField
    val PURPLE_WOOL = CatalogRegistry.getProvider(ItemType::class, "purple_wool").get()

    @JvmField
    val BLUE_WOOL = CatalogRegistry.getProvider(ItemType::class, "blue_wool").get()

    @JvmField
    val BROWN_WOOL = CatalogRegistry.getProvider(ItemType::class, "brown_wool").get()

    @JvmField
    val GREEN_WOOL = CatalogRegistry.getProvider(ItemType::class, "green_wool").get()

    @JvmField
    val RED_WOOL = CatalogRegistry.getProvider(ItemType::class, "red_wool").get()

    @JvmField
    val BLACK_WOOL = CatalogRegistry.getProvider(ItemType::class, "black_wool").get()

    @JvmField
    val DANDELION = CatalogRegistry.getProvider(ItemType::class, "dandelion").get()

    @JvmField
    val POPPY = CatalogRegistry.getProvider(ItemType::class, "poppy").get()

    @JvmField
    val BLUE_ORCHID = CatalogRegistry.getProvider(ItemType::class, "blue_orchid").get()

    @JvmField
    val ALLIUM = CatalogRegistry.getProvider(ItemType::class, "allium").get()

    @JvmField
    val AZURE_BLUET = CatalogRegistry.getProvider(ItemType::class, "azure_bluet").get()

    @JvmField
    val RED_TULIP = CatalogRegistry.getProvider(ItemType::class, "red_tulip").get()

    @JvmField
    val ORANGE_TULIP = CatalogRegistry.getProvider(ItemType::class, "orange_tulip").get()

    @JvmField
    val WHITE_TULIP = CatalogRegistry.getProvider(ItemType::class, "white_tulip").get()

    @JvmField
    val PINK_TULIP = CatalogRegistry.getProvider(ItemType::class, "pink_tulip").get()

    @JvmField
    val OXEYE_DAISY = CatalogRegistry.getProvider(ItemType::class, "oxeye_daisy").get()

    @JvmField
    val CORNFLOWER = CatalogRegistry.getProvider(ItemType::class, "cornflower").get()

    @JvmField
    val LILY_OF_THE_VALLEY = CatalogRegistry.getProvider(ItemType::class, "lily_of_the_valley").get()

    @JvmField
    val WITHER_ROSE = CatalogRegistry.getProvider(ItemType::class, "wither_rose").get()

    @JvmField
    val BROWN_MUSHROOM = CatalogRegistry.getProvider(ItemType::class, "brown_mushroom").get()

    @JvmField
    val RED_MUSHROOM = CatalogRegistry.getProvider(ItemType::class, "red_mushroom").get()

    @JvmField
    val CRIMSON_FUNGUS = CatalogRegistry.getProvider(ItemType::class, "crimson_fungus").get()

    @JvmField
    val WARPED_FUNGUS = CatalogRegistry.getProvider(ItemType::class, "warped_fungus").get()

    @JvmField
    val CRIMSON_ROOTS = CatalogRegistry.getProvider(ItemType::class, "crimson_roots").get()

    @JvmField
    val WARPED_ROOTS = CatalogRegistry.getProvider(ItemType::class, "warped_roots").get()

    @JvmField
    val NETHER_SPROUTS = CatalogRegistry.getProvider(ItemType::class, "nether_sprouts").get()

    @JvmField
    val WEEPING_VINES = CatalogRegistry.getProvider(ItemType::class, "weeping_vines").get()

    @JvmField
    val TWISTING_VINES = CatalogRegistry.getProvider(ItemType::class, "twisting_vines").get()

    @JvmField
    val SUGAR_CANE = CatalogRegistry.getProvider(ItemType::class, "sugar_cane").get()

    @JvmField
    val KELP = CatalogRegistry.getProvider(ItemType::class, "kelp").get()

    @JvmField
    val BAMBOO = CatalogRegistry.getProvider(ItemType::class, "bamboo").get()

    @JvmField
    val GOLD_BLOCK = CatalogRegistry.getProvider(ItemType::class, "gold_block").get()

    @JvmField
    val IRON_BLOCK = CatalogRegistry.getProvider(ItemType::class, "iron_block").get()

    @JvmField
    val OAK_SLAB = CatalogRegistry.getProvider(ItemType::class, "oak_slab").get()

    @JvmField
    val SPRUCE_SLAB = CatalogRegistry.getProvider(ItemType::class, "spruce_slab").get()

    @JvmField
    val BIRCH_SLAB = CatalogRegistry.getProvider(ItemType::class, "birch_slab").get()

    @JvmField
    val JUNGLE_SLAB = CatalogRegistry.getProvider(ItemType::class, "jungle_slab").get()

    @JvmField
    val ACACIA_SLAB = CatalogRegistry.getProvider(ItemType::class, "acacia_slab").get()

    @JvmField
    val DARK_OAK_SLAB = CatalogRegistry.getProvider(ItemType::class, "dark_oak_slab").get()

    @JvmField
    val CRIMSON_SLAB = CatalogRegistry.getProvider(ItemType::class, "crimson_slab").get()

    @JvmField
    val WARPED_SLAB = CatalogRegistry.getProvider(ItemType::class, "warped_slab").get()

    @JvmField
    val STONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "stone_slab").get()

    @JvmField
    val SMOOTH_STONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "smooth_stone_slab").get()

    @JvmField
    val SANDSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "sandstone_slab").get()

    @JvmField
    val CUT_SANDSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "cut_sandstone_slab").get()

    @JvmField
    val PETRIFIED_OAK_SLAB = CatalogRegistry.getProvider(ItemType::class, "petrified_oak_slab").get()

    @JvmField
    val COBBLESTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "cobblestone_slab").get()

    @JvmField
    val BRICK_SLAB = CatalogRegistry.getProvider(ItemType::class, "brick_slab").get()

    @JvmField
    val STONE_BRICK_SLAB = CatalogRegistry.getProvider(ItemType::class, "stone_brick_slab").get()

    @JvmField
    val NETHER_BRICK_SLAB = CatalogRegistry.getProvider(ItemType::class, "nether_brick_slab").get()

    @JvmField
    val QUARTZ_SLAB = CatalogRegistry.getProvider(ItemType::class, "quartz_slab").get()

    @JvmField
    val RED_SANDSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "red_sandstone_slab").get()

    @JvmField
    val CUT_RED_SANDSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "cut_red_sandstone_slab").get()

    @JvmField
    val PURPUR_SLAB = CatalogRegistry.getProvider(ItemType::class, "purpur_slab").get()

    @JvmField
    val PRISMARINE_SLAB = CatalogRegistry.getProvider(ItemType::class, "prismarine_slab").get()

    @JvmField
    val PRISMARINE_BRICK_SLAB = CatalogRegistry.getProvider(ItemType::class, "prismarine_brick_slab").get()

    @JvmField
    val DARK_PRISMARINE_SLAB = CatalogRegistry.getProvider(ItemType::class, "dark_prismarine_slab").get()

    @JvmField
    val SMOOTH_QUARTZ = CatalogRegistry.getProvider(ItemType::class, "smooth_quartz").get()

    @JvmField
    val SMOOTH_RED_SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "smooth_red_sandstone").get()

    @JvmField
    val SMOOTH_SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "smooth_sandstone").get()

    @JvmField
    val SMOOTH_STONE = CatalogRegistry.getProvider(ItemType::class, "smooth_stone").get()

    @JvmField
    val BRICKS = CatalogRegistry.getProvider(ItemType::class, "bricks").get()

    @JvmField
    val TNT = CatalogRegistry.getProvider(ItemType::class, "tnt").get()

    @JvmField
    val BOOKSHELF = CatalogRegistry.getProvider(ItemType::class, "bookshelf").get()

    @JvmField
    val MOSSY_COBBLESTONE = CatalogRegistry.getProvider(ItemType::class, "mossy_cobblestone").get()

    @JvmField
    val OBSIDIAN = CatalogRegistry.getProvider(ItemType::class, "obsidian").get()

    @JvmField
    val TORCH = CatalogRegistry.getProvider(ItemType::class, "torch").get()

    @JvmField
    val END_ROD = CatalogRegistry.getProvider(ItemType::class, "end_rod").get()

    @JvmField
    val CHORUS_PLANT = CatalogRegistry.getProvider(ItemType::class, "chorus_plant").get()

    @JvmField
    val CHORUS_FLOWER = CatalogRegistry.getProvider(ItemType::class, "chorus_flower").get()

    @JvmField
    val PURPUR_BLOCK = CatalogRegistry.getProvider(ItemType::class, "purpur_block").get()

    @JvmField
    val PURPUR_PILLAR = CatalogRegistry.getProvider(ItemType::class, "purpur_pillar").get()

    @JvmField
    val PURPUR_STAIRS = CatalogRegistry.getProvider(ItemType::class, "purpur_stairs").get()

    @JvmField
    val SPAWNER = CatalogRegistry.getProvider(ItemType::class, "spawner").get()

    @JvmField
    val OAK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "oak_stairs").get()

    @JvmField
    val CHEST = CatalogRegistry.getProvider(ItemType::class, "chest").get()

    @JvmField
    val DIAMOND_ORE = CatalogRegistry.getProvider(ItemType::class, "diamond_ore").get()

    @JvmField
    val DIAMOND_BLOCK = CatalogRegistry.getProvider(ItemType::class, "diamond_block").get()

    @JvmField
    val CRAFTING_TABLE = CatalogRegistry.getProvider(ItemType::class, "crafting_table").get()

    @JvmField
    val FARMLAND = CatalogRegistry.getProvider(ItemType::class, "farmland").get()

    @JvmField
    val FURNACE = CatalogRegistry.getProvider(ItemType::class, "furnace").get()

    @JvmField
    val LADDER = CatalogRegistry.getProvider(ItemType::class, "ladder").get()

    @JvmField
    val RAIL = CatalogRegistry.getProvider(ItemType::class, "rail").get()

    @JvmField
    val COBBLESTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "cobblestone_stairs").get()

    @JvmField
    val LEVER = CatalogRegistry.getProvider(ItemType::class, "lever").get()

    @JvmField
    val STONE_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "stone_pressure_plate").get()

    @JvmField
    val OAK_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "oak_pressure_plate").get()

    @JvmField
    val SPRUCE_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "spruce_pressure_plate").get()

    @JvmField
    val BIRCH_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "birch_pressure_plate").get()

    @JvmField
    val JUNGLE_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "jungle_pressure_plate").get()

    @JvmField
    val ACACIA_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "acacia_pressure_plate").get()

    @JvmField
    val DARK_OAK_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "dark_oak_pressure_plate").get()

    @JvmField
    val CRIMSON_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "crimson_pressure_plate").get()

    @JvmField
    val WARPED_PRESSURE_PLATE = CatalogRegistry.getProvider(ItemType::class, "warped_pressure_plate").get()

    @JvmField
    val POLISHED_BLACKSTONE_PRESSURE_PLATE =
        CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_pressure_plate").get()

    @JvmField
    val REDSTONE_ORE = CatalogRegistry.getProvider(ItemType::class, "redstone_ore").get()

    @JvmField
    val REDSTONE_TORCH = CatalogRegistry.getProvider(ItemType::class, "redstone_torch").get()

    @JvmField
    val SNOW = CatalogRegistry.getProvider(ItemType::class, "snow").get()

    @JvmField
    val ICE = CatalogRegistry.getProvider(ItemType::class, "ice").get()

    @JvmField
    val SNOW_BLOCK = CatalogRegistry.getProvider(ItemType::class, "snow_block").get()

    @JvmField
    val CACTUS = CatalogRegistry.getProvider(ItemType::class, "cactus").get()

    @JvmField
    val CLAY = CatalogRegistry.getProvider(ItemType::class, "clay").get()

    @JvmField
    val JUKEBOX = CatalogRegistry.getProvider(ItemType::class, "jukebox").get()

    @JvmField
    val OAK_FENCE = CatalogRegistry.getProvider(ItemType::class, "oak_fence").get()

    @JvmField
    val SPRUCE_FENCE = CatalogRegistry.getProvider(ItemType::class, "spruce_fence").get()

    @JvmField
    val BIRCH_FENCE = CatalogRegistry.getProvider(ItemType::class, "birch_fence").get()

    @JvmField
    val JUNGLE_FENCE = CatalogRegistry.getProvider(ItemType::class, "jungle_fence").get()

    @JvmField
    val ACACIA_FENCE = CatalogRegistry.getProvider(ItemType::class, "acacia_fence").get()

    @JvmField
    val DARK_OAK_FENCE = CatalogRegistry.getProvider(ItemType::class, "dark_oak_fence").get()

    @JvmField
    val CRIMSON_FENCE = CatalogRegistry.getProvider(ItemType::class, "crimson_fence").get()

    @JvmField
    val WARPED_FENCE = CatalogRegistry.getProvider(ItemType::class, "warped_fence").get()

    @JvmField
    val PUMPKIN = CatalogRegistry.getProvider(ItemType::class, "pumpkin").get()

    @JvmField
    val CARVED_PUMPKIN = CatalogRegistry.getProvider(ItemType::class, "carved_pumpkin").get()

    @JvmField
    val NETHERRACK = CatalogRegistry.getProvider(ItemType::class, "netherrack").get()

    @JvmField
    val SOUL_SAND = CatalogRegistry.getProvider(ItemType::class, "soul_sand").get()

    @JvmField
    val SOUL_SOIL = CatalogRegistry.getProvider(ItemType::class, "soul_soil").get()

    @JvmField
    val BASALT = CatalogRegistry.getProvider(ItemType::class, "basalt").get()

    @JvmField
    val POLISHED_BASALT = CatalogRegistry.getProvider(ItemType::class, "polished_basalt").get()

    @JvmField
    val SOUL_TORCH = CatalogRegistry.getProvider(ItemType::class, "soul_torch").get()

    @JvmField
    val GLOWSTONE = CatalogRegistry.getProvider(ItemType::class, "glowstone").get()

    @JvmField
    val JACK_O_LANTERN = CatalogRegistry.getProvider(ItemType::class, "jack_o_lantern").get()

    @JvmField
    val OAK_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "oak_trapdoor").get()

    @JvmField
    val SPRUCE_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "spruce_trapdoor").get()

    @JvmField
    val BIRCH_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "birch_trapdoor").get()

    @JvmField
    val JUNGLE_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "jungle_trapdoor").get()

    @JvmField
    val ACACIA_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "acacia_trapdoor").get()

    @JvmField
    val DARK_OAK_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "dark_oak_trapdoor").get()

    @JvmField
    val CRIMSON_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "crimson_trapdoor").get()

    @JvmField
    val WARPED_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "warped_trapdoor").get()

    @JvmField
    val INFESTED_STONE = CatalogRegistry.getProvider(ItemType::class, "infested_stone").get()

    @JvmField
    val INFESTED_COBBLESTONE = CatalogRegistry.getProvider(ItemType::class, "infested_cobblestone").get()

    @JvmField
    val INFESTED_STONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "infested_stone_bricks").get()

    @JvmField
    val INFESTED_MOSSY_STONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "infested_mossy_stone_bricks").get()

    @JvmField
    val INFESTED_CRACKED_STONE_BRICKS =
        CatalogRegistry.getProvider(ItemType::class, "infested_cracked_stone_bricks").get()

    @JvmField
    val INFESTED_CHISELED_STONE_BRICKS =
        CatalogRegistry.getProvider(ItemType::class, "infested_chiseled_stone_bricks").get()

    @JvmField
    val STONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "stone_bricks").get()

    @JvmField
    val MOSSY_STONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "mossy_stone_bricks").get()

    @JvmField
    val CRACKED_STONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "cracked_stone_bricks").get()

    @JvmField
    val CHISELED_STONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "chiseled_stone_bricks").get()

    @JvmField
    val BROWN_MUSHROOM_BLOCK = CatalogRegistry.getProvider(ItemType::class, "brown_mushroom_block").get()

    @JvmField
    val RED_MUSHROOM_BLOCK = CatalogRegistry.getProvider(ItemType::class, "red_mushroom_block").get()

    @JvmField
    val MUSHROOM_STEM = CatalogRegistry.getProvider(ItemType::class, "mushroom_stem").get()

    @JvmField
    val IRON_BARS = CatalogRegistry.getProvider(ItemType::class, "iron_bars").get()

    @JvmField
    val CHAIN = CatalogRegistry.getProvider(ItemType::class, "chain").get()

    @JvmField
    val GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "glass_pane").get()

    @JvmField
    val MELON = CatalogRegistry.getProvider(ItemType::class, "melon").get()

    @JvmField
    val VINE = CatalogRegistry.getProvider(ItemType::class, "vine").get()

    @JvmField
    val OAK_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "oak_fence_gate").get()

    @JvmField
    val SPRUCE_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "spruce_fence_gate").get()

    @JvmField
    val BIRCH_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "birch_fence_gate").get()

    @JvmField
    val JUNGLE_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "jungle_fence_gate").get()

    @JvmField
    val ACACIA_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "acacia_fence_gate").get()

    @JvmField
    val DARK_OAK_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "dark_oak_fence_gate").get()

    @JvmField
    val CRIMSON_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "crimson_fence_gate").get()

    @JvmField
    val WARPED_FENCE_GATE = CatalogRegistry.getProvider(ItemType::class, "warped_fence_gate").get()

    @JvmField
    val BRICK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "brick_stairs").get()

    @JvmField
    val STONE_BRICK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "stone_brick_stairs").get()

    @JvmField
    val MYCELIUM = CatalogRegistry.getProvider(ItemType::class, "mycelium").get()

    @JvmField
    val LILY_PAD = CatalogRegistry.getProvider(ItemType::class, "lily_pad").get()

    @JvmField
    val NETHER_BRICKS = CatalogRegistry.getProvider(ItemType::class, "nether_bricks").get()

    @JvmField
    val CRACKED_NETHER_BRICKS = CatalogRegistry.getProvider(ItemType::class, "cracked_nether_bricks").get()

    @JvmField
    val CHISELED_NETHER_BRICKS = CatalogRegistry.getProvider(ItemType::class, "chiseled_nether_bricks").get()

    @JvmField
    val NETHER_BRICK_FENCE = CatalogRegistry.getProvider(ItemType::class, "nether_brick_fence").get()

    @JvmField
    val NETHER_BRICK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "nether_brick_stairs").get()

    @JvmField
    val ENCHANTING_TABLE = CatalogRegistry.getProvider(ItemType::class, "enchanting_table").get()

    @JvmField
    val END_PORTAL_FRAME = CatalogRegistry.getProvider(ItemType::class, "end_portal_frame").get()

    @JvmField
    val END_STONE = CatalogRegistry.getProvider(ItemType::class, "end_stone").get()

    @JvmField
    val END_STONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "end_stone_bricks").get()

    @JvmField
    val DRAGON_EGG = CatalogRegistry.getProvider(ItemType::class, "dragon_egg").get()

    @JvmField
    val REDSTONE_LAMP = CatalogRegistry.getProvider(ItemType::class, "redstone_lamp").get()

    @JvmField
    val SANDSTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "sandstone_stairs").get()

    @JvmField
    val EMERALD_ORE = CatalogRegistry.getProvider(ItemType::class, "emerald_ore").get()

    @JvmField
    val ENDER_CHEST = CatalogRegistry.getProvider(ItemType::class, "ender_chest").get()

    @JvmField
    val TRIPWIRE_HOOK = CatalogRegistry.getProvider(ItemType::class, "tripwire_hook").get()

    @JvmField
    val EMERALD_BLOCK = CatalogRegistry.getProvider(ItemType::class, "emerald_block").get()

    @JvmField
    val SPRUCE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "spruce_stairs").get()

    @JvmField
    val BIRCH_STAIRS = CatalogRegistry.getProvider(ItemType::class, "birch_stairs").get()

    @JvmField
    val JUNGLE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "jungle_stairs").get()

    @JvmField
    val CRIMSON_STAIRS = CatalogRegistry.getProvider(ItemType::class, "crimson_stairs").get()

    @JvmField
    val WARPED_STAIRS = CatalogRegistry.getProvider(ItemType::class, "warped_stairs").get()

    @JvmField
    val COMMAND_BLOCK = CatalogRegistry.getProvider(ItemType::class, "command_block").get()

    @JvmField
    val BEACON = CatalogRegistry.getProvider(ItemType::class, "beacon").get()

    @JvmField
    val COBBLESTONE_WALL = CatalogRegistry.getProvider(ItemType::class, "cobblestone_wall").get()

    @JvmField
    val MOSSY_COBBLESTONE_WALL = CatalogRegistry.getProvider(ItemType::class, "mossy_cobblestone_wall").get()

    @JvmField
    val BRICK_WALL = CatalogRegistry.getProvider(ItemType::class, "brick_wall").get()

    @JvmField
    val PRISMARINE_WALL = CatalogRegistry.getProvider(ItemType::class, "prismarine_wall").get()

    @JvmField
    val RED_SANDSTONE_WALL = CatalogRegistry.getProvider(ItemType::class, "red_sandstone_wall").get()

    @JvmField
    val MOSSY_STONE_BRICK_WALL = CatalogRegistry.getProvider(ItemType::class, "mossy_stone_brick_wall").get()

    @JvmField
    val GRANITE_WALL = CatalogRegistry.getProvider(ItemType::class, "granite_wall").get()

    @JvmField
    val STONE_BRICK_WALL = CatalogRegistry.getProvider(ItemType::class, "stone_brick_wall").get()

    @JvmField
    val NETHER_BRICK_WALL = CatalogRegistry.getProvider(ItemType::class, "nether_brick_wall").get()

    @JvmField
    val ANDESITE_WALL = CatalogRegistry.getProvider(ItemType::class, "andesite_wall").get()

    @JvmField
    val RED_NETHER_BRICK_WALL = CatalogRegistry.getProvider(ItemType::class, "red_nether_brick_wall").get()

    @JvmField
    val SANDSTONE_WALL = CatalogRegistry.getProvider(ItemType::class, "sandstone_wall").get()

    @JvmField
    val END_STONE_BRICK_WALL = CatalogRegistry.getProvider(ItemType::class, "end_stone_brick_wall").get()

    @JvmField
    val DIORITE_WALL = CatalogRegistry.getProvider(ItemType::class, "diorite_wall").get()

    @JvmField
    val BLACKSTONE_WALL = CatalogRegistry.getProvider(ItemType::class, "blackstone_wall").get()

    @JvmField
    val POLISHED_BLACKSTONE_WALL = CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_wall").get()

    @JvmField
    val POLISHED_BLACKSTONE_BRICK_WALL =
        CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_brick_wall").get()

    @JvmField
    val STONE_BUTTON = CatalogRegistry.getProvider(ItemType::class, "stone_button").get()

    @JvmField
    val OAK_BUTTON = CatalogRegistry.getProvider(ItemType::class, "oak_button").get()

    @JvmField
    val SPRUCE_BUTTON = CatalogRegistry.getProvider(ItemType::class, "spruce_button").get()

    @JvmField
    val BIRCH_BUTTON = CatalogRegistry.getProvider(ItemType::class, "birch_button").get()

    @JvmField
    val JUNGLE_BUTTON = CatalogRegistry.getProvider(ItemType::class, "jungle_button").get()

    @JvmField
    val ACACIA_BUTTON = CatalogRegistry.getProvider(ItemType::class, "acacia_button").get()

    @JvmField
    val DARK_OAK_BUTTON = CatalogRegistry.getProvider(ItemType::class, "dark_oak_button").get()

    @JvmField
    val CRIMSON_BUTTON = CatalogRegistry.getProvider(ItemType::class, "crimson_button").get()

    @JvmField
    val WARPED_BUTTON = CatalogRegistry.getProvider(ItemType::class, "warped_button").get()

    @JvmField
    val POLISHED_BLACKSTONE_BUTTON = CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_button").get()

    @JvmField
    val ANVIL = CatalogRegistry.getProvider(ItemType::class, "anvil").get()

    @JvmField
    val CHIPPED_ANVIL = CatalogRegistry.getProvider(ItemType::class, "chipped_anvil").get()

    @JvmField
    val DAMAGED_ANVIL = CatalogRegistry.getProvider(ItemType::class, "damaged_anvil").get()

    @JvmField
    val TRAPPED_CHEST = CatalogRegistry.getProvider(ItemType::class, "trapped_chest").get()

    @JvmField
    val LIGHT_WEIGHTED_PRESSURE_PLATE =
        CatalogRegistry.getProvider(ItemType::class, "light_weighted_pressure_plate").get()

    @JvmField
    val HEAVY_WEIGHTED_PRESSURE_PLATE =
        CatalogRegistry.getProvider(ItemType::class, "heavy_weighted_pressure_plate").get()

    @JvmField
    val DAYLIGHT_DETECTOR = CatalogRegistry.getProvider(ItemType::class, "daylight_detector").get()

    @JvmField
    val REDSTONE_BLOCK = CatalogRegistry.getProvider(ItemType::class, "redstone_block").get()

    @JvmField
    val NETHER_QUARTZ_ORE = CatalogRegistry.getProvider(ItemType::class, "nether_quartz_ore").get()

    @JvmField
    val HOPPER = CatalogRegistry.getProvider(ItemType::class, "hopper").get()

    @JvmField
    val CHISELED_QUARTZ_BLOCK = CatalogRegistry.getProvider(ItemType::class, "chiseled_quartz_block").get()

    @JvmField
    val QUARTZ_BLOCK = CatalogRegistry.getProvider(ItemType::class, "quartz_block").get()

    @JvmField
    val QUARTZ_BRICKS = CatalogRegistry.getProvider(ItemType::class, "quartz_bricks").get()

    @JvmField
    val QUARTZ_PILLAR = CatalogRegistry.getProvider(ItemType::class, "quartz_pillar").get()

    @JvmField
    val QUARTZ_STAIRS = CatalogRegistry.getProvider(ItemType::class, "quartz_stairs").get()

    @JvmField
    val ACTIVATOR_RAIL = CatalogRegistry.getProvider(ItemType::class, "activator_rail").get()

    @JvmField
    val DROPPER = CatalogRegistry.getProvider(ItemType::class, "dropper").get()

    @JvmField
    val WHITE_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "white_terracotta").get()

    @JvmField
    val ORANGE_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "orange_terracotta").get()

    @JvmField
    val MAGENTA_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "magenta_terracotta").get()

    @JvmField
    val LIGHT_BLUE_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "light_blue_terracotta").get()

    @JvmField
    val YELLOW_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "yellow_terracotta").get()

    @JvmField
    val LIME_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "lime_terracotta").get()

    @JvmField
    val PINK_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "pink_terracotta").get()

    @JvmField
    val GRAY_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "gray_terracotta").get()

    @JvmField
    val LIGHT_GRAY_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "light_gray_terracotta").get()

    @JvmField
    val CYAN_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "cyan_terracotta").get()

    @JvmField
    val PURPLE_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "purple_terracotta").get()

    @JvmField
    val BLUE_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "blue_terracotta").get()

    @JvmField
    val BROWN_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "brown_terracotta").get()

    @JvmField
    val GREEN_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "green_terracotta").get()

    @JvmField
    val RED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "red_terracotta").get()

    @JvmField
    val BLACK_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "black_terracotta").get()

    @JvmField
    val BARRIER = CatalogRegistry.getProvider(ItemType::class, "barrier").get()

    @JvmField
    val IRON_TRAPDOOR = CatalogRegistry.getProvider(ItemType::class, "iron_trapdoor").get()

    @JvmField
    val HAY_BLOCK = CatalogRegistry.getProvider(ItemType::class, "hay_block").get()

    @JvmField
    val WHITE_CARPET = CatalogRegistry.getProvider(ItemType::class, "white_carpet").get()

    @JvmField
    val ORANGE_CARPET = CatalogRegistry.getProvider(ItemType::class, "orange_carpet").get()

    @JvmField
    val MAGENTA_CARPET = CatalogRegistry.getProvider(ItemType::class, "magenta_carpet").get()

    @JvmField
    val LIGHT_BLUE_CARPET = CatalogRegistry.getProvider(ItemType::class, "light_blue_carpet").get()

    @JvmField
    val YELLOW_CARPET = CatalogRegistry.getProvider(ItemType::class, "yellow_carpet").get()

    @JvmField
    val LIME_CARPET = CatalogRegistry.getProvider(ItemType::class, "lime_carpet").get()

    @JvmField
    val PINK_CARPET = CatalogRegistry.getProvider(ItemType::class, "pink_carpet").get()

    @JvmField
    val GRAY_CARPET = CatalogRegistry.getProvider(ItemType::class, "gray_carpet").get()

    @JvmField
    val LIGHT_GRAY_CARPET = CatalogRegistry.getProvider(ItemType::class, "light_gray_carpet").get()

    @JvmField
    val CYAN_CARPET = CatalogRegistry.getProvider(ItemType::class, "cyan_carpet").get()

    @JvmField
    val PURPLE_CARPET = CatalogRegistry.getProvider(ItemType::class, "purple_carpet").get()

    @JvmField
    val BLUE_CARPET = CatalogRegistry.getProvider(ItemType::class, "blue_carpet").get()

    @JvmField
    val BROWN_CARPET = CatalogRegistry.getProvider(ItemType::class, "brown_carpet").get()

    @JvmField
    val GREEN_CARPET = CatalogRegistry.getProvider(ItemType::class, "green_carpet").get()

    @JvmField
    val RED_CARPET = CatalogRegistry.getProvider(ItemType::class, "red_carpet").get()

    @JvmField
    val BLACK_CARPET = CatalogRegistry.getProvider(ItemType::class, "black_carpet").get()

    @JvmField
    val TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "terracotta").get()

    @JvmField
    val COAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "coal_block").get()

    @JvmField
    val PACKED_ICE = CatalogRegistry.getProvider(ItemType::class, "packed_ice").get()

    @JvmField
    val ACACIA_STAIRS = CatalogRegistry.getProvider(ItemType::class, "acacia_stairs").get()

    @JvmField
    val DARK_OAK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "dark_oak_stairs").get()

    @JvmField
    val SLIME_BLOCK = CatalogRegistry.getProvider(ItemType::class, "slime_block").get()

    @JvmField
    val GRASS_PATH = CatalogRegistry.getProvider(ItemType::class, "grass_path").get()

    @JvmField
    val SUNFLOWER = CatalogRegistry.getProvider(ItemType::class, "sunflower").get()

    @JvmField
    val LILAC = CatalogRegistry.getProvider(ItemType::class, "lilac").get()

    @JvmField
    val ROSE_BUSH = CatalogRegistry.getProvider(ItemType::class, "rose_bush").get()

    @JvmField
    val PEONY = CatalogRegistry.getProvider(ItemType::class, "peony").get()

    @JvmField
    val TALL_GRASS = CatalogRegistry.getProvider(ItemType::class, "tall_grass").get()

    @JvmField
    val LARGE_FERN = CatalogRegistry.getProvider(ItemType::class, "large_fern").get()

    @JvmField
    val WHITE_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "white_stained_glass").get()

    @JvmField
    val ORANGE_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "orange_stained_glass").get()

    @JvmField
    val MAGENTA_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "magenta_stained_glass").get()

    @JvmField
    val LIGHT_BLUE_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "light_blue_stained_glass").get()

    @JvmField
    val YELLOW_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "yellow_stained_glass").get()

    @JvmField
    val LIME_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "lime_stained_glass").get()

    @JvmField
    val PINK_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "pink_stained_glass").get()

    @JvmField
    val GRAY_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "gray_stained_glass").get()

    @JvmField
    val LIGHT_GRAY_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "light_gray_stained_glass").get()

    @JvmField
    val CYAN_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "cyan_stained_glass").get()

    @JvmField
    val PURPLE_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "purple_stained_glass").get()

    @JvmField
    val BLUE_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "blue_stained_glass").get()

    @JvmField
    val BROWN_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "brown_stained_glass").get()

    @JvmField
    val GREEN_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "green_stained_glass").get()

    @JvmField
    val RED_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "red_stained_glass").get()

    @JvmField
    val BLACK_STAINED_GLASS = CatalogRegistry.getProvider(ItemType::class, "black_stained_glass").get()

    @JvmField
    val WHITE_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "white_stained_glass_pane").get()

    @JvmField
    val ORANGE_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "orange_stained_glass_pane").get()

    @JvmField
    val MAGENTA_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "magenta_stained_glass_pane").get()

    @JvmField
    val LIGHT_BLUE_STAINED_GLASS_PANE =
        CatalogRegistry.getProvider(ItemType::class, "light_blue_stained_glass_pane").get()

    @JvmField
    val YELLOW_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "yellow_stained_glass_pane").get()

    @JvmField
    val LIME_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "lime_stained_glass_pane").get()

    @JvmField
    val PINK_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "pink_stained_glass_pane").get()

    @JvmField
    val GRAY_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "gray_stained_glass_pane").get()

    @JvmField
    val LIGHT_GRAY_STAINED_GLASS_PANE =
        CatalogRegistry.getProvider(ItemType::class, "light_gray_stained_glass_pane").get()

    @JvmField
    val CYAN_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "cyan_stained_glass_pane").get()

    @JvmField
    val PURPLE_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "purple_stained_glass_pane").get()

    @JvmField
    val BLUE_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "blue_stained_glass_pane").get()

    @JvmField
    val BROWN_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "brown_stained_glass_pane").get()

    @JvmField
    val GREEN_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "green_stained_glass_pane").get()

    @JvmField
    val RED_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "red_stained_glass_pane").get()

    @JvmField
    val BLACK_STAINED_GLASS_PANE = CatalogRegistry.getProvider(ItemType::class, "black_stained_glass_pane").get()

    @JvmField
    val PRISMARINE = CatalogRegistry.getProvider(ItemType::class, "prismarine").get()

    @JvmField
    val PRISMARINE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "prismarine_bricks").get()

    @JvmField
    val DARK_PRISMARINE = CatalogRegistry.getProvider(ItemType::class, "dark_prismarine").get()

    @JvmField
    val PRISMARINE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "prismarine_stairs").get()

    @JvmField
    val PRISMARINE_BRICK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "prismarine_brick_stairs").get()

    @JvmField
    val DARK_PRISMARINE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "dark_prismarine_stairs").get()

    @JvmField
    val SEA_LANTERN = CatalogRegistry.getProvider(ItemType::class, "sea_lantern").get()

    @JvmField
    val RED_SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "red_sandstone").get()

    @JvmField
    val CHISELED_RED_SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "chiseled_red_sandstone").get()

    @JvmField
    val CUT_RED_SANDSTONE = CatalogRegistry.getProvider(ItemType::class, "cut_red_sandstone").get()

    @JvmField
    val RED_SANDSTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "red_sandstone_stairs").get()

    @JvmField
    val REPEATING_COMMAND_BLOCK = CatalogRegistry.getProvider(ItemType::class, "repeating_command_block").get()

    @JvmField
    val CHAIN_COMMAND_BLOCK = CatalogRegistry.getProvider(ItemType::class, "chain_command_block").get()

    @JvmField
    val MAGMA_BLOCK = CatalogRegistry.getProvider(ItemType::class, "magma_block").get()

    @JvmField
    val NETHER_WART_BLOCK = CatalogRegistry.getProvider(ItemType::class, "nether_wart_block").get()

    @JvmField
    val WARPED_WART_BLOCK = CatalogRegistry.getProvider(ItemType::class, "warped_wart_block").get()

    @JvmField
    val RED_NETHER_BRICKS = CatalogRegistry.getProvider(ItemType::class, "red_nether_bricks").get()

    @JvmField
    val BONE_BLOCK = CatalogRegistry.getProvider(ItemType::class, "bone_block").get()

    @JvmField
    val STRUCTURE_VOID = CatalogRegistry.getProvider(ItemType::class, "structure_void").get()

    @JvmField
    val OBSERVER = CatalogRegistry.getProvider(ItemType::class, "observer").get()

    @JvmField
    val SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "shulker_box").get()

    @JvmField
    val WHITE_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "white_shulker_box").get()

    @JvmField
    val ORANGE_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "orange_shulker_box").get()

    @JvmField
    val MAGENTA_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "magenta_shulker_box").get()

    @JvmField
    val LIGHT_BLUE_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "light_blue_shulker_box").get()

    @JvmField
    val YELLOW_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "yellow_shulker_box").get()

    @JvmField
    val LIME_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "lime_shulker_box").get()

    @JvmField
    val PINK_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "pink_shulker_box").get()

    @JvmField
    val GRAY_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "gray_shulker_box").get()

    @JvmField
    val LIGHT_GRAY_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "light_gray_shulker_box").get()

    @JvmField
    val CYAN_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "cyan_shulker_box").get()

    @JvmField
    val PURPLE_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "purple_shulker_box").get()

    @JvmField
    val BLUE_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "blue_shulker_box").get()

    @JvmField
    val BROWN_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "brown_shulker_box").get()

    @JvmField
    val GREEN_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "green_shulker_box").get()

    @JvmField
    val RED_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "red_shulker_box").get()

    @JvmField
    val BLACK_SHULKER_BOX = CatalogRegistry.getProvider(ItemType::class, "black_shulker_box").get()

    @JvmField
    val WHITE_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "white_glazed_terracotta").get()

    @JvmField
    val ORANGE_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "orange_glazed_terracotta").get()

    @JvmField
    val MAGENTA_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "magenta_glazed_terracotta").get()

    @JvmField
    val LIGHT_BLUE_GLAZED_TERRACOTTA =
        CatalogRegistry.getProvider(ItemType::class, "light_blue_glazed_terracotta").get()

    @JvmField
    val YELLOW_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "yellow_glazed_terracotta").get()

    @JvmField
    val LIME_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "lime_glazed_terracotta").get()

    @JvmField
    val PINK_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "pink_glazed_terracotta").get()

    @JvmField
    val GRAY_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "gray_glazed_terracotta").get()

    @JvmField
    val LIGHT_GRAY_GLAZED_TERRACOTTA =
        CatalogRegistry.getProvider(ItemType::class, "light_gray_glazed_terracotta").get()

    @JvmField
    val CYAN_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "cyan_glazed_terracotta").get()

    @JvmField
    val PURPLE_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "purple_glazed_terracotta").get()

    @JvmField
    val BLUE_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "blue_glazed_terracotta").get()

    @JvmField
    val BROWN_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "brown_glazed_terracotta").get()

    @JvmField
    val GREEN_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "green_glazed_terracotta").get()

    @JvmField
    val RED_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "red_glazed_terracotta").get()

    @JvmField
    val BLACK_GLAZED_TERRACOTTA = CatalogRegistry.getProvider(ItemType::class, "black_glazed_terracotta").get()

    @JvmField
    val WHITE_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "white_concrete").get()

    @JvmField
    val ORANGE_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "orange_concrete").get()

    @JvmField
    val MAGENTA_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "magenta_concrete").get()

    @JvmField
    val LIGHT_BLUE_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "light_blue_concrete").get()

    @JvmField
    val YELLOW_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "yellow_concrete").get()

    @JvmField
    val LIME_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "lime_concrete").get()

    @JvmField
    val PINK_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "pink_concrete").get()

    @JvmField
    val GRAY_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "gray_concrete").get()

    @JvmField
    val LIGHT_GRAY_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "light_gray_concrete").get()

    @JvmField
    val CYAN_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "cyan_concrete").get()

    @JvmField
    val PURPLE_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "purple_concrete").get()

    @JvmField
    val BLUE_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "blue_concrete").get()

    @JvmField
    val BROWN_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "brown_concrete").get()

    @JvmField
    val GREEN_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "green_concrete").get()

    @JvmField
    val RED_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "red_concrete").get()

    @JvmField
    val BLACK_CONCRETE = CatalogRegistry.getProvider(ItemType::class, "black_concrete").get()

    @JvmField
    val WHITE_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "white_concrete_powder").get()

    @JvmField
    val ORANGE_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "orange_concrete_powder").get()

    @JvmField
    val MAGENTA_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "magenta_concrete_powder").get()

    @JvmField
    val LIGHT_BLUE_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "light_blue_concrete_powder").get()

    @JvmField
    val YELLOW_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "yellow_concrete_powder").get()

    @JvmField
    val LIME_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "lime_concrete_powder").get()

    @JvmField
    val PINK_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "pink_concrete_powder").get()

    @JvmField
    val GRAY_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "gray_concrete_powder").get()

    @JvmField
    val LIGHT_GRAY_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "light_gray_concrete_powder").get()

    @JvmField
    val CYAN_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "cyan_concrete_powder").get()

    @JvmField
    val PURPLE_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "purple_concrete_powder").get()

    @JvmField
    val BLUE_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "blue_concrete_powder").get()

    @JvmField
    val BROWN_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "brown_concrete_powder").get()

    @JvmField
    val GREEN_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "green_concrete_powder").get()

    @JvmField
    val RED_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "red_concrete_powder").get()

    @JvmField
    val BLACK_CONCRETE_POWDER = CatalogRegistry.getProvider(ItemType::class, "black_concrete_powder").get()

    @JvmField
    val TURTLE_EGG = CatalogRegistry.getProvider(ItemType::class, "turtle_egg").get()

    @JvmField
    val DEAD_TUBE_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "dead_tube_coral_block").get()

    @JvmField
    val DEAD_BRAIN_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "dead_brain_coral_block").get()

    @JvmField
    val DEAD_BUBBLE_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "dead_bubble_coral_block").get()

    @JvmField
    val DEAD_FIRE_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "dead_fire_coral_block").get()

    @JvmField
    val DEAD_HORN_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "dead_horn_coral_block").get()

    @JvmField
    val TUBE_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "tube_coral_block").get()

    @JvmField
    val BRAIN_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "brain_coral_block").get()

    @JvmField
    val BUBBLE_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "bubble_coral_block").get()

    @JvmField
    val FIRE_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "fire_coral_block").get()

    @JvmField
    val HORN_CORAL_BLOCK = CatalogRegistry.getProvider(ItemType::class, "horn_coral_block").get()

    @JvmField
    val TUBE_CORAL = CatalogRegistry.getProvider(ItemType::class, "tube_coral").get()

    @JvmField
    val BRAIN_CORAL = CatalogRegistry.getProvider(ItemType::class, "brain_coral").get()

    @JvmField
    val BUBBLE_CORAL = CatalogRegistry.getProvider(ItemType::class, "bubble_coral").get()

    @JvmField
    val FIRE_CORAL = CatalogRegistry.getProvider(ItemType::class, "fire_coral").get()

    @JvmField
    val HORN_CORAL = CatalogRegistry.getProvider(ItemType::class, "horn_coral").get()

    @JvmField
    val DEAD_BRAIN_CORAL = CatalogRegistry.getProvider(ItemType::class, "dead_brain_coral").get()

    @JvmField
    val DEAD_BUBBLE_CORAL = CatalogRegistry.getProvider(ItemType::class, "dead_bubble_coral").get()

    @JvmField
    val DEAD_FIRE_CORAL = CatalogRegistry.getProvider(ItemType::class, "dead_fire_coral").get()

    @JvmField
    val DEAD_HORN_CORAL = CatalogRegistry.getProvider(ItemType::class, "dead_horn_coral").get()

    @JvmField
    val DEAD_TUBE_CORAL = CatalogRegistry.getProvider(ItemType::class, "dead_tube_coral").get()

    @JvmField
    val TUBE_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "tube_coral_fan").get()

    @JvmField
    val BRAIN_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "brain_coral_fan").get()

    @JvmField
    val BUBBLE_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "bubble_coral_fan").get()

    @JvmField
    val FIRE_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "fire_coral_fan").get()

    @JvmField
    val HORN_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "horn_coral_fan").get()

    @JvmField
    val DEAD_TUBE_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "dead_tube_coral_fan").get()

    @JvmField
    val DEAD_BRAIN_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "dead_brain_coral_fan").get()

    @JvmField
    val DEAD_BUBBLE_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "dead_bubble_coral_fan").get()

    @JvmField
    val DEAD_FIRE_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "dead_fire_coral_fan").get()

    @JvmField
    val DEAD_HORN_CORAL_FAN = CatalogRegistry.getProvider(ItemType::class, "dead_horn_coral_fan").get()

    @JvmField
    val BLUE_ICE = CatalogRegistry.getProvider(ItemType::class, "blue_ice").get()

    @JvmField
    val CONDUIT = CatalogRegistry.getProvider(ItemType::class, "conduit").get()

    @JvmField
    val POLISHED_GRANITE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "polished_granite_stairs").get()

    @JvmField
    val SMOOTH_RED_SANDSTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "smooth_red_sandstone_stairs").get()

    @JvmField
    val MOSSY_STONE_BRICK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "mossy_stone_brick_stairs").get()

    @JvmField
    val POLISHED_DIORITE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "polished_diorite_stairs").get()

    @JvmField
    val MOSSY_COBBLESTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "mossy_cobblestone_stairs").get()

    @JvmField
    val END_STONE_BRICK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "end_stone_brick_stairs").get()

    @JvmField
    val STONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "stone_stairs").get()

    @JvmField
    val SMOOTH_SANDSTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "smooth_sandstone_stairs").get()

    @JvmField
    val SMOOTH_QUARTZ_STAIRS = CatalogRegistry.getProvider(ItemType::class, "smooth_quartz_stairs").get()

    @JvmField
    val GRANITE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "granite_stairs").get()

    @JvmField
    val ANDESITE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "andesite_stairs").get()

    @JvmField
    val RED_NETHER_BRICK_STAIRS = CatalogRegistry.getProvider(ItemType::class, "red_nether_brick_stairs").get()

    @JvmField
    val POLISHED_ANDESITE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "polished_andesite_stairs").get()

    @JvmField
    val DIORITE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "diorite_stairs").get()

    @JvmField
    val POLISHED_GRANITE_SLAB = CatalogRegistry.getProvider(ItemType::class, "polished_granite_slab").get()

    @JvmField
    val SMOOTH_RED_SANDSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "smooth_red_sandstone_slab").get()

    @JvmField
    val MOSSY_STONE_BRICK_SLAB = CatalogRegistry.getProvider(ItemType::class, "mossy_stone_brick_slab").get()

    @JvmField
    val POLISHED_DIORITE_SLAB = CatalogRegistry.getProvider(ItemType::class, "polished_diorite_slab").get()

    @JvmField
    val MOSSY_COBBLESTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "mossy_cobblestone_slab").get()

    @JvmField
    val END_STONE_BRICK_SLAB = CatalogRegistry.getProvider(ItemType::class, "end_stone_brick_slab").get()

    @JvmField
    val SMOOTH_SANDSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "smooth_sandstone_slab").get()

    @JvmField
    val SMOOTH_QUARTZ_SLAB = CatalogRegistry.getProvider(ItemType::class, "smooth_quartz_slab").get()

    @JvmField
    val GRANITE_SLAB = CatalogRegistry.getProvider(ItemType::class, "granite_slab").get()

    @JvmField
    val ANDESITE_SLAB = CatalogRegistry.getProvider(ItemType::class, "andesite_slab").get()

    @JvmField
    val RED_NETHER_BRICK_SLAB = CatalogRegistry.getProvider(ItemType::class, "red_nether_brick_slab").get()

    @JvmField
    val POLISHED_ANDESITE_SLAB = CatalogRegistry.getProvider(ItemType::class, "polished_andesite_slab").get()

    @JvmField
    val DIORITE_SLAB = CatalogRegistry.getProvider(ItemType::class, "diorite_slab").get()

    @JvmField
    val SCAFFOLDING = CatalogRegistry.getProvider(ItemType::class, "scaffolding").get()

    @JvmField
    val IRON_DOOR = CatalogRegistry.getProvider(ItemType::class, "iron_door").get()

    @JvmField
    val OAK_DOOR = CatalogRegistry.getProvider(ItemType::class, "oak_door").get()

    @JvmField
    val SPRUCE_DOOR = CatalogRegistry.getProvider(ItemType::class, "spruce_door").get()

    @JvmField
    val BIRCH_DOOR = CatalogRegistry.getProvider(ItemType::class, "birch_door").get()

    @JvmField
    val JUNGLE_DOOR = CatalogRegistry.getProvider(ItemType::class, "jungle_door").get()

    @JvmField
    val ACACIA_DOOR = CatalogRegistry.getProvider(ItemType::class, "acacia_door").get()

    @JvmField
    val DARK_OAK_DOOR = CatalogRegistry.getProvider(ItemType::class, "dark_oak_door").get()

    @JvmField
    val CRIMSON_DOOR = CatalogRegistry.getProvider(ItemType::class, "crimson_door").get()

    @JvmField
    val WARPED_DOOR = CatalogRegistry.getProvider(ItemType::class, "warped_door").get()

    @JvmField
    val REPEATER = CatalogRegistry.getProvider(ItemType::class, "repeater").get()

    @JvmField
    val COMPARATOR = CatalogRegistry.getProvider(ItemType::class, "comparator").get()

    @JvmField
    val STRUCTURE_BLOCK = CatalogRegistry.getProvider(ItemType::class, "structure_block").get()

    @JvmField
    val JIGSAW = CatalogRegistry.getProvider(ItemType::class, "jigsaw").get()

    @JvmField
    val TURTLE_HELMET = CatalogRegistry.getProvider(ItemType::class, "turtle_helmet").get()

    @JvmField
    val SCUTE = CatalogRegistry.getProvider(ItemType::class, "scute").get()

    @JvmField
    val FLINT_AND_STEEL = CatalogRegistry.getProvider(ItemType::class, "flint_and_steel").get()

    @JvmField
    val APPLE = CatalogRegistry.getProvider(ItemType::class, "apple").get()

    @JvmField
    val BOW = CatalogRegistry.getProvider(ItemType::class, "bow").get()

    @JvmField
    val ARROW = CatalogRegistry.getProvider(ItemType::class, "arrow").get()

    @JvmField
    val COAL = CatalogRegistry.getProvider(ItemType::class, "coal").get()

    @JvmField
    val CHARCOAL = CatalogRegistry.getProvider(ItemType::class, "charcoal").get()

    @JvmField
    val DIAMOND = CatalogRegistry.getProvider(ItemType::class, "diamond").get()

    @JvmField
    val IRON_INGOT = CatalogRegistry.getProvider(ItemType::class, "iron_ingot").get()

    @JvmField
    val GOLD_INGOT = CatalogRegistry.getProvider(ItemType::class, "gold_ingot").get()

    @JvmField
    val NETHERITE_INGOT = CatalogRegistry.getProvider(ItemType::class, "netherite_ingot").get()

    @JvmField
    val NETHERITE_SCRAP = CatalogRegistry.getProvider(ItemType::class, "netherite_scrap").get()

    @JvmField
    val WOODEN_SWORD = CatalogRegistry.getProvider(ItemType::class, "wooden_sword").get()

    @JvmField
    val WOODEN_SHOVEL = CatalogRegistry.getProvider(ItemType::class, "wooden_shovel").get()

    @JvmField
    val WOODEN_PICKAXE = CatalogRegistry.getProvider(ItemType::class, "wooden_pickaxe").get()

    @JvmField
    val WOODEN_AXE = CatalogRegistry.getProvider(ItemType::class, "wooden_axe").get()

    @JvmField
    val WOODEN_HOE = CatalogRegistry.getProvider(ItemType::class, "wooden_hoe").get()

    @JvmField
    val STONE_SWORD = CatalogRegistry.getProvider(ItemType::class, "stone_sword").get()

    @JvmField
    val STONE_SHOVEL = CatalogRegistry.getProvider(ItemType::class, "stone_shovel").get()

    @JvmField
    val STONE_PICKAXE = CatalogRegistry.getProvider(ItemType::class, "stone_pickaxe").get()

    @JvmField
    val STONE_AXE = CatalogRegistry.getProvider(ItemType::class, "stone_axe").get()

    @JvmField
    val STONE_HOE = CatalogRegistry.getProvider(ItemType::class, "stone_hoe").get()

    @JvmField
    val GOLDEN_SWORD = CatalogRegistry.getProvider(ItemType::class, "golden_sword").get()

    @JvmField
    val GOLDEN_SHOVEL = CatalogRegistry.getProvider(ItemType::class, "golden_shovel").get()

    @JvmField
    val GOLDEN_PICKAXE = CatalogRegistry.getProvider(ItemType::class, "golden_pickaxe").get()

    @JvmField
    val GOLDEN_AXE = CatalogRegistry.getProvider(ItemType::class, "golden_axe").get()

    @JvmField
    val GOLDEN_HOE = CatalogRegistry.getProvider(ItemType::class, "golden_hoe").get()

    @JvmField
    val IRON_SWORD = CatalogRegistry.getProvider(ItemType::class, "iron_sword").get()

    @JvmField
    val IRON_SHOVEL = CatalogRegistry.getProvider(ItemType::class, "iron_shovel").get()

    @JvmField
    val IRON_PICKAXE = CatalogRegistry.getProvider(ItemType::class, "iron_pickaxe").get()

    @JvmField
    val IRON_AXE = CatalogRegistry.getProvider(ItemType::class, "iron_axe").get()

    @JvmField
    val IRON_HOE = CatalogRegistry.getProvider(ItemType::class, "iron_hoe").get()

    @JvmField
    val DIAMOND_SWORD = CatalogRegistry.getProvider(ItemType::class, "diamond_sword").get()

    @JvmField
    val DIAMOND_SHOVEL = CatalogRegistry.getProvider(ItemType::class, "diamond_shovel").get()

    @JvmField
    val DIAMOND_PICKAXE = CatalogRegistry.getProvider(ItemType::class, "diamond_pickaxe").get()

    @JvmField
    val DIAMOND_AXE = CatalogRegistry.getProvider(ItemType::class, "diamond_axe").get()

    @JvmField
    val DIAMOND_HOE = CatalogRegistry.getProvider(ItemType::class, "diamond_hoe").get()

    @JvmField
    val NETHERITE_SWORD = CatalogRegistry.getProvider(ItemType::class, "netherite_sword").get()

    @JvmField
    val NETHERITE_SHOVEL = CatalogRegistry.getProvider(ItemType::class, "netherite_shovel").get()

    @JvmField
    val NETHERITE_PICKAXE = CatalogRegistry.getProvider(ItemType::class, "netherite_pickaxe").get()

    @JvmField
    val NETHERITE_AXE = CatalogRegistry.getProvider(ItemType::class, "netherite_axe").get()

    @JvmField
    val NETHERITE_HOE = CatalogRegistry.getProvider(ItemType::class, "netherite_hoe").get()

    @JvmField
    val STICK = CatalogRegistry.getProvider(ItemType::class, "stick").get()

    @JvmField
    val BOWL = CatalogRegistry.getProvider(ItemType::class, "bowl").get()

    @JvmField
    val MUSHROOM_STEW = CatalogRegistry.getProvider(ItemType::class, "mushroom_stew").get()

    @JvmField
    val STRING = CatalogRegistry.getProvider(ItemType::class, "string").get()

    @JvmField
    val FEATHER = CatalogRegistry.getProvider(ItemType::class, "feather").get()

    @JvmField
    val GUNPOWDER = CatalogRegistry.getProvider(ItemType::class, "gunpowder").get()

    @JvmField
    val WHEAT_SEEDS = CatalogRegistry.getProvider(ItemType::class, "wheat_seeds").get()

    @JvmField
    val WHEAT = CatalogRegistry.getProvider(ItemType::class, "wheat").get()

    @JvmField
    val BREAD = CatalogRegistry.getProvider(ItemType::class, "bread").get()

    @JvmField
    val LEATHER_HELMET = CatalogRegistry.getProvider(ItemType::class, "leather_helmet").get()

    @JvmField
    val LEATHER_CHESTPLATE = CatalogRegistry.getProvider(ItemType::class, "leather_chestplate").get()

    @JvmField
    val LEATHER_LEGGINGS = CatalogRegistry.getProvider(ItemType::class, "leather_leggings").get()

    @JvmField
    val LEATHER_BOOTS = CatalogRegistry.getProvider(ItemType::class, "leather_boots").get()

    @JvmField
    val CHAINMAIL_HELMET = CatalogRegistry.getProvider(ItemType::class, "chainmail_helmet").get()

    @JvmField
    val CHAINMAIL_CHESTPLATE = CatalogRegistry.getProvider(ItemType::class, "chainmail_chestplate").get()

    @JvmField
    val CHAINMAIL_LEGGINGS = CatalogRegistry.getProvider(ItemType::class, "chainmail_leggings").get()

    @JvmField
    val CHAINMAIL_BOOTS = CatalogRegistry.getProvider(ItemType::class, "chainmail_boots").get()

    @JvmField
    val IRON_HELMET = CatalogRegistry.getProvider(ItemType::class, "iron_helmet").get()

    @JvmField
    val IRON_CHESTPLATE = CatalogRegistry.getProvider(ItemType::class, "iron_chestplate").get()

    @JvmField
    val IRON_LEGGINGS = CatalogRegistry.getProvider(ItemType::class, "iron_leggings").get()

    @JvmField
    val IRON_BOOTS = CatalogRegistry.getProvider(ItemType::class, "iron_boots").get()

    @JvmField
    val DIAMOND_HELMET = CatalogRegistry.getProvider(ItemType::class, "diamond_helmet").get()

    @JvmField
    val DIAMOND_CHESTPLATE = CatalogRegistry.getProvider(ItemType::class, "diamond_chestplate").get()

    @JvmField
    val DIAMOND_LEGGINGS = CatalogRegistry.getProvider(ItemType::class, "diamond_leggings").get()

    @JvmField
    val DIAMOND_BOOTS = CatalogRegistry.getProvider(ItemType::class, "diamond_boots").get()

    @JvmField
    val GOLDEN_HELMET = CatalogRegistry.getProvider(ItemType::class, "golden_helmet").get()

    @JvmField
    val GOLDEN_CHESTPLATE = CatalogRegistry.getProvider(ItemType::class, "golden_chestplate").get()

    @JvmField
    val GOLDEN_LEGGINGS = CatalogRegistry.getProvider(ItemType::class, "golden_leggings").get()

    @JvmField
    val GOLDEN_BOOTS = CatalogRegistry.getProvider(ItemType::class, "golden_boots").get()

    @JvmField
    val NETHERITE_HELMET = CatalogRegistry.getProvider(ItemType::class, "netherite_helmet").get()

    @JvmField
    val NETHERITE_CHESTPLATE = CatalogRegistry.getProvider(ItemType::class, "netherite_chestplate").get()

    @JvmField
    val NETHERITE_LEGGINGS = CatalogRegistry.getProvider(ItemType::class, "netherite_leggings").get()

    @JvmField
    val NETHERITE_BOOTS = CatalogRegistry.getProvider(ItemType::class, "netherite_boots").get()

    @JvmField
    val FLINT = CatalogRegistry.getProvider(ItemType::class, "flint").get()

    @JvmField
    val PORKCHOP = CatalogRegistry.getProvider(ItemType::class, "porkchop").get()

    @JvmField
    val COOKED_PORKCHOP = CatalogRegistry.getProvider(ItemType::class, "cooked_porkchop").get()

    @JvmField
    val PAINTING = CatalogRegistry.getProvider(ItemType::class, "painting").get()

    @JvmField
    val GOLDEN_APPLE = CatalogRegistry.getProvider(ItemType::class, "golden_apple").get()

    @JvmField
    val ENCHANTED_GOLDEN_APPLE = CatalogRegistry.getProvider(ItemType::class, "enchanted_golden_apple").get()

    @JvmField
    val OAK_SIGN = CatalogRegistry.getProvider(ItemType::class, "oak_sign").get()

    @JvmField
    val SPRUCE_SIGN = CatalogRegistry.getProvider(ItemType::class, "spruce_sign").get()

    @JvmField
    val BIRCH_SIGN = CatalogRegistry.getProvider(ItemType::class, "birch_sign").get()

    @JvmField
    val JUNGLE_SIGN = CatalogRegistry.getProvider(ItemType::class, "jungle_sign").get()

    @JvmField
    val ACACIA_SIGN = CatalogRegistry.getProvider(ItemType::class, "acacia_sign").get()

    @JvmField
    val DARK_OAK_SIGN = CatalogRegistry.getProvider(ItemType::class, "dark_oak_sign").get()

    @JvmField
    val CRIMSON_SIGN = CatalogRegistry.getProvider(ItemType::class, "crimson_sign").get()

    @JvmField
    val WARPED_SIGN = CatalogRegistry.getProvider(ItemType::class, "warped_sign").get()

    @JvmField
    val BUCKET = CatalogRegistry.getProvider(ItemType::class, "bucket").get()

    @JvmField
    val WATER_BUCKET = CatalogRegistry.getProvider(ItemType::class, "water_bucket").get()

    @JvmField
    val LAVA_BUCKET = CatalogRegistry.getProvider(ItemType::class, "lava_bucket").get()

    @JvmField
    val MINECART = CatalogRegistry.getProvider(ItemType::class, "minecart").get()

    @JvmField
    val SADDLE = CatalogRegistry.getProvider(ItemType::class, "saddle").get()

    @JvmField
    val REDSTONE = CatalogRegistry.getProvider(ItemType::class, "redstone").get()

    @JvmField
    val SNOWBALL = CatalogRegistry.getProvider(ItemType::class, "snowball").get()

    @JvmField
    val OAK_BOAT = CatalogRegistry.getProvider(ItemType::class, "oak_boat").get()

    @JvmField
    val LEATHER = CatalogRegistry.getProvider(ItemType::class, "leather").get()

    @JvmField
    val MILK_BUCKET = CatalogRegistry.getProvider(ItemType::class, "milk_bucket").get()

    @JvmField
    val PUFFERFISH_BUCKET = CatalogRegistry.getProvider(ItemType::class, "pufferfish_bucket").get()

    @JvmField
    val SALMON_BUCKET = CatalogRegistry.getProvider(ItemType::class, "salmon_bucket").get()

    @JvmField
    val COD_BUCKET = CatalogRegistry.getProvider(ItemType::class, "cod_bucket").get()

    @JvmField
    val TROPICAL_FISH_BUCKET = CatalogRegistry.getProvider(ItemType::class, "tropical_fish_bucket").get()

    @JvmField
    val BRICK = CatalogRegistry.getProvider(ItemType::class, "brick").get()

    @JvmField
    val CLAY_BALL = CatalogRegistry.getProvider(ItemType::class, "clay_ball").get()

    @JvmField
    val DRIED_KELP_BLOCK = CatalogRegistry.getProvider(ItemType::class, "dried_kelp_block").get()

    @JvmField
    val PAPER = CatalogRegistry.getProvider(ItemType::class, "paper").get()

    @JvmField
    val BOOK = CatalogRegistry.getProvider(ItemType::class, "book").get()

    @JvmField
    val SLIME_BALL = CatalogRegistry.getProvider(ItemType::class, "slime_ball").get()

    @JvmField
    val CHEST_MINECART = CatalogRegistry.getProvider(ItemType::class, "chest_minecart").get()

    @JvmField
    val FURNACE_MINECART = CatalogRegistry.getProvider(ItemType::class, "furnace_minecart").get()

    @JvmField
    val EGG = CatalogRegistry.getProvider(ItemType::class, "egg").get()

    @JvmField
    val COMPASS = CatalogRegistry.getProvider(ItemType::class, "compass").get()

    @JvmField
    val FISHING_ROD = CatalogRegistry.getProvider(ItemType::class, "fishing_rod").get()

    @JvmField
    val CLOCK = CatalogRegistry.getProvider(ItemType::class, "clock").get()

    @JvmField
    val GLOWSTONE_DUST = CatalogRegistry.getProvider(ItemType::class, "glowstone_dust").get()

    @JvmField
    val COD = CatalogRegistry.getProvider(ItemType::class, "cod").get()

    @JvmField
    val SALMON = CatalogRegistry.getProvider(ItemType::class, "salmon").get()

    @JvmField
    val TROPICAL_FISH = CatalogRegistry.getProvider(ItemType::class, "tropical_fish").get()

    @JvmField
    val PUFFERFISH = CatalogRegistry.getProvider(ItemType::class, "pufferfish").get()

    @JvmField
    val COOKED_COD = CatalogRegistry.getProvider(ItemType::class, "cooked_cod").get()

    @JvmField
    val COOKED_SALMON = CatalogRegistry.getProvider(ItemType::class, "cooked_salmon").get()

    @JvmField
    val INK_SAC = CatalogRegistry.getProvider(ItemType::class, "ink_sac").get()

    @JvmField
    val COCOA_BEANS = CatalogRegistry.getProvider(ItemType::class, "cocoa_beans").get()

    @JvmField
    val LAPIS_LAZULI = CatalogRegistry.getProvider(ItemType::class, "lapis_lazuli").get()

    @JvmField
    val WHITE_DYE = CatalogRegistry.getProvider(ItemType::class, "white_dye").get()

    @JvmField
    val ORANGE_DYE = CatalogRegistry.getProvider(ItemType::class, "orange_dye").get()

    @JvmField
    val MAGENTA_DYE = CatalogRegistry.getProvider(ItemType::class, "magenta_dye").get()

    @JvmField
    val LIGHT_BLUE_DYE = CatalogRegistry.getProvider(ItemType::class, "light_blue_dye").get()

    @JvmField
    val YELLOW_DYE = CatalogRegistry.getProvider(ItemType::class, "yellow_dye").get()

    @JvmField
    val LIME_DYE = CatalogRegistry.getProvider(ItemType::class, "lime_dye").get()

    @JvmField
    val PINK_DYE = CatalogRegistry.getProvider(ItemType::class, "pink_dye").get()

    @JvmField
    val GRAY_DYE = CatalogRegistry.getProvider(ItemType::class, "gray_dye").get()

    @JvmField
    val LIGHT_GRAY_DYE = CatalogRegistry.getProvider(ItemType::class, "light_gray_dye").get()

    @JvmField
    val CYAN_DYE = CatalogRegistry.getProvider(ItemType::class, "cyan_dye").get()

    @JvmField
    val PURPLE_DYE = CatalogRegistry.getProvider(ItemType::class, "purple_dye").get()

    @JvmField
    val BLUE_DYE = CatalogRegistry.getProvider(ItemType::class, "blue_dye").get()

    @JvmField
    val BROWN_DYE = CatalogRegistry.getProvider(ItemType::class, "brown_dye").get()

    @JvmField
    val GREEN_DYE = CatalogRegistry.getProvider(ItemType::class, "green_dye").get()

    @JvmField
    val RED_DYE = CatalogRegistry.getProvider(ItemType::class, "red_dye").get()

    @JvmField
    val BLACK_DYE = CatalogRegistry.getProvider(ItemType::class, "black_dye").get()

    @JvmField
    val BONE_MEAL = CatalogRegistry.getProvider(ItemType::class, "bone_meal").get()

    @JvmField
    val BONE = CatalogRegistry.getProvider(ItemType::class, "bone").get()

    @JvmField
    val SUGAR = CatalogRegistry.getProvider(ItemType::class, "sugar").get()

    @JvmField
    val CAKE = CatalogRegistry.getProvider(ItemType::class, "cake").get()

    @JvmField
    val WHITE_BED = CatalogRegistry.getProvider(ItemType::class, "white_bed").get()

    @JvmField
    val ORANGE_BED = CatalogRegistry.getProvider(ItemType::class, "orange_bed").get()

    @JvmField
    val MAGENTA_BED = CatalogRegistry.getProvider(ItemType::class, "magenta_bed").get()

    @JvmField
    val LIGHT_BLUE_BED = CatalogRegistry.getProvider(ItemType::class, "light_blue_bed").get()

    @JvmField
    val YELLOW_BED = CatalogRegistry.getProvider(ItemType::class, "yellow_bed").get()

    @JvmField
    val LIME_BED = CatalogRegistry.getProvider(ItemType::class, "lime_bed").get()

    @JvmField
    val PINK_BED = CatalogRegistry.getProvider(ItemType::class, "pink_bed").get()

    @JvmField
    val GRAY_BED = CatalogRegistry.getProvider(ItemType::class, "gray_bed").get()

    @JvmField
    val LIGHT_GRAY_BED = CatalogRegistry.getProvider(ItemType::class, "light_gray_bed").get()

    @JvmField
    val CYAN_BED = CatalogRegistry.getProvider(ItemType::class, "cyan_bed").get()

    @JvmField
    val PURPLE_BED = CatalogRegistry.getProvider(ItemType::class, "purple_bed").get()

    @JvmField
    val BLUE_BED = CatalogRegistry.getProvider(ItemType::class, "blue_bed").get()

    @JvmField
    val BROWN_BED = CatalogRegistry.getProvider(ItemType::class, "brown_bed").get()

    @JvmField
    val GREEN_BED = CatalogRegistry.getProvider(ItemType::class, "green_bed").get()

    @JvmField
    val RED_BED = CatalogRegistry.getProvider(ItemType::class, "red_bed").get()

    @JvmField
    val BLACK_BED = CatalogRegistry.getProvider(ItemType::class, "black_bed").get()

    @JvmField
    val COOKIE = CatalogRegistry.getProvider(ItemType::class, "cookie").get()

    @JvmField
    val FILLED_MAP = CatalogRegistry.getProvider(ItemType::class, "filled_map").get()

    @JvmField
    val SHEARS = CatalogRegistry.getProvider(ItemType::class, "shears").get()

    @JvmField
    val MELON_SLICE = CatalogRegistry.getProvider(ItemType::class, "melon_slice").get()

    @JvmField
    val DRIED_KELP = CatalogRegistry.getProvider(ItemType::class, "dried_kelp").get()

    @JvmField
    val PUMPKIN_SEEDS = CatalogRegistry.getProvider(ItemType::class, "pumpkin_seeds").get()

    @JvmField
    val MELON_SEEDS = CatalogRegistry.getProvider(ItemType::class, "melon_seeds").get()

    @JvmField
    val BEEF = CatalogRegistry.getProvider(ItemType::class, "beef").get()

    @JvmField
    val COOKED_BEEF = CatalogRegistry.getProvider(ItemType::class, "cooked_beef").get()

    @JvmField
    val CHICKEN = CatalogRegistry.getProvider(ItemType::class, "chicken").get()

    @JvmField
    val COOKED_CHICKEN = CatalogRegistry.getProvider(ItemType::class, "cooked_chicken").get()

    @JvmField
    val ROTTEN_FLESH = CatalogRegistry.getProvider(ItemType::class, "rotten_flesh").get()

    @JvmField
    val ENDER_PEARL = CatalogRegistry.getProvider(ItemType::class, "ender_pearl").get()

    @JvmField
    val BLAZE_ROD = CatalogRegistry.getProvider(ItemType::class, "blaze_rod").get()

    @JvmField
    val GHAST_TEAR = CatalogRegistry.getProvider(ItemType::class, "ghast_tear").get()

    @JvmField
    val GOLD_NUGGET = CatalogRegistry.getProvider(ItemType::class, "gold_nugget").get()

    @JvmField
    val NETHER_WART = CatalogRegistry.getProvider(ItemType::class, "nether_wart").get()

    @JvmField
    val POTION = CatalogRegistry.getProvider(ItemType::class, "potion").get()

    @JvmField
    val GLASS_BOTTLE = CatalogRegistry.getProvider(ItemType::class, "glass_bottle").get()

    @JvmField
    val SPIDER_EYE = CatalogRegistry.getProvider(ItemType::class, "spider_eye").get()

    @JvmField
    val FERMENTED_SPIDER_EYE = CatalogRegistry.getProvider(ItemType::class, "fermented_spider_eye").get()

    @JvmField
    val BLAZE_POWDER = CatalogRegistry.getProvider(ItemType::class, "blaze_powder").get()

    @JvmField
    val MAGMA_CREAM = CatalogRegistry.getProvider(ItemType::class, "magma_cream").get()

    @JvmField
    val BREWING_STAND = CatalogRegistry.getProvider(ItemType::class, "brewing_stand").get()

    @JvmField
    val CAULDRON = CatalogRegistry.getProvider(ItemType::class, "cauldron").get()

    @JvmField
    val ENDER_EYE = CatalogRegistry.getProvider(ItemType::class, "ender_eye").get()

    @JvmField
    val GLISTERING_MELON_SLICE = CatalogRegistry.getProvider(ItemType::class, "glistering_melon_slice").get()

    @JvmField
    val BAT_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "bat_spawn_egg").get()

    @JvmField
    val BEE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "bee_spawn_egg").get()

    @JvmField
    val BLAZE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "blaze_spawn_egg").get()

    @JvmField
    val CAT_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "cat_spawn_egg").get()

    @JvmField
    val CAVE_SPIDER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "cave_spider_spawn_egg").get()

    @JvmField
    val CHICKEN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "chicken_spawn_egg").get()

    @JvmField
    val COD_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "cod_spawn_egg").get()

    @JvmField
    val COW_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "cow_spawn_egg").get()

    @JvmField
    val CREEPER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "creeper_spawn_egg").get()

    @JvmField
    val DOLPHIN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "dolphin_spawn_egg").get()

    @JvmField
    val DONKEY_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "donkey_spawn_egg").get()

    @JvmField
    val DROWNED_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "drowned_spawn_egg").get()

    @JvmField
    val ELDER_GUARDIAN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "elder_guardian_spawn_egg").get()

    @JvmField
    val ENDERMAN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "enderman_spawn_egg").get()

    @JvmField
    val ENDERMITE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "endermite_spawn_egg").get()

    @JvmField
    val EVOKER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "evoker_spawn_egg").get()

    @JvmField
    val FOX_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "fox_spawn_egg").get()

    @JvmField
    val GHAST_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "ghast_spawn_egg").get()

    @JvmField
    val GUARDIAN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "guardian_spawn_egg").get()

    @JvmField
    val HOGLIN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "hoglin_spawn_egg").get()

    @JvmField
    val HORSE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "horse_spawn_egg").get()

    @JvmField
    val HUSK_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "husk_spawn_egg").get()

    @JvmField
    val LLAMA_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "llama_spawn_egg").get()

    @JvmField
    val MAGMA_CUBE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "magma_cube_spawn_egg").get()

    @JvmField
    val MOOSHROOM_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "mooshroom_spawn_egg").get()

    @JvmField
    val MULE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "mule_spawn_egg").get()

    @JvmField
    val OCELOT_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "ocelot_spawn_egg").get()

    @JvmField
    val PANDA_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "panda_spawn_egg").get()

    @JvmField
    val PARROT_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "parrot_spawn_egg").get()

    @JvmField
    val PHANTOM_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "phantom_spawn_egg").get()

    @JvmField
    val PIG_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "pig_spawn_egg").get()

    @JvmField
    val PIGLIN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "piglin_spawn_egg").get()

    @JvmField
    val PIGLIN_BRUTE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "piglin_brute_spawn_egg").get()

    @JvmField
    val PILLAGER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "pillager_spawn_egg").get()

    @JvmField
    val POLAR_BEAR_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "polar_bear_spawn_egg").get()

    @JvmField
    val PUFFERFISH_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "pufferfish_spawn_egg").get()

    @JvmField
    val RABBIT_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "rabbit_spawn_egg").get()

    @JvmField
    val RAVAGER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "ravager_spawn_egg").get()

    @JvmField
    val SALMON_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "salmon_spawn_egg").get()

    @JvmField
    val SHEEP_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "sheep_spawn_egg").get()

    @JvmField
    val SHULKER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "shulker_spawn_egg").get()

    @JvmField
    val SILVERFISH_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "silverfish_spawn_egg").get()

    @JvmField
    val SKELETON_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "skeleton_spawn_egg").get()

    @JvmField
    val SKELETON_HORSE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "skeleton_horse_spawn_egg").get()

    @JvmField
    val SLIME_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "slime_spawn_egg").get()

    @JvmField
    val SPIDER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "spider_spawn_egg").get()

    @JvmField
    val SQUID_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "squid_spawn_egg").get()

    @JvmField
    val STRAY_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "stray_spawn_egg").get()

    @JvmField
    val STRIDER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "strider_spawn_egg").get()

    @JvmField
    val TRADER_LLAMA_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "trader_llama_spawn_egg").get()

    @JvmField
    val TROPICAL_FISH_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "tropical_fish_spawn_egg").get()

    @JvmField
    val TURTLE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "turtle_spawn_egg").get()

    @JvmField
    val VEX_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "vex_spawn_egg").get()

    @JvmField
    val VILLAGER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "villager_spawn_egg").get()

    @JvmField
    val VINDICATOR_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "vindicator_spawn_egg").get()

    @JvmField
    val WANDERING_TRADER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "wandering_trader_spawn_egg").get()

    @JvmField
    val WITCH_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "witch_spawn_egg").get()

    @JvmField
    val WITHER_SKELETON_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "wither_skeleton_spawn_egg").get()

    @JvmField
    val WOLF_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "wolf_spawn_egg").get()

    @JvmField
    val ZOGLIN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "zoglin_spawn_egg").get()

    @JvmField
    val ZOMBIE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "zombie_spawn_egg").get()

    @JvmField
    val ZOMBIE_HORSE_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "zombie_horse_spawn_egg").get()

    @JvmField
    val ZOMBIE_VILLAGER_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "zombie_villager_spawn_egg").get()

    @JvmField
    val ZOMBIFIED_PIGLIN_SPAWN_EGG = CatalogRegistry.getProvider(ItemType::class, "zombified_piglin_spawn_egg").get()

    @JvmField
    val EXPERIENCE_BOTTLE = CatalogRegistry.getProvider(ItemType::class, "experience_bottle").get()

    @JvmField
    val FIRE_CHARGE = CatalogRegistry.getProvider(ItemType::class, "fire_charge").get()

    @JvmField
    val WRITABLE_BOOK = CatalogRegistry.getProvider(ItemType::class, "writable_book").get()

    @JvmField
    val WRITTEN_BOOK = CatalogRegistry.getProvider(ItemType::class, "written_book").get()

    @JvmField
    val EMERALD = CatalogRegistry.getProvider(ItemType::class, "emerald").get()

    @JvmField
    val ITEM_FRAME = CatalogRegistry.getProvider(ItemType::class, "item_frame").get()

    @JvmField
    val FLOWER_POT = CatalogRegistry.getProvider(ItemType::class, "flower_pot").get()

    @JvmField
    val CARROT = CatalogRegistry.getProvider(ItemType::class, "carrot").get()

    @JvmField
    val POTATO = CatalogRegistry.getProvider(ItemType::class, "potato").get()

    @JvmField
    val BAKED_POTATO = CatalogRegistry.getProvider(ItemType::class, "baked_potato").get()

    @JvmField
    val POISONOUS_POTATO = CatalogRegistry.getProvider(ItemType::class, "poisonous_potato").get()

    @JvmField
    val MAP = CatalogRegistry.getProvider(ItemType::class, "map").get()

    @JvmField
    val GOLDEN_CARROT = CatalogRegistry.getProvider(ItemType::class, "golden_carrot").get()

    @JvmField
    val SKELETON_SKULL = CatalogRegistry.getProvider(ItemType::class, "skeleton_skull").get()

    @JvmField
    val WITHER_SKELETON_SKULL = CatalogRegistry.getProvider(ItemType::class, "wither_skeleton_skull").get()

    @JvmField
    val PLAYER_HEAD = CatalogRegistry.getProvider(ItemType::class, "player_head").get()

    @JvmField
    val ZOMBIE_HEAD = CatalogRegistry.getProvider(ItemType::class, "zombie_head").get()

    @JvmField
    val CREEPER_HEAD = CatalogRegistry.getProvider(ItemType::class, "creeper_head").get()

    @JvmField
    val DRAGON_HEAD = CatalogRegistry.getProvider(ItemType::class, "dragon_head").get()

    @JvmField
    val CARROT_ON_A_STICK = CatalogRegistry.getProvider(ItemType::class, "carrot_on_a_stick").get()

    @JvmField
    val WARPED_FUNGUS_ON_A_STICK = CatalogRegistry.getProvider(ItemType::class, "warped_fungus_on_a_stick").get()

    @JvmField
    val NETHER_STAR = CatalogRegistry.getProvider(ItemType::class, "nether_star").get()

    @JvmField
    val PUMPKIN_PIE = CatalogRegistry.getProvider(ItemType::class, "pumpkin_pie").get()

    @JvmField
    val FIREWORK_ROCKET = CatalogRegistry.getProvider(ItemType::class, "firework_rocket").get()

    @JvmField
    val FIREWORK_STAR = CatalogRegistry.getProvider(ItemType::class, "firework_star").get()

    @JvmField
    val ENCHANTED_BOOK = CatalogRegistry.getProvider(ItemType::class, "enchanted_book").get()

    @JvmField
    val NETHER_BRICK = CatalogRegistry.getProvider(ItemType::class, "nether_brick").get()

    @JvmField
    val QUARTZ = CatalogRegistry.getProvider(ItemType::class, "quartz").get()

    @JvmField
    val TNT_MINECART = CatalogRegistry.getProvider(ItemType::class, "tnt_minecart").get()

    @JvmField
    val HOPPER_MINECART = CatalogRegistry.getProvider(ItemType::class, "hopper_minecart").get()

    @JvmField
    val PRISMARINE_SHARD = CatalogRegistry.getProvider(ItemType::class, "prismarine_shard").get()

    @JvmField
    val PRISMARINE_CRYSTALS = CatalogRegistry.getProvider(ItemType::class, "prismarine_crystals").get()

    @JvmField
    val RABBIT = CatalogRegistry.getProvider(ItemType::class, "rabbit").get()

    @JvmField
    val COOKED_RABBIT = CatalogRegistry.getProvider(ItemType::class, "cooked_rabbit").get()

    @JvmField
    val RABBIT_STEW = CatalogRegistry.getProvider(ItemType::class, "rabbit_stew").get()

    @JvmField
    val RABBIT_FOOT = CatalogRegistry.getProvider(ItemType::class, "rabbit_foot").get()

    @JvmField
    val RABBIT_HIDE = CatalogRegistry.getProvider(ItemType::class, "rabbit_hide").get()

    @JvmField
    val ARMOR_STAND = CatalogRegistry.getProvider(ItemType::class, "armor_stand").get()

    @JvmField
    val IRON_HORSE_ARMOR = CatalogRegistry.getProvider(ItemType::class, "iron_horse_armor").get()

    @JvmField
    val GOLDEN_HORSE_ARMOR = CatalogRegistry.getProvider(ItemType::class, "golden_horse_armor").get()

    @JvmField
    val DIAMOND_HORSE_ARMOR = CatalogRegistry.getProvider(ItemType::class, "diamond_horse_armor").get()

    @JvmField
    val LEATHER_HORSE_ARMOR = CatalogRegistry.getProvider(ItemType::class, "leather_horse_armor").get()

    @JvmField
    val LEAD = CatalogRegistry.getProvider(ItemType::class, "lead").get()

    @JvmField
    val NAME_TAG = CatalogRegistry.getProvider(ItemType::class, "name_tag").get()

    @JvmField
    val COMMAND_BLOCK_MINECART = CatalogRegistry.getProvider(ItemType::class, "command_block_minecart").get()

    @JvmField
    val MUTTON = CatalogRegistry.getProvider(ItemType::class, "mutton").get()

    @JvmField
    val COOKED_MUTTON = CatalogRegistry.getProvider(ItemType::class, "cooked_mutton").get()

    @JvmField
    val WHITE_BANNER = CatalogRegistry.getProvider(ItemType::class, "white_banner").get()

    @JvmField
    val ORANGE_BANNER = CatalogRegistry.getProvider(ItemType::class, "orange_banner").get()

    @JvmField
    val MAGENTA_BANNER = CatalogRegistry.getProvider(ItemType::class, "magenta_banner").get()

    @JvmField
    val LIGHT_BLUE_BANNER = CatalogRegistry.getProvider(ItemType::class, "light_blue_banner").get()

    @JvmField
    val YELLOW_BANNER = CatalogRegistry.getProvider(ItemType::class, "yellow_banner").get()

    @JvmField
    val LIME_BANNER = CatalogRegistry.getProvider(ItemType::class, "lime_banner").get()

    @JvmField
    val PINK_BANNER = CatalogRegistry.getProvider(ItemType::class, "pink_banner").get()

    @JvmField
    val GRAY_BANNER = CatalogRegistry.getProvider(ItemType::class, "gray_banner").get()

    @JvmField
    val LIGHT_GRAY_BANNER = CatalogRegistry.getProvider(ItemType::class, "light_gray_banner").get()

    @JvmField
    val CYAN_BANNER = CatalogRegistry.getProvider(ItemType::class, "cyan_banner").get()

    @JvmField
    val PURPLE_BANNER = CatalogRegistry.getProvider(ItemType::class, "purple_banner").get()

    @JvmField
    val BLUE_BANNER = CatalogRegistry.getProvider(ItemType::class, "blue_banner").get()

    @JvmField
    val BROWN_BANNER = CatalogRegistry.getProvider(ItemType::class, "brown_banner").get()

    @JvmField
    val GREEN_BANNER = CatalogRegistry.getProvider(ItemType::class, "green_banner").get()

    @JvmField
    val RED_BANNER = CatalogRegistry.getProvider(ItemType::class, "red_banner").get()

    @JvmField
    val BLACK_BANNER = CatalogRegistry.getProvider(ItemType::class, "black_banner").get()

    @JvmField
    val END_CRYSTAL = CatalogRegistry.getProvider(ItemType::class, "end_crystal").get()

    @JvmField
    val CHORUS_FRUIT = CatalogRegistry.getProvider(ItemType::class, "chorus_fruit").get()

    @JvmField
    val POPPED_CHORUS_FRUIT = CatalogRegistry.getProvider(ItemType::class, "popped_chorus_fruit").get()

    @JvmField
    val BEETROOT = CatalogRegistry.getProvider(ItemType::class, "beetroot").get()

    @JvmField
    val BEETROOT_SEEDS = CatalogRegistry.getProvider(ItemType::class, "beetroot_seeds").get()

    @JvmField
    val BEETROOT_SOUP = CatalogRegistry.getProvider(ItemType::class, "beetroot_soup").get()

    @JvmField
    val DRAGON_BREATH = CatalogRegistry.getProvider(ItemType::class, "dragon_breath").get()

    @JvmField
    val SPLASH_POTION = CatalogRegistry.getProvider(ItemType::class, "splash_potion").get()

    @JvmField
    val SPECTRAL_ARROW = CatalogRegistry.getProvider(ItemType::class, "spectral_arrow").get()

    @JvmField
    val TIPPED_ARROW = CatalogRegistry.getProvider(ItemType::class, "tipped_arrow").get()

    @JvmField
    val LINGERING_POTION = CatalogRegistry.getProvider(ItemType::class, "lingering_potion").get()

    @JvmField
    val SHIELD = CatalogRegistry.getProvider(ItemType::class, "shield").get()

    @JvmField
    val ELYTRA = CatalogRegistry.getProvider(ItemType::class, "elytra").get()

    @JvmField
    val SPRUCE_BOAT = CatalogRegistry.getProvider(ItemType::class, "spruce_boat").get()

    @JvmField
    val BIRCH_BOAT = CatalogRegistry.getProvider(ItemType::class, "birch_boat").get()

    @JvmField
    val JUNGLE_BOAT = CatalogRegistry.getProvider(ItemType::class, "jungle_boat").get()

    @JvmField
    val ACACIA_BOAT = CatalogRegistry.getProvider(ItemType::class, "acacia_boat").get()

    @JvmField
    val DARK_OAK_BOAT = CatalogRegistry.getProvider(ItemType::class, "dark_oak_boat").get()

    @JvmField
    val TOTEM_OF_UNDYING = CatalogRegistry.getProvider(ItemType::class, "totem_of_undying").get()

    @JvmField
    val SHULKER_SHELL = CatalogRegistry.getProvider(ItemType::class, "shulker_shell").get()

    @JvmField
    val IRON_NUGGET = CatalogRegistry.getProvider(ItemType::class, "iron_nugget").get()

    @JvmField
    val KNOWLEDGE_BOOK = CatalogRegistry.getProvider(ItemType::class, "knowledge_book").get()

    @JvmField
    val DEBUG_STICK = CatalogRegistry.getProvider(ItemType::class, "debug_stick").get()

    @JvmField
    val MUSIC_DISC_13 = CatalogRegistry.getProvider(ItemType::class, "music_disc_13").get()

    @JvmField
    val MUSIC_DISC_CAT = CatalogRegistry.getProvider(ItemType::class, "music_disc_cat").get()

    @JvmField
    val MUSIC_DISC_BLOCKS = CatalogRegistry.getProvider(ItemType::class, "music_disc_blocks").get()

    @JvmField
    val MUSIC_DISC_CHIRP = CatalogRegistry.getProvider(ItemType::class, "music_disc_chirp").get()

    @JvmField
    val MUSIC_DISC_FAR = CatalogRegistry.getProvider(ItemType::class, "music_disc_far").get()

    @JvmField
    val MUSIC_DISC_MALL = CatalogRegistry.getProvider(ItemType::class, "music_disc_mall").get()

    @JvmField
    val MUSIC_DISC_MELLOHI = CatalogRegistry.getProvider(ItemType::class, "music_disc_mellohi").get()

    @JvmField
    val MUSIC_DISC_STAL = CatalogRegistry.getProvider(ItemType::class, "music_disc_stal").get()

    @JvmField
    val MUSIC_DISC_STRAD = CatalogRegistry.getProvider(ItemType::class, "music_disc_strad").get()

    @JvmField
    val MUSIC_DISC_WARD = CatalogRegistry.getProvider(ItemType::class, "music_disc_ward").get()

    @JvmField
    val MUSIC_DISC_11 = CatalogRegistry.getProvider(ItemType::class, "music_disc_11").get()

    @JvmField
    val MUSIC_DISC_WAIT = CatalogRegistry.getProvider(ItemType::class, "music_disc_wait").get()

    @JvmField
    val MUSIC_DISC_PIGSTEP = CatalogRegistry.getProvider(ItemType::class, "music_disc_pigstep").get()

    @JvmField
    val TRIDENT = CatalogRegistry.getProvider(ItemType::class, "trident").get()

    @JvmField
    val PHANTOM_MEMBRANE = CatalogRegistry.getProvider(ItemType::class, "phantom_membrane").get()

    @JvmField
    val NAUTILUS_SHELL = CatalogRegistry.getProvider(ItemType::class, "nautilus_shell").get()

    @JvmField
    val HEART_OF_THE_SEA = CatalogRegistry.getProvider(ItemType::class, "heart_of_the_sea").get()

    @JvmField
    val CROSSBOW = CatalogRegistry.getProvider(ItemType::class, "crossbow").get()

    @JvmField
    val SUSPICIOUS_STEW = CatalogRegistry.getProvider(ItemType::class, "suspicious_stew").get()

    @JvmField
    val LOOM = CatalogRegistry.getProvider(ItemType::class, "loom").get()

    @JvmField
    val FLOWER_BANNER_PATTERN = CatalogRegistry.getProvider(ItemType::class, "flower_banner_pattern").get()

    @JvmField
    val CREEPER_BANNER_PATTERN = CatalogRegistry.getProvider(ItemType::class, "creeper_banner_pattern").get()

    @JvmField
    val SKULL_BANNER_PATTERN = CatalogRegistry.getProvider(ItemType::class, "skull_banner_pattern").get()

    @JvmField
    val MOJANG_BANNER_PATTERN = CatalogRegistry.getProvider(ItemType::class, "mojang_banner_pattern").get()

    @JvmField
    val GLOBE_BANNER_PATTERN = CatalogRegistry.getProvider(ItemType::class, "globe_banner_pattern").get()

    @JvmField
    val PIGLIN_BANNER_PATTERN = CatalogRegistry.getProvider(ItemType::class, "piglin_banner_pattern").get()

    @JvmField
    val COMPOSTER = CatalogRegistry.getProvider(ItemType::class, "composter").get()

    @JvmField
    val BARREL = CatalogRegistry.getProvider(ItemType::class, "barrel").get()

    @JvmField
    val SMOKER = CatalogRegistry.getProvider(ItemType::class, "smoker").get()

    @JvmField
    val BLAST_FURNACE = CatalogRegistry.getProvider(ItemType::class, "blast_furnace").get()

    @JvmField
    val CARTOGRAPHY_TABLE = CatalogRegistry.getProvider(ItemType::class, "cartography_table").get()

    @JvmField
    val FLETCHING_TABLE = CatalogRegistry.getProvider(ItemType::class, "fletching_table").get()

    @JvmField
    val GRINDSTONE = CatalogRegistry.getProvider(ItemType::class, "grindstone").get()

    @JvmField
    val LECTERN = CatalogRegistry.getProvider(ItemType::class, "lectern").get()

    @JvmField
    val SMITHING_TABLE = CatalogRegistry.getProvider(ItemType::class, "smithing_table").get()

    @JvmField
    val STONECUTTER = CatalogRegistry.getProvider(ItemType::class, "stonecutter").get()

    @JvmField
    val BELL = CatalogRegistry.getProvider(ItemType::class, "bell").get()

    @JvmField
    val LANTERN = CatalogRegistry.getProvider(ItemType::class, "lantern").get()

    @JvmField
    val SOUL_LANTERN = CatalogRegistry.getProvider(ItemType::class, "soul_lantern").get()

    @JvmField
    val SWEET_BERRIES = CatalogRegistry.getProvider(ItemType::class, "sweet_berries").get()

    @JvmField
    val CAMPFIRE = CatalogRegistry.getProvider(ItemType::class, "campfire").get()

    @JvmField
    val SOUL_CAMPFIRE = CatalogRegistry.getProvider(ItemType::class, "soul_campfire").get()

    @JvmField
    val SHROOMLIGHT = CatalogRegistry.getProvider(ItemType::class, "shroomlight").get()

    @JvmField
    val HONEYCOMB = CatalogRegistry.getProvider(ItemType::class, "honeycomb").get()

    @JvmField
    val BEE_NEST = CatalogRegistry.getProvider(ItemType::class, "bee_nest").get()

    @JvmField
    val BEEHIVE = CatalogRegistry.getProvider(ItemType::class, "beehive").get()

    @JvmField
    val HONEY_BOTTLE = CatalogRegistry.getProvider(ItemType::class, "honey_bottle").get()

    @JvmField
    val HONEY_BLOCK = CatalogRegistry.getProvider(ItemType::class, "honey_block").get()

    @JvmField
    val HONEYCOMB_BLOCK = CatalogRegistry.getProvider(ItemType::class, "honeycomb_block").get()

    @JvmField
    val LODESTONE = CatalogRegistry.getProvider(ItemType::class, "lodestone").get()

    @JvmField
    val NETHERITE_BLOCK = CatalogRegistry.getProvider(ItemType::class, "netherite_block").get()

    @JvmField
    val ANCIENT_DEBRIS = CatalogRegistry.getProvider(ItemType::class, "ancient_debris").get()

    @JvmField
    val TARGET = CatalogRegistry.getProvider(ItemType::class, "target").get()

    @JvmField
    val CRYING_OBSIDIAN = CatalogRegistry.getProvider(ItemType::class, "crying_obsidian").get()

    @JvmField
    val BLACKSTONE = CatalogRegistry.getProvider(ItemType::class, "blackstone").get()

    @JvmField
    val BLACKSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "blackstone_slab").get()

    @JvmField
    val BLACKSTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "blackstone_stairs").get()

    @JvmField
    val GILDED_BLACKSTONE = CatalogRegistry.getProvider(ItemType::class, "gilded_blackstone").get()

    @JvmField
    val POLISHED_BLACKSTONE = CatalogRegistry.getProvider(ItemType::class, "polished_blackstone").get()

    @JvmField
    val POLISHED_BLACKSTONE_SLAB = CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_slab").get()

    @JvmField
    val POLISHED_BLACKSTONE_STAIRS = CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_stairs").get()

    @JvmField
    val CHISELED_POLISHED_BLACKSTONE =
        CatalogRegistry.getProvider(ItemType::class, "chiseled_polished_blackstone").get()

    @JvmField
    val POLISHED_BLACKSTONE_BRICKS = CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_bricks").get()

    @JvmField
    val POLISHED_BLACKSTONE_BRICK_SLAB =
        CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_brick_slab").get()

    @JvmField
    val POLISHED_BLACKSTONE_BRICK_STAIRS =
        CatalogRegistry.getProvider(ItemType::class, "polished_blackstone_brick_stairs").get()

    @JvmField
    val CRACKED_POLISHED_BLACKSTONE_BRICKS =
        CatalogRegistry.getProvider(ItemType::class, "cracked_polished_blackstone_bricks").get()

    @JvmField
    val RESPAWN_ANCHOR = CatalogRegistry.getProvider(ItemType::class, "respawn_anchor").get()
}