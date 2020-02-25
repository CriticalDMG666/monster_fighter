package monsterfighter;

public class Item {

    //Item parameters:
    public String type;
    public String name;
    public int count = 0;
    public int heal = 0;
    public int price = 0;
    public int armour = 0;
    public int damage = 0;
    //End of item parameters

    Item(String name,String type, int count, int price, int armour, int damage, int heal){
        this.count = count;
        this.price = price;
        this.armour = armour;
        this.damage = damage;
        this.heal = heal;
        this.type = type;
        this.name = name;
    }


}
