package org.bukkit.craftbukkit.entity.memory;

import net.minecraft.util.registry.Registry;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.entity.memory.MemoryKey;

public final class CraftMemoryKey {

    private CraftMemoryKey() {}

    public static <T, U> MemoryModuleType<U> fromMemoryKey(MemoryKey<T> memoryKey) {
        return (MemoryModuleType<U>) IRegistry.MEMORY_MODULE_TYPE.get(CraftNamespacedKey.toMinecraft(memoryKey.getKey()));
    }

    public static <T, U> MemoryKey<U> toMemoryKey(MemoryModuleType<T> memoryModuleType) {
        return MemoryKey.getByKey(CraftNamespacedKey.fromMinecraft(IRegistry.MEMORY_MODULE_TYPE.getKey(memoryModuleType)));
    }
}
