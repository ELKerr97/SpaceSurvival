import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;
    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("Usage: java Main <filename>");
            return;
        }

        try {
            int mapSelection = Integer.parseInt(args[0]);

            if (mapSelection == EASY){
                System.out.println("Easy Map");
            } else if (mapSelection == MEDIUM){
                System.out.println("Medium Map");
            } else if (mapSelection == HARD){
                System.out.println("Hard Map");
            } else {
                System.out.println("No such map selection. Please try again with 1 (easy), 2 (medium), or 3 (hard)");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Input is not a valid number.");
            return;
        }
        
        // Get filename from command line
        int mapType = Integer.parseInt(args[0]);
        
        MapGenerator mapGenerator = new MapGenerator();

        // Get map from map generator
        int[][] map = mapGenerator.generateMap(mapType);
        if (map == null){
            System.out.println("Failed to get map.");
            return;
        }
        
        mapGenerator.printMap();

        // Display map on GUI
        // SwingUtilities.invokeLater(() -> {
        //     JFrame frame = new JFrame("Grid Display");
        //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //     GridDisplay gridDisplay = new GridDisplay(map);
        //     frame.add(gridDisplay);
        //     frame.pack();
        //     frame.setVisible(true);
        // });
    }

}
