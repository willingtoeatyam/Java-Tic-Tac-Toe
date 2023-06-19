package tictactoe;

import java.util.Scanner;

public class Main {
    static String completedMove = "";
    static Scanner move;
    static String gameState = "_________";
    static Boolean X = true;
    static int wCount = 0;
    static Boolean done = false;
    static Boolean win = false;

    public static void main(String[] args) {
        // write your code here
        displayTick(gameState);

        while (!done){
            int[] tired = getMove();
            validateMove(tired);
        }
    }

    public static void displayTick(String gameData) {
        System.out.println("---------");
        for (int p = 0; p <= 6; p += 3) {
            System.out.print('|');
            System.out.print(" ");
            for (int j = 0; j < 3; j++) {
                if(gameData.charAt(j+p) == '_'){
                    System.out.print(" ");
                }else{
                    System.out.print(gameData.charAt(j + p));
                }
                System.out.print(" ");
            }
            System.out.println('|');
        }
        System.out.println("---------");
    }

    public static int[] getMove() {
        int[] play = new int[2];
        move = new Scanner(System.in);
        if(move.hasNext()){
            System.out.println("Make your move: ");
            String game = move.next();
            String game2 = move.next();

            if(game.matches("\\d+") || game2.matches("\\d+")){
                play[0] = Integer.parseInt(game);
                play[1] = Integer.parseInt(game2);
            } else{
                System.out.println("You should enter numbers!");
            }
        }
        return play;
    }

    public static void validateMove(int [] x) {
        int xAxis = x[0] - 1;
        int yAxis = x[1] - 1;
        int actualMove = (xAxis * 3) + yAxis;
        //if the spot is not '_ throw error
         if ((x[0] > 3 || x[0] < 1) || (x[1] > 3 || x[1] < 1)) {
            System.out.println("Coordinates should be from 1 to 3!");
            validateMove(getMove());
        }else if(gameState.charAt(actualMove) != ('_')){
            System.out.println("This cell is occupied! Choose another one!");
            validateMove(getMove());
        } else if(gameState.charAt(actualMove) == ('_')){
            char moved[] = gameState.toCharArray();
            moved[actualMove] = (X) ? 'X' : 'O';
            X = !X;
            completedMove = new String(moved);
             gameState = completedMove;
            displayTick(gameState);
            checkStatus();
         }
    }

    public static void checkStatus(){
            //Horizontal Wins
            for (int i = 1; i < gameState.length(); i += 3) {
                if(gameState.charAt(i) != '_'){
                    if (gameState.charAt(i) == gameState.charAt(i + 1) && gameState.charAt(i) == gameState.charAt(i - 1)) {
                        System.out.println(gameState.charAt(i) + " wins");
                        wCount++;
                        done =  true;
                        win = true;
                        break;
                    }
                }
            }

            //Vertical Wins
            for (int i = 0; i < 3; i++) {
                if(gameState.charAt(i) != '_'){
                    if (gameState.charAt(i) == gameState.charAt(i + 3) && gameState.charAt(i) == gameState.charAt(i + 6)) {
                        System.out.println(gameState.charAt(i) + " wins");
                        wCount++;
                        done = true;
                        win = true;
                        break;
                    }
                }
            }

            //Diagonal Win
            for (int i = 0; i < 3; i += 2) {
                if(gameState.charAt(i) != '_'){
                    if (gameState.charAt(i) == gameState.charAt(4) && gameState.charAt(i) == gameState.charAt(8 - i)) {
                        System.out.println(gameState.charAt(i) + " wins");
                        wCount++;
                        done = true;
                        win = true;
                        break;
                    }
                }
            }

            //Finished or Draw Check
            boolean finished = true;
            for (int i = 0; i < gameState.length(); i++) {
                if(gameState.charAt(i) == '_') {
                    finished = false;
                    break;
                }
            }

            if(finished && !win){
                done = true;
                System.out.println("Draw");
            }
    }
}
