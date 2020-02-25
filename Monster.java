package monsterfighter;

import java.util.Random;

public class Monster {
    //These are the monsters' stats:
    public int max_hp = 100;
    public int current_hp = 100;
    public int base_damage = 10;
    public int final_damage;
    public String name;
    public int level = 0;
    //End of monster stats.

    private static Random rng = new Random();

    public static String pick_name(){ //This picks the name of the monster
        String[] monster_names = {"Rabidt", "Crabbington", "Zombie", "Slime", "Jew", "Skeleton", "String placeholder_monster_name", "Lil' Wolf"};
        int monster_name_index = rng.nextInt(monster_names.length);
        return monster_names[monster_name_index];
    }

    public static String description(String monster_name){//This assigns a description to every monster name
        String monster_description = "";
        switch (monster_name) {
            case "Rabidt":
                monster_description = "A rabid rabbit. What the fuck did you expect?";
                break;
            case "Crabbington":
                monster_description = "A crab, only a cultured one, unlike you. Pleb.";
                break;
            case "Zombie":
                monster_description = "Yes, I know that's generic, fuck off.";
                break;
            case "Slime":
                monster_description = "*Slime sounds*";
                break;
            case "Jew":
                monster_description = "He'll get your money. Get it? Because it's a jew. It's funny, because it's a stereotype for jews to be greedy.";
                break;
            case "Skeleton":
                monster_description = "Ok look, I know it's generic, it's just that I really don't know what to add.";
                break;
            case "String placeholder_monster_name":
                monster_description = "It doesn't have a name. It's funny, because this game has very shitty code.";
                break;
            case "Lil' Wolf":
                monster_description = "Its rapping career was ruined by drugs and alcohol. Shoot that bitch to death!";
                break;
        }
        return monster_description;
    }

    public static void attack(Player player, Monster monster) throws InterruptedException{
        monster.final_damage = monster.base_damage + rng.nextInt(monster.base_damage);
        player.current_hp -= monster.final_damage;

        Effects.print("# It's " + monster.name + "'s turn #" +
                "\n>They attack you for " + monster.final_damage + " damage.\n");
    }

    public static void print_info(Player player, Monster monster) throws InterruptedException{
        Effects.print(monster.name + ":" +
                "\nHP:" + monster.current_hp + "/" + monster.max_hp +
                "\nAttack:" + monster.base_damage + " to " + monster.base_damage * 2 +
                "\n" + Monster.description(monster.name) + "\n\n");
        player.choice = "99";
    }

    public static void drop(Player player, Monster monster) throws InterruptedException{
        int equipment_roll = rng.nextInt(6);
        int food_roll = rng.nextInt(2);
        int equipment_roll_2;

        if(monster.level > 2 && monster.level < 8) {
            if (equipment_roll == 1) {
                equipment_roll_2 = rng.nextInt(5);

                if (equipment_roll_2 == 0) {
                    player.inv.stick.count++;
                    Effects.print(monster.name + " has dropped a 'Stick'. It's not much, but it should help you hit harder.");
                } else if (equipment_roll_2 == 1) {
                    player.inv.worn_helm.count++;
                    Effects.print(monster.name + " has dropped a 'Worn Helm'. It's not much, but it should protect you from attacks a little.");
                } else if (equipment_roll_2 == 2) {
                    player.inv.worn_chestplate.count++;
                    Effects.print(monster.name + " has dropped a 'Worn Chestplate'. It's not much, but it will safeguard you from attacks a little.");
                } else if (equipment_roll_2 == 3) {
                    player.inv.worn_leggings.count++;
                    Effects.print(monster.name + " has dropped a 'Worn Leggings'. It's not much, but it will keep you a little safer from attacks.");
                } else if (equipment_roll_2 == 4) {
                    player.inv.worn_boots.count++;
                    Effects.print(monster.name + " has dropped a 'Worn Boots'. It's not much, but it will protect you from attacks a little");
                }
            }

            if(food_roll == 1){
                player.inv.carrot.count++;
                Effects.print(monster.name + " has dropped a 'Carrot'. It can heal you for 50HP when you use it.");
            }
        }

    }

}
