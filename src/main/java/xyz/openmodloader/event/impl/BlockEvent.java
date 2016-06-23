package xyz.openmodloader.event.impl;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.openmodloader.event.Event;
import xyz.openmodloader.event.Events;

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

        public static IBlockState onPlace (World var3, IBlockState var13, BlockPos var4) {
            xyz.openmodloader.event.impl.BlockEvent.Place event = new xyz.openmodloader.event.impl.BlockEvent.Place(var3, var13, var4);
            if (!xyz.openmodloader.event.Events.BLOCK_PLACE.post(event))
                return null;
            return event.getBlockState();
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

        public static float onDig (float digSpeed, World world, IBlockState state, BlockPos pos) {
            DigSpeed event = new DigSpeed(digSpeed, world, state, pos);
            if(!Events.DIG_SPEED.post(event) || event.getDigSpeed() < 0.0F)
                return 0.0F;
            return event.getDigSpeed();
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
