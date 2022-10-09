package pl.bagnolimited.template.config

import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit
import java.io.File

class ConfigurationFactory {

    fun <T : OkaeriConfig> create(type: Class<T>?, file: File?, bukkitTypes: Boolean): T {
        return if (bukkitTypes) ConfigManager.create(type!!) { config: OkaeriConfig ->
            config.withConfigurer(YamlBukkitConfigurer(), SerdesBukkit())
            config.withBindFile(file!!)
            config.saveDefaults()
            config.load()
        } else ConfigManager.create(type!!) { config: OkaeriConfig ->
            config.withConfigurer(YamlBukkitConfigurer())
            config.withBindFile(file!!)
            config.saveDefaults()
            config.load()
        }
    }

    fun <T : OkaeriConfig> createDefault(type: Class<T>, file: File): T {
        return create(type, file, false)
    }

}