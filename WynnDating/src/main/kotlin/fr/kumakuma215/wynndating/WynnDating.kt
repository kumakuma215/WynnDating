package fr.kumakuma215.wynndating

import fr.kumakuma215.wynndating.callbacks.PlayerSneakCallback
import fr.kumakuma215.wynndating.config.ConfigManager
import fr.kumakuma215.wynndating.listeners.UseEntityListener
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.option.StickyKeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import org.lwjgl.glfw.GLFW


@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object WynnDating: ModInitializer {
    private const val MOD_ID = "wynndating"
    override fun onInitialize() {
        println("Wynndating mod has been initialized.")
        UseEntityCallback.EVENT.register(UseEntityListener())
        PlayerSneakCallback.EVENT.register(PlayerSneakCallback{
            println(it.isSneaky)
            ActionResult.PASS
        })
    }
}