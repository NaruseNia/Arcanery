# Codebase Structure

## Root Structure
```
Arcanery/
├── common/              # Shared resources and minimal common init
├── fabric/              # Full Fabric implementation with all gameplay logic
├── neoforge/            # NeoForge implementation (mirrors Fabric)
├── build.gradle         # Root build configuration
├── settings.gradle      # Gradle settings
├── gradle.properties    # Mod properties and versions
└── lombok.config        # Lombok configuration (fluent accessors)
```

## Module Structure

### common/
```
common/
└── src/main/resources/assets/arcanery/
    ├── models/          # Item and block models (JSON)
    ├── blockstates/     # Block state definitions (JSON)
    └── lang/            # Language files (en_us.json)
```

### fabric/
```
fabric/
└── src/main/java/one/nxeu/arcanery/fabric/
    ├── ArcaneryFabric.java              # Main mod entry point
    ├── client/
    │   └── ArcaneryFabricClient.java    # Client-side setup (rendering, models)
    ├── block/
    │   └── BrewersCauldronBlock.java    # Cauldron block logic
    ├── block/entity/
    │   ├── BrewersCauldronBlockEntity.java  # Cauldron BE (element storage, brewing)
    │   └── SimpleInventoryContainer.java    # Inventory helper
    ├── data/
    │   └── ElementData.java             # Core elemental data component
    ├── item/
    │   ├── ArcaneryPotionItem.java      # Custom potion item
    │   └── IngredientItem.java          # Ingredient item with elements
    ├── registry/
    │   ├── ArcaneryBlocks.java          # Block registry
    │   ├── ArcaneryBlockEntities.java   # Block entity registry
    │   ├── ArcaneryDataComponents.java  # Data component registry
    │   ├── ArcaneryItemGroups.java      # Creative tab registry
    │   └── ArcaneryItems.java           # Item registry
    └── util/
        ├── LevelUtil.java               # Platform-specific level utilities
        └── ParticleUtil.java            # Particle effect helpers
```

### neoforge/
```
neoforge/
└── src/main/java/one/nxeu/arcanery/neoforge/
    └── (structure mirrors fabric/)
```

## Key Files by Category

### Core Systems
- `ElementData.java` - Elemental properties (8 float values: air, death, earth, explosion, fire, life, magic, water)
- `BrewersCauldronBlock.java` - Player interaction handling
- `BrewersCauldronBlockEntity.java` - Brewing mechanics and element collection

### Registration
- `ArcaneryItems.java` - 47+ ingredient items, potions, block items
- `ArcaneryBlocks.java` - Brewer's Cauldron and related blocks
- `ArcaneryBlockEntities.java` - Block entity types
- `ArcaneryDataComponents.java` - Custom data components
- `ArcaneryItemGroups.java` - Creative tab organization

### Entry Points
- `ArcaneryFabric.java` - Fabric mod initialization
- `ArcaneryFabricClient.java` - Fabric client-side setup
