package xyz.openmodloader.event.impl;

import xyz.openmodloader.event.Event;
import xyz.openmodloader.event.Events;

import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosionEvent extends Event {
    public final World world;

    public Entity entity;
    public double x;
    public double y;
    public double z;
    public float explosionSize;
    public boolean isFlaming;
    public boolean isSmoking;

    public ExplosionEvent (World world, Entity entity, double x, double y, double z, float explosionSize, boolean isFlaming, boolean isSmoking) {
        this.world = world;
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.explosionSize = explosionSize;
        this.isFlaming = isFlaming;
        this.isSmoking = isSmoking;
    }

    public static Explosion onExplosion(World world, Entity entity, double x, double y, double z, float explosionSize, boolean isFlaming, boolean isSmoking) {
        final ExplosionEvent event = new ExplosionEvent(world,entity,x,y,z,explosionSize, isFlaming, isSmoking);
        Events.EXPLOSION_EVENT_EVENTS.post(event);
        if(event.isCancelable() && event.isCanceled())
            return null;

        return new Explosion(event.world, event.entity, event.x, event.y, event.z, event.explosionSize, event.isFlaming, event.isSmoking);
    }
}