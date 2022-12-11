public abstract class Plante {

    protected int x;
    protected int y;

    public Plante(int ligne, int colonne)
    {
        this.x = ligne;
        this.y = colonne;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public abstract int[] secreteNectar();

}