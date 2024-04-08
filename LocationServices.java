import java.util.ArrayList;

public class LocationServices extends GameConstants {
    private boolean describePositions = false;

    protected int[] getPLayerPosition(int[][] map){
        int[] position = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == PLAYER) {
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
                if (map[i][j] == PORTAL) {
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

    protected ArrayList<int[]> getAlienPositions(int[][] map){
        ArrayList<int[]> positions = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == ALIEN) {
                    // add location to positions array
                    positions.add(new int[] {i,j});
                }
            }
        }
        
        if (describePositions) {
            System.out.println(positions.size() + " alien(s) found on map.");
            System.out.println("Alien Positions:");
            for (int[] pos : positions) {
                System.out.println("(" + pos[0] + "," + pos[1] + ")");
            }
        }
        return positions;
    }

    protected void printMap(int[][] map){
        for (int i = 0; i < map[0].length; i++){
            for (int j = 0; j < map.length; j++){
                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
            System.out.println();
        }
    }
}
