package com.example.game;

import android.content.Context;
import android.util.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

/**A class that manages players.**/
class PlayerManager {

    private static ArrayList<Player> players = new ArrayList<>();

    private ArrayList<String> playerData = new ArrayList<>();

    private HashMap<Player, Integer> playerStates = new HashMap<>();

    private static ArrayList<Pair<String, Integer>> highscores = new ArrayList<>();

    static Context context;

    public static void makeList() {

        try (Scanner scanner = new Scanner(context.openFileInput("players.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                players.add(new Player(data));
                highscores.add(new Pair<>(data[0], parseInt(data[3])));

            }
        }
        catch (IOException e) {}



    }

    static ArrayList<Pair<String, Integer>> getHighScores() {
        return highscores;
    }


    static boolean authenticate(String user, String pass) {

        for (Player p: players) {
            if (p.userName.equals(user) && p.password.equals(pass)) {
                return true;
            }
        }

        return false;

    }

    public PlayerManager(Context context) {

        this.context = context;

       // File rootDir = context.getFilesDir();
        makeList();


    }

    /**Checks if the specified player exists. This is used to check if usernames are taken
     * when a player tries to register an account.
     * **/
    static boolean checkExists(String user) {
        //  makeList();
        for (Player p : players) {
            if (p.userName.equals(user)) {
                return true;
            }
        }
        return false;
    }

    static void addPlayer(String user, String pass) {
       // players.add(player);

        PrintWriter out = null;

        try {
            OutputStream outStream = context.openFileOutput("players.txt", Context.MODE_APPEND);
            out = new PrintWriter(outStream);

        } catch (FileNotFoundException e) {}

        out.append(user + " " + pass + " SS 0 0 0 0\n"); //write(user + " " + pass + " SS 0");
        out.close();


        String[] info = {user, pass, "SS", "0", "0", "0", "0"};
        players.add(new Player(info));
        highscores.add(new Pair<>(user, 0));

        // TODO: ADD PLAYER DATA FILE TO playerData
    }

    void removePlayer(Player player) {
        players.remove(player);
        // TODO: REMOVE PLAYER DATA FILE FROM playerData
    }

    void addPlayerState(Player player, int state) {
        playerStates.put(player, state);
    }

    void updatePlayerState(Player player, int newState) {
        playerStates.put(player, newState);
    }

    void removePlayerState(Player player) {
        playerStates.remove(player);
    }
}
