public class TestSimulation {
    public static void main(String[] args) {
        
        Simulation s = new Simulation(10);

        VenusAttrapeMouches v1 = new VenusAttrapeMouches(2, 3);
        VenusAttrapeMouches v2 = new VenusAttrapeMouches(5, 3);
        VenusAttrapeMouches v3 = new VenusAttrapeMouches(9, 3);
        VenusAttrapeMouches v4 = new VenusAttrapeMouches(0, 2);
        VenusAttrapeMouches v5 = new VenusAttrapeMouches(8, 1);
        VenusAttrapeMouches v6 = new VenusAttrapeMouches(1, 7);
        VenusAttrapeMouches v7 = new VenusAttrapeMouches(5, 7);
        VenusAttrapeMouches v8 = new VenusAttrapeMouches(0, 6);

        Tulip t1 = new Tulip(5,5);
        Tulip t2 = new Tulip(9,7);
        Tulip t3 = new Tulip(7,3);

        CereusBloom c1 = new CereusBloom(1,1);
        CereusBloom c2 = new CereusBloom(3,8);
        CereusBloom c3 = new CereusBloom(8, 2);

        s.ajoutePlante(v1);
        s.ajoutePlante(v2);
        s.ajoutePlante(v3);
        s.ajoutePlante(v4);
        s.ajoutePlante(v5);
        s.ajoutePlante(v6);
        s.ajoutePlante(v7);
        s.ajoutePlante(v8);

        s.ajoutePlante(t1);
        s.ajoutePlante(t2);
        s.ajoutePlante(t3);

        s.ajoutePlante(c1);
        s.ajoutePlante(c2);
        s.ajoutePlante(c3);

        Mouche m1 = new Mouche(4,4);
        Mouche m2 = new Mouche(8,8);
        Mouche m3 = new Mouche(m2);

        s.ajouteAgent(m1);
        s.ajouteAgent(m2);
        s.ajouteAgent(m3);

        Nectar n1 = new Nectar(0, 0);
        Nectar n2 = new Nectar(0, 9);

        s.ajouteNectar(n1);
        s.ajouteNectar(n2);

        s.affiche();
        try{
            Thread.sleep(500);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        while (s.getNbAgent() > 0) {
            s.updateTerrain();
            s.affiche();
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        if (s.getNbAgent() == 0) System.out.println("La simulation se termine après " + s.getJours() + " jours et " + s.getTemps() + " heures");
    }
}