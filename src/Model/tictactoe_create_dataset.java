package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;



public class tictactoe_create_dataset {
//    private static int[][] board;
//    private int[] move;

//    public tictactoe_test(){
//        board = Create_board();
//        System.out.println("Поле:");
//        System.out.println(Arrays.deepToString(board));
//        System.out.println("----------");
//    }

    public static void main(String[] args) throws IOException {

        // create dataset
        Attribute p0 = new Attribute("p0");
        Attribute p1 = new Attribute("p1");
        Attribute p2 = new Attribute("p2");
        Attribute p3 = new Attribute("p3");
        Attribute p4 = new Attribute("p4");
        Attribute p5 = new Attribute("p5");
        Attribute p6 = new Attribute("p6");
        Attribute p7 = new Attribute("p7");
        Attribute p8 = new Attribute("p8");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("0");
        labels.add("1");
        labels.add("2");

        Attribute cls = new Attribute("class", labels);
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(p0);
        attributes.add(p1);
        attributes.add(p2);
        attributes.add(p3);
        attributes.add(p4);
        attributes.add(p5);
        attributes.add(p6);
        attributes.add(p7);
        attributes.add(p8);
        attributes.add(cls);
        Instances dataset = new Instances("tictactoe-dataset", attributes, 0);

        System.out.println("start");

        //tictactoe Game = new tictactoe();

        int numgames = 10;
        int[] players = {1,2,1,2,1,2,1,2,1};

        for (int game = 1; game <= numgames; game++){
            int turn = 1;
            double[] values = new double[dataset.numAttributes()];
            System.out.println("Game: " + game);
            int[][] board = Create_board();
//            System.out.println("Поле:");
//            System.out.println(Arrays.deepToString(board));
//            System.out.println("----------");
            for (int player : players ){
                System.out.println("Turn: " + turn++);

                //Place(board,2, 2, 2);
                //Place(board,1, 0, 1);
                //Place(board,2, 0, 2);

                //demo
//                ArrayList<int[]> possibilities = Possibilities(board);
//                System.out.println("@@@@@@@@@@@");
//                System.out.println("Возможные ходы:");
//                System.out.println(Arrays.deepToString(possibilities.toArray()));
//                System.out.println("@@@@@@@@@@@");
                //demo

                Random_placement(board, player);
//                System.out.println("Случайный ход:");//убрать сид!
//                System.out.println(Arrays.deepToString(board));
//                System.out.println("----------");

                //System.out.println(board);

                ArrayList<Integer> win_results = new ArrayList();
                int i = 0;

                if (Win_check(board,player)){
                    System.out.println("Player " + player + " won!");
                    //arrlist
                    //i
                    for (int[] row : board){
                        for (int value : row){
                            win_results.add(value);
                            values[i] = value;
                            i++;
                            //System.out.println(win_results);
                        }
                    }
                    win_results.add(player);
                    values[9] = player;
                    System.out.println("Board state (last is the winner): " + win_results);
                    break;
                }
                else if (Possibilities(board).isEmpty()){
                    System.out.println("No one won!");
                    for (int[] row : board){
                        for (int value : row){
                            win_results.add(value);
                            values[i] = value;
                            i++;
                            //System.out.println(win_results);
                        }
                    }
                    win_results.add(0);
                    values[9] = 0;
                    System.out.println("Board state (noone won): " + win_results);
                    break;
                }
            }
            Instance inst = new DenseInstance(1.0,values);
            dataset.add(inst);
        }

//        move = Make_a_move(board,player, row, col);
//        // test
//        Game.make_a_move();
//        // test


        //Storing in arff
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("out.arff"));
        writer.write(dataset.toString());
        writer.newLine();
        writer.flush();
        writer.close();

        System.out.println("end");
    }

    // test
//    private void make_a_move(){
//        move = Make_a_move(board,1,0,0);
//        System.out.println("Row: " + move[0]+ " Col: " + move[1]);
//    }
    // test

//    public String test(){
//        String greeting = "IT'S ME, TEST!";
//        return greeting;
//    }

//    private void Turn(int index, String figure){
//        int player = 0;
//        if (figure == "Крестики"){
//            player = 1;
//        }else{
//            player = 2;
//        }
//
//        int row = 0;
//        int col = 0;
//        switch (index){
//            case 0:
//                row=0;
//                col=0;
//                break;
//            case 1:
//                row=0;
//                col=1;
//                break;
//            case 2:
//                row=0;
//                col=2;
//                break;
//            case 3:
//                row=1;
//                col=0;
//                break;
//            case 4:
//                row=1;
//                col=1;
//                break;
//            case 5:
//                row=1;
//                col=2;
//                break;
//            case 6:
//                row=2;
//                col=0;
//                break;
//            case 7:
//                row=2;
//                col=1;
//                break;
//            case 8:
//                row=2;
//                col=2;
//                break;
//        }
//        System.out.println("Row: " + row+ " Col: " + col);
//    }

    //3x3
    private static int[][] Create_board(){
        return new int[3][3];
    }

    private static void Place(int[][] board, int player, int row, int col){
        board[row][col] = player;
    }

    private static ArrayList<int[]> Possibilities(int[][] board){
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

    private static void Random_placement(int[][] board, int player){
        ArrayList<int[]> possibilities = Possibilities(board);
        if (!possibilities.isEmpty()){
            Random random = new Random();
            //random.setSeed(2);// delete seed

            int[] position = possibilities.get(random.nextInt(possibilities.size()));//[1,2]
            Place(board,player,position[0],position[1]);
        }
    }

    private static boolean Win_check(int[][] board, int player){
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

    private static void Make_a_move(int[][] board, int player, int row, int col){
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
    }
}
