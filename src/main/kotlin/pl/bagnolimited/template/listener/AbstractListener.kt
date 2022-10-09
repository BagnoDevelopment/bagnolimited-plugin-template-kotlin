package pl.bagnolimited.template.listener

import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.Listener
import pl.bagnolimited.template.config.MessagesConfiguration

abstract class AbstractListener(bukkitAudiences: BukkitAudiences, miniMessage: MiniMessage, messagesConfiguration: MessagesConfiguration) : Listener {

    protected val bukkitAudiences : BukkitAudiences
    protected val miniMessage : MiniMessage
    protected val messagesConfiguration : MessagesConfiguration

    init {
        this.bukkitAudiences = bukkitAudiences
        this.miniMessage = miniMessage
        this.messagesConfiguration = messagesConfiguration
    }

}