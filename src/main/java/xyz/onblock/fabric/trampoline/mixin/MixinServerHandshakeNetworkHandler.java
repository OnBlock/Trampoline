package xyz.onblock.fabric.trampoline.mixin;

import xyz.onblock.fabric.trampoline.MixinHelpers;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerHandshakeNetworkHandler;
import net.minecraft.server.network.packet.HandshakeC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerHandshakeNetworkHandler.class)
public class MixinServerHandshakeNetworkHandler {
    
    @Shadow
    private ClientConnection client;
    
    @Inject(at = @At(value = "INVOKE", target = "net/minecraft/network/ClientConnection.setPacketListener(Lnet/minecraft/network/listener/PacketListener;)V", ordinal = 0, shift = Shift.AFTER), method = "onHandshake(Lnet/minecraft/server/network/packet/HandshakeC2SPacket;)V")
    public void onHandshake(HandshakeC2SPacket handshakePacket, CallbackInfo ci) { // First to go.
        MixinHelpers.handshake(handshakePacket, client);
    }
}
