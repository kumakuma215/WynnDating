package fr.kumakuma215.wynndating.listeners

import fr.kumakuma215.wynndating.Utils
import fr.kumakuma215.wynndating.callbacks.PlayerSneakCallback
import fr.kumakuma215.wynndating.client.data.PlayerData
import net.minecraft.util.registry.Registry
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import kotlin.math.cos
import kotlin.math.sin

class PlayerSneakListener : PlayerSneakCallback {
    override fun interact(player: PlayerEntity?): ActionResult {
        if(player != null){
            if(PlayerData.isInDialogue){


                return ActionResult.PASS
            }else {

                val correctYaw = Math.toRadians(player.yaw.toDouble() + 90)


                val length = 0.5
                val x = (cos(correctYaw) * length) + player.x
                val z = (sin(correctYaw) * length) + player.z


                val entitiesInBound = MinecraftClient.getInstance().world?.getOtherEntities(
                    player,
                    Box(player.x, player.y, player.z, x, player.y + 3, z)
                )

                val closestEntity = Utils.getClosestEntity(entitiesInBound, player)
                if (closestEntity != null) {

                    player.sendMessage(Text.of("§2You kissed ${Utils.getEntityName(closestEntity)}"), false)
                }



                return ActionResult.PASS
            }
        }else{
            return ActionResult.FAIL
        }
    }
}