import java.util.ArrayList;

public final class Terrain
{
    public static final int NBLIGNESMAX = 20;
    public static final int NBCOLONNESMAX = 20;
    public final int nbLignes;
    public final int nbColonnes;
    private Ressource[][] terrain;
    
    public Terrain() {
        this(20, 20);
    }
    
    public Terrain(final int nbLignes, final int nbColonnes) {
        if (nbLignes > 20) {
            this.nbLignes = 20;
        }
        else if (nbLignes <= 0) {
            this.nbLignes = 1;
        }
        else {
            this.nbLignes = nbLignes;
        }
        if (nbColonnes > 20) {
            this.nbColonnes = 20;
        }
        else if (nbColonnes <= 0) {
            this.nbColonnes = 1;
        }
        else {
            this.nbColonnes = nbColonnes;
        }
        this.terrain = new Ressource[this.nbLignes][this.nbColonnes];
    }
    
    public Ressource getCase(final int n, final int n2) {
        if (this.sontValides(n, n2)) {
            return this.terrain[n][n2];
        }
        return null;
    }
    
    public Ressource videCase(final int n, final int n2) {
        if (this.sontValides(n, n2) && this.terrain[n][n2] != null) {
            final Ressource ressource = this.terrain[n][n2];
            ressource.initialisePosition();
            this.terrain[n][n2] = null;
            return ressource;
        }
        return null;
    }
    
    public boolean setCase(final int n, final int n2, final Ressource ressource) {
        if (this.sontValides(n, n2)) {
            if (this.terrain[n][n2] != null) {
                this.terrain[n][n2].initialisePosition();
            }
            (this.terrain[n][n2] = ressource).setPosition(n, n2);
            return true;
        }
        return false;
    }
    
    public boolean caseEstVide(final int n, final int n2) {
        return !this.sontValides(n, n2) || this.terrain[n][n2] == null;
    }
    
    public ArrayList<Ressource> lesRessources() {
        final ArrayList<Ressource> list = new ArrayList<Ressource>();
        for (int i = 0; i < this.nbLignes; ++i) {
            for (int j = 0; j < this.nbColonnes; ++j) {
                if (this.terrain[i][j] != null) {
                    list.add(this.terrain[i][j]);
                }
            }
        }
        return list;
    }
    
    public boolean sontValides(final int n, final int n2) {
        return n >= 0 && n < this.nbLignes && n2 >= 0 && n2 < this.nbColonnes;
    }
    
    public void affiche(final int a) {
        String string = ":";
        String string2 = "";
        final int max = Math.max(a, 1);
        for (int i = 0; i < max; ++i) {
            string2 += "-";
        }
        for (int j = 0; j < this.nbColonnes; ++j) {
            string = string + string2 + ":";
        }
        String s;
        final String str = s = string + "\n";
        for (int k = 0; k < this.nbLignes; ++k) {
            for (int l = 0; l < this.nbColonnes; ++l) {
                if (this.terrain[k][l] == null) {
                    s = s + "|" + String.format("%-" + max + "s", " ");
                }
                else {
                    s = s + "|" + this.premiersCar(this.terrain[k][l].type, max);
                }
            }
            s = s + "|\n" + str;
        }
        System.out.println(s);
    }
    
    @Override
    public String toString() {
        int i = 0;
        for (int j = 0; j < this.nbLignes; ++j) {
            for (int k = 0; k < this.nbColonnes; ++k) {
                if (this.terrain[j][k] != null) {
                    ++i;
                }
            }
        }
        final String string = "Terrain de " + this.nbLignes + "x" + this.nbColonnes + " cases: ";
        String s;
        if (i == 0) {
            s = string + "toutes les cases sont libres.";
        }
        else if (i == 1) {
            s = string + "il y a une case occupée.";
        }
        else {
            s = string + "il y a " + i + " cases occupées.";
        }
        return s;
    }
    
    private String premiersCar(final String s, final int n) {
        return String.format("%-" + n + "s", s).substring(0, n);
    }
}

