public abstract class IA {

    protected char[][] grille         = new char[3][3];
    protected int derniereLigneJoue   = -1;
    protected int derniereColonneJoue = -1;

    protected int ligne = -1;
    protected int colonne = -1;

    public abstract void run();

    public void setGrille(char[][] grille) {
        this.grille = grille;
    }

    public void setDerniereCoord(int derniereLigneJoue, int derniereColonneJoue ){
        this.derniereLigneJoue = derniereLigneJoue;
        this.derniereColonneJoue = derniereColonneJoue;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
