package btw.lowercase.colored_beds_remastered.mixins;

import btw.lowercase.colored_beds_remastered.util.BedColor;
import net.minecraft.block.BlockBed;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO/NOTE: This is for Legacy Fabric as Forge most likely has a API/way to do this
@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {
    @Shadow
    protected abstract ModelBlockDefinition getModelBlockDefinition(ResourceLocation identifier);

    @Shadow
    protected abstract void registerVariant(ModelBlockDefinition modelVariantMap, ModelResourceLocation modelIdentifier);

    @Inject(method = "loadVariantItemModels", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/ModelBakery;loadVariantModels()V"))
    private void coloredBedsRemastered$registerStates(CallbackInfo ci) {
        for (BedColor bedColor : BedColor.values()) {
            ResourceLocation identifier = bedColor.getIdentifier();
            ModelBlockDefinition modelVariantMap = this.getModelBlockDefinition(identifier);
            for (BlockBed.EnumPartType bedPart : BlockBed.EnumPartType.values()) {
                for (EnumFacing direction : EnumFacing.Plane.HORIZONTAL.facings()) {
                    this.registerVariant(modelVariantMap, new ModelResourceLocation(identifier, "facing=" + direction.name() + ",part=" + bedPart.name()));
                }
            }
        }
    }
}
