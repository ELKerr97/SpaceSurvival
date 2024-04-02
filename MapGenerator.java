public class MapGenerator extends GameConstants{    
    private int[][] map;

    // Easy Map: Player starts out right in front of a weapon, and alien is on far side of the map
    private static final int[][] EASY_MAP = {
        {0,0,0,0,ALIEN,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,WEAPON,0,0,0,0},
        {0,0,0,0,PLAYER,0,0,0,0}
    };

    public MapGenerator(){
        this.map = null;
    }

    public int[][] generateMap(int mapType){
        switch (mapType) {
            case EASY:
                map = EASY_MAP;
                break;
            default:
                map = EASY_MAP;
                break;
        }
        return map;
    }

    public void printMap(){
        for (int i = 0; i < map[0].length; i++){
            for (int j = 0; j < map.length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
