package monsterfighter;

import java.util.Random;
import java.util.Scanner;

public class Player {
    //These are the player stats:
    public int max_hp = 300;
    public int current_hp = 300;
    public int base_dmg = 20;
    public int final_dmg;
    public String choice;
    public int battles_finished = 0;
    public int battle_points = 0;
    public String name;
    public boolean escape_death;
    public boolean choice_town;
    public boolean choice_escape;
    //End of player stats.

    private static Scanner sc = new Scanner(System.in);
    private static Random rng = new Random();
    public static Items inv = new Items();

    public static void turn(Player player, Monster monster) throws InterruptedException {
        do {
            Effects.print("It's your turn. What will you do? Pick a number from below." +
                    "\t\t\t\tYour HP:" + player.current_hp + "/" + player.max_hp +
                    "\n1.Attack for " + player.base_dmg + " to " + player.base_dmg * 2 + " damage." +
                    "\n2.Run." +
                    "\n3.Display info about " + monster.name + ".\n");
            player.choice = sc.nextLine();
            if(!Utils.choice_valid(player.choice)){
                Effects.print("You must only choose with numbers from 1 to 3, no letters or symbols");
                player.choice = "gay piece of shit";
            }
            if(player.choice.equals("3"))
                Monster.print_info(player, monster);
        }while(!(player.choice.equals("1") || player.choice.equals("2")));

        switch(player.choice){
            case "1":
                Player.attack(player, monster);break;
            case "2":
                Player.run(player, monster); break;
        }
    }

    public static void attack(Player player, Monster monster) throws InterruptedException{
        Effects.print("POW!");
        Thread.sleep(1000);
        player.final_dmg = player.base_dmg+ rng.nextInt(player.base_dmg);
        monster.current_hp -= player.final_dmg;
        Effects.print(">" + monster.name + " was hit for " + player.final_dmg + "!\n");
    }

    public static void win(Player player, Monster monster) throws InterruptedException{
        Effects.print("# " + player.name + ", you have bested " + monster.name + "! The upcoming monsters will be 16% tougher, and you have gained a Battle Point #");
        player.battle_points++;
        // this is where the monsters' level will increase:
        monster.max_hp *= 1.16;
        monster.base_damage *= 1.16;
        monster.current_hp = monster.max_hp;
        monster.level++;
        Thread.sleep(500);
    }

    public static void run(Player player, Monster monster) throws InterruptedException{
        int escape_chance = rng.nextInt(2);
        if (escape_chance == 0) {
            Effects.print(">You successfully escape " + monster.name + ".");
            Effects.dots_delay(3);
            Effects.print("Pussy.");
        }
        else {
            monster.final_damage = monster.base_damage + rng.nextInt(monster.base_damage);
            Effects.print(">You escape " + monster.name + ", but not without a scratch." +
                    "\n>You were hit for " + monster.final_damage + " damage.");
            player.current_hp -= monster.final_damage;
            if(player.current_hp <= 0)
                player.escape_death = true;
        }
        player.choice_escape = true;
    }

    public static void lose(Player player, Monster monster) throws InterruptedException{
        Effects.print("You have fallen in battle against "+ monster.name + "!");
        Effects.print("Now you will have to start from scratch, but hang in there! " +
                "\n-Every 5 battles, you will be able to empower yourself in the healing fountain." +
                "\n-Don't be afraid to run away from the monster. The next one won't be more powerful and you'll reach the healing fountain more easily.");
    }
}
