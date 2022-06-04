<div align="center">

<img alt="Obsidian Mod Icon" src="src/main/resources/assets/obsidian/icon.png" width="128">

# Obsidian

Customize the blast resistance of any minecraft block for the [Fabric loader][fabric] in Minecraft 1.18

[![Release](https://img.shields.io/github/v/release/ickerio/obsidian?style=for-the-badge&include_prereleases&sort=semver)][github:releases]

</div>

Simply create a config file `obsidian.json` in the `config` directory of your game/server directory. For example the following config file will set all blocks that were previously unexplodable, to a blast resistance of 9 (same as end stone)
```json
{
    "minecraft:ancient_debris": 9,
    "minecraft:anvil": 9,
    "minecraft:netherite_block": 9,
    "minecraft:crying_obsidian": 9,
    "minecraft:respawn_anchor": 9,
    "minecraft:enchanting_table": 9,
    "minecraft:obsidian": 9,
    "minecraft:reinforced_deepslate": 9,
    "minecraft:ender_chest": 9
}
```

# License
[MIT](LICENSE)

[fabric]: https://fabricmc.net/
[curseforge]: https://www.curseforge.com/minecraft/mc-mods/obsidian
[curseforge:releases]: https://www.curseforge.com/minecraft/mc-mods/obsidian/files
[github:releases]: https://github.com/ickerio/obsidian/releases