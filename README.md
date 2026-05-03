# Argonath Plugin API

> Platform-neutral plugin lifecycle contracts for Argonath modules

## Overview

This module contains the common plugin API used by Argonath runtime modules. It does not extend or expose any game-platform plugin base class. Platform launchers such as the Folia adapter own the server API integration and inject an `ArgonathPluginContext` into each `ArgonathPlugin`.

## Architecture Rule

This artifact must not depend on Hytale, Folia, Paper, Bukkit, Fabric, NeoForge, or client UI libraries. Platform-specific wrappers belong in adapter modules.

## Runtime Model

Argonath modules implement or extend `ArgonathPlugin`:

```java
public final class MyArgonathModule extends ArgonathPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Module enabled");
        getContext().getPlayerAccessor();
    }
}
```

The platform adapter is responsible for:

- loading the game-server plugin entrypoint;
- constructing an `ArgonathPluginContext`;
- registering an `AccessorProvider`;
- initializing the module lifecycle;
- delegating shutdown to `ArgonathPlugin#onDisable()`.

## Current Platform Adapters

- `02-adapter-folia` provides the active Minecraft/Folia server runtime.
- Hytale-specific wrappers are legacy code and are not part of this neutral API.

## Building

```bash
mvn clean install
```
