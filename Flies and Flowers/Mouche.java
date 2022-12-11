public class Mouche extends Agent implements Routine{
    
    private int reveil; // heure de reveil, heure de dormir va etre (reveil + 11) % 24
    private int coucher; // heure de coucher
    public static int cpt = 0;
    private int id;

    public Mouche(int reveil, int x, int y) {
        super(x, y);
        Mouche.cpt ++;
        this.id = Mouche.cpt;
        this.reveil = reveil;
        this.coucher = (reveil + 11) % 24;
    }

    public Mouche(int x, int y) {
        this((int) (Math.random() * 24), x, y);
    }

    public Mouche(Mouche m) {
        this(m.reveil, m.x + ((int) (Math.random() * 2)) * 2 - 1, m.y + ((int) (Math.random() * 2)) * 2 - 1);
    }

    public int getReveil() {
        return this.reveil;
    }

    public int getCoucher() {
        return this.coucher;
    }

    public int getX() {
        return super.x;
    }

    public int getY() {
        return super.y;
    }

    public static int nbMouche(){
        return Mouche.cpt;
    }

    public double distance(int x2, int y2) {
        return Math.sqrt((x2 - super.x) * (x2 - super.x)  + (y2 - super.y) * (y2 - super.y));
    }

    public void seDeplacer(int newx, int newy) {
        super.x = newx;
        super.y = newy;
    }

    public boolean estReveille(int temps) 
    {
        if (this.coucher > this.reveil) {
            return (temps >= this.reveil && temps < this.coucher);
        }
        else {
            return (temps >= this.reveil || temps < this.coucher);
        }
    }

    public String toString()
    {
        return "Mouche " + this.id + " en position (" + super.x + ", " + super.y + ")" + ", reveil à: " + this.reveil + ", dort à: " + this.coucher;
    }
    
}

