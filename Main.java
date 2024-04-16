import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends GameConstants{

    public static void main(String[] args){
        if (args.length < 4){
            System.out.println("Usage: java Main [Game Number 90-91] [Speed 1-5] [Alien Detection Accuracy 0.0-1.0] [Player Risk Factor 0.0-1.0]");
            return;
        }

        int mapType;
        try {
            mapType = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Map type input is not a valid int.");
            return;
        }

        int speedSelection;
        try {
            speedSelection = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Speed selection input is not a valid int.");
            return;
        }

        double alienDetectionAccuracy;
        try {
            alienDetectionAccuracy = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Detection accuracy input is not a valid double.");
            return;
        }

        double playerRiskFactor;
        try {
            playerRiskFactor = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            System.out.println("Risk factor input is not a valid double.");
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

        Game game = new Game(map, 1, 1, speedSelection, alienDetectionAccuracy, playerRiskFactor);

        game.playGame();

        System.out.println("Thanks for playing!");
        System.exit(0);;
    }

}
