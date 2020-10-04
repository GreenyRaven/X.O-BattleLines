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

        int numgames = 10;
        int[] players = {1,2,1,2,1,2,1,2,1};

        for (int game = 1; game <= numgames; game++){
            int turn = 1;
            double[] values = new double[dataset.numAttributes()];
            System.out.println("Game: " + game);
            int[][] board = Create_board();
            for (int player : players ){
                System.out.println("Turn: " + turn++);

                Random_placement(board, player);

                ArrayList<Integer> win_results = new ArrayList();
                int i = 0;

                if (Win_check(board,player)){
                    System.out.println("Player " + player + " won!");
                    for (int[] row : board){
                        for (int value : row){
                            win_results.add(value);
                            values[i] = value;
                            i++;
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

        //Storing in arff
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("out.arff"));
        writer.write(dataset.toString());
        writer.newLine();
        writer.flush();
        writer.close();

    }

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

}
