package de.kirito.afkattack;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.options.StickyKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

@Environment(EnvType.CLIENT)
public class AfkAttack implements ClientModInitializer {

	private float swing = 0.0f;
	public static boolean isDown=false;
	@Override
	public void onInitializeClient() {
		KeyBinding stickyBinding = KeyBindingHelper.registerKeyBinding(new StickyKeyBinding("ToggleAfkAttack", GLFW.GLFW_KEY_R, "AfkAttack", () -> true));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(stickyBinding.wasPressed()){
				client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.1f));
				client.player.sendMessage(new LiteralText("Turned afkAttack off"),true);
				isDown = false;
			}
			if (stickyBinding.isPressed()) {
				if(isDown == false){
					client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,2.0f));
					client.player.sendMessage(new LiteralText("Turned afkAttack on"),true);
					isDown = true;
				}
				swing += .05f;
				if(swing>1){
					HitResult target = client.crosshairTarget;

					switch(target.getType()){
						case MISS:
						case BLOCK:
						default:
							break;
						case ENTITY:
							EntityHitResult entityTarget = (EntityHitResult) target;
							if(entityTarget.getEntity() instanceof PlayerEntity) break;
							try {
								client.interactionManager.attackEntity((PlayerEntity) client.player, entityTarget.getEntity());
							}catch(Exception e) {
							}

							swing = 0;
							break;
					}

				}
			}
		});
	}
}