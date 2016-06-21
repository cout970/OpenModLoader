package xyz.openmodloader.event.impl;

import xyz.openmodloader.event.Event;

public class UpdateEvent extends Event {
    public static class World extends UpdateEvent {
        private net.minecraft.world.World world;

        public World(net.minecraft.world.World world) {
            this.world = world;
        }

        public net.minecraft.world.World getWorld() {
            return world;
        }
    }

    public static class Entity extends World {
        private net.minecraft.entity.Entity entity;

        public Entity(net.minecraft.world.World world, net.minecraft.entity.Entity entity) {
            super(world);
            this.entity = entity;
        }

        public net.minecraft.entity.Entity getEntity() {
            return entity;
        }
    }

    public static class Render extends UpdateEvent {
        private float partialTicks;

        public Render(float partialTicks) {
            this.partialTicks = partialTicks;
        }

        public float getPartialTicks() {
            return partialTicks;
        }
    }
}
