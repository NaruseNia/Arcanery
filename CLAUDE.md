# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Arcanery is a Minecraft mod for version 1.21.11 that adds an elemental brewing system using a custom Brewer's Cauldron. The mod uses a multi-loader architecture via Architectury Loom to support both Fabric and NeoForge platforms.

**Key Gameplay Mechanics:**
- Brewer's Cauldron: Collects elements from ingredient items
- Element System: 8 element types (Air, Death, Earth, Explosion, Fire, Life, Magic, Water)
- Potion Creation: Combine collected elements into potions using glass bottles
- 47+ ingredient items with unique elemental properties

## Build Commands

```bash
# Build all enabled platforms (fabric,neoforge)
./gradlew build

# Build specific platform
./gradlew :fabric:build
./gradlew :neoforge:build

# Run client (for testing)
./gradlew :fabric:runClient

# Clean build artifacts
./gradlew clean

# Generate sources for debugging
./gradlew :fabric: sourcesJar
```

## Architecture

### Multi-Loader Structure
```
common/          # Shared resources (assets, lang) and minimal common init
fabric/          # Full Fabric implementation with all gameplay logic
neoforge/        # NeoForge implementation (mirrors Fabric)
```

The common module contains only shared assets and a simple `Arcanery` init class. All gameplay logic is implemented separately in platform-specific modules.

### Key Systems

**ElementData** (`fabric/src/main/java/one/nxeu/arcanery/fabric/data/ElementData.java`)
- Core data component using Minecraft's data component system
- Stores 8 float values for elemental properties
- Supports merging operations and persistent storage

**BrewersCauldronBlock** (`fabric/src/main/java/one/nxeu/arcanery/fabric/block/BrewersCauldronBlock.java`)
- Handles player interactions (right-click to collect bottles, throw items to add elements)
- Triggers brewing process with particle effects and sounds

**BrewersCauldronBlockEntity** (`fabric/src/main/java/one/nxeu/arcanery/fabric/block/entity/BrewersCauldronBlockEntity.java`)
- Manages element storage and brewing mechanics
- Automatically collects elements from items within range
- Creates potions when player interacts with glass bottle

### Registry Pattern

All mod components are registered in centralized registry classes:
- `ArcaneryBlocks`: All block types
- `ArcaneryItems`: All item types (ingredients, potions, block items)
- `ArcaneryBlockEntities`: Block entity types
- `ArcaneryDataComponents`: Custom data component types
- `ArcaneryItemGroups`: Creative tab organization

Registration uses `ResourceKey.create(Registries.ITEM, ...)` pattern for items and similar patterns for other registries.

### Entry Points

**Fabric:**
- `ArcaneryFabric`: Main mod initialization
- `ArcaneryFabricClient`: Client-side setup (rendering, models)

**NeoForge:**
- Uses Architectury's platform setup (implementation pending)

### Data Management

The mod uses **direct JSON files** rather than data generation classes:
- Models: `common/src/main/resources/assets/arcanery/models/`
- Block states: `common/src/main/resources/assets/arcanery/blockstates/`
- Language: `common/src/main/resources/assets/arcanery/lang/`

When adding new items or blocks, create JSON files manually in the appropriate locations.

## Development Notes

- **Java 21** is required (configured in build.gradle)
- **Lombok** is used for cleaner code (data classes, getters/setters)
- Debug messages in BrewersCauldronBlockEntity can be enabled for troubleshooting brewing logic
- The ElementData system is central to most gameplay mechanics - understand this class first when working on item or brewing features
- Platform-specific utilities (like `LevelUtil`) exist for operations that differ between Fabric/NeoForge
