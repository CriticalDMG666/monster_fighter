package monsterfighter;

import java.util.Random;

class Item{
    int count = 0;
    int heal;
    String name;
    Type type;

    enum Type{
        food,
        weapon,
        helmet,
        chestplate,
        leggings,
        boots
    }

    public Item(Type type, String name, int heal){
        this.name = name;
        this.heal = heal;
        this.type = type;
    }

}

class Items{
    Item carrot = new Item(Item.Type.food, "Carrot", 50);
    Item apple = new Item(Item.Type.food, "Apple", 200);
    Item banana = new Item(Item.Type.food, "Banana", 800);
}

public class Inventory {
    private static Random rng = new Random();

}
