package org.bukkit.craftbukkit.entity;

import net.minecraft.entity.projectile.SnowballEntity;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;

public class CraftSnowball extends CraftProjectile implements Snowball {
    public CraftSnowball(CraftServer server, EntitySnowball entity) {
        super(server, entity);
    }

    @Override
    public EntitySnowball getHandle() {
        return (EntitySnowball) entity;
    }

    @Override
    public String toString() {
        return "CraftSnowball";
    }

    @Override
    public EntityType getType() {
        return EntityType.SNOWBALL;
    }
}
