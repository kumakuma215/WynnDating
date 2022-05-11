package fr.kumakuma215.wynndating.mixins;

import fr.kumakuma215.wynndating.callbacks.PlayerSneakCallback;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class PlayerSneakMixin {
    @Shadow
    public Input input;

    private boolean riding = false;
    private boolean lastSneak;

    @Inject(at = @At("HEAD"), method = "startRiding")
    public void onRide(Entity entity, boolean force, CallbackInfoReturnable<Boolean> cir){
        riding = true;
    }

    @Inject(at= @At("HEAD"), method = "method_29239")
    public void stopRide(CallbackInfo ci){
        riding = false;
    }

    @Inject(at = @At("TAIL"), method = "tickMovement()V", cancellable = true)
    public void onSneak(CallbackInfo info) {
        if(input != null && input.sneaking != this.lastSneak){
            this.lastSneak = this.input.sneaking;
            if (lastSneak && !riding) {

                ClientPlayerEntity casted = (ClientPlayerEntity)(Object)this;
                ActionResult result = PlayerSneakCallback.EVENT.invoker().interact(casted);
                if (result == ActionResult.FAIL) {
                    info.cancel();
                }
            }
        }

    }
}
