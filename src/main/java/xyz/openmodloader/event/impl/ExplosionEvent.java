package xyz.openmodloader.event.impl;

import xyz.openmodloader.event.Event;
import xyz.openmodloader.event.Events;

import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosionEvent extends Event {
    private World world;
    private Entity entity;
    private Explosion explosion;

    public ExplosionEvent(World world, Entity entity, Explosion explosion) {
        this.world = world;
        this.entity = entity;
        this.explosion = explosion;
    }

    public World getWorld() {
        return world;
    }

    public Entity getEntity() {
        return entity;
    }

    public Explosion getExplosion() {
        return explosion;
    }

    public void setExplosion(Explosion explosion) {
        this.explosion = explosion;
    }
}