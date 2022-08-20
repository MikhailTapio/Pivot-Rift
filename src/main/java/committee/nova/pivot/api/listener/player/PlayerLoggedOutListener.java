package committee.nova.pivot.api.listener.player;

import net.minecraft.entity.player.EntityPlayerMP;

public interface PlayerLoggedOutListener {
    void onPlayerLoggedOut(EntityPlayerMP player);
}
