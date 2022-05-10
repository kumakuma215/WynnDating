package fr.kumakuma215.wynndating.mixins

import fr.kumakuma215.wynndating.WynnDating
import fr.kumakuma215.wynndating.config.ConfigManager
import net.minecraft.entity.passive.MerchantEntity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.village.TradeOfferList
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable


@Mixin(MerchantEntity::class)
class MerchantEntityMixin {

    @Inject(method = ["getOffers"], at = [At("HEAD")], cancellable = true)
    private fun injectGetOffers(cir: CallbackInfoReturnable<TradeOfferList>) {
        if(ConfigManager.isModEnabled) {
            cir.cancel()
        }
    }
}