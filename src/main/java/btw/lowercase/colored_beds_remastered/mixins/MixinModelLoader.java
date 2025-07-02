package btw.lowercase.colored_beds_remastered.mixins;

import btw.lowercase.colored_beds_remastered.util.BedColor;
import net.minecraft.block.BedBlock;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.ModelVariantMap;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ModelLoader.class)
public abstract class MixinModelLoader {
    @Shadow protected abstract ModelVariantMap method_10391(Identifier identifier);

    @Shadow protected abstract void method_10387(ModelVariantMap modelVariantMap, ModelIdentifier modelIdentifier);

    @Inject(method = "method_10393", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;method_10396()V"))
    private void coloredBedsRemastered$loadBlockstates(CallbackInfo ci) {
        for (BedColor c : BedColor.values()) {
            Identifier identifier = c.getIdentifier();
            ModelVariantMap modelVariantMap = this.method_10391(identifier);

            for (BedBlock.BedBlockType type : BedBlock.BedBlockType.values()) {
                for (Direction direction : Direction.DirectionType.HORIZONTAL.getDirections()) {
                    ModelIdentifier modelIdentifier = new ModelIdentifier(identifier, "facing=" + direction.name() + ",part=" + type.name());
                    this.method_10387(modelVariantMap, modelIdentifier);
                }
            }
        }
    }
}
