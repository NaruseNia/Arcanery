# Task Completion Checklist

## When a Task is Completed

### Build Verification
1. **Build the project** to ensure no compilation errors:
   ```batch
   gradlew build
   ```
   Or for specific platform:
   ```batch
   gradlew :fabric:build
   ```

2. **Fix any build errors** that arise

### Testing (if applicable)
3. **Run the client** to test gameplay changes:
   ```batch
   gradlew :fabric:runClient
   ```

4. **Test the specific feature** that was modified

### Asset Files (if items/blocks added)
5. **Create JSON files** for new items/blocks:
   - Models: `common/src/main/resources/assets/arcanery/models/`
   - Block states: `common/src/main/resources/assets/arcanery/blockstates/`
   - Language: `common/src/main/resources/assets/arcanery/lang/`

### Code Review
6. **Check for consistency** with existing code patterns
7. **Verify Lombok annotations** are used correctly
8. **Ensure registry entries** follow the established pattern

### Version Control (if requested by user)
9. **Stage changes** for commit:
   ```batch
   git add .
   ```

10. **Review changes**:
    ```batch
   git diff
   ```

## Note
- Do **not** automatically commit or push changes unless explicitly requested
- Do **not** add excessive comments or documentation beyond what's necessary
- Follow the principle: minimum changes needed to complete the task
