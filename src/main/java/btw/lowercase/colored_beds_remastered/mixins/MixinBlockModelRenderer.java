/**
 * Colored Beds Remastered
 * A remake of the classic colored beds mod that backports the 1.12 colored beds, for Bedwars!
 * <p>
 * Copyright (C) 2024-2025 lowercasebtw
 * Copyright (C) 2024-2025 Contributors to the project retain their copyright
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package btw.lowercase.colored_beds_remastered.mixins;

import btw.lowercase.colored_beds_remastered.util.BedColor;
import btw.lowercase.colored_beds_remastered.util.BedUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.io.IOException;

@Mixin(BlockModelRenderer.class)
public abstract class MixinBlockModelRenderer {
    @Unique
    private static IBakedModel coloredBedsRemastered$getModelById(ModelResourceLocation identifier) {
        Minecraft client = Minecraft.getMinecraft();
        ModelManager modelManager = client.getBlockRendererDispatcher().getBlockModelShapes().getModelManager();
        return modelManager.getModel(identifier);
    }

    @WrapOperation(method = "renderModel(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/resources/model/IBakedModel;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/BlockPos;Lnet/minecraft/client/renderer/WorldRenderer;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/BlockModelRenderer;renderModel(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/resources/model/IBakedModel;Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/BlockPos;Lnet/minecraft/client/renderer/WorldRenderer;Z)Z"))
    private boolean coloredBedsRemastered$redirectBedModel(BlockModelRenderer instance, IBlockAccess blockView, IBakedModel model, IBlockState state, BlockPos pos, WorldRenderer buffer, boolean cull, Operation<Boolean> original) throws IOException {
        if (BedUtil.isBed(pos)) {
            BedColor color = BedUtil.getBedColor(pos, state.getValue(PropertyDirection.create("facing")));
            if (color != null) {
                model = coloredBedsRemastered$getModelById(color.getBlockStateIdentifier(state));
            }
        }

        return original.call(instance, blockView, model, state, pos, buffer, cull);
    }
}
