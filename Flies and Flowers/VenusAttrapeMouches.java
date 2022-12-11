public class VenusAttrapeMouches extends PlanteQuiDort
{
    public static int cpt = 0;
    private int id;

    public VenusAttrapeMouches(int x, int y, int reveil)
    {
        super(x,y, reveil);
        VenusAttrapeMouches.cpt ++;
        this.id = VenusAttrapeMouches.cpt;
    }

    public VenusAttrapeMouches(int x, int y)
    {
        this(x, y, (int) (Math.random() * 24));
    }

    public int getId() {
        return this.id;
    }

    public int getReveil()
    {
        return super.reveil;
    }

    public int getCoucher()
    {
        return super.coucher;
    }
    
    public static int nbVenusAttrapeMouches()
    {
        return VenusAttrapeMouches.cpt;
    }

    public int getX() 
    {
        return super.getX();
    }

    public int getY() 
    {
        return super.getY();
    }
    
    public int[] secreteNectar()
    {
        int[] res = new int[2];
        int newx = (int) (Math.random() * 3) - 1;
        int newy = (int) (Math.random() * 3) - 1;
        if (newx == 0 && newy == 0) {
            newx = (int) (Math.random() * 2) * 2 - 1;
        }
        res[0] = super.getX() + newx;
        res[1] = super.getY() + newy;
        return res;
    }
    
    public boolean estReveille(int temps) {
        return super.estReveille(temps);
    }

    public String toString()
    {
        return "Venus Attrape Mouches " + this.id + " en position (" + super.x + ", " + super.y + ")" + ", reveil à: " + super.reveil + ", dort à: " + super.coucher;
    }

}