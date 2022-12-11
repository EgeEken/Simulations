public class Ressource
{
    private static int nbRessourcesCreees;
    public final int ident;
    public final String type;
    private int quantite;
    private int x;
    private int y;
    
    public Ressource(final String type, final int quantite) {
        this.type = type;
        this.quantite = quantite;
        this.ident = Ressource.nbRessourcesCreees++;
        this.x = -1;
        this.y = -1;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getQuantite() {
        return this.quantite;
    }
    
    public void setQuantite(final int quantite) {
        this.quantite = quantite;
    }
    
    public void setPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void initialisePosition() {
        this.x = -1;
        this.y = -1;
    }
    
    @Override
    public String toString() {
        final String string = this.type + "[id:" + this.ident + " quantite: " + this.quantite + "] ";
        String s;
        if (this.x == -1 || this.y == -1) {
            s = string + " n'est pas sur le terrain.";
        }
        else {
            s = string + " en position (" + this.x + ", " + this.y + ")";
        }
        return s;
    }
    
    static {
        Ressource.nbRessourcesCreees = 0;
    }
}