# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Architectural review and documentation
- Orphan features tracking
- Issue tracking for platform abstraction violations
- **MIGRATION-001 Pattern Adoption**: Platform-agnostic plugin lifecycle (2026-01-29)
- Dependencies: slf4j-api and argonath-grey-havens-accessor (2026-01-29)
- ArgonathPluginContext interface for framework service access (2026-01-29)
- `getEntityAccessor()` method to ArgonathPluginContext interface (2026-01-30)

### Changed
- **BREAKING**: ArgonathPlugin now follows MIGRATION-001 pattern (does NOT extend JavaPlugin)
- **BREAKING**: Plugin initialization uses platform-agnostic context pattern

### Deprecated

### Removed
- Unused `ArgonathPluginInit.java` class
- TextStylingAccessor reference (not yet available in accessor framework)

### Fixed
- **CRITICAL**: Class naming mismatch `ArgonathPluginInitconfig` → `ArgonathPluginInitConfig` (2026-01-29)
- **CRITICAL**: Accessor import path `framework.accessor.accessorapi` → `framework.accessorapi` (2026-01-29)
- **CRITICAL**: 14+ compilation errors resolved (2026-01-29)
- POM artifact ID mismatch (`argonath-one-api-adapter` → `argonath-mod-api-adapter`)
- Constructor name typo in ArgonathPluginInitConfig (2026-01-29)

### Security

### Audit Corrections (2026-01-29)
- **CORRECTED**: Version history - module is at 0.1.0 (SKELETON), not 1.0.0
- **IDENTIFIED**: Critical architectural issue - Hytale types in public API
- **DOCUMENTED**: Missing specification SA-ADAPTER-002
- **UPDATED**: Implementation tracking with architectural issues

## [0.1.0] - 2026-01-27

### Added
- Initial project structure
- `ArgonathPlugin` base class for mod plugins
- `ArgonathPluginInitConfig` wrapper for Hytale init context
- `HytaleModApiPlugin` reference implementation
- Basic plugin lifecycle integration

### Notes
- Module status: SKELETON (~38% complete)
- Missing: ModRegistry, ModLoader, DependencyResolver, LifecycleManager
- Critical: No specification exists yet

[Unreleased]: https://github.com/Argonath-Systems/02-adapter-mod-api/compare/v0.1.0...HEAD
[0.1.0]: https://github.com/Argonath-Systems/02-adapter-mod-api/releases/tag/v0.1.0
