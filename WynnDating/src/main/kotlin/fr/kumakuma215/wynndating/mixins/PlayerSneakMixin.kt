package fr.kumakuma215.wynndating.mixins

import fr.kumakuma215.wynndating.callbacks.PlayerSneakCallback
import net.minecraft.client.input.Input
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.util.ActionResult
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo


@Mixin(ClientPlayerEntity::class)
abstract class PlayerSneakMixin {

    @Shadow val input: Input? = null

    private var lastSneak: Boolean? = null

    @Inject(at = [At("TAIL")], method = ["tickMovement()V"])
    private fun onSneak(info: CallbackInfo) {
        if(input?.sneaking != lastSneak){
            this.lastSneak = input?.sneaking
            println("toggled")
            if(lastSneak == true){
                val casted: ClientPlayerEntity = this as ClientPlayerEntity
                val result: ActionResult = PlayerSneakCallback.EVENT.invoker().interact(casted)

                if (result === ActionResult.FAIL) {
                    info.cancel()
                }
            }
        }
    }
}