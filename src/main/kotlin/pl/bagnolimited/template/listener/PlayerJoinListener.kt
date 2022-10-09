package pl.bagnolimited.template.listener

import eu.okaeri.injector.annotation.Inject
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import pl.bagnolimited.template.config.MessagesConfiguration

class PlayerJoinListener @Inject constructor(bukkitAudiences: BukkitAudiences, miniMessage: MiniMessage, messagesConfiguration: MessagesConfiguration) : AbstractListener(bukkitAudiences, miniMessage, messagesConfiguration) {

    @EventHandler
    private fun handle(event : PlayerJoinEvent) {
        val player : Player = event.player
        val audience : Audience = this.bukkitAudiences.player(player)

        val component : Component = this.miniMessage
            .deserialize(this.messagesConfiguration.exampleListenerMessage, Placeholder.parsed("player", player.displayName))
        audience.sendMessage(component)
    }

}