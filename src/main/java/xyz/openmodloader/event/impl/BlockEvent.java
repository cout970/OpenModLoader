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

    public static class PlaceEvent extends BlockEvent {
        public PlaceEvent(World world, IBlockState state, BlockPos pos) {
            super(world, state, pos);
        }

        public void setBlockState(IBlockState state) {
            this.state = state;
        }

        @Override
        public boolean isCancelable() {
            return true;
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
}
