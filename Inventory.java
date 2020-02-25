package monsterfighter;

public class Inventory {
    //Food
    Item carrot = new Item("Carrot","Food", 0, 10, 0, 0, 50);
    Item apple = new Item("Apple","Food", 0, 20, 0, 0, 100);
    Item orange = new Item("Orange","Food", 0, 20, 0, 0, 200);

    //Weapons
    Item stick = new Item("Stick","Weapon", 0, 50, 0, 5, 0);

    //Helmets
    Item worn_helm = new Item("Worn Helm", "Helm", 0, 30, 3, 0, 0);

    //Chests
    Item worn_chestplate = new Item("Worn Chestplate", "Chestplate", 0, 50, 5, 0, 0);

    //Leggings
    Item worn_leggings = new Item("Worn Leggings", "Leggings", 0, 40, 4, 0, 0);

    //Boots
    Item worn_boots = new Item("Worn Boots", "Boots", 0, 20, 2, 0, 0);
}
