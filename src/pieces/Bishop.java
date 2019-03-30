package pieces;

import java.awt.*;

public class Bishop extends Piece {
    public Bishop(boolean isColored, int y, int x)
    {
        super(isColored, y, x);
    }
    @Override
    public boolean isValidMove(char[][] board, Point end) {
        int i = end.y - currIndex.y;
        int j = end.x - currIndex.x;
        //check if the coord can be moved with bishop
        if (i * i != j * j)
        {
            return false;
        }
        //getting the steps required to move
        while (i * i != 1)
        {
            //adding 1 or subtracting 1
            i = (i > 0) ? i-- : i++;
            j = (j > 0) ? j-- : j++;
            //checking if the current location in the way is empty. if it's blocking the way the bishop can't get there.
            if (board[i + currIndex.y][j + currIndex.x] != '#')
            {
                return false;
            }
        }
        return true;
    }
}
