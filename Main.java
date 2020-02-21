package monsterfighter;

import java.util.*;

public class Main {
    public static void main(String[] args)throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        Monster monster = new Monster();
        Events events = new Events();

        Effects.print("Greetings, adventurer! What should we call you?");
        player.name = sc.nextLine();
        Effects.print("So be it, " + player.name + ". Your tale starts today!\n");
        Thread.sleep(1000);

        while(true) {
            for (int i = 0; i < 5; i++) {
                Events.battle(player, monster);
                if(player.choice_town) return;
            }
            player.battles_finished = 0;
            Events.healing_fountain(player, events);
            player.battle_points = 0;

        }
    }
}
