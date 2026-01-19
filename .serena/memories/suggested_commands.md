# Suggested Commands

## Build Commands
```batch
# Build all enabled platforms (fabric, neoforge)
gradlew build

# Build specific platform
gradlew :fabric:build
gradlew :neoforge:build

# Run client (for testing)
gradlew :fabric:runClient

# Clean build artifacts
gradlew clean

# Generate sources for debugging
gradlew :fabric:sourcesJar
```

## Windows System Commands
```batch
# List directory contents
dir

# Navigate directories
cd <path>

# Search in files
findstr /s /i "pattern" *.java

# Copy files
copy source destination

# Delete files
del filename

# Git operations
git status
git add .
git commit -m "message"
git push
git pull
```

## Gradle Tasks
```batch
# View available tasks
gradlew tasks

# Build with dependencies
gradlew build --refresh-dependencies

# Run specific task
gradlew :fabric:runClient
```

## Module-Specific Commands
```batch
# Fabric module
gradlew :fabric:build
gradlew :fabric:runClient
gradlew :fabric:sourcesJar

# NeoForge module
gradlew :neoforge:build
gradlew :neoforge:runClient
```
