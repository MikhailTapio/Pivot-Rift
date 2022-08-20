package committee.nova.pivot.mixin;

import committee.nova.pivot.api.listener.player.PlayerPostTickListener;
import committee.nova.pivot.api.listener.player.PlayerPreTickListener;
import net.minecraft.entity.player.EntityPlayer;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {
    @Inject(method = "tick", at = @At("HEAD"))
    public void preTick(CallbackInfo ci) {
        for (PlayerPreTickListener listener : RiftLoader.instance.getListeners(PlayerPreTickListener.class)) {
            listener.onPlayerPreTick((EntityPlayer) (Object) this);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void postTick(CallbackInfo ci) {
        for (PlayerPostTickListener listener : RiftLoader.instance.getListeners(PlayerPostTickListener.class)) {
            listener.onPlayerPostTick((EntityPlayer) (Object) this);
        }
    }


}
