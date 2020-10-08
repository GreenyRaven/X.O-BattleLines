package Model;

import ModelView.ViewUpdater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.core.*;

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

    public tictactoe(ViewUpdater UIUpdater) {
        board = Create_board();
        System.out.println("Поле:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");
    }

    public tictactoe(ViewUpdater UIUpdater, String aiDifficulty) {
        board = Create_board();
        System.out.println("Поле:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");
    }

    public void Play(int index, String gamestep, ViewUpdater UIUpdater) {
        tictactoe game = new tictactoe(UIUpdater);
        game.Turn(index, gamestep);//to variable: place(row and col) and player
//        game.Make_a_move(this.board, this.player, this.row, this.col);
        game.Place(this.board, this.player, this.row, this.col);
        if (game.Win_check(this.board, this.player)) {
            game.Won_place();
            UIUpdater.endThisGameWithWinner(this.won, this.img);
        } else {
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
                this.won = new int[]{0, 1, 2};
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
        if (this.diag_princ == 1) {
            this.won = new int[]{0, 4, 8};
            this.img = "";
        }
        if (this.diag_sec == 1) {
            this.won = new int[]{2, 4, 6};
            this.img = "";
        }
    }

    //3x3
    private int[][] Create_board() {
        return new int[3][3];
    }

    private void Place(int[][] board, int player, int row, int col) {
        board[row][col] = player;
    }

    private void Place_AI(int[][] board, int player) throws Exception {
        // create dataset in memory
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
        Instances dataset = new Instances("tictactoe-move-data", attributes, 0);//накапливаются?

        Classifier mlp = (Classifier) weka.core.SerializationHelper.read("model_mlp.model");

        ArrayList<int[]> possibilities = Possibilities(board);
        int[] best_move = new int[2];
        double best_dist = 0.0;
        for (int[] possible_move : possibilities) {
            //Instances dataset = new Instances("tictactoe-move-data", attributes, 0);
            int[][] current_board_copy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
            double[] predict_board = new double[dataset.numAttributes()];//for dataset
            Place(current_board_copy, player, possible_move[0], possible_move[1]);
            int i = 0;
            for (int[] row : current_board_copy) {
                for (int value : row) {
                    predict_board[i] = value;
                    i++;
                }
            }

            Instance inst = new DenseInstance(1.0, predict_board);
            inst.setMissing(9);
            System.out.println("Instance: " + inst);
            dataset.add(inst);
            dataset.setClassIndex(dataset.numAttributes() - 1);

            double clsLabel = mlp.classifyInstance(dataset.lastInstance());// who is winning?
            double[] dist_labels = mlp.distributionForInstance(dataset.lastInstance());
            System.out.println("Current move: " + possible_move);
            System.out.println("Class label: " + clsLabel);
            System.out.println("Distribution for classes: " + Utils.arrayToString(dist_labels));

            if (dist_labels[player] > best_dist){
                best_dist = dist_labels[player];
                best_move = possible_move;
            }

        }
        Place(board, player, best_move[0], best_move[1]);
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
            //random.setSeed(1);
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
