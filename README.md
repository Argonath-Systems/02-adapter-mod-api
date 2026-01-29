# Mod API Adapter

> **Bridge between frameworks and Hytale mod API**

[![GitHub](https://img.shields.io/badge/GitHub-Argonath--Systems-181717?logo=github)](https://github.com/Argonath-Systems/02-adapter-mod-api)
[![Maven](https://img.shields.io/badge/Maven-Central-C71A36?logo=apache-maven)](https://maven.apache.org/)
[![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](../LICENSE)
[![Website](https://img.shields.io/badge/Docs-argonath--systems.github.io-blue)](https://argonath-systems.github.io/00-Argonath-Wiki)

---

## 📋 Overview

The Mod API Adapter provides base plugin classes for Argonath mods to integrate with the Hytale mod loading system. It serves as the entry point for Argonath-based mods, providing:

- **Plugin lifecycle abstraction** via `ArgonathPlugin` base class
- **Initialization context** wrapper for Hytale-specific setup
- **Reference implementation** for mod entry points

> [!WARNING]  
> **Architectural Note**: This module currently exposes Hytale SDK types (`JavaPlugin`, `JavaPluginInit`) in its public API, creating a dependency on Hytale for all consumer mods. This is an intentional trade-off for plugin lifecycle integration but violates the strict platform abstraction principle used elsewhere in the Argonath ecosystem.

## 🏗️ C4 Component Diagram

> [!NOTE]
> Detailed architecture diagrams can be found in the [Argonath Wiki C4 Documentation](https://argonath-systems.github.io/00-Argonath-Wiki/diagrams/).

## ✨ Features

### Current (v0.1.0 - ~38% Complete)

- ✅ **ArgonathPlugin Base Class** - Abstract base for all Argonath mods extending Hytale's `JavaPlugin`
- ✅ **ArgonathPluginInitConfig** - Wrapper for `JavaPluginInit` providing Argonath-specific context
- ✅ **HytaleModApiPlugin** - Reference implementation and entry point example

### Planned (Future Releases)

- ⏳ **ModRegistry** - Central registry for mod discovery and metadata
- ⏳ **ModLoader** - Dynamic JAR loading and mod initialization
- ⏳ **DependencyResolver** - Topological sorting for mod load order
- ⏳ **LifecycleManager** - Enable/disable/reload hooks for mods
- ⏳ **ModConfigProvider** - Per-mod configuration management

## 📦 Installation

### Maven

```xml
<dependency>
    <groupId>com.argonathsystems.adapter</groupId>
    <artifactId>argonath-mod-api-adapter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Build from Source

```bash
# Clone the repository
git clone https://github.com/Argonath-Systems/02-adapter-mod-api.git
cd 02-adapter-mod-api

# Build with justfile
just compile

# Or build with Maven
mvn clean install
```

## 🚀 Usage

### Basic Mod Entry Point

```java
package com.example.mymod;

import com.argonathsystems.adapter.api.ArgonathPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

public class MyMod extends ArgonathPlugin {
    
    public MyMod(JavaPluginInit init) {
        super(init);
    }
    
    @Override
    public void onEnable() {
        // Mod initialization logic
        getLogger().info("MyMod enabled!");
        
        // Access Argonath-specific init context
        var argonathContext = getArgonathInit();
        // ... use context for Argonath framework integration
    }
    
    @Override
    public void onDisable() {
        // Cleanup logic
        getLogger().info("MyMod disabled!");
    }
}
```

### Manifest Configuration

Create `src/main/resources/manifest.json`:

```json
{
  "Group": "com.example",
  "Name": "MyMod",
  "Version": "1.0.0",
  "Description": "My Argonath-based mod",
  "Authors": [{"Name": "Your Name"}],
  "ServerVersion": "*",
  "Main": "com.example.mymod.MyMod"
}
```

## 🛠️ Development

### Prerequisites

- Java 25 or higher
- Maven 3.9+
- just (command runner)

### Building

```bash
# Compile the project
just compile

# Run tests
just test

# Deploy to local Maven repository
just deploy
```

## 🏛️ Architecture

### Zero Hytale Imports Policy

⚠️ **Policy Exception**: This module is an **exception** to the "no Hytale imports" rule:
- `ArgonathPlugin` **extends** `com.hypixel.hytale.server.core.plugin.JavaPlugin`
- `ArgonathPluginInitConfig` **wraps** `com.hypixel.hytale.server.core.plugin.JavaPluginInit`
- Consumer mods **must import** Hytale types to extend `ArgonathPlugin`

**Rationale**: Direct integration with Hytale's plugin lifecycle system requires exposing these types. This trade-off enables seamless mod loading while sacrificing strict platform abstraction.

**Alternative Approach** (future consideration): Abstract the plugin lifecycle entirely and use adapter pattern to bridge to Hytale internally.

### Dependencies

- **See pom.xml for current dependencies**

## 📖 Documentation

- [Argonath Systems Wiki](https://argonath-systems.github.io/00-Argonath-Wiki)
- [API Documentation](./docs/api)
- [Architecture Documentation](../design/C4)

## 🤝 Contributing

Please read [CONTRIBUTING.md](../CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

## 🔗 Related Projects

- [Argonath Systems](https://github.com/Argonath-Systems)
- [LordOfTheTales](https://github.com/K1ntus/LordOfTheTales)
- [HyUI](https://github.com/Argonath-Systems/HyUI)

---

**Built with ❤️ by the Argonath Systems Team**
