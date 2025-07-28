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

package btw.lowercase.colored_beds_remastered;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.ResourceLocation;

public class ColoredBedsRemastered implements ClientModInitializer {
    public static final String MOD_ID = "colored_beds_remastered";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
    }
}