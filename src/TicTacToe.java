import java.util.ArrayList;

import static java.lang.Math.abs;

public class TicTacToe {

    private char[][] grille         = new char[3][3];
    private int[][]  grilleNumerote = new int[grille.length][grille.length];
    private ArrayList<IA> listeIA   = new ArrayList<>();

    private int nbTours = 1;
    private int tourDuJoueur = 0;
    private int penaliteO = 0;
    private int penaliteX = 0;
    private boolean grilleGagne = false;
    private boolean grilleFini  = false;

    private int derniereLigneJoue   = -1;
    private int derniereColonneJoue = -1;



    public TicTacToe() {
        initialisation();
    }

    public TicTacToe(IA joueur){
        initialisation();
        this.listeIA.add(joueur);
        refreshInformation();

    }

    private void initialisation(){
        for (int ligne = 0;ligne < this.grille.length;ligne++){
            for(int colonne = 0; colonne < this.grille[0].length;colonne++){
                this.grille[ligne][colonne] = ' ';
                this.grilleNumerote[ligne][colonne] = 0;
            }
        }
    }

    public void joue(int ligne, int colonne){
        if ( ligne>=0 && colonne>=0 && ligne<grille.length && colonne<grille.length){
            if (grille[ligne][colonne] == ' ' &&  grilleNumerote[ligne][colonne] == 0 ){
                if (this.nbTours%2==0){
                    grille[ligne][colonne] = 'O';
                    grilleNumerote[ligne][colonne] += 1;
                    this.nbTours++;
                    this.tourDuJoueur = 0;
                }else{
                    grille[ligne][colonne] = 'X';
                    grilleNumerote[ligne][colonne] += -1;
                    this.nbTours++;
                    this.tourDuJoueur = 1;
                }
                this.derniereLigneJoue = ligne;
                this.derniereColonneJoue = colonne;
                refreshInformation();
                verifVictoire();
                verifFin();
            }else{
                if (this.nbTours % 2 == 0) {
                    System.out.println("Penalité joueur O");
                    penaliteO++;
                } else {
                    System.out.println("Penalité joueur X");
                    penaliteX++;
                }
            }
        }else{
            if (this.nbTours % 2 == 0) {
                System.out.println("Penalité joueur O");
                penaliteO++;
            } else {
                System.out.println("Penalité joueur X");
                penaliteX++;
            }
        }
    }

    private void refreshInformation(){
        for (IA ia:listeIA) {
            ia.setDerniereCoord(this.derniereLigneJoue,this.derniereColonneJoue);
            ia.setGrille(this.grille);
        }
    }

    private void verifFin(){
        this.grilleFini = true;
        for(int i = 0;i<grille.length;i++){
            for (int j = 0;j<grille.length;j++){
               if (grille[i][j] == ' '){
                   this.grilleFini = false;
               }
            }
        }
    }


    private void verifVictoire(){

        int sommeColonne = 0;
        int sommeLigne = 0;
        int sommeDiag1 = 0;
        int sommeDiag2 = 0;

        for (int x = 0;x<grille.length;x++){

            sommeDiag1 += grilleNumerote[x][x];
            sommeDiag2 += grilleNumerote[x][2-x];

            for (int y = 0;y< grille.length;y++){
                sommeLigne   += grilleNumerote[x][y];
                sommeColonne += grilleNumerote[y][x];
            }
            if (    abs(sommeLigne/3)   == 1 ||
                    abs(sommeColonne/3) == 1 ||
                    abs(sommeDiag1/3)   == 1 ||
                    abs(sommeDiag2/3)   == 1 ){

                this.grilleGagne = true;
            }
            sommeLigne = 0;
            sommeColonne = 0;
        }

    }

    public String toString() {
        String retour = "";

        if (!this.grilleGagne){
            retour += "Tour n°"+this.nbTours+"\n";
        }else{
            retour += "Le joueur " + (this.nbTours%2 == 0 ? "X" : "O") + " l'emporte\n";
        }
        for (int i=1;i<8;i++){
            if (i%2 != 0){
                retour += "+----+----+----+\n";
            }else{
                retour +=("| "+grille[(i/2)-1][0]+"  | "+grille[(i/2)-1][1]+"  | "+grille[(i/2)-1][2]+"  |\n");
            }
        }

        return retour;
    }


    public int getPenaliteO() {
        return penaliteO;
    }

    public int getPenaliteX() {
        return penaliteX;
    }

    public boolean isGrilleGagne() {
        return grilleGagne;
    }

    public boolean isGrilleFini() {
        return grilleFini;
    }

    public int getTourDuJoueur() {
        return tourDuJoueur;
    }

    public ArrayList<IA> getListeIA() {
        return listeIA;
    }
}
