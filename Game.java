public class Game extends GameConstants{
    private int[][] map;

    public Game(int[][] map) {
        this.map = map;
    }

    public void PlayGame() {
        boolean gameEnd = false;
        while (!gameEnd){
            int[] playerPosition = getPLayerPosition();
            System.out.println("Player position = " + playerPosition[0] + "," + playerPosition[1]);
        }

        return;
    }

    private int[] getPLayerPosition(){
        int[] position = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == PLAYER) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        
        System.out.println("Player not found in map.");
        return null;
    }
}
