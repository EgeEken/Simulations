public class Tulip extends PlanteQuiDortPas {
    
    public static int cpt = 1;
    private int id;

    public Tulip(int x, int y) {
        super(x, y);
        Tulip.cpt++;
        this.id = Tulip.cpt;
    }

    public int[] secreteNectar() {
        int[] res = new int[2];
        int newx = (int) (Math.random() * 3) - 1;
        int newy = (int) (Math.random() * 3) - 1;
        if (newx == 0 && newy == 0) {
            newx = (int) (Math.random() * 2) * 2 - 1;
        }
        res[0] = super.x + newx;
        res[1] = super.y + newy;
        return res;
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public static int nbTulip() {
        return Tulip.cpt;
    }

    public String toString() {
        return "Tulipe " + this.id + " en position (" + super.x + ", " + super.y + ")";
    }

}
