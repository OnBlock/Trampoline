package xyz.onblock.fabric.trampoline.mixin;

import java.net.InetSocketAddress;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import xyz.onblock.fabric.trampoline.accessors.BungeeProxiedPlayer;
import xyz.onblock.fabric.trampoline.accessors.BungeeConnectionModifier;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity implements BungeeProxiedPlayer {
    
    @Shadow
    private ServerPlayNetworkHandler networkHandler;
    
    @Override
    public InetSocketAddress getRealAddress() {
        return ((BungeeConnectionModifier) networkHandler.client).getRealAddress();
    }
    
}
