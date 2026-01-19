# Tech Stack

## Build System
- **Gradle** with Architectury Loom plugin (version 1.13-SNAPSHOT)
- **Architectury Plugin** (version 3.4-SNAPSHOT) for multi-loader support
- **Lombok** (version 9.1.0) with io.freefair lombok plugin

## Runtime Requirements
- **Java 21** (sourceCompatibility and targetCompatibility set to VERSION_21)
- **Minecraft**: 1.21.11
- **Architectury API**: 19.0.1

### Platform-Specific Dependencies
- **Fabric**:
  - Fabric Loader: 0.18.4
  - Fabric API: 0.140.2+1.21.11

- **NeoForge**:
  - NeoForge: 21.11.24-beta

## Java Libraries
- **Lombok**: Used for cleaner code (data classes, getters/setters)
  - Fluent accessors enabled (`lombok.accessors.fluent = true`)

## Build Configuration
- Maven group: `one.nxeu`
- Mod archives name: `arcanery`
- Current version: 0.0.0-alpha
- Enabled platforms: fabric, neoforge
