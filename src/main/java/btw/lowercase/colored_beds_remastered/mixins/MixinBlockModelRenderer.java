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
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.io.IOException;

@Mixin(BlockModelRenderer.class)
public abstract class MixinBlockModelRenderer {
    @Unique
    private static BakedModel coloredBedsRemastered$getModelById(ModelIdentifier identifier) {
        MinecraftClient client = MinecraftClient.getInstance();
        BakedModelManager modelManager = client.getBlockRenderManager().getModels().getBakedModelManager();
        return modelManager.getByIdentifier(identifier);
    }

    @WrapOperation(method = "render(Lnet/minecraft/world/BlockView;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/render/BufferBuilder;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockModelRenderer;render(Lnet/minecraft/world/BlockView;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/render/BufferBuilder;Z)Z"))
    private boolean coloredBedsRemastered$redirectBedModel(BlockModelRenderer instance, BlockView blockView, BakedModel model, BlockState state, BlockPos pos, BufferBuilder buffer, boolean cull, Operation<Boolean> original) throws IOException {
        if (BedUtil.isBed(pos)) {
            BedColor color = BedUtil.getBedColor(pos, state.get(DirectionProperty.of("facing")));
            if (color != null) {
                model = coloredBedsRemastered$getModelById(color.getBlockStateIdentifier(state));
            }
        }

        return original.call(instance, blockView, model, state, pos, buffer, cull);
    }
}
