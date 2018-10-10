package game;

/**
 * Created by user1 on 10.10.2018.
 */
public class BattleField {
    char[][] arrayField;

    public void makeEmptyField() {
        arrayField = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrayField[i][j] = ' ';
            }
        }
    }

    public void drawField() {
        System.out.println(arrayField[0][0] + "|" + arrayField[1][0] + "|" + arrayField[2][0]);
        System.out.println(arrayField[0][1] + "|" + arrayField[1][1] + "|" + arrayField[2][1]);
        System.out.println(arrayField[0][2] + "|" + arrayField[1][2] + "|" + arrayField[2][2]);
    }

    public void setCell(int x, int y, char symbol){
        arrayField[x][y] = symbol;
    }


}
