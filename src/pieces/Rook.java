package pieces;

import java.awt.*;

public class Rook extends Piece {
    public Rook(boolean isColored, int y, int x)
    {
        super(isColored, y, x);
    }

    @Override
    public boolean isValidMove(char[][] board, Point end) {
        //checking if x or y remain constant
        if (end.x != currIndex.x && end.y != currIndex.y)
        {
            return false;
        }
        //getting the steps required to move
        int i = end.y - currIndex.y;
        int j = end.x - currIndex.x;
        while (i * i != 1 && i != 0)
        {
            //adding 1 or subtracting 1
            j = (i > 0) ? i-- : i++;
            //checking if the current location in the way is empty. if it's blocking the way the rook can't get there.
            if (board[i + currIndex.y][currIndex.x] != NO_PLAYER)
            {
                return false;
            }
        }
        while (j * j != 1 && j != 0)
        {
            //adding 1 or subtracting 1s
            j = (j > 0) ? j-- : j++;
            //checking if the current location in the way is empty. if it's blocking the way the rook can't get there.
            if (board[currIndex.y][currIndex.x + j] != NO_PLAYER)
            {
                return false;
            }
        }
        return true;
    }

}
