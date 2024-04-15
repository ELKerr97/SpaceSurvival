public class MapGenerator extends GameConstants{    
    private int[][] map;

    // Easy Map: Player starts out right in front of a weapon, and alien is on far side of the map
    private static final int[][] MAP_90 = {
        {0,0,0,0,PORT,OBST,0,0,ALIN},
        {0,0,0,0,0,0,0,OBST,0},
        {0,0,0,0,0,0,0,OBST,0},
        {0,0,0,0,0,0,0,OBST,0},
        {0,0,0,0,0,0,0,OBST,0},
        {0,0,0,0,OBST,OBST,0,OBST,0},
        {0,0,0,0,OBST,OBST,0,OBST,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,PLYR,0,0,0,0}
    };

    private static final int[][] MAP_91 = {
        {0,0,0,0,PORT,OBST,0,0,0},
        {0,OBST,0,0,0,0,0,OBST,0},
        {0,OBST,OBST,OBST,OBST,OBST,OBST,OBST,0},
        {0,ALIN,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,OBST,0,0,0},
        {0,0,0,0,0,OBST,0,0,0},
        {0,OBST,OBST,OBST,OBST,OBST,OBST,0,0},
        {0,0,0,0,PLYR,0,0,0,0}
    };

    private static final int[][] MAP_92 = {
        {0,0,0,ALIN,PORT,OBST,0,0,0},
        {0,OBST,0,0,0,0,0,OBST,OBST},
        {0,OBST,OBST,OBST,OBST,OBST,OBST,OBST,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,OBST,0,0,0},
        {0,0,0,0,0,OBST,0,0,0},
        {0,0,OBST,OBST,OBST,OBST,OBST,0,0},
        {PLYR,0,0,0,0,0,0,0,0}
    };

    private static final int[][] MAP_93 = {
        {0,0,0,ALIN,PORT,OBST,0,0,0},
        {0,OBST,0,0,0,0,0,OBST,OBST},
        {0,OBST,OBST,OBST,OBST,OBST,OBST,OBST,0},
        {0,0,0,0,0,0,0,0,0},
        {0,OBST,0,0,0,0,0,0,0},
        {0,OBST,0,0,0,OBST,0,0,0},
        {0,OBST,0,0,0,OBST,0,0,0},
        {0,OBST,OBST,OBST,OBST,OBST,OBST,0,0},
        {PLYR,0,0,0,0,0,0,0,0}
    };

    private static final int[][] MAP_100 = {
        {EMTY, EMTY, EMTY, EMTY, OBST, OBST, OBST, OBST, EMTY, EMTY, OBST, PORT},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, OBST, EMTY, EMTY, ALIN},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY, OBST, OBST},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, EMTY, OBST, OBST, OBST, OBST, OBST, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY},
        {OBST, OBST, OBST, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY},
        {EMTY, OBST, EMTY, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {EMTY, OBST, EMTY, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {PLYR, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY}
    };

    private static final int[][] MAP_101 = {
        {EMTY, EMTY, EMTY, EMTY, OBST, OBST, OBST, OBST, EMTY, EMTY, OBST, PORT},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, OBST, EMTY, EMTY, ALIN},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, EMTY, OBST, OBST, OBST, OBST, OBST, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY},
        {OBST, OBST, OBST, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {EMTY, EMTY, EMTY, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY},
        {EMTY, OBST, EMTY, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {EMTY, OBST, EMTY, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY},
        {PLYR, OBST, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, EMTY, OBST, EMTY}
    };

    public MapGenerator(){
        this.map = null;
    }

    public int[][] generateMap(int mapType){
        switch (mapType) {
            case 90:
                map = MAP_90;
                break;
            case 91:
                map = MAP_91;
                break;
            case 92:
                map = MAP_92;
                break;
            case 93:
                map = MAP_93;
                break;
            case 100:
                map = MAP_100;
                break;
            case 101:
                map = MAP_101;
                break;
            default:
                map = MAP_90;
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
