package de.kirito.afkattack.mixin;

import de.kirito.afkattack.AfkAttack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.StringVisitable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    @Inject(method = { "renderStatusEffectOverlay" }, at = { @At("RETURN") })
    private void onRenderStatusEffectOverlay(CallbackInfo ci) {
        String status = AfkAttack.isDown == true ? "on" : "off";
        MinecraftClient.getInstance().textRenderer.drawTrimmed(StringVisitable.plain(status),5,5,500,16);
    }
}
