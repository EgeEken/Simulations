public abstract class PlanteQuiDort extends Plante implements Routine
{
    protected int reveil;
    protected int coucher;

    public PlanteQuiDort(int x, int y, int reveil)
    {
        super(x,y);
        this.reveil = reveil;
        this.coucher = (this.reveil + 11) % 24;
    }

    public PlanteQuiDort(int x, int y)
    {
        this(x, y, (int) (Math.random() * 24));
    }

    public int getReveil()
    {
        return this.reveil;
    }

    public int getCoucher()
    {
        return this.coucher;
    }

    public int getX() 
    {
        return super.getX();
    }

    public int getY() 
    {
        return super.getY();
    }

    public boolean estReveille(int temps)
    {
        if (coucher > reveil) {
            return (temps >= reveil && temps < coucher);
        }
        else {
            return (temps >= reveil || temps < coucher);
        }
    }
    
}