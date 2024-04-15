public class GameConstants {
    // Game difficulties
    protected final static int EASY = 98;
    protected final static int MEDIUM = 99;
    protected final static int HARD = 100;

    // "Pieces" on board
    protected final static int EMTY = 0;
    protected final static int PLYR = 1;
    protected final static int ALIN = 2;
    protected final static int PORT = 3;
    protected final static int OBST = 10;

    // Possible movements for alien and human
    protected final static int MOVE_UP = 4;
    protected final static int MOVE_DOWN = 5;
    protected final static int MOVE_LEFT = 6;
    protected final static int MOVE_RIGHT = 7;
    protected final static int STAY = 8;

    // Possible human action to shoot alien if wielding a gun
    protected final static int SHOOT_ALIEN = 9;
}
