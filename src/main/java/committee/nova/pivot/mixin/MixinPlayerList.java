package committee.nova.pivot.mixin;

import committee.nova.pivot.api.listener.player.PlayerLoggedInListener;
import committee.nova.pivot.api.listener.player.PlayerLoggedOutListener;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Inject(method = "playerLoggedIn", at = @At("TAIL"))
    public void onLoggedIn(EntityPlayerMP playerIn, CallbackInfo ci) {
        for (PlayerLoggedInListener listener : RiftLoader.instance.getListeners(PlayerLoggedInListener.class)) {
            listener.onPlayerLoggedIn(playerIn);
        }
    }

    @Inject(method = "playerLoggedOut", at = @At("TAIL"))
    public void onLoggedOut(EntityPlayerMP playerIn, CallbackInfo ci) {
        for (PlayerLoggedOutListener listener : RiftLoader.instance.getListeners(PlayerLoggedOutListener.class)) {
            listener.onPlayerLoggedOut(playerIn);
        }
    }
}
