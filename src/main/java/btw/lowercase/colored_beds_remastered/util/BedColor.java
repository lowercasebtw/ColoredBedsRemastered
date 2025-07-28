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

package btw.lowercase.colored_beds_remastered.util;

import btw.lowercase.colored_beds_remastered.ColoredBedsRemastered;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public enum BedColor {
    WHITE,
    LIGHT_GRAY,
    GRAY,
    BLACK,
    BROWN,
    RED,
    ORANGE,
    YELLOW,
    LIME,
    GREEN,
    CYAN,
    LIGHT_BLUE,
    BLUE,
    PURPLE,
    MAGENTA,
    PINK;

    public ResourceLocation getIdentifier() {
        return ColoredBedsRemastered.id(this.name().toLowerCase() + "_bed");
    }

    public ModelResourceLocation getBlockStateIdentifier(IBlockState state) {
        BlockBed.EnumPartType type = state.getValue(BlockBed.PART);
        EnumFacing direction = state.getValue(BlockBed.FACING);
        return new ModelResourceLocation(this.getIdentifier(), "facing=" + direction.name() + ",part=" + type.name());
    }
}
