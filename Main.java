import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("Usage: java Main <filename>");
            return;
        }
        
        // Get filename from command line
        String filename = args[0];


        // Example map
        int[][] map = loadMatrixFromFile(filename);
        if (map == null){
            System.out.println("Fialed to load grid from file: " + filename);
            return;
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grid Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            GridDisplay gridDisplay = new GridDisplay(map);
            frame.add(gridDisplay);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private static int[][] loadMatrixFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int rows = 0;
            int cols = 0;
            while ((line = reader.readLine()) != null) {
                cols = Math.max(cols, line.length());
                rows++;
            }
    
            int[][] matrix = new int[rows][cols];
    
            // Reset reader to read from the beginning of the file
            reader.close();
            BufferedReader newReader = new BufferedReader(new FileReader(filename));
    
            int row = 0;
            while ((line = newReader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char ch = line.charAt(col);
                    matrix[row][col] = Character.getNumericValue(ch);
                }
                row++;
            }
            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
