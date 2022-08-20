package committee.nova.pivot.mixin;

import committee.nova.pivot.api.listener.client.ClientChatListener;
import committee.nova.pivot.eventInfo.ClientChatInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.dimdev.riftloader.RiftLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public abstract class MixinGuiScreen {
    @Shadow
    protected Minecraft mc;

    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
    public void onClientMessage(String msg, boolean addToChat, CallbackInfo ci) {
        String msg1 = msg;
        boolean addToChat1 = addToChat;
        for (ClientChatListener listener : RiftLoader.instance.getListeners(ClientChatListener.class)) {
            final ClientChatInfo original = new ClientChatInfo(msg, msg1, addToChat);
            final ClientChatInfo modified = listener.onClientChat(original);
            if (original.equals(modified)) continue;
            msg1 = modified.getMsg();
            addToChat1 = modified.willAddToChat();
        }
        if (msg1.equals(msg) && addToChat == addToChat1) return;
        if (addToChat) this.mc.ingameGUI.getChatGUI().addToSentMessages(msg);
        this.mc.player.sendChatMessage(msg);
        ci.cancel();
    }
}
