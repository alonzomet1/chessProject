package pieces;

import java.awt.*;



public class Pawn extends Piece {

    public Pawn(boolean isColored, int y, int x)
    {
        super(isColored, y, x);
    }

    @Override
    public boolean isValidMove(char[][] board, Point end) {
        //if the colored pawn wants to move forward
        if (currIndex.y - end.y == -1 &&  isColored)
        {
            //moving straight
            if (currIndex.x == end.x && board[end.y][end.x] == NO_PLAYER)
            {
                return(true);
            }
            //if the change in x is only one (moving diagnolly to eat)
            if (Math.pow(currIndex.x - end.x, 2) == 1 && board[end.y][end.x] != NO_PLAYER)
            {
                return(true);
            }
        }
        //if the white player wnats to move forward
        else if (currIndex.y - end.y == 1 &&  !isColored)
        {
            //moving straight
            if (currIndex.x == end.x && board[end.y][end.x] == NO_PLAYER)
            {
                return(true);
            }
            //if the change in x is only one (moving diagnolly to eat)
            if (Math.pow(currIndex.x - end.x, 2) == 1 && board[end.y][end.x] != NO_PLAYER)
            {
                return(true);
            }
        }//checking if it's the first line so the player can move two steps
        else if ((currIndex.y == STARTING_LINE_COLORED && isColored) || (currIndex.y == STARTING_LINE_WHITE && !isColored) && currIndex.x == end.x)
        {
            //checking if ti's two steps in the right direction
            if ((currIndex.y - end.y == -2 && isColored) || (currIndex.y - end.y == 2 && !isColored))
            {
                //checking if there is a piece between the ending and starting point
                if (board[(currIndex.y + end.y) / 2][end.x] == NO_PLAYER)
                {
                    return(true);
                }
            }
        }
        return false;
    }
}
