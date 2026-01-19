# Code Style and Conventions

## Java Conventions
- **Java Version**: 21
- **Lombok**: Used extensively with fluent accessors
- **Package Structure**: `one.nxeu.arcanery.fabric.*` (or `.neoforge.*`)

## Registry Pattern
Components registered using `ResourceKey.create(Registries.ITEM, ...)` pattern:
```java
public static final Item MY_ITEM = register("my_item", Item::new, new Item.Properties());
```

## Naming Conventions
- **Classes**: PascalCase (e.g., `BrewersCauldronBlock`, `ElementData`)
- **Fields**: UPPER_SNAKE_CASE for static final registry items
- **Methods**: camelCase
- **Items/Blocks**: lowercase_with_underscores for registry names

## File Organization
- **Registry classes**: Centralized in `registry/` package
  - `ArcaneryBlocks`, `ArcaneryItems`, `ArcaneryBlockEntities`, `ArcaneryDataComponents`, `ArcaneryItemGroups`
- **Data components**: `data/` package (e.g., `ElementData`)
- **Blocks**: `block/` package
- **Block entities**: `block/entity/` package
- **Items**: `item/` package
- **Client**: `client/` package for rendering, models
- **Utilities**: `util/` package

## Data Management
- **Direct JSON files** for models/blockstates (no data generation classes)
- Located in: `common/src/main/resources/assets/arcanery/`
- When adding new items/blocks, create JSON files manually

## Code Style Notes
- `@SuppressWarnings("unused")` used in registry classes
- Registry items declared as `public static final`
- Debug messages can be enabled in BrewersCauldronBlockEntity for troubleshooting
