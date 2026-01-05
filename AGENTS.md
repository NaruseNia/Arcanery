# Repository Guidelines

## Overview

This document outlines the guidelines for contributing to the `Arcanery` mod project,
which supports both Fabric and NeoForge loaders using the `Architectury` framework.
It covers project structure, build commands, coding style, testing, and contribution
practices to ensure consistency and maintainability across the codebase.

## Mod Overview

- Mod Name: Arcanery
- Mod ID: arcanery
- Supported Loaders: Fabric, NeoForge
- Framework: Architectury
- Minecraft Version: 1.21.11
- Java Version: 21
- Description:
  This mod is a magic mod centered around potion brewing. It introduces new brewing mechanics,
  ingredients, and effects to enhance the gameplay experience.

## Project Structure & Module Organization

- Multi-loader Architectury project with shared code in `common` and loader-specific code in `fabric` and `neoforge`.
- Java sources live under `*/src/main/java` (for example, `common/src/main/java/one/nxeu/arcanery`).
- Resources live under `*/src/main/resources` (for example, `fabric/src/main/resources/fabric.mod.json`).
- Root Gradle config is in `build.gradle` and `settings.gradle` with module builds in `common/build.gradle`,
  `fabric/build.gradle`, and `neoforge/build.gradle`.

## Build, Test, and Development Commands

- `./gradlew build` (or `./gradlew.bat build` on Windows): builds all modules and produces remapped jars.
- `./gradlew :fabric:runClient`: launches a Fabric dev client for local testing.
- `./gradlew :neoforge:runClient`: launches a NeoForge dev client for local testing.
- `./gradlew clean`: removes build outputs in all modules.

## Coding Style & Naming Conventions

- Java 21 is the target; keep code compatible with `sourceCompatibility` and `targetCompatibility` set to 21.
- Indentation is 4 spaces; opening braces are on the same line.
- Class names use PascalCase, methods and fields use lowerCamelCase, and constants use UPPER_SNAKE_CASE (see
  `Arcanery.MOD_ID`).
- No formatter or linter is configured; keep diffs minimal and follow existing style in nearby files.

## Testing Guidelines

- No automated test framework is currently configured.
- If adding tests, place them under the module they target (for example, `common/src/test/java`) and document how to run
  them.

## Commit & Pull Request Guidelines

- Commit messages follow a short prefix style like `init: ...` or `add: ...`; keep summaries concise and imperative.
- PRs should include a clear description of the change, the target loader(s), and screenshots or logs when behavior is
  user-facing.

## Configuration Notes

- Version and dependency pins are in `gradle.properties`. Update `mod_version` and loader versions there when releasing.
