package de.kirito.afkattack.mixin;

import de.kirito.afkattack.AfkAttack;
import de.kirito.afkattack.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
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

        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        if(!config.EnableOnScreenState) return;

        String status = AfkAttack.isDown == true ? "on" : "off";
        MinecraftClient.getInstance().textRenderer.drawTrimmed(StringVisitable.plain(status),5,5,500,16);
    }
}
