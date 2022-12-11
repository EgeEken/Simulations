public abstract class Agent 
{
    protected int x;
    protected int y;

    public Agent(int ligne, int colonne)
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
    
    public abstract double distance(int ligne2, int colonne2);
    public abstract void seDeplacer(int lnew, int cnew);

}