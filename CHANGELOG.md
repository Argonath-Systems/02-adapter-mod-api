# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Architectural review and documentation
- Orphan features tracking
- Issue tracking for platform abstraction violations

### Changed

### Deprecated

### Removed
- Unused `ArgonathPluginInit.java` class

### Fixed
- POM artifact ID mismatch (`argonath-one-api-adapter` → `argonath-mod-api-adapter`)
- Class naming inconsistency (`ArgonathPluginInitconfig` → `ArgonathPluginInitConfig`)

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
