package xyz.openmodloader.event.impl;

import java.util.List;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import xyz.openmodloader.event.Event;
import xyz.openmodloader.event.Events;

/**
 * Fired when the player enchants an item. Allows for the cost and enchantments
 * to be altered, along with the enchanted item itself and the fuel item.
 */
public class ItemEnchantedEvent extends Event {

    /**
     * The player who is enchanting the item.
     */
    private final EntityPlayer player;

    /**
     * The item being enchanted.
     */
    private final ItemStack stack;

    /**
     * The item being used as fuel in the Lapis Lazuli slot.
     */
    private final ItemStack fuel;

    /**
     * The list containing all of the enchantments to be added to the enchanted
     * item.
     */
    private final List<EnchantmentData> enchantments;

    /**
     * The amount of experience levels being paid for the enchantments.
     */
    private int levels;

    /**
     * Constructs an event that is fired when the player enchants an item.
     *
     * @param player The player enchanting the item.
     * @param stack The ItemStack being enchanted.
     * @param fuel The ItemStack in the fuel/lapis slot.
     * @param levels The experience level cost for the enchantment.
     * @param enchantments A list of enchantment data being applied to the item.
     */
    public ItemEnchantedEvent(EntityPlayer player, ItemStack stack, ItemStack fuel, int levels, List<EnchantmentData> enchantments) {
        this.player = player;
        this.stack = stack;
        this.fuel = fuel;
        this.levels = levels;
        this.enchantments = enchantments;
    }

    /**
     * Gets the player who enchanted the item.
     *
     * @return The player who enchanted the item.
     */
    public EntityPlayer getPlayer() {
        return player;
    }

    /**
     * Gets the ItemStack being enchanted.
     *
     * @return The ItemStack being enchanted.
     */
    public ItemStack getItemStack() {
        return stack;
    }

    /**
     * Gets the ItemStack in the fuel slot. This is where Lapis Lazuli goes.
     *
     * @return The ItemStack in the fuel slot.
     */
    public ItemStack getFuelStack() {
        return fuel;
    }

    /**
     * Gets the list of enchantment data being added to the ItemStack.
     *
     * @return The enchantment data being added to the ItemStack.
     */
    public List<EnchantmentData> getEnchantments() {
        return enchantments;
    }

    /**
     * Gets the experience level cost for the enchantments.
     *
     * @return The experience level cost for the enchantments.
     */
    public int getLevels() {
        return levels;
    }

    /**
     * Sets the experience level cost for the enchantments.
     *
     * @param levels The new experience level cost for the enchantments.
     */
    public void setLevels(int levels) {
        this.levels = levels;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    public static List<EnchantmentData> onItemEnchanted(EntityPlayer player, ItemStack stack, ItemStack fuel, int levels, List<EnchantmentData> enchantments) {
        ItemEnchantedEvent event = new ItemEnchantedEvent(player, stack, fuel, levels, enchantments);
        if (!Events.ITEM_ENCHANTED.post(event))
            return null;
        return event.getEnchantments();
    }
}