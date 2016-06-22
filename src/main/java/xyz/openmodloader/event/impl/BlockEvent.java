package xyz.openmodloader.event.impl;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.openmodloader.event.Event;

public class BlockEvent extends Event {
    protected World world;
    protected IBlockState state;
    protected BlockPos pos;

    public BlockEvent(World world, IBlockState state, BlockPos pos) {
        this.world = world;
        this.state = state;
        this.pos = pos;
    }

    public static class Place extends BlockEvent {
        public Place(World world, IBlockState state, BlockPos pos) {
            super(world, state, pos);
        }

        public void setBlockState(IBlockState state) {
            this.state = state;
        }
    }

    public static class Destroy extends BlockEvent {
        public Destroy(World world, IBlockState state, BlockPos pos) {
            super(world, state, pos);
        }
    }

    public static class DigSpeed extends BlockEvent {
        private float digSpeed;

        public DigSpeed(float digSpeed, World world, IBlockState state, BlockPos pos) {
            super(world, state, pos);
            this.digSpeed = digSpeed;
        }

        public float getDigSpeed() {
            return digSpeed;
        }

        public void setDigSpeed(float digSpeed) {
            this.digSpeed = digSpeed;
        }
    }

    public World getWorld() {
        return world;
    }

    public IBlockState getBlockState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
