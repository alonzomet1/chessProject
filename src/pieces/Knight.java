package pieces;

import java.awt.*;

public class Knight extends Piece{
    final int KNIGHT_MOVE_CONSTANT = 5;
    public Knight(boolean isColored, int y, int x)
    {
        super(isColored, y, x);
    }
    @Override
    public boolean isValidMove(char[][] board, Point end) {
        //checking if the knight moves two steps to on diraction, and one step to the other direction
        int deltaX = currIndex.x - end.x;
        int deltaY = currIndex.y - end.y;
        //if deltaY and deltaX are valid one must be 1 or -1 and the other 2 or -2. if this is correct then sum of squeres is 5 and the diffferrnce between deltas is 1
        return(deltaX*deltaX + deltaY * deltaY == KNIGHT_MOVE_CONSTANT && (deltaX*deltaX == 1 || deltaY * deltaY == 1));

    }
}
