package pieces;

import java.awt.*;

public class Queen extends Piece {
    public Queen(boolean isColored, int y, int x)
    {
        super(isColored, y, x);
    }
    @Override
    public boolean isValidMove(char[][] board, Point end) {
        Bishop b = new Bishop(isColored, currIndex.y, currIndex.x);
        Rook r = new Rook(isColored, currIndex.y, currIndex.x);
        Point p = new Point(end.y, end.x);
        if (r.isValidMove(board, p) || b.isValidMove(board, p))
        {
            return true;
        }
        return false;

    }
}
