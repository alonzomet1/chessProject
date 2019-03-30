package pieces;

import java.awt.*;

public class King extends Piece{
    public King(boolean isColored, int y, int x)
    {
        super(isColored, y, x);
    }

    @Override
    public boolean isValidMove(char[][] board, Point end) {
        int dX = currIndex.x - end.x, dY = currIndex.y - end.y;
        //the king can only move 1 or zero in any diraction, so the sum of the squeres for a valid move must be 0 or 1.
        if (dX * dX <= 1 && dY * dY <= 1)
        {
            return true;
        }
        return false;
    }
}
