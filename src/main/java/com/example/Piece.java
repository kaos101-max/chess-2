package com.example;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;

    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;

        try {
            if (this.img == null) {
                this.img = ImageIO.read(new File(System.getProperty("user.dir") + img_file));
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public boolean getColor() {
        return color;
    }

    public Image getImage() {
        return img;
    }

    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();

        g.drawImage(this.img, x, y, null);
    }

    // TO BE IMPLEMENTED!
    // return a list of every square that is "controlled" by this piece. A square is
    // controlled
    // if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        return null;
    }

    // TO BE IMPLEMENTED!
    // implement the move function here
    // it's up to you how the piece moves, but at the very least the rules should be
    // logical and it should never move off the board!
    // returns an arraylist of squares which are legal to move to
    // please note that your piece must have some sort of logic. Just being able to
    // move to every square on the board is not
    // going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start) {
        ArrayList<Square> moves = new ArrayList<>();

        // the board in which you can move
        Square[][] board = b.getSquareArray();

        // each move is up (or down if we're black) one row and either left or right

        int startX = start.getCol();
        int startY = start.getRow();
        int leftRight = 1;
        //The custom chess piece has its uniqueness and limitations. 
        // It moves stiraght and goes right and left like a zigzag, and it can only be moved on the two middle rows of the board.
        if (color) {
            for (int i = startY - 1; i >= 0; i--) {
                startX += leftRight;
                if(startX >= 8){
                    startX-=8;
                }
                if(startX < 0){
                    startX+=8;
                }
                
                if (!board[i][startX].isOccupied() || board[i][startX].getOccupyingPiece().getColor() != color) {
                    moves.add(board[i][startX]);
                } else {
                    break;
                }
                if (leftRight == 1){
                    leftRight = -1;
                }
                else{
                    leftRight = 1;
                }
            }
            if(start.getRow() == 0){
                for(int c =0; c <=7; c++){
                    if(!board[7][c].isOccupied() || board[7][c].getOccupyingPiece().getColor() != color){
                        moves.add(board[7][c]);
                    }
                }
            }

        }
      else{
        //black pieces (move down)
         for (int i = startY + 1; i < 8; i++) {
                startX += leftRight;
                if(startX >= 8){
                    startX-=8;
                }
                if(startX < 0){
                    startX+=8;
                }
                
                if (!board[i][startX].isOccupied() || board[i][startX].getOccupyingPiece().getColor() != color) {
                    moves.add(board[i][startX]);
                } else {
                    break;
                }
                if (leftRight == 1){
                    leftRight = -1;
                }
                else{
                    leftRight = 1;
                }
            }
            if(start.getRow() == 7){
                for(int c =0; c <=7; c++)){
                    if(!board[7][c].isOccupied() || board[7][c].getOccupyingPiece().getColor() != color){
                        moves.add(board[7][c]);
                    }
                }
            }
      }
        return moves;
    }
}