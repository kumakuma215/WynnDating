package fr.kumakuma215.wynndating

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.Box

object Utils {
    fun getEntityName(entity: Entity): String {
        val entities = entity.world.getOtherEntities(
            entity,
            Box(entity.x - 0.5, entity.y, entity.z - 0.5, entity.x + 0.5, entity.y + 8, entity.z + 0.5)
        ) { e -> e is ArmorStandEntity && e.hasCustomName() && e.isCustomNameVisible }

        return if (entities.size == 0) {
            entity.name.string
        } else {

            val farthestEntity = getFarthestEntity(entities, entity)


            if (farthestEntity != null) {
                farthestEntity.name.string
            } else {
                throw RuntimeException("[WynnDating] Failed to fetch farthest entity wth this shouldn't be happening")
            }


        }
    }

    fun getFarthestEntity(entities: List<Entity>, entity: Entity): Entity? {
        var maxDist = -1.0
        var farthestEntity: Entity? = null
        for (e in entities) {
            val dist = e.squaredDistanceTo(entity.x, entity.eyeY, entity.z)
            if (dist > maxDist) {
                maxDist = dist
                farthestEntity = e
            }
        }
        return farthestEntity
    }

    fun getClosestEntity(entities: List<Entity>?, entity: Entity): Entity? {
        if (entities == null) {
            return null
        }
        var minDist = Double.MAX_VALUE
        var closestEntity: Entity? = null
        for (e in entities) {
            val dist = e.squaredDistanceTo(entity.x, entity.eyeY, entity.z)
            if (dist < minDist) {
                minDist = dist
                closestEntity = e
            }
        }
        return closestEntity
    }

}