public abstract class PlanteQuiDortPas extends Plante
{
    public PlanteQuiDortPas(int x, int y)
    {
        super(x,y);
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }
    
    public abstract int[] secreteNectar();

}