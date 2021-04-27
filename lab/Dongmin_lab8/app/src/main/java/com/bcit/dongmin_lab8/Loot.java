package com.bcit.dongmin_lab8;

public class Loot
{
    String lootId;
    String itemOne;
    String itemTwo;
    String itemThree;

    public Loot() {}

    public Loot(String lootId, String itemOne, String itemTwo, String itemThree)
    {
        this.lootId = lootId;
        this.itemOne = itemOne;
        this.itemTwo = itemTwo;
        this.itemThree = itemThree;
    }

    public String getLootId() { return lootId; }

    public void setLootId(String lootId) {
        this.lootId = lootId;
    }

    public String getItemOne() {return itemOne;}

    public void setItemOne(String itemOne) {this.itemOne = itemOne;}

    public String getItemTwo() {return itemTwo;}

    public void setItemTwo(String itemTwo) {this.itemTwo = itemTwo;}

    public String getItemThree() {return itemThree;}

    public void setItemThree(String itemThree) {
        this.itemThree = itemThree;
    }
}
