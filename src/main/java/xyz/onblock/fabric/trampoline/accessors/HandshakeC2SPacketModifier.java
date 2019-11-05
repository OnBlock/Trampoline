package xyz.onblock.fabric.trampoline.accessors;

public interface HandshakeC2SPacketModifier {
    String getAddress();
    
    void setAddress(String s);
    
    int getPort();
}
