package logic;

import pieces.*;

import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;

public class Board {
    //for coord
    final int A_ASCII = 97;
    final int ZERO_ASCII = 48;
    final int TEN = 10;
    //validLoc def
    final int THIRD = 2;
    final int FOURTH = 3;
    final int STARTING_SIDE_LOC = 64;
    final int KING1 = 4;
    final int KING2 = 3;
    final public static int BOARD_SIZE = 8;

    ArrayList<Piece> whitePieces;
    ArrayList<Piece> coloredPieces;
    boolean isColoredTurn;
    Piece[][] boardPieces;
    char[][] boardData;

    public Board(String startingBoard) {
        //initialize board variables
        this.boardData = new char[BOARD_SIZE][BOARD_SIZE];
        this.whitePieces = new ArrayList<Piece>();
        this.coloredPieces = new ArrayList<Piece>();
        boardPieces = new Piece[BOARD_SIZE][BOARD_SIZE];

        convertStrBoardToList(startingBoard, boardData);
        addPieceToBoards(0, KING1, true, new King(true, 0, KING1));
        addPieceToBoards(BOARD_SIZE - 1, KING2, false, new King( false, BOARD_SIZE - 1, KING2));
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (boardData[i][j] != '#') {
                    boolean currChrIsColored = checkChrIsColored(boardData[i][j]);
                    //checking the type and dynamically allocating memory for the Pieces
                    if (boardData[i][j] == 'r' || boardData[i][j] == 'R') {
                        addPieceToBoards(i, j, currChrIsColored, new Rook(currChrIsColored, i, j));
                    }
                    if (boardData[i][j] == 'p' || boardData[i][j] == 'P') {
                        addPieceToBoards(i, j, currChrIsColored, new Pawn(currChrIsColored, i, j));
                    }
                    if (boardData[i][j] == 'n' || boardData[i][j] == 'N') {
                        addPieceToBoards(i, j, currChrIsColored, new Knight(currChrIsColored, i, j));
                    }
                    if (boardData[i][j] == 'b' || boardData[i][j] == 'B') {
                        addPieceToBoards(i, j, currChrIsColored, new Bishop(currChrIsColored, i, j));
                    }
                    if (boardData[i][j] == 'q' || boardData[i][j] == 'Q') {
                        addPieceToBoards(i, j, currChrIsColored, new Queen(currChrIsColored, i, j));
                    }
                }
            }
        }
        isColoredTurn = false;
    }

    private void addPieceToBoards(int y, int x, boolean isColored, Piece piece) {
        boardPieces[y][x] = piece;
        if (isColored)
        {
            coloredPieces.add(piece);
        }
        else
        {
            whitePieces.add(piece);
        }
    }

    /*
    this function converts a char array from the frontend to a char matrix in the size BOARD_SIZE.
    input: the board string (char array), and the matrix to load the data.
    it does not throw any exceptions and used in the constructor and has no output.
    */
    private void convertStrBoardToList(String strBoard, char charMatrix[][]) {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                charMatrix[i][j] = strBoard.charAt(BOARD_SIZE * i + j);
            }
        }

    }
    private boolean checkChrIsColored(char chrPiece) {
        if (chrPiece >= A_ASCII)
        {
            return true;
        }
        return false;
    }
    int getCoord(char firstCoor, char secCoor) throws Exception {
        int col = Character.getNumericValue(firstCoor);//in the board the 8 row in command the first one in the matrix
        int row = 7 - ((int)secCoor - ZERO_ASCII - 1);
        if (row >= BOARD_SIZE || col >= BOARD_SIZE || row < 0 || col < 0)
        {
            throw new Exception("Out of bounds exception. ");
        }
        return col * 10 + row;
    }

    void friendlyPoint(int y, int x) throws Exception
    {
        //checking if there is a friendly Piece in the location
        char curr = boardData[y][x];
        if (curr == '#' || checkChrIsColored(curr)  != isColoredTurn) {
            throw new Exception("you didnt choose a friendly location");
        }
    }
    void clearOrEnemyPoint(int y, int x) throws Exception
    {
        //checking if there is a friendly Piece in the location, and if so throwing an exception
        if (boardData[y][x] != '#' && checkChrIsColored(boardData[y][x]) == isColoredTurn)
        {
            throw new Exception("destination is not valid");
        }
    }
    boolean checkCheck(boolean isColored, char boardData[][], Piece eaten)
    {
        boolean check = false;
        int kingX, kingY;
        int size = whitePieces.size();
        kingY = coloredPieces.get(0).getY();
        kingX = coloredPieces.get(0).getX();
        if (isColored)
        {
            kingY = whitePieces.get(0).getY();
            kingX = whitePieces.get(0).getX();
            size = coloredPieces.size();
        }
        for (int i = 1; i < size && !check; i++)
        {
            if (isColored)
            {
                if (coloredPieces.get(i) != eaten)
                {
                    check = coloredPieces.get(i).isValidMove(boardData, new Point(kingX, kingY));
                }
            }
            else
            {
                if (whitePieces.get(i) != eaten)
                {
                    check = whitePieces.get(i).isValidMove(boardData, new Point(kingX, kingY));
                }
            }
        }
        return check;
    }

    void validLocBoard(int startY, int startX, int endY, int endX) throws Exception {
        if (startX == endX && startY == endY)
        {
            throw new Exception("Error: same location");
        }
        friendlyPoint(startY, startX);
        clearOrEnemyPoint(endY, endX);
    }
    void cpyCharArray(char newList[][])
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                newList[i][j] = boardData[i][j];
            }
        }
    }

    void moveCharOnBoard(int startY, int startX, int endY, int endX, char boardData[][]) {
        boardData[endY][endX] = boardData[startY][startX];
        boardData[startY][startX] = '#';
    }


    boolean validMoveBoard(int startY, int startX, int endY, int endX) throws Exception
    {
        if (!(boardPieces[startY][startX].isValidMove(boardData, new Point(endX, endY))))
        {
            throw new  Exception("invalid piece move");
        }
        char[][] newBoardData = new char[BOARD_SIZE][BOARD_SIZE];
        Piece eaten = boardPieces[endY][endX];
        boardPieces[startY][startX].setY(endY);
        boardPieces[startY][startX].setX(endX);
        cpyCharArray(newBoardData);
        moveCharOnBoard(startY, startX, endY, endX, newBoardData);
        //check if move doesn't make check on you
        if (checkCheck(!(isColoredTurn), newBoardData, eaten))
        {
            boardPieces[startY][startX].setY(startY);
            boardPieces[startY][startX].setX(startX);
            throw new Exception("you just helped the enemy to do check");
        }
        if (checkCheck(isColoredTurn, newBoardData, eaten))
        {
            boardPieces[startY][startX].setY(startY);
            boardPieces[startY][startX].setX(startX);
            return true;
        }
        return false;
    }

    void movePiece(int startY, int startX, int endY, int endX)
    {
        moveCharOnBoard(startY, startX, endY, endX, boardData);
        if (boardPieces[endY][endX] != null)
        {
            if (isColoredTurn)
            {
                whitePieces.remove(boardPieces[endY][endX]);
            }
            else
            {
                coloredPieces.remove(boardPieces[endY][endX]);

            }
            boardPieces[endY][endX] = null;
        }
        boardPieces[endY][endX] = boardPieces[startY][startX];
        boardPieces[startY][startX] = null;
        boardPieces[endY][endX].setY(endY);
        boardPieces[endY][endX].setX(endX);
    }

    public char[][] manageMove(Point start, Point end) {
        boolean isCheck = false;
        try {
            validLocBoard(start.y, start.x, end.y, end.x);
            isCheck = validMoveBoard(start.y, start.x, end.y, end.x);
            movePiece(start.y, start.x, end.y, end.x);
        } catch (Exception  ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
        if(isCheck)
            System.out.println("check");
        isColoredTurn = !isColoredTurn;
        return  boardData;
    }
}