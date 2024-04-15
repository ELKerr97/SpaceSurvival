import java.util.ArrayList;

public class LocationServices extends GameConstants {
    private boolean describePositions = false;

    protected int[] getPLayerPosition(int[][] map){
        int[] position = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == PLYR) {
                    position[0] = i;
                    position[1] = j;
                    if (describePositions) {System.out.println("Player at position (" + position[0] + "," + position[1] + ")");}
                    return position;
                }
            }
        }
        
        System.out.println("Player not found in map.");
        return null;
    }

    protected int[] getPortalPosition(int[][] map){
        int[] position = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == PORT) {
                    position[0] = i;
                    position[1] = j;
                    if (describePositions) {System.out.println("Portal at position (" + position[0] + "," + position[1] + ")");}
                    return position;
                }
            }
        }
        
        System.out.println("Portal not found in map.");
        return null;
    }

    protected int[] getAlienPositions(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == ALIN) {
                    // add location to positions array
                    return new int[]{i,j};
                }
            }
        }
        System.out.println("Alien position not found.");
        return null;
    }

    // Print method for int[][] arrays
    protected void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }
    }

    // Print method for double[][] arrays
    protected void printMap(double[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%8.2f", map[i][j]); // Adjust format for double values
            }
            System.out.println();
        }
    }
}
