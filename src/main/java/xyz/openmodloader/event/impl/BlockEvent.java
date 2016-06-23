package xyz.openmodloader.event.impl;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.openmodloader.OpenModLoader;
import xyz.openmodloader.event.Event;

/**
 * Parent class for block related events. All events that fall within this scope
 * should extend this class. They should also be added as an inner class however
 * this is not required.
 */
public class BlockEvent extends Event {

    /**
     * The world where the event took place.
     */
    protected World world;

    /**
     * The state of the block involved with the event.
     */
    protected IBlockState state;

    /**
     * The position of the event.
     */
    protected BlockPos pos;

    /**
     * Constructor for the base block event. This constructor should only be
     * accessed through super calls.
     * 
     * @param world The world where the event took place.
     * @param state The state of the block involved with the event.
     * @param pos The position of the event.
     */
    public BlockEvent(World world, IBlockState state, BlockPos pos) {
        this.world = world;
        this.state = state;
        this.pos = pos;
    }

    /**
     * An event that is fired just before a block is placed.
     */
    public static class Place extends BlockEvent {

        /**
         * Constructor for a new event that is fired when a block is placed.
         * 
         * @param world The world where the event took place.
         * @param state The state of the block involved with the event.
         * @param pos The position of the event.
         */
        public Place(World world, IBlockState state, BlockPos pos) {
            super(world, state, pos);
        }

        /**
         * Sets the state to block state to a new one.
         * 
         * @param state The new state for the block.
         */
        public void setBlockState(IBlockState state) {
            this.state = state;
        }

        /**
         * Hook to make related patches much cleaner.
         * 
         * @param world The world where the event took place.
         * @param state The state of the block involved with the event.
         * @param pos The position of the event.
         * @return The state for the block to be placed.
         */
        public static IBlockState onPlace(World world, IBlockState state, BlockPos pos) {
            xyz.openmodloader.event.impl.BlockEvent.Place event = new xyz.openmodloader.event.impl.BlockEvent.Place(world, state, pos);
            return OpenModLoader.INSTANCE.EVENT_BUS.post(event) ? event.getBlockState() : null;
        }
    }

    /**
     * An event that is fired just before a block is destroyed.
     */
    public static class Destroy extends BlockEvent {

        /**
         * Constructor for a new event that is fired when a block is destroyed.
         * 
         * @param world The world where the event took place.
         * @param state The state of the block involved with the event.
         * @param pos The position of the event.
         */
        public Destroy(World world, IBlockState state, BlockPos pos) {
            super(world, state, pos);
        }
    }

    /**
     * An event that is fired while a player mines a block. It is specifically
     * focused on the speed of which the block is mined.
     */
    public static class DigSpeed extends BlockEvent {

        /**
         * The speed that the block will be mined.
         */
        private float digSpeed;

        /**
         * Constructor for a new event that is fired while a block is being
         * mined.
         * 
         * @param digSpeed The speed that the block is being mined at.
         * @param world The world where the event took place.
         * @param state The state of the block involved with the event.
         * @param pos The position of the event.
         */
        public DigSpeed(float digSpeed, World world, IBlockState state, BlockPos pos) {
            super(world, state, pos);
            this.digSpeed = digSpeed;
        }

        /**
         * Gets the speed that the the block is being mined at.
         * 
         * @return The speed the block is being mined at.
         */
        public float getDigSpeed() {
            return digSpeed;
        }

        /**
         * Sets the speed that the block should be mined at.
         * 
         * @param digSpeed The new dig speed.
         */
        public void setDigSpeed(float digSpeed) {
            this.digSpeed = digSpeed;
        }

        /**
         * Hook to make related patches much cleaner.
         * 
         * @param digSpeed The speed at which the block is being mined.
         * @param world The world where the event took place.
         * @param state The state of the block involved with the event.
         * @param pos The position of the event.
         * @return The state for the block to be placed.
         */
        public static float onDig(float digSpeed, World world, IBlockState state, BlockPos pos) {
            DigSpeed event = new DigSpeed(digSpeed, world, state, pos);
            return OpenModLoader.INSTANCE.EVENT_BUS.post(event) || event.getDigSpeed() < 0F ? 0f : event.getDigSpeed();
        }
    }

    /**
     * Gets the world that the event took place in.
     * 
     * @return The world where the event took place.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the state of the block involved with the event.
     * 
     * @return The state of the block involved with the event.
     */
    public IBlockState getBlockState() {
        return state;
    }

    /**
     * Gets the position of the event.
     * 
     * @return The position of the event.
     */
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
