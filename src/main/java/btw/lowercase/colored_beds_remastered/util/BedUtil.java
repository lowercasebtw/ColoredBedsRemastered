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

import net.minecraft.block.BedBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
        World world = MinecraftClient.getInstance().world;
        if (world != null) {
            return world.getBlockState(pos).getBlock() instanceof BedBlock;
        } else {
            return false;
        }
    }

    private static boolean expectPart(World world, BlockPos blockPos, BedBlock.BedBlockType part) {
        return world.getBlockState(blockPos).get(BedBlock.BED_TYPE) == part;
    }

    public static @Nullable BedColor getBedColor(BlockPos pos, Direction direction) {
        World world = MinecraftClient.getInstance().world;
        for (Map.Entry<BlockPos, BedColor> entry : BED_LOCATION_COLOR_MAP.entrySet()) {
            boolean isFoot = (expectPart(world, pos, BedBlock.BedBlockType.FOOT) && pos.equals(entry.getKey())) && (expectPart(world, pos.offset(direction), BedBlock.BedBlockType.HEAD));
            BlockPos headPos = pos.offset(isFoot ? direction : direction.getOpposite());
            boolean isHead = expectPart(world, pos, BedBlock.BedBlockType.HEAD) && ((expectPart(world, headPos, BedBlock.BedBlockType.FOOT) && headPos.equals(entry.getKey())));
            if (isFoot || isHead) {
                return entry.getValue();
            }
        }

        return null;
    }
}
