package pl.bagnolimited.template

import eu.okaeri.injector.Injector
import eu.okaeri.injector.OkaeriInjector
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin
import pl.bagnolimited.template.config.ConfigurationFactory
import pl.bagnolimited.template.config.MessagesConfiguration
import pl.bagnolimited.template.listener.PlayerJoinListener
import java.io.File
import kotlin.math.abs

class TemplatePlugin : JavaPlugin() {

    private val MESSAGES_FILE : File = File(this.dataFolder, "messages.yml")

    /* Plugin startup logic */
    override fun onEnable() {
        val ENABLE_TIME : Long = System.currentTimeMillis()

        val injector : Injector = OkaeriInjector.create()

        val configurationFactory = ConfigurationFactory()

        val bukkitAudiences : BukkitAudiences = BukkitAudiences.create(this)
        injector.registerInjectable(bukkitAudiences)

        val miniMessage : MiniMessage = MiniMessage.miniMessage()
        injector.registerInjectable(miniMessage)

        val messagesConfiguration : MessagesConfiguration = configurationFactory
            .createDefault(MessagesConfiguration::class.java, this.MESSAGES_FILE)
        injector.registerInjectable(messagesConfiguration)

        /* Register listeners */
        this.server.pluginManager.registerEvents(injector.createInstance(PlayerJoinListener::class.java), this)

        val milliseconds : Long = abs(ENABLE_TIME - System.currentTimeMillis())
        this.logger.info(" _                            _  _         _  _            _ ");
        this.logger.info("| |__  __ _  __ _  _ _   ___ | |(_) _ __  (_)| |_  ___  __| |");
        this.logger.info("| '_ \\/ _` |/ _` || ' \\ / _ \\| || || '  \\ | ||  _|/ -_)/ _` |");
        this.logger.info("|_.__/\\__,_|\\__, ||_||_|\\___/|_||_||_|_|_||_| \\__|\\___|\\__,_|");
        this.logger.info("            |___/                                            ");

        this.logger.info("Plugin enabled in %d ms.".format(milliseconds))
    }

    /* Plugin shutdown logic */
    override fun onDisable() {}

}