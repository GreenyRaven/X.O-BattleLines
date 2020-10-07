package Model;

import ModelView.ViewUpdater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class tictactoe {
    private int[][] board;
    private int player;
    private int row;
    private int col;

    private int num_row;
    private int num_col;
    private int diag_princ;
    private int diag_sec;
    private int[] won;
    private String img;

    public tictactoe() {
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

    // добавить идентификатор что играет AI
    public void test(int index, String gamestep, ViewUpdater UIUpdater) {// boolean ai
        tictactoe game = new tictactoe();
        game.Turn(index, gamestep);//в переменные: игрока и место шага
//        game.Make_a_move(this.board, this.player, this.row, this.col);
        game.Place(this.board, this.player, this.row, this.col);
        if (game.Win_check(this.board, this.player)){
            game.Won_place();
            UIUpdater.endThisGame(this.won, this.img);
        }else{
            //draw
        }
        UIUpdater.toNextStep();

    }

    // decode turn
    private void Turn(int index, String figure) {
        if (figure.equals("Крестики")) {
            this.player = 1;
        } else {
            this.player = 2;
        }

        switch (index) {
            case 0 -> {
                this.row = 0;
                this.col = 0;
            }
            case 1 -> {
                this.row = 0;
                this.col = 1;
            }
            case 2 -> {
                this.row = 0;
                this.col = 2;
            }
            case 3 -> {
                this.row = 1;
                this.col = 0;
            }
            case 4 -> {
                this.row = 1;
                this.col = 1;
            }
            case 5 -> {
                this.row = 1;
                this.col = 2;
            }
            case 6 -> {
                this.row = 2;
                this.col = 0;
            }
            case 7 -> {
                this.row = 2;
                this.col = 1;
            }
            case 8 -> {
                this.row = 2;
                this.col = 2;
            }
        }
        System.out.println("Row: " + row + " Col: " + col);
    }

    // encode won_place
    private void Won_place() {
        switch (this.num_row) {
            case 0 -> {
                this.won= new int[]{0, 1, 2};
                this.img = "";
            }
            case 1 -> {
                this.won = new int[]{3, 4, 5};
                this.img = "";
            }
            case 2 -> {
                this.won = new int[]{6, 7, 8};
                this.img = "";
            }
        }
        switch (this.num_col) {
            case 0 -> {
                this.won = new int[]{0, 3, 6};
                this.img = "";
            }
            case 1 -> {
                this.won = new int[]{1, 4, 7};
                this.img = "";
            }
            case 2 -> {
                this.won = new int[]{2, 5, 8};
                this.img = "";
            }
        }
        if (this.diag_princ == 1){
            this.won = new int[]{0, 4, 8};
            this.img = "";
        }
        if (this.diag_sec == 1){
            this.won = new int[]{2, 4, 6};
            this.img = "";
        }
    }

    //3x3
    private int[][] Create_board() {
        return new int[3][3];
    }

    private void Place(int[][] board, int player, int row, int col) {
        board[row][col] = player;// this.board ?
    }

    private void Place_AI(int[][] board, int player){

    }

    private ArrayList<int[]> Possibilities(int[][] board) {
        ArrayList<int[]> possibilities = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    possibilities.add(new int[]{i, j});
                }
            }
        }
        return possibilities;
    }

    private void Random_placement(int[][] board, int player) {
        ArrayList<int[]> possibilities = Possibilities(board);
        if (!possibilities.isEmpty()) {
            Random random = new Random();
            //random.setSeed(2);// delete seed

            int[] position = possibilities.get(random.nextInt(possibilities.size()));//[1,2]
            Place(board, player, position[0], position[1]);
        }
    }

    private boolean Win_check(int[][] board, int player) {
        // row
        int num_row = 0;
        for (int[] row : board) {
            int count = 0;
            for (int value : row) {
                if (value == player) {
                    count++;
                    if (count == 3) {
                        this.num_row = num_row;
                        return true;
                    }
                }
            }
            num_row++;
        }
        // column
        for (int numcol = 0; numcol < board[0].length; numcol++) {
            int count = 0;
            for (int[] row : board) {
                int value = row[numcol];
                if (value == player) {
                    count++;
                    if (count == 3) {
                        this.num_col = numcol;
                        return true;
                    }
                }
            }
        }
        // diagonal
        int n = board.length;
        int countprinc = 0;
        int countsecnd = 0;
        for (int numrow = 0; numrow < board.length; numrow++) {
            for (int numcol = 0; numcol < board[0].length; numcol++) {
                // principal
                if (numrow == numcol) {
                    int[] row = board[numrow];
                    int value = row[numcol];
                    if (value == player) {
                        countprinc++;
                        if (countprinc == 3) {
                            this.diag_princ = 1;
                            return true;
                        }
                    }
                }
            }
            for (int numcol = 0; numcol < board[0].length; numcol++) {
                // secondary
                if (numcol == (n - 1)) {
                    int[] row = board[numrow];
                    int value = row[numcol];
                    if (value == player) {
                        countsecnd++;
                        if (countsecnd == 3) {
                            this.diag_sec = 1;
                            return true;
                        }
                    }
                }
            }
            n--;
        }
        return false;
    }

//    private void Make_a_move(int[][] board, int player, int row, int col) {
////        if (ai == True){
////
////        }
//        Place(board, player, row, col);
//        System.out.println("Ход:");
//        System.out.println(Arrays.deepToString(board));
//        if (Win_check(board, player)) {
//            System.out.println("Player " + player + " won!");
//        } else if (Possibilities(board).isEmpty()) {
//            System.out.println("No one won!");
//        }
//    }
}
