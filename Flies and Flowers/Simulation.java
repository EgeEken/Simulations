import java.util.ArrayList;

public class Simulation{

    private int width;
    private int height;
    private Terrain terrain;
    private Object[][] map;
    private ArrayList<Plante> plantes;
    private ArrayList<Agent> agents;
    private ArrayList<Nectar> nectars;
    private int temps = 0;
    private int jours = 0;

    public Simulation(int taillex, int tailley) {
        this.width = taillex;
        this.height = tailley;
        this.terrain = new Terrain(taillex, tailley);
        this.map = new Object[taillex][tailley];
        this.plantes = new ArrayList<Plante>();
        this.agents = new ArrayList<Agent>();
        this.nectars = new ArrayList<Nectar>();
    }

    public Simulation(int taille) {
        this(taille, taille);
    }

    public Simulation() {
        this(10);
    }

    public void ajoutePlante(Plante p) {
        if (this.map[p.getX()][p.getY()] == null && this.terrain.getCase(p.getX(), p.getY()) == null) {
            this.plantes.add(p);
            this.map[p.getX()][p.getY()] = p;
        }
        else {
            System.out.println("Impossible d'ajouter la plante " + p.toString() + " : case occupée");
        }
    }

    public void ajouteAgent(Agent a) {
        if (this.map[a.getX()][a.getY()] == null && this.terrain.getCase(a.getX(), a.getY()) == null) {
            this.agents.add(a);
            this.map[a.getX()][a.getY()] = a;
        }
        else {
            System.out.println("Impossible d'ajouter l'agent " + a.toString() + " : case occupée");
        }
    }

    public int getNbAgent() {
        return this.agents.size();
    }

    public void ajouteNectar(Nectar n) {
        if (this.map[n.getX()][n.getY()] == null && this.terrain.getCase(n.getX(), n.getY()) == null) {
            this.nectars.add(n);
            this.map[n.getX()][n.getY()] = n;
        }
        else {
            System.out.println("Impossible d'ajouter, case occupée!!!");
        }
    }

    public void secreteNectar(Plante p) {
        int[] nectarpos = new int[2];
        nectarpos[0] = 0;
        nectarpos[1] = 0;
        while (true) {
            nectarpos = p.secreteNectar();
            if ((nectarpos[0] >= 0 && nectarpos[0] < this.width) && (nectarpos[1] >= 0 && nectarpos[1] < this.height)) {
                ajouteNectar(new Nectar(nectarpos[0], nectarpos[1]));
                return ;
            }
        }
    }

    public void allSecrete() {
        for (Plante p : plantes) {
            if (p instanceof PlanteQuiDortPas) secreteNectar(p);
            else if (p instanceof PlanteQuiDort) {
                if (((PlanteQuiDort) p).estReveille(this.temps)) secreteNectar(p);
            }
        }
    }

    public void mangerNectar(Nectar n) {
        this.terrain.videCase(n.getX(), n.getY());
        this.map[n.getX()][n.getY()] = null;
        this.nectars.remove(n);
    }

    public void mangerMouche(Mouche m) {
        this.terrain.videCase(m.getX(), m.getY());
        this.map[m.getX()][m.getY()] = null;
        this.agents.remove(m);
    }

    public void bougerMouche(Mouche m) {
        double distmin = 100;
        int[] res = new int[2];
        res[0] = (int) (Math.random()*this.width);
        res[1] = (int) (Math.random()*this.height);

        for (Nectar n : nectars) {
            if (m.distance(n.getX(), n.getY()) < distmin) {
                distmin = m.distance(n.getX(), n.getY());
                res[0] = n.getX();
                res[1] = n.getY();
            }
            if (distmin <= 1) {
                mangerNectar(n);
                this.terrain.videCase(m.getX(), m.getY());
                m.seDeplacer(res[0], res[1]);
                return ;
            }
        }
        if (res[0] > m.getX()) {
            res[0] = m.getX() + 1;
            res[1] = m.getY();
        }
        else if (res[0] < m.getX()) {
            res[0] = m.getX() - 1;
            res[1] = m.getY();
        }
        else if (res[1] > m.getY()) {
            res[1] = m.getY() + 1;
            res[0] = m.getX();
        } 
        else if (res[1] < m.getY()) {
            res[1] = m.getY() - 1;
            res[0] = m.getX();
        } 

        if ((this.map[res[0]][res[1]] instanceof VenusAttrapeMouches) && (((VenusAttrapeMouches) this.map[res[0]][res[1]]).estReveille(this.temps))) {
            mangerMouche(m);
            return ;
        } 
        this.terrain.videCase(m.getX(), m.getY());
        m.seDeplacer(res[0], res[1]);
        return ;
    }



    public void updateTerrain() {

        for (Plante p : plantes) {
            if (p instanceof Tulip) {
                this.terrain.setCase(((Tulip) p).getX(), ((Tulip) p).getY(), new Ressource(" @ ", 1));
            }
            else if (p instanceof VenusAttrapeMouches) {
                if (((VenusAttrapeMouches) p).estReveille(this.temps)) this.terrain.setCase(((VenusAttrapeMouches) p).getX(), ((VenusAttrapeMouches) p).getY(), new Ressource("{=}", 1));
                else this.terrain.setCase(((VenusAttrapeMouches) p).getX(), ((VenusAttrapeMouches) p).getY(), new Ressource(" ()", 1));
            }
            else if (p instanceof CereusBloom) {
                if (((CereusBloom) p).estReveille(this.temps)) this.terrain.setCase(((CereusBloom) p).getX(), ((CereusBloom) p).getY(), new Ressource("---", 1));
                else this.terrain.setCase(((CereusBloom) p).getX(), ((CereusBloom) p).getY(), new Ressource("oOo", 1));
            }
        }


        for (int i = 0; i < agents.size(); i ++) {
            if (agents.get(i) instanceof Mouche) {
                if (((Mouche) agents.get(i)).estReveille(this.temps)) {
                    bougerMouche((Mouche) agents.get(i));
                    if (i < agents.size()) this.terrain.setCase(agents.get(i).getX(), agents.get(i).getY(), new Ressource(">8<", 1));
                }
                else if (i < agents.size()) this.terrain.setCase(agents.get(i).getX(), agents.get(i).getY(), new Ressource("zZz", 1));
            }
        }

        for (Nectar n : nectars) {
            this.terrain.setCase(n.getX(), n.getY(), new Ressource(" o ", 1));
        }
        if (this.temps < 23) this.temps ++;
        else {
            this.temps = 0;
            this.jours ++;
            allSecrete();
        }
    }

    public void affiche() {
        System.out.println("-----------------------------------------------------------------------------------");
        this.terrain.affiche(3);
        System.out.println("Jour: " + this.jours + ", " + this.temps + ":00");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Plante p : plantes) {
            if (p instanceof PlanteQuiDort) {
                if (((PlanteQuiDort) p).estReveille(this.temps)) System.out.println(p.toString() + " REVEILLÉ");
                else System.out.println(p.toString() + " ENDORMIE");
            }
        }
        for (Agent a : agents) {
            if (((Mouche) a).estReveille(this.temps)) System.out.println(a.toString() + " REVEILLÉ");
            else System.out.println(a.toString() + " ENDORMIE");
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public int getTemps() {
        return this.temps;
    }

    public int getJours(){
        return this.jours;
    }

}