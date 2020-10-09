package Model;

import ModelView.ViewUpdater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.core.*;

public class Tictactoe {
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
    private int ai_move;
    private String ai_fig;

    private ViewUpdater UIUpdater;
    private String gameType; // field 3x3 or 6x6
    private String aiDifficulty;


    public Tictactoe(ViewUpdater UIUpdater, String gameType) {
        this.UIUpdater = UIUpdater;
        this.gameType = gameType;

        this.board = create_Board();
        System.out.println("Field:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");
    }

    public Tictactoe(ViewUpdater UIUpdater, String aiDifficulty, String gameType) {
        this.UIUpdater = UIUpdater;
        this.gameType = gameType;
        this.aiDifficulty = aiDifficulty;

        this.board = create_Board();
        System.out.println("Field:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");
    }

    public void play(int index, String gameStep, ViewUpdater UIUpdater) {
        try {
            turn(index, gameStep);// now i have: player , row , col
            place(this.board, this.player, this.row, this.col);
            System.out.println("Turn: " + Arrays.deepToString(this.board));
            UIUpdater.toNextStep();
//        temporary !
            switch (aiDifficulty) {
                case "Hard" -> {
                    placeAI(this.board, this.player);
                    System.out.println("Turn AI hard: " + Arrays.deepToString(this.board));
                    encode_ai_fig();
                    if (this.player == 1) {
                        this.player = 2;
                    } else {
                        this.player = 1;
                    }
                    UIUpdater.makeAIStep(this.ai_move, this.ai_fig);
                    win_and_draw_check();
                }
                case "Easy" -> {
                    random_placement(this.board, this.player);
                    System.out.println("Turn AI ez: " + Arrays.deepToString(this.board));
                    encode_ai_fig();
                    if (this.player == 1) {
                        this.player = 2;
                    } else {
                        this.player = 1;
                    }
                    UIUpdater.makeAIStep(this.ai_move, this.ai_fig);
                    win_and_draw_check();
                }
            }
            win_and_draw_check();
        } catch (Exception ignored) {
        }
    }

    // decode turn
    private void turn(int index, String figure) {
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

    // encode turn
    private void encode_ai_move(int[] placement) {
        if (placement[0] == 0 && placement[1] == 0) {
            this.ai_move = 0;
        }
        if (placement[0] == 0 && placement[1] == 1) {
            this.ai_move = 1;
        }
        if (placement[0] == 0 && placement[1] == 2) {
            this.ai_move = 2;
        }
        if (placement[0] == 1 && placement[1] == 0) {
            this.ai_move = 3;
        }
        if (placement[0] == 1 && placement[1] == 1) {
            this.ai_move = 4;
        }
        if (placement[0] == 1 && placement[1] == 2) {
            this.ai_move = 5;
        }
        if (placement[0] == 2 && placement[1] == 0) {
            this.ai_move = 6;
        }
        if (placement[0] == 2 && placement[1] == 2) {
            this.ai_move = 7;
        }
        if (placement[0] == 2 && placement[1] == 2) {
            this.ai_move = 8;
        }
    }

    // encode fig
    private void encode_ai_fig() {
        if (this.player == 1) {
            this.ai_fig = "Крестики";
        } else {
            this.ai_fig = "Нолики";
        }
    }

    // encode won_place
    private void won_place() {
        switch (this.num_row) {
            case 0 -> {
                this.won = new int[]{0, 1, 2};
                if (this.player == 1) {
                    this.img = "Cross_Horizontal";
                } else {
                    this.img = "Null_Horizontal";
                }
            }
            case 1 -> {
                this.won = new int[]{3, 4, 5};
                if (this.player == 1) {
                    this.img = "Cross_Horizontal";
                } else {
                    this.img = "Null_Horizontal";
                }
            }
            case 2 -> {
                this.won = new int[]{6, 7, 8};
                if (this.player == 1) {
                    this.img = "Cross_Horizontal";
                } else {
                    this.img = "Null_Horizontal";
                }
            }
        }
        switch (this.num_col) {
            case 0 -> {
                this.won = new int[]{0, 3, 6};
                if (this.player == 1) {
                    this.img = "Cross_Vertical";
                } else {
                    this.img = "Null_Vertical";
                }
            }
            case 1 -> {
                this.won = new int[]{1, 4, 7};
                if (this.player == 1) {
                    this.img = "Cross_Vertical";
                } else {
                    this.img = "Null_Vertical";
                }
            }
            case 2 -> {
                this.won = new int[]{2, 5, 8};
                if (this.player == 1) {
                    this.img = "Cross_Vertical";
                } else {
                    this.img = "Null_Vertical";
                }
            }
        }
        if (this.diag_princ == 1) {
            this.won = new int[]{0, 4, 8};
            if (this.player == 1) {
                this.img = "Cross_Diagonal_RD";
            } else {
                this.img = "Null_Diagonal_RD";
            }
        }
        if (this.diag_sec == 1) {
            this.won = new int[]{2, 4, 6};
            if (this.player == 1) {
                this.img = "Cross_Diagonal_LD";
            } else {
                this.img = "Null_Diagonal_LD";
            }
        }
    }

    //3x3
    private int[][] create_Board() {
        return new int[3][3];
    }

    private void place(int[][] board, int player, int row, int col) {
        board[row][col] = player;
    }

    private void placeAI(int[][] board, int player) throws Exception {
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
        Instances dataset = new Instances("tictactoe-move-data", attributes, 0);//stacking?

        Classifier mlp = (Classifier) weka.core.SerializationHelper.read("model_mlp.model");

        ArrayList<int[]> possibilities = possibilities(board);
        int[] best_move = new int[2];
        double best_dist = 0.0;
        for (int[] possible_move : possibilities) {
            //Instances dataset = new Instances("tictactoe-move-data", attributes, 0);
            int[][] current_board_copy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
            double[] predict_board = new double[dataset.numAttributes()];//for dataset
            place(current_board_copy, player, possible_move[0], possible_move[1]);
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

            if (dist_labels[player] > best_dist) {
                best_dist = dist_labels[player];
                best_move = possible_move;
            }

        }
        place(board, player, best_move[0], best_move[1]);
        encode_ai_move(best_move);
    }

    private ArrayList<int[]> possibilities(int[][] board) {
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

    private void random_placement(int[][] board, int player) {
        ArrayList<int[]> possibilities = possibilities(board);
        if (!possibilities.isEmpty()) {
            Random random = new Random();
            //random.setSeed(1);
            int[] position = possibilities.get(random.nextInt(possibilities.size()));
            place(board, player, position[0], position[1]);
            encode_ai_move(position);
        }
    }

    private boolean win_check(int[][] board, int player) {
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

    private void win_and_draw_check() {
        if (win_check(this.board, player)) {
            System.out.println("Player " + this.player + " won!");
            won_place();// now i have: won , img
            UIUpdater.endThisGameWithWinner(this.won, img);
        } else if (possibilities(this.board).isEmpty()) {
            System.out.println("No one won!");
            UIUpdater.endThisGameWithoutWinner();
        }
    }

}

