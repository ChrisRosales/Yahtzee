
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Depends2 {

    static final char ur = '\u2557';
    static final char ul = '\u2554';
    static final char top = '\u2550';
    static final char side = '\u2551';
    static final char sideTL = '\u2560';
    static final char sideRL = '\u2563';
    static final char bl = '\u255A';
    static final char br = '\u255D';
    static final char one = '\u2776';
    static final char two = '\u2777';
    static final char thr = '\u2778';
    static final char fou = '\u2779';
    static final char fiv = '\u277A';
    static final char six = '\u277B';
    static final char sev = '\u277C';
    static final char eig = '\u277D';
    static final char nin = '\u277E';
    static final char ten = '\u277F';
    static final char ele = '\u277F';
    static final char twe = '\u277F';
    static final char thir = '\u277F';
    //----------------------------------------------------------------
    //---------------- G L O B A L  V A R I A B L E S ------------
    //----------------------------------------------------------------
    int game1 = 1;
    boolean game1Finished = false;
    boolean game2Finished = false;
    int cantFill = 0;
    int gameChange = 0;
    int game2 = 1;
    int score = 0;
    int totalRoll = 1;
    Scanner input = new Scanner(System.in);
    int[] dice = new int[5];
    ArrayList<Integer> total = new ArrayList<Integer>();
    String[][] scoresheet = new String[20][8];
    String[][] scoresheet2 = new String[20][8];
    String[][] tempScore = new String[20][8];
    String[][] tempScore2 = new String[20][8];
    ArrayList<Integer> roll = new ArrayList<Integer>();
    int player;
    int b = 0;
    int check = 0;
    boolean flag = true;
    int a = 0;
    int choice;
    int totalOfUpper;
    int totalOfLower;
    int grandTotal;
    int totalOfUpper2;
    int totalOfLower2;
    int grandTotal2;
    boolean yahtzeeFlag = true;

    public static void main(String[] args) {
        Depends2 es = new Depends2();  //invoke constructor
    }

    /**
     *
     * @param text give me a text expression
     * @param totalLength total length of String to be filled
     * @return a String of spaces to "pad" the region where len =
     * total.len-text.len
     */
    public String padding(String text, int totalLength) {
        int difference = totalLength - text.length();
        if (difference > 0) {

            for (int i = 0; i < difference; i++) {
                text += " ";
            }
        }
        return text;
    }

    public Depends2() {
        menu();
    }

    public void menu() {

        while (true) {
            Date now = new Date();
            System.out.print("\t\t" + ul);
            for (int i = 0; i < 68; i++) {
                System.out.print(top);
            }
            //_________________________________________");
            System.out.println(ur);
            System.out.println("\t\t" + side + "               ***       Yahtzee       ***                          " + side);
            System.out.println("\t\t" + side + "               ***  By Chris Rosales   ***                          " + side);
            System.out.print("\t\t" + sideTL);

            for (int i = 0; i < 68; i++) {
                System.out.print(top);
            }
            //_________________________________________
            System.out.println(sideRL);
            System.out.println("\t\t" + side + "        " + one + " = Player 1 Scoreboard                                      " + side);
            System.out.println("\t\t" + side + "        " + two + " = Player 2 Scoreboard                                     " + side);
            System.out.println("\t\t" + side + "        " + thr + " = Roll                                                    " + side);
            System.out.println("\t\t" + side + "        " + fou + " = Exit                                                    " + side);
            System.out.println("\t\t" + side + "        " + fiv + " = Developer Roll                                          " + side);
            System.out.println("\t\t" + side + "  Your Selection?                                                   " + side);
            System.out.print("\t\t" + bl);
            for (int i = 0; i < 68; i++) {
                System.out.print(top);
            }
            //+"_________________________________________
            System.out.println(br);

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    player1Display();
                    break;
                case 2:
                    player2Display();
                    break;
                case 3:
                    firstRoll();
                    break;
                case 4:
                    System.exit(0);
                    break;
                case 5:
                    developerRoll();
                    break;
            }
        }
    }

    public void developerRoll() {
        for (int r = 0; r < scoresheet.length; r++) {//next game function
            if (scoresheet[r][game1 + 1] != null) {
                gameChange++;
            }
        }
        System.out.println("Score filled for player 1 : " + gameChange);
        if (gameChange >= 17) {
            game1Finished = true;
            gameChange = 0;
        }
        gameChange = 0;
        //player 2...
        for (int r = 0; r < scoresheet2.length; r++) {//next game function
            if (scoresheet2[r][game1 + 1] != null) {
                gameChange++;
            }
        }
        System.out.println("Score filled for player 2 : " + gameChange);
        if (gameChange >= 17) {
            game1Finished = true;
            gameChange = 0;
        }
        gameChange = 0;
        if (game1Finished && game2Finished) {//dont forget about THISSSSSS
            game1++;
            totalOfUpper = 0;
            totalOfLower = 0;
            grandTotal = 0;
            totalOfUpper2 = 0;
            totalOfLower2 = 0;
            grandTotal2 = 0;
            game1Finished = false;
            game2Finished = false;
        }
        System.out.println("GAME  " + game1);
        System.out.println("Enter Player Who is Next (1 or 2) : ");
        player = input.nextInt();
        int[] devRoll = new int[5];
        System.out.println("Enter in roll followed by a space for each integer..");
        for (int i = 0; i < devRoll.length; i++) {
            devRoll[i] = input.nextInt();
            total.add(devRoll[i]);
        }
        equation();
    }

    public void player2Display() { //0 based.. original is 20 by 8
        System.out.println("Y A H T Z E E");
        //FIRST COLUMN
        scoresheet2[0][0] = "UPPER SECTION                |    ";
        scoresheet2[1][0] = "Aces (1)                     |";
        scoresheet2[2][0] = "Twos (2)                     |";
        scoresheet2[3][0] = "Threes (3)                   |";
        scoresheet2[4][0] = "Fours (4)                    |";
        scoresheet2[5][0] = "Fives (5)                    |";
        scoresheet2[6][0] = "Sixes (6)                    |";
        scoresheet2[7][0] = "TOTAL SCORE                  |";
        scoresheet2[8][0] = "BONUS (63 or over) (8)       |";
        scoresheet2[9][0] = "TOTAL Of Upper Section       |";
        scoresheet2[10][0] = "LOWER SECTION               ";
        scoresheet2[11][0] = "3 of a kind  (11)            |";
        scoresheet2[12][0] = "4 of a kind  (12)            |";
        scoresheet2[13][0] = "Full House   (13)            |";
        scoresheet2[14][0] = "Straight Sequence of 4 (14)  |";
        scoresheet2[15][0] = "Straight Sequence of 5 (15)  |";
        scoresheet2[16][0] = "YAHTZEE 5 of a kind (16)     |";
        scoresheet2[17][0] = "Chance (17)                  |";
        scoresheet2[18][0] = "Yahtzee Checkmark            |";
        scoresheet2[19][0] = "TOTAL Of Lower Section       |";

        for (int r = 0; r < scoresheet2.length; r++) {
            int i = 1;
            System.out.println("");
            //System.out.println("--------------- --------------------------------");
            //System.out.println("------------------------------------------------");
            for (int c = 0; c < scoresheet2[r].length; c++) {
                if (r == 0 && c > 2) {
                    System.out.print("GAME #" + i + "  ");
                    i++;
                }
                if (r > 0 && c > 1 && scoresheet2[r][c] != null) {
                    System.out.print("\t    ");
                }
                if (scoresheet2[r][c] == null) {
                    System.out.print("");
                } else {
                    System.out.print(scoresheet2[r][c]);
                }

            }
            System.out.println("");
        }
    }

    public void equation2() {
        if (player == 2) {
            Collections.sort(total);
            System.out.print("You have chosen ");
            System.out.println(total);
            String[] totalFromInt = new String[total.size()];
            int repeat = 0;
            int yahtzeeCount = 0;
            int count = 0;
            int straight = 1;
            int totalScore = 0;
            int chance = 0;

            //implement all possible available spots with number
            for (int i = 0; i < total.size(); i++) {
                chance += total.get(i);
                for (int j = 0; j < total.size(); j++) {
                    if (total.get(i) == total.get(j)) {
                        repeat += total.get(i);
                        count++;
                    }
                }

                for (int roll = 1; roll <= 6; roll++) {
                    if (total.get(i) == roll && scoresheet2[roll][game1 + 1] == null) {
                        totalFromInt[i] = String.valueOf(repeat);
                        tempScore2[roll][game1 + 1] = "(" + totalFromInt[i] + ")";

                    }
                }

                //here
                if (count == 3 && scoresheet2[11][game1 + 1] == null) {
                    //three of a kind
                    count = 0;
                    count += repeat;
                    String kindFromInt = String.valueOf(count);
                    tempScore2[11][game1 + 1] = "(" + kindFromInt + ")";
                    kindFromInt = "";
                }
                if (count == 4 && scoresheet2[12][game1 + 1] == null) {
                    //four of a kind
                    count = 0;
                    count += repeat;
                    String kindFromInt = String.valueOf(count);
                    tempScore2[12][game1 + 1] = "(" + kindFromInt + ")";
                    kindFromInt = "";
                }
                if (count == 5 && scoresheet2[16][game1 + 1] == null) {
                    //yahtzee
                    yahtzeeFlag = true;
                    count = 0;
                    String kindFromInt = String.valueOf(50);
                    tempScore2[16][game1 + 1] = "(" + kindFromInt + ")";
                    kindFromInt = "";

                }
                count = 0;
                repeat = 0;
            }
            if (yahtzeeFlag) {
                yahtzeeCount++;
                yahtzeeFlag = false;
            }
            for (int i = 0; i < total.size() - 1; i++) {
                if (total.get(i + 1) - total.get(i) == 1) {
                    straight++;
                }
            }

            if (straight == 4 && scoresheet2[14][game1 + 1] == null) {
                int score = 30;
                String scoreFromInt = String.valueOf(score);
                tempScore2[14][game1 + 1] = "(" + scoreFromInt + ")";
                straight = 1;
                scoreFromInt = "";
                score = 0;
            }
            if (straight == 5 && scoresheet2[15][game1 + 1] == null) {
                int score = 40;
                String scoreFromInt = String.valueOf(score);
                tempScore2[15][game1 + 1] = "(" + scoreFromInt + ")";
                straight = 1;
                scoreFromInt = "";
                score = 0;
            }
            if (yahtzeeCount > 1) {
                String oneHundo = String.valueOf(100);
                scoresheet2[18][game1 + 1] = oneHundo;
                grandTotal2 += 100;
            }
            if (scoresheet2[17][game1 + 1] == null) {
                String chanceFromtInt = String.valueOf(chance);
                tempScore2[17][game1 + 1] = "(" + chanceFromtInt + ")";
            }
            if ((Objects.equals(total.get(0), total.get(1)) && Objects.equals(total.get(2), total.get(3)) && Objects.equals(total.get(3), total.get(4)) && !Objects.equals(total.get(1), total.get(2)))
                    || (Objects.equals(total.get(0), total.get(1)) && Objects.equals(total.get(1), total.get(2)) && Objects.equals(total.get(3), total.get(4)) && !Objects.equals(total.get(2), total.get(3)))) {
                int score = 25;
                String scoreFromHouse = String.valueOf(score);
                tempScore2[13][game1 + 1] = "(" + scoreFromHouse + ")";
                score = 0;
            }

            for (int r = 0; r < tempScore2.length; r++) {
                if (tempScore2[r][game1 + 1] != null && tempScore2[r][game1 + 1].contains("(")) {
                    cantFill++;
                }
            }
            if (cantFill == 0) {//if roll has no open spots..
                for (int r = 0; r < scoresheet2.length; r++) {
                    if (scoresheet2[r][game1 + 1] == null && scoresheet2[r][0].contains("(") && r != 8) {
                        tempScore2[r][game1 + 1] = "X";
                    }
                }
                tempDisplay2();
                System.out.println("Which spot would you like to place a X? (Choose the number next to the available catagory..)");
                choice = input.nextInt();
                scoresheet2[choice][game1 + 1] = "X";
                cantFill = 0;
                for (int i = 1; i < tempScore2.length - 1; i++) {
                    tempScore2[i][game1 + 1] = null;
                }

            } else {
                tempDisplay2();
                System.out.println("Which available space would you like to chose? (Choose the number next to the available catagory..)");
                choice = input.nextInt();
            }

            for (int r = 0; r < scoresheet2.length; r++) {
                for (int c = 0; c < scoresheet2[r].length; c++) {
                    if (scoresheet2[r][game1 + 1] == null) {
                        if (choice == r) {
                            String first = tempScore2[choice][game1 + 1].replaceAll("[^0-9]+", "");
                            scoresheet2[choice][game1 + 1] = first;
                            if (choice < 7) {//finding total of upper 
                                //remove parenthesis..
                                int toIntTempScore = Integer.parseInt(first);
                                totalOfUpper2 += toIntTempScore;
                                String uT = String.valueOf(totalOfUpper2);
                                scoresheet2[9][game1 + 1] = uT;
                            }
                            if (choice > 10) {//finding total of lower
                                int toIntTempScore = Integer.parseInt(first);
                                totalOfLower2 += toIntTempScore;
                                String lT = String.valueOf(totalOfLower2);
                                scoresheet2[19][game1 + 1] = lT;
                            }
                        }
                    }
                }
            }
            grandTotal2 = totalOfLower2 + totalOfUpper2;
            String grandtotalFromInt = String.valueOf(grandTotal);
            scoresheet2[7][game1 + 1] = grandtotalFromInt;
            if (grandTotal > 62) {
                String bonuss = String.valueOf(35);
                scoresheet2[8][game1 + 1] = bonuss;
                grandTotal += 35;
            }
            gameChange = 0;
            cantFill = 0;
            straight = 1;
            chance = 0;
            choice = 0;
            total.removeAll(total);
            totalRoll = 1;
            clear2();
            menu();
        }
    }

    public void equation() {

        if (player == 2) {
            equation2();
        }
        if (player == 1) {
            Collections.sort(total);
            System.out.print("You have chosen ");
            System.out.println(total);
            String[] totalFromInt = new String[total.size()];
            int repeat = 0;
            int yahtzeeCount = 0;
            int count = 0;
            int straight = 1;
            int totalScore = 0;
            int chance = 0;

            //implement all possible available spots with number
            for (int i = 0; i < total.size(); i++) {
                chance += total.get(i);
                for (int j = 0; j < total.size(); j++) {
                    if (total.get(i) == total.get(j)) {
                        repeat += total.get(i);
                        count++;
                    }
                }

                for (int roll = 1; roll <= 6; roll++) {
                    if (total.get(i) == roll && scoresheet[roll][game1 + 1] == null) {

                        totalFromInt[i] = String.valueOf(repeat);
                        tempScore[roll][game1 + 1] = "(" + totalFromInt[i] + ")";

                    }
                }

                //here
                if (count == 3 && scoresheet[11][game1 + 1] == null) {
                    //three of a kind
                    count = 0;
                    count += repeat;
                    String kindFromInt = String.valueOf(count);
                    tempScore[11][game1 + 1] = "(" + kindFromInt + ")";
                    kindFromInt = "";

                }
                if (count == 4 && scoresheet[12][game1 + 1] == null) {
                    //four of a kind
                    count = 0;
                    count += repeat;
                    String kindFromInt = String.valueOf(count);
                    tempScore[12][game1 + 1] = "(" + kindFromInt + ")";
                    kindFromInt = "";
                }

                if (count == 5 && scoresheet[16][game1 + 1] == null) {
                    //yahtzee
                    yahtzeeFlag = true;
                    count = 0;
                    String kindFromInt = String.valueOf(50);
                    tempScore[16][game1 + 1] = "(" + kindFromInt + ")";
                    kindFromInt = "";

                }
                count = 0;
                repeat = 0;
            }
            if (yahtzeeFlag) {
                yahtzeeCount++;
                yahtzeeFlag = false;
            }
            for (int i = 0; i < total.size() - 1; i++) {
                if (total.get(i + 1) - total.get(i) == 1) {
                    straight++;
                }
            }

            if (straight == 4 && scoresheet[14][game1 + 1] == null) {
                int score = 30;
                String scoreFromInt = String.valueOf(score);
                tempScore[14][game1 + 1] = "(" + scoreFromInt + ")";
                straight = 1;
                scoreFromInt = "";
                score = 0;
            }
            if (straight == 5 && scoresheet[15][game1 + 1] == null) {
                int score = 40;
                String scoreFromInt = String.valueOf(score);
                tempScore[15][game1 + 1] = "(" + scoreFromInt + ")";
                straight = 1;
                scoreFromInt = "";
                score = 0;
            }
            if (yahtzeeCount > 1) {
                String oneHundo = String.valueOf(100);
                scoresheet[18][game1 + 1] = oneHundo;
                grandTotal += 100;
            }
            if (scoresheet[17][game1 + 1] == null) {
                String chanceFromtInt = String.valueOf(chance);
                tempScore[17][game1 + 1] = "(" + chanceFromtInt + ")";
            }
            if ((Objects.equals(total.get(0), total.get(1)) && Objects.equals(total.get(2), total.get(3)) && Objects.equals(total.get(3), total.get(4)) && !Objects.equals(total.get(1), total.get(2)))
                    || (Objects.equals(total.get(0), total.get(1)) && Objects.equals(total.get(1), total.get(2)) && Objects.equals(total.get(3), total.get(4)) && !Objects.equals(total.get(2), total.get(3)))) {
                int score = 25;
                String scoreFromHouse = String.valueOf(score);
                tempScore[13][game1 + 1] = "(" + scoreFromHouse + ")";
                score = 0;
            }

            for (int r = 0; r < tempScore.length; r++) {
                if (tempScore[r][game1 + 1] != null && tempScore[r][game1 + 1].contains("(")) {
                    cantFill++;
                }
            }
            if (cantFill == 0) {//if roll has no open spots..
                for (int r = 0; r < scoresheet.length; r++) {
                    if (scoresheet[r][game1 + 1] == null && scoresheet[r][0].contains("(") && r != 8) {
                        tempScore[r][game1 + 1] = "X";
                    }
                }
                tempDisplay1();
                System.out.println("Which spot would you like to place a X? (Choose the number next to the available catagory..)");
                choice = input.nextInt();
                scoresheet[choice][game1 + 1] = "X";
                cantFill = 0;
                for (int i = 1; i < tempScore.length - 1; i++) {
                    tempScore[i][game1 + 1] = null;
                }

            } else {
                tempDisplay1();
                System.out.println("Which available space would you like to chose? (Choose the number next to the available catagory..)");
                choice = input.nextInt();
            }

            for (int r = 0; r < scoresheet.length; r++) {//assign it to the scoreboard
                for (int c = 0; c < scoresheet[r].length; c++) {
                    if (scoresheet[r][game1 + 1] == null) {
                        if (choice == r) {
                            String first = tempScore[choice][game1 + 1].replaceAll("[^0-9]+", "");
                            scoresheet[choice][game1 + 1] = first;
                            if (choice < 7) {//finding total of upper 
                                //remove parenthesis..
                                int toIntTempScore = Integer.parseInt(first);
                                totalOfUpper += toIntTempScore;
                                String uT = String.valueOf(totalOfUpper);
                                scoresheet[9][game1 + 1] = uT;
                            }
                            if (choice > 10) {//finding total of lower
                                int toIntTempScore = Integer.parseInt(first);
                                totalOfLower += toIntTempScore;
                                String lT = String.valueOf(totalOfLower);
                                scoresheet[19][game1 + 1] = lT;
                            }
                        }
                    }
                }
            }

            grandTotal = totalOfLower + totalOfUpper;
            String grandtotalFromInt = String.valueOf(grandTotal);
            scoresheet[7][game1 + 1] = grandtotalFromInt;
            if (grandTotal > 62) {
                String bonuss = String.valueOf(35);
                scoresheet[8][game1 + 1] = bonuss;
                grandTotal += 35;
            }
            gameChange = 0;
            cantFill = 0;
            straight = 1;
            chance = 0;
            choice = 0;
            total.removeAll(total);
            totalRoll = 1;
            clear();
            menu();
        }
    }

    public void clear() {
        for (int r = 0; r < scoresheet.length; r++) {
            for (int c = 0; c < scoresheet[r].length; c++) {
                if (tempScore[r][c] != null) {
                    tempScore[r][c] = "";
                }
            }
        }
    }

    public void clear2() {
        for (int r = 0; r < scoresheet2.length; r++) {
            for (int c = 0; c < scoresheet2[r].length; c++) {
                if (tempScore2[r][c] != null) {
                    tempScore2[r][c] = "";
                }
            }
        }
    }

    public void tempDisplay2() {
        //0 based.. original is 20 by 8
        System.out.println("Y A H T Z E E");
        //FIRST COLUMN
        tempScore2[0][0] = "UPPER SECTION                |    ";
        tempScore2[1][0] = "Aces (1)                     |";
        tempScore2[2][0] = "Twos (2)                     |";
        tempScore2[3][0] = "Threes (3)                   |";
        tempScore2[4][0] = "Fours (4)                    |";
        tempScore2[5][0] = "Fives (5)                    |";
        tempScore2[6][0] = "Sixes (6)                    |";
        tempScore2[7][0] = "TOTAL SCORE                  |";
        tempScore2[8][0] = "BONUS (63 or over) (8)       |";
        tempScore2[9][0] = "TOTAL Of Upper Section       |";
        tempScore2[10][0] = "LOWER SECTION               ";
        tempScore2[11][0] = "3 of a kind  (11)            |";
        tempScore2[12][0] = "4 of a kind  (12)            |";
        tempScore2[13][0] = "Full House   (13)            |";
        tempScore2[14][0] = "Straight Sequence of 4 (14)  |";
        tempScore2[15][0] = "Straight Sequence of 5 (15)  |";
        tempScore2[16][0] = "YAHTZEE 5 of a kind (16)     |";
        tempScore2[17][0] = "Chance (17)                  |";
        tempScore2[18][0] = "Yahtzee Checkmark            |";
        tempScore2[19][0] = "TOTAL Of Lower Section       |";
        //gameboard is [1][2] -> [21][7]
        for (int r = 0; r < tempScore2.length; r++) {
            int i = 1;
            System.out.println("");
            //System.out.println("--------------- --------------------------------");
            //System.out.println("------------------------------------------------");
            for (int c = 0; c < tempScore2[r].length; c++) {
                if (r == 0 && c > 2) {
                    System.out.print("GAME #" + i + "  ");
                    i++;
                }
                if (r > 0 && c > 1 && tempScore2[r][c] != null) {
                    System.out.print("\t    ");
                }
                if (tempScore2[r][c] == null) {
                    System.out.print("");
                } else {
                    System.out.print(tempScore2[r][c]);
                }

            }
            System.out.println("");
        }

    }

    public void tempDisplay1() {
        //0 based.. original is 20 by 8
        System.out.println("Y A H T Z E E");
        //FIRST COLUMN
        tempScore[0][0] = "UPPER SECTION                |    ";
        tempScore[1][0] = "Aces (1)                     |";
        tempScore[2][0] = "Twos (2)                     |";
        tempScore[3][0] = "Threes (3)                   |";
        tempScore[4][0] = "Fours (4)                    |";
        tempScore[5][0] = "Fives (5)                    |";
        tempScore[6][0] = "Sixes (6)                    |";
        tempScore[7][0] = "TOTAL SCORE                  |";
        tempScore[8][0] = "BONUS (63 or over) (8)       |";
        tempScore[9][0] = "TOTAL Of Upper Section       |";
        tempScore[10][0] = "LOWER SECTION               ";
        tempScore[11][0] = "3 of a kind  (11)            |";
        tempScore[12][0] = "4 of a kind  (12)            |";
        tempScore[13][0] = "Full House   (13)            |";
        tempScore[14][0] = "Straight Sequence of 4 (14)  |";
        tempScore[15][0] = "Straight Sequence of 5 (15)  |";
        tempScore[16][0] = "YAHTZEE 5 of a kind (16)     |";
        tempScore[17][0] = "Chance (17)                  |";
        tempScore[18][0] = "Yahtzee Checkmark            |";
        tempScore[19][0] = "TOTAL Of Lower Section       |";
        //gameboard is [1][2] -> [21][7]
        for (int r = 0; r < tempScore.length; r++) {
            int i = 1;
            System.out.println("");
            //System.out.println("--------------- --------------------------------");
            //System.out.println("------------------------------------------------");
            for (int c = 0; c < tempScore[r].length; c++) {
                if (r == 0 && c > 2) {
                    System.out.print("GAME #" + i + "  ");
                    i++;
                }
                if (r > 0 && c > 1 && tempScore[r][c] != null) {
                    System.out.print("\t    ");
                }
                if (tempScore[r][c] == null) {
                    System.out.print("");
                } else {
                    System.out.print(tempScore[r][c]);
                }

            }
            System.out.println("");
        }

    }

    public void firstRoll() {
        for (int r = 0; r < scoresheet.length; r++) {//next game function
            if (scoresheet[r][game1 + 1] != null) {
                gameChange++;
            }
        }
        if (gameChange == 18) {
            game1Finished = true;
            gameChange = 0;
        }
        gameChange = 0;
        //player 2...
        for (int r = 0; r < scoresheet2.length; r++) {
            if (scoresheet2[r][game1 + 1] != null) {
                gameChange++;
            }
        }
        if (gameChange == 18) {
            game1Finished = true;
            gameChange = 0;
        }
        gameChange = 0;
        if (game1Finished && game2Finished) {
            game1++;
            totalOfUpper = 0;
            totalOfLower = 0;
            grandTotal = 0;
            totalOfUpper2 = 0;
            totalOfLower2 = 0;
            grandTotal2 = 0;
            game1Finished = false;
            game2Finished = false;
        }
        //scoresheet 1 and 2
        System.out.println("GAME  " + game1);
        System.out.println("Enter Player Who is Next (1 or 2) : ");
        player = input.nextInt();
        roll();

    }

    public void roll() {
        // if any input is 7
        //if first input is 5 or 6

        if (totalRoll > 3) {
            equation();
            totalRoll = 1;
        }
        System.out.println("Roll " + totalRoll);
        for (int i = 0; i < dice.length; i++) {//roll 1
            dice[i] = (int) (Math.random() * 6) + 1;
            System.out.print(dice[i] + " ");
            //total.add(dice[i]); //change add here
        }
        System.out.println("\nHow many numbers will you freeze? 7 for next roll, 8 to quit, 9 to take all");//work on 7 and 8
        a = input.nextInt();
        if (a == 7) {
            totalRoll += 1;
            a = 0;
            secondRoll();
        }
        if (a == 8) {
            a = 0;
            totalRoll = 1;
            menu();
        }
        if (a == 9) {
            a = 0;
            totalRoll = 1;
            for (int i = 0; i < dice.length; i++) {
                total.add(dice[i]);
            }
            equation();
        } else if (totalRoll < 4) {
            System.out.println("Enter in the " + a + " numbers, followed by a space and in order");//freezing the numbers
            int[] freeze = new int[a];
            for (int i = 0; i < freeze.length; i++) {
                freeze[i] = input.nextInt();
                total.add(freeze[i]); //adding freeze to total
                for (int j = 0; j < dice.length; j++) {
                    if (freeze[i] == dice[j]) {
                        dice[j] = 0;
                        check++;
                    }
                }
            }
            if (check < a) {
                flag = false;
                System.out.println(a);
                System.out.println(check);
                System.out.println("Number not in roll or misinput :(");
                a = 0;
                check = 0;
                for (int i = 0; i < freeze.length; i++) {
                    freeze[i] = 0;
                }
                totalRoll = 0;
                menu();
            }
            totalRoll++;
            secondRoll();
        }
    }

    public void secondRoll() {
        if (totalRoll > 3) {
            roll();
        } else if (flag && totalRoll < 4) {

            check = 0;
            System.out.println("Next Roll..");
            System.out.println("Roll " + totalRoll);
            for (int i = 0; i < dice.length - a; i++) {//roll2
                dice[i] = 0;
                dice[i] = (int) (Math.random() * 6) + 1;
                System.out.print(dice[i] + " ");
                //total.add(dice[i]); //change add here
            }
            System.out.println("");
            System.out.print("Frozen: " + total);
            System.out.println("\nHow many numbers will you take? 7 for next roll, 8 to quit, 9 to take all, 10 to remove frozen any frozen");//work on 7 and 8
            b = input.nextInt();
            if (b == 7) {
                totalRoll += 1;
                b = 0;
                thirdRoll();

            }
            if (b == 8) {
                b = 0;
                totalRoll = 1;
                menu();
            }
            if (b == 9) {
                totalRoll = 1;
                for (int i = 0; i < dice.length - a; i++) {
                    total.add(dice[i]);

                }
                a = 0;
                b = 0;
                equation();
            }
            if (b == 10) {//work on this
                int count = 0;
                System.out.println("Frozen: " + total);
                System.out.println("Enter in how many number you would like to remove...");
                int amount = input.nextInt();
                System.out.println("Enter in the " + amount + " numbers..");
                int[] remove = new int[amount];
                for (int i = 0; i < amount; i++) {
                    remove[i] = input.nextInt();
                    if (remove[i] == total.get(i)) {
                        total.remove(i);
                        count++;
                    }
                }
                a -= count;
                b = 0;
                count = 0;
                secondRoll();

            }
            System.out.println("Enter in the " + b + " numbers, followed by a space and in order");
            int[] freeze2 = new int[b];
            for (int i = 0; i < freeze2.length; i++) {//inputting the 2 if any freze
                freeze2[i] = input.nextInt();
                total.add(freeze2[i]);
                for (int j = 0; j < dice.length - a; j++) {
                    if (freeze2[i] == dice[j]) {
                        dice[j] = 0;
                        check++;
                    }
                }
            }
            if (check < b) {
                flag = false;
                System.out.println("Number not in roll or misinput :(");
                total.removeAll(total);
                a = 0;
                b = 0;
                check = 0;
                for (int i = 0; i < freeze2.length; i++) {
                    freeze2[i] = 0;
                }
                totalRoll = 0;
                menu();

            }
        }
        totalRoll++;
        thirdRoll();

    }

    public void thirdRoll() {
        int d = a + b;
        if (totalRoll > 3) {
            roll();
        } else if (flag && totalRoll < 4) {
            check = 0;
            System.out.println("Next Roll..");
        }
        System.out.println("Roll " + totalRoll);
        //int[] choice3 = new int[dice.length - d];
        for (int i = 0; i < dice.length - d; i++) {//roll2
            dice[i] = 0;
            dice[i] = (int) (Math.random() * 6) + 1;
            total.add(dice[i]); ///AAAAAAAAADDDDDDDDDDDDDDDDDDD///AAAAAAAAADDDDDDDDDDDDDDDDDDD///AAAAAAAAADDDDDDDDDDDDDDDDDDD///AAAAAAAAADDDDDDDDDDDDDDDDDDD
        }
        System.out.println("");
        System.out.println("Press ENTER to go to display..");
        String enter = input.next();
        totalRoll++;
        if (totalRoll > 3) {
            roll();

        }
        a = 0;
        b = 0;
        d = 0;
        check = 0;

        equation();
    }

    public void player1Display() {
        //0 based.. original is 20 by 8
        System.out.println("Y A H T Z E E");
        //FIRST COLUMN
        scoresheet[0][0] = "UPPER SECTION                |    ";
        scoresheet[1][0] = "Aces (1)                     |";
        scoresheet[2][0] = "Twos (2)                     |";
        scoresheet[3][0] = "Threes (3)                   |";
        scoresheet[4][0] = "Fours (4)                    |";
        scoresheet[5][0] = "Fives (5)                    |";
        scoresheet[6][0] = "Sixes (6)                    |";
        scoresheet[7][0] = "TOTAL SCORE                  |";
        scoresheet[8][0] = "BONUS (63 or over) (8)       |";
        scoresheet[9][0] = "TOTAL Of Upper Section       |";
        scoresheet[10][0] = "LOWER SECTION               ";
        scoresheet[11][0] = "3 of a kind  (11)            |";
        scoresheet[12][0] = "4 of a kind  (12)            |";
        scoresheet[13][0] = "Full House   (13)            |";
        scoresheet[14][0] = "Straight Sequence of 4 (14)  |";
        scoresheet[15][0] = "Straight Sequence of 5 (15)  |";
        scoresheet[16][0] = "YAHTZEE 5 of a kind (16)     |";
        scoresheet[17][0] = "Chance (17)                  |";
        scoresheet[18][0] = "Yahtzee Checkmark            |";
        scoresheet[19][0] = "TOTAL Of Lower Section       |";
        //gameboard is [1][2] -> [21][7]
        for (int r = 0; r < scoresheet.length; r++) {
            int i = 1;
            System.out.println("");
            //System.out.println("--------------- --------------------------------");
            //System.out.println("------------------------------------------------");
            for (int c = 0; c < scoresheet[r].length; c++) {
                if (r == 0 && c > 2) {
                    System.out.print("GAME #" + i + "  ");
                    i++;
                }
                if (r > 0 && c > 1 && scoresheet[r][c] != null) {
                    System.out.print("\t    ");
                }
                if (scoresheet[r][c] == null) {
                    System.out.print("");
                } else {
                    System.out.print(scoresheet[r][c]);
                }

            }
            System.out.println("");
        }
    }
}
