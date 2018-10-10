package game;

import org.springframework.security.core.parameters.P;

import java.util.Scanner;

/**
 * Created by user1 on 10.10.2018.
 */
public class BattleController {
    static char whoseTurn = ' ';

    public static void startGame() {
        BattleField battleField = new BattleField();
        battleField.makeEmptyField();
        battleField.drawField();
        whoseTurn = 'O';
    }

    public static void proccesGame() {
        do {
            read();

        } while (read().equals("закончить"));
    }

    public static void turn() {

    }

    public static String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
