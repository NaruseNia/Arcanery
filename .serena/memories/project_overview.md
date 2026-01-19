# Arcanery - Project Overview

## Purpose
Arcanery is a Minecraft mod for version 1.21.11 that adds an elemental brewing system using a custom Brewer's Cauldron.

## Key Gameplay Mechanics
- **Brewer's Cauldron**: Collects elements from ingredient items
- **Element System**: 8 element types (Air, Death, Earth, Explosion, Fire, Life, Magic, Water)
- **Potion Creation**: Combine collected elements into potions using glass bottles
- **47+ ingredient items** with unique elemental properties

## Architecture
Multi-loader architecture via Architectury Loom supporting:
- **Fabric** - Full implementation with all gameplay logic
- **NeoForge** - Implementation mirroring Fabric (in progress)
- **Common** - Shared resources (assets, lang) and minimal common init

## Key Systems
- **ElementData**: Core data component using Minecraft's data component system, stores 8 float values for elemental properties
- **BrewersCauldronBlock**: Handles player interactions (right-click to collect bottles, throw items to add elements)
- **BrewersCauldronBlockEntity**: Manages element storage and brewing mechanics, automatically collects elements from items within range

## Registry Pattern
All mod components registered in centralized registry classes:
- `ArcaneryBlocks`: All block types
- `ArcaneryItems`: All item types (ingredients, potions, block items)
- `ArcaneryBlockEntities`: Block entity types
- `ArcaneryDataComponents`: Custom data component types
- `ArcaneryItemGroups`: Creative tab organization

## Entry Points
- **Fabric**: `ArcaneryFabric` (main), `ArcaneryFabricClient` (client-side)
- **NeoForge**: Uses Architectury's platform setup (implementation pending)
