package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class tictactoe {
    public static void main(String[] args) {
        System.out.println("start");


        int[][] board = Create_board();
        System.out.println("Поле:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");

        Place(board,1, 2, 1);
        System.out.println("Ход первого:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");

        Place(board,2, 0, 2);
        System.out.println("Ход второго:");
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");

        //demo
        ArrayList<int[]> possibilities = Possibilities(board);
        System.out.println("Возможные ходы");
        System.out.println(Arrays.deepToString(possibilities.toArray()));
        System.out.println("----------");
        //demo

        Random_placement(board,1);
        System.out.println("Случайный ход первого игрока");//убрать сид!
        System.out.println(Arrays.deepToString(board));
        System.out.println("----------");



        System.out.println("end");
    }

    //3x3
    public static int[][] Create_board(){
        int[][] arr = new int[3][3];
        return arr;
    }

    // номер кнопки ->(преобразуется) в координаты row=2,col=1 и т.д.
    public static void Place(int[][] board, int player, int row, int col){
        board[row][col] = player;
    }

    // можно проверять по кнопкам / можно проверять по массиву
    public static ArrayList<int[]> Possibilities(int[][] board){
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

    //случайный ход выбранного игрока
    public static void Random_placement(int[][] board, int player){
        ArrayList<int[]> possibilities = Possibilities(board);
        if (possibilities.isEmpty() == false){
            Random random = new Random();
            random.setSeed(1);// убрать сид
            int[] position = possibilities.get(random.nextInt(possibilities.size()));//[0,0]
            Place(board,player,position[0],position[1]);
        }
    }
    

}
