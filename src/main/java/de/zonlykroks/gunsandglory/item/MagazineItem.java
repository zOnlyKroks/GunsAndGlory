package de.zonlykroks.gunsandglory.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.ArrayDeque;
import java.util.Deque;

public class MagazineItem extends Item {

    private final Deque<ItemStack> currentAmmunition;
    private final int maxAmount;

    public MagazineItem(int size) {
        super(new FabricItemSettings());
        currentAmmunition = new ArrayDeque<>();
        this.maxAmount = size;
    }

    public void saveNBT(ItemStack stack) {
        NbtList nbtList = new NbtList();

        currentAmmunition.forEach(bulletItem -> {
            NbtCompound compound = new NbtCompound();
            bulletItem.writeNbt(compound);
            nbtList.add(compound);
        });

        stack.getNbt().put("currentAmmunition",nbtList);
    }

    public void loadNBT(ItemStack stack) {
        NbtList nbtList = stack.getNbt().getList("currentAmmunition",9);
        nbtList.forEach(nbtElement -> {
            currentAmmunition.add(ItemStack.fromNbt((NbtCompound) nbtElement));
        });
    }

    public boolean addBullet(ItemStack item) {
        if(currentAmmunition.size() < maxAmount) {
            currentAmmunition.add(item);
            return true;
        }
        return false;
    }
}
