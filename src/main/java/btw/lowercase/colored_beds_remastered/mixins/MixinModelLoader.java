package btw.lowercase.colored_beds_remastered.mixins;

import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class MixinModelLoader {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void coloredBedsRemastered$loadBlockstates(CallbackInfo ci) {
    }
}
