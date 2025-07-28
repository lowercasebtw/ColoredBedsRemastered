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

import net.minecraft.block.BlockBed;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BedUtil {
    private static final Map<BlockPos, BedColor> BED_LOCATION_COLOR_MAP = new HashMap<>();

    // NOTE: The blockPos is the position of the beds foot, not the head!
    static {
        BED_LOCATION_COLOR_MAP.put(new BlockPos(-7, 1, 0), BedColor.BLUE);
        BED_LOCATION_COLOR_MAP.put(new BlockPos(-5, 1, 2), BedColor.GREEN);
        BED_LOCATION_COLOR_MAP.put(new BlockPos(-3, 1, 0), BedColor.PURPLE);
        BED_LOCATION_COLOR_MAP.put(new BlockPos(-5, 1, -2), BedColor.WHITE);
    }

    public static boolean isBed(BlockPos pos) {
        World world = Minecraft.getMinecraft().theWorld;
        if (world != null) {
            return world.getBlockState(pos).getBlock() instanceof BlockBed;
        } else {
            return false;
        }
    }

    private static boolean expectPart(World world, BlockPos blockPos, BlockBed.EnumPartType part) {
        return world.getBlockState(blockPos).getValue(BlockBed.PART) == part;
    }

    public static @Nullable BedColor getBedColor(BlockPos pos, EnumFacing direction) {
        World world = Minecraft.getMinecraft().theWorld;
        return BED_LOCATION_COLOR_MAP.entrySet().stream().filter(entry -> {
            boolean isFoot = (expectPart(world, pos, BlockBed.EnumPartType.FOOT) && pos.equals(entry.getKey())) && (expectPart(world, pos.offset(direction), BlockBed.EnumPartType.HEAD));
            BlockPos headPos = pos.offset(isFoot ? direction : direction.getOpposite());
            boolean isHead = expectPart(world, pos, BlockBed.EnumPartType.HEAD) && ((expectPart(world, headPos, BlockBed.EnumPartType.FOOT) && headPos.equals(entry.getKey())));
            return isFoot || isHead;
        }).findFirst().map(Map.Entry::getValue).orElse(null);
    }
}
