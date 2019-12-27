package org.bukkit.craftbukkit;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.world.World;
import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.entity.Raider;

public final class CraftRaid implements Raid {

    private final net.minecraft.server.Raid handle;

    public CraftRaid(net.minecraft.server.Raid handle) {
        this.handle = handle;
    }

    @Override
    public boolean isStarted() {
        return handle.isStarted();
    }

    @Override
    public long getActiveTicks() {
        return handle.ticksActive;
    }

    @Override
    public int getBadOmenLevel() {
        return handle.badOmenLevel;
    }

    @Override
    public void setBadOmenLevel(int badOmenLevel) {
        int max = handle.getMaxBadOmenLevel();
        Preconditions.checkArgument(0 <= badOmenLevel && badOmenLevel <= max, "Bad Omen level must be between 0 and %s", max);
        handle.badOmenLevel = badOmenLevel;
    }

    @Override
    public Location getLocation() {
        BlockPosition pos = handle.getCenter();
        World world = handle.getWorld();
        return new Location(world.getWorld(), pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public RaidStatus getStatus() {
        if (handle.isStopped()) {
            return RaidStatus.STOPPED;
        } else if (handle.isVictory()) {
            return RaidStatus.VICTORY;
        } else if (handle.isLoss()) {
            return RaidStatus.LOSS;
        } else {
            return RaidStatus.ONGOING;
        }
    }

    @Override
    public int getSpawnedGroups() {
        return handle.getGroupsSpawned();
    }

    @Override
    public int getTotalGroups() {
        return handle.numGroups + (handle.badOmenLevel > 1 ? 1 : 0);
    }

    @Override
    public int getTotalWaves() {
        return handle.numGroups;
    }

    @Override
    public float getTotalHealth() {
        return handle.sumMobHealth();
    }

    @Override
    public Set<UUID> getHeroes() {
        return Collections.unmodifiableSet(handle.heroes);
    }

    @Override
    public List<Raider> getRaiders() {
        return handle.getRaiders().stream().map(new Function<EntityRaider, Raider>() {
            @Override
            public Raider apply(EntityRaider entityRaider) {
                return (Raider) entityRaider.getBukkitEntity();
            }
        }).collect(ImmutableList.toImmutableList());
    }
}
