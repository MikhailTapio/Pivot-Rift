package committee.nova.pivot.api.listener.player;

import net.minecraft.entity.player.EntityPlayer;

public interface PlayerPostTickListener {
    void onPlayerPostTick(EntityPlayer player);
}
