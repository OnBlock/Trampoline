package xyz.onblock.fabric.trampoline.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.onblock.fabric.trampoline.Trampoline;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CommandManager.class)
public class MixinCommandManager {

    @Shadow @Final private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(method = "<init>", at = @At(value = "HEAD", target = "Lnet/minecraft/server/command/CommandManager;<init>(Z)V"))

    private void setDispatcher(boolean boolean_1, CallbackInfo ci) {
        Trampoline.dispatcher = this.dispatcher;
    }

}
