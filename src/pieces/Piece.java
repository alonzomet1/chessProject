package pieces;

import java.awt.*;

public abstract class Piece {
    char NO_PLAYER = '#';
    int STARTING_LINE_COLORED = 1;
    int STARTING_LINE_WHITE =  6;
    final boolean isColored;
    protected Point currIndex;
    public Piece(boolean isColored, int y, int x)
    {
        currIndex = new Point(x, y);
        this.isColored = isColored;
    }
    public void setX(int x)
    {
        currIndex.x = x;
    }
    public int getY()
    {
        return currIndex.y;
    }
    public int getX()
    {
        return currIndex.x;
    }
    public void setY(int y)
    {
        currIndex.y = y;
    }
    abstract public boolean isValidMove(char[][] board, Point end);
}
