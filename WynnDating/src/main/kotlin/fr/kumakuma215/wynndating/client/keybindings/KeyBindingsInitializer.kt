package fr.kumakuma215.wynndating.client.keybindings

import fr.kumakuma215.wynndating.config.ConfigManager
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

object KeyBindingsInitializer : ClientModInitializer {
    override fun onInitializeClient() {



        val binding1: KeyBinding = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.wynn-dating-mod.toggle-mod",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "key.wynn-dating.config"
            )
        )

        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: MinecraftClient ->
            while (binding1.wasPressed()) {
                ConfigManager.toggleMod()
                if(ConfigManager.isModEnabled) {
                    client.player!!.sendMessage(Text.of("§aMod was enabled"), false)
                }else{
                    client.player!!.sendMessage(Text.of("§cMod was disabled"), false)
                }
            }
        })


    }
}