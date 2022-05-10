package fr.kumakuma215.wynndating

import net.minecraft.entity.Entity
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.text.Text
import net.minecraft.util.math.Box

class Utils {

    companion object {
        fun getEntityName(entity: Entity) : String{
            val entities = entity.world.getOtherEntities(
                entity,
                Box(entity.x - 0.5, entity.y, entity.z - 0.5, entity.x + 0.5, entity.y + 5, entity.z + 0.5)
            ) { e -> e is ArmorStandEntity && e.hasCustomName() && e.isCustomNameVisible }

            if (entities.size == 0) {
                return entity.name.string
            } else {

                var maxDist = -1.0
                var farthestEntity: Entity? = null
                for (e in entities) {
                    val dist = e.squaredDistanceTo(entity.x, entity.eyeY, entity.z)
                    if (dist > maxDist) {
                        maxDist = dist
                        farthestEntity = e
                    }
                }

                if (farthestEntity != null) {
                    return farthestEntity.name.string
                } else {
                    throw RuntimeException("[WynnDating] Failed to fetch farthest entity wth this shouldn't be happening")
                }


            }
        }
    }
}