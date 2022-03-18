import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //TicTacToe jeu = new TicTacToe();

        Scanner sc = new Scanner(System.in);
        int  positionLigne  = -1;
        int  positionColonne = -1;
        int  position = -1;

        MonIA test = new MonIA();
        TicTacToe jeu = new TicTacToe(test);

        IA robot = jeu.getListeIA().get(0);



        while (!jeu.isGrilleFini() && !jeu.isGrilleGagne() && jeu.getPenaliteO()<=3 && jeu.getPenaliteX() <= 3){

            System.out.println(jeu.toString());

            if (jeu.getTourDuJoueur() == 0){
                robot.run();
                jeu.joue(robot.getLigne(),robot.getColonne());

            }else{

                System.out.print("Position : ");
                position = sc.nextInt();
                positionLigne = (int)(position/10);
                positionColonne = position%10;
                jeu.joue(positionLigne, positionColonne);
            }


            positionLigne = -1;
            positionColonne = -1;

        }

        System.out.println(jeu.toString());



    }
}
