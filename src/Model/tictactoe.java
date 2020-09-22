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

        int[] players = {1,2,1,2,1,2,1,2,1};
        int turn = 0;

        for (int player : players ){
            System.out.println("Ход: " + turn++);

            //Place(board,2, 2, 2);
            //Place(board,1, 0, 1);
            //Place(board,2, 0, 2);

            //demo
            ArrayList<int[]> possibilities = Possibilities(board);
            System.out.println("@@@@@@@@@@@");
            System.out.println("Возможные ходы:");
            System.out.println(Arrays.deepToString(possibilities.toArray()));
            System.out.println("@@@@@@@@@@@");
            //demo

            Random_placement(board, player);
            System.out.println("Случайный ход:");//убрать сид!
            System.out.println(Arrays.deepToString(board));
            System.out.println("----------");

            // по нажатию кнопки должно быть размещение и проверка на победу(после 5ого хода)
            if (Win_check(board,player)){
                System.out.println("Player " + player + " won!");
                break;
            }
            else if (Possibilities(board).isEmpty()){
                System.out.println("No one won!");
                break;
            }
        }

        System.out.println("end");
    }

    //3x3
    public static int[][] Create_board(){
        int[][] arr = new int[3][3];
        return arr;
    }

    // номер кнопки ->(преобразуется) в координаты row=2,col=1 и т.д.
    // сделать ход
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
            random.setSeed(2);// убрать сид
            int[] position = possibilities.get(random.nextInt(possibilities.size()));//[1,2]
            Place(board,player,position[0],position[1]);
        }
    }

    // проверка победы
    public static boolean Win_check(int[][] board, int player){
        // горизонталь
        for (int numrow = 0; numrow < board.length; numrow++){
            int[] row = board[numrow];
            int count = 0;
            for(int value : row){
                if (value == player){
                    count++;
                    if(count == 3){
                        return true;
                    }
                }
            }
        }
        // вертикаль
        for(int numcol = 0; numcol < board[0].length; numcol++) {
            int count = 0;
            for (int numrow = 0; numrow < board.length; numrow++) {
                int[] row = board[numrow];
                int value = row[numcol];
                if (value == player){
                    count++;
                    if (count == 3){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
