package fr.kumakuma215.wynndating.listeners

import fr.kumakuma215.wynndating.Utils
import fr.kumakuma215.wynndating.WynnDating
import fr.kumakuma215.wynndating.config.ConfigManager
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.entity.passive.HorseBaseEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.Box
import net.minecraft.world.World


class UseEntityListener : UseEntityCallback {

    override fun interact(
        player: PlayerEntity?,
        world: World?,
        hand: Hand?,
        entity: Entity?,
        hitResult: EntityHitResult?
    ): ActionResult {
        if(ConfigManager.isModEnabled) {

            if (entity != null && player?.isSneaky == true) {
                val name = Utils.getEntityName(entity)
                player.sendMessage(Text.of("§aName : $name"), false)
                return ActionResult.CONSUME
            }

        }
        return ActionResult.PASS
    }
}