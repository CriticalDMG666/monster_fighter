package monsterfighter;

import java.util.Random;
import java.util.Scanner;

public class Events {
    private static Random rng = new Random();
    private static Scanner sc = new Scanner(System.in);
    boolean fountain_tutorial = true;

    public static void battle_end(Player player) throws InterruptedException {
        Effects.print("--------------------------------------------");
        do {
            Effects.print("\nThe battle is over, " + player.name + ". Do you wish to exit the dungeon and return to town?" +
                    "\n1.Yes, I want to take a rest(THERE IS NO SAVING, YOUR PROGRESS WILL BE LOST!)" +
                    "\n2.No, I want to continue fighting.");

            player.choice = sc.nextLine();

            if (!Utils.choice_valid(player.choice)) {
                Effects.print("You must only choose with numbers 1 or 2, no letters or symbols");
                player.choice = "gay piece of shit";
            }

        } while (!(player.choice.equals("2") || player.choice.equals("1")));

        switch (player.choice) {
            case "1":
                Effects.print("Alright, returning to town.");
                Thread.sleep(1000);
                player.choice_town = true;
                break;

            case "2":
                Effects.print("Alright, delving deeper into the dungeon!");
                player.battles_finished++;
                Effects.print(">" + (5 - player.battles_finished) + " battles left until you reach a Healing Fountain. Hold on!\n\n" +
                        "----------------------------------------------------------------------------\n");
                Thread.sleep(1000);
                break;
        }
    }

    public static void battle(Player player, Monster monster) throws InterruptedException {
        monster.name = Monster.pick_name();
        int first_turn = rng.nextInt(2);

        Effects.print(player.name + ", " + monster.name + " is in your way!\n");
        if(first_turn == 1)
            Events.battle_player_first(player, monster);
        else
            Events.battle_monster_first(player, monster);

        player.choice_escape = false;
        battle_end(player);
    }

    public static void healing_fountain_empower(Player player) throws InterruptedException {
        for (int i = 0; i < player.battle_points; i++) {
            do {
                Effects.print("1.Amplify Attack (+10% base damage, a bonus of " + (player.base_dmg * 0.1) + " base damage)" +
                        "\n2. Amplify Health (+10% HP, a bonus of " + (player.max_hp * 0.1) + " HP)\n");
                player.choice = sc.nextLine();
            } while (!(player.choice.equals("1") || player.choice.equals("2")));

            switch (player.choice) {
                case "1":
                    player.base_dmg *= 1.1;
                    Effects.print("# Congratulations, " + player.name + "! Your base damage is now " + player.base_dmg + ". You will now slay enemies faster! #\n");
                    break;

                case "2":
                    player.max_hp *= 1.1;
                    player.current_hp = player.max_hp;
                    Effects.print("# Congratulations, " + player.name + "! Your HP is now " + player.max_hp + ". You will now endure more attacks from enemies!\n");
                    break;
            }
        }
    }

    public static void healing_fountain_opening(Player player, Events events) throws InterruptedException {
        Effects.print("# " + player.name + ", good job! You have reached a healing fountain! #\n");
        Thread.sleep(1000);
        if(events.fountain_tutorial) {
            Effects.print("-------------------------------------------------------------------" +
                    "\n\t\t\t Tutorial:" +
                    "\nYou may have noticed that when you beat a monster you obtain a 'Battle Point'." +
                    "\n These battle points serve as chances to improve your attack or your health pool." +
                    "\n-------------------------------------------------------------------");
            events.fountain_tutorial = false;
        }
        player.current_hp = player.max_hp;
        Effects.print("# You have been healed to maximum HP. You are at " + player.max_hp + "HP now. #");
        Effects.print("# In the last 5 battles, you have gathered " + player.battle_points + "/5 battle points. #" +
                "\nYou can now spend them to amplify your HP or attack. Choose wisely:");
    }

    public static void healing_fountain(Player player, Events events) throws InterruptedException {
        healing_fountain_opening(player, events);
        healing_fountain_empower(player);
    }

    public static void battle_player_first(Player player, Monster monster) throws InterruptedException{
        while (true) {
            Player.turn(player, monster);
            if (monster.current_hp <= 0) {
                Player.win(player, monster);
                break;
            }
            else if (player.escape_death) {
                Player.lose(player, monster);
                break;
            }
            else if (player.choice_escape) {
                monster.current_hp = monster.max_hp;
                break;
            }

            Monster.attack(player, monster);
            if (player.current_hp <= 0) {
                Player.lose(player, monster);
                break;
            }
        }
    }

    public static void battle_monster_first(Player player, Monster monster) throws InterruptedException{
       while(true){
           Monster.attack(player, monster);
           if (player.current_hp <= 0) {
               Player.lose(player, monster);
               break;
           }

           Player.turn(player, monster);

           if (monster.current_hp <= 0) {
               Player.win(player, monster);
               break;
           }
           else if (player.escape_death) {
               Player.lose(player, monster);
               break;
           }
               else if (player.choice_escape) {
               monster.current_hp = monster.max_hp;
               break;
           }
       }
    }
}
