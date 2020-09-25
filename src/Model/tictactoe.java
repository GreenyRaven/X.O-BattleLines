package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class tictactoe {
    private int[] move;
    private int[][] board;

    public tictactoe(){
        board = Create_board();
        System.out.println("Поле:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");
    }

//    public static void main(String[] args) {
//        System.out.println("start");
//
//        tictactoe Game = new tictactoe();
////        //demo
////        ArrayList<int[]> possibilities = Possibilities(board);
////        System.out.println("@@@@@@@@@@@");
////        System.out.println("Возможные ходы:");
////        System.out.println(Arrays.deepToString(possibilities.toArray()));
////        System.out.println("@@@@@@@@@@@");
////        //demo
////
////        Random_placement(board, player);
////        System.out.println("Случайный ход:");//убрать сид!
////        System.out.println(Arrays.deepToString(board));
////        System.out.println("----------");
////        move = Make_a_move(board,player, row, col);
//        // test
//        Game.make_a_move();
//        // test
//
//        System.out.println("end");
//    }

//    // test
//    private void make_a_move(){
//        move = Make_a_move(board,1,0,0);
//        System.out.println("Row: " + move[0]+ " Col: " + move[1]);
//    }
//    // test

    public String test(){
        String greeting = "IT'S ME, TEST!";
        return greeting;
    }

    private void Turn(int index, String figure){
        int player = 0;
        if (figure == "Крестики"){
            player = 1;
        }else{
            player = 2;
        }

        int row = 0;
        int col = 0;
        switch (index){
            case 0:
                row=0;
                col=0;
                break;
            case 1:
                row=0;
                col=1;
                break;
            case 2:
                row=0;
                col=2;
                break;
            case 3:
                row=1;
                col=0;
                break;
            case 4:
                row=1;
                col=1;
                break;
            case 5:
                row=1;
                col=2;
                break;
            case 6:
                row=2;
                col=0;
                break;
            case 7:
                row=2;
                col=1;
                break;
            case 8:
                row=2;
                col=2;
                break;
        }
        System.out.println("Row: " + move[0]+ " Col: " + move[1]);
    }

    //3x3
    private int[][] Create_board(){
        return new int[3][3];
    }

    private void Place(int[][] board, int player, int row, int col){
        board[row][col] = player;
    }

    private ArrayList<int[]> Possibilities(int[][] board){
        ArrayList<int[]> possibilities = new ArrayList<>();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 0){
                    possibilities.add(new int[]{i,j});
                }
            }
        }
        return possibilities;
    }

    private void Random_placement(int[][] board, int player){
        ArrayList<int[]> possibilities = Possibilities(board);
        if (!possibilities.isEmpty()){
            Random random = new Random();
            random.setSeed(2);// delete seed

            int[] position = possibilities.get(random.nextInt(possibilities.size()));//[1,2]
            Place(board,player,position[0],position[1]);
        }
    }

    private boolean Win_check(int[][] board, int player){
        // row
        for (int[] row : board) {
            int count = 0;
            for (int value : row) {
                if (value == player) {
                    count++;
                    if (count == 3) {
   
                        return true;
                    }
                }
            }
        }
        // column
        for(int numcol = 0; numcol < board[0].length; numcol++) {
            int count = 0;
            for (int[] row : board) {
                int value = row[numcol];
                if (value == player) {
                    count++;
                    if (count == 3) {
                        return true;
                    }
                }
            }
        }
        // diagonal
        int n = board.length;
        int countprinc = 0;
        int countsecnd = 0;
        for(int numrow = 0; numrow < board.length; numrow++) {
            for(int numcol = 0; numcol < board[0].length; numcol++){
                // principal
                if (numrow == numcol){
                    int[] row = board[numrow];
                    int value = row[numcol];
                    if (value == player){
                        countprinc++;
                        if (countprinc == 3){
                            return true;
                        }
                    }
                }
            }
            for(int numcol = 0; numcol < board[0].length; numcol++){
                // secondary
                if (numcol == (n-1)){
                    int[] row = board[numrow];
                    int value = row[numcol];
                    if (value == player){
                        countsecnd++;
                        if (countsecnd == 3){
                            return true;
                        }
                    }
                }
            }
            n --;
        }
        return false;
    }

    private int[] Make_a_move(int[][] board, int player, int row, int col){
        //int turn = 0;
        //System.out.println("Номер хода: " + turn);
        Place(board, player, row, col);
        System.out.println("Ход:");
        System.out.println(Arrays.deepToString(board));
        //turn++;
        if (Win_check(board,player)){
            System.out.println("Player " + player + " won!");
        }
        else if (Possibilities(board).isEmpty()){
            System.out.println("No one won!");
        }
        int [] move = {row, col};
        return move;
    }
}
