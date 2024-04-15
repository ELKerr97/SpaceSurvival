import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends GameConstants{

    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("Usage: java Main [Game Number 90-91] [Speed 1-5]");
            return;
        }

        int mapType;
        try {
            mapType = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Input is not a valid number.");
            return;
        }

        int speedSelection;
        try {
            speedSelection = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Input is not a valid number.");
            return;
        }
        
        MapGenerator mapGenerator = new MapGenerator();

        // Get map from map generator
        int[][] map = mapGenerator.generateMap(mapType);
        if (map == null){
            System.out.println("Failed to get map.");
            return;
        }
        
        mapGenerator.printMap();
        System.out.println();
        System.out.println();

        Game game = new Game(map, 1, 1, speedSelection);

        game.playGame();

        return;
    }

}
