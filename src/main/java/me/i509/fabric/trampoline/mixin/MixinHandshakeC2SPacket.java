package me.i509.fabric.trampoline.mixin;

import java.io.IOException;

import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import me.i509.fabric.trampoline.accessors.HandshakeC2SPacketModifier;
import net.minecraft.network.NetworkState;
import net.minecraft.util.PacketByteBuf;

@Mixin(HandshakeC2SPacket.class)
public class MixinHandshakeC2SPacket implements HandshakeC2SPacketModifier {

    @Shadow
    private int port;

    @Shadow
    private String address;


    @Shadow private NetworkState intendedState;

    @Shadow private int protocolVersion;

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public int getPort() {
        return this.port;
    }
    
    /**
     * @reason Bungee sends a much larger handshake address than 255 characters, so we must read all of it.
     * @author i509VCB
     */
    @Overwrite
    public void read(PacketByteBuf byteBuf) throws IOException {
        this.protocolVersion = byteBuf.readVarInt();
        this.address = byteBuf.readString(Short.MAX_VALUE);
        this.port = byteBuf.readUnsignedShort();
        this.intendedState = NetworkState.byId(byteBuf.readVarInt());
    }
}
