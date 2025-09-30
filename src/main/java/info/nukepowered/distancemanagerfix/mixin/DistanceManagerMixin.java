package info.nukepowered.distancemanagerfix.mixin;

import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.DistanceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(DistanceManager.class)
public class DistanceManagerMixin {

    @Shadow
    @Final
    @Mutable
    Set<ChunkHolder> chunksToUpdateFutures;

    @Inject(
        method = { "<init>(Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;)V" },
        at = @At("RETURN")
    )
    private void reinit(CallbackInfo ci) {
        this.chunksToUpdateFutures = ConcurrentHashMap.newKeySet();
    }
}
