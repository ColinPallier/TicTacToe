public class MonIA extends IA{

    private int compteur = 1;

    public MonIA() {

    }

    @Override
    public void run() {
        if (verifPremierCoup()){
            this.colonne = 1;
            this.ligne = 1;
        }else {
            this.ligne = aleatoire();
            this.colonne = aleatoire();

            while (!verifCase(this.ligne,this.colonne)){
                this.ligne = aleatoire();
                this.colonne = aleatoire();
            }
        }

    }

    private boolean verifPremierCoup(){
        return this.derniereColonneJoue == -1 && this.derniereLigneJoue == -1;
    }

    private boolean verifCase(int ligne, int colonne){
        return this.grille[ligne][colonne] == ' ';
    }

    private int aleatoire(){
        return (int)(Math.random() * 3);
    }
}
