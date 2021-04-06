import java.util.*;

public class Peaklass {
    /*
    public static void main(String[] args) {
        Mängija m1 = new Mängija("Player 1", 20, 10, 5, 2,1);
        Vastane v1 = new Rott(6, 4, 2);
        Ogad lõks1 = new Ogad(8,40, true);


        automaatneVõitlus(m1, v1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");

        m1.puhka();
        System.out.println("Sul on alles " + m1.getElud() + " elu");


        lõks1.aktiveerumine(m1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");

    } */
    private static void automaatneVõitlus(Mängija mängija, Vastane vastane) {
        while (true) {
            if (mängija.isElus()) {
                mängija.ründa(vastane);
                System.out.println("Vastasel on järgi " + vastane.getElud() + " elu");
            } else break;
            if (vastane.isElus()) {
                vastane.ründa(mängija);
                System.out.println("Sul on järgi " + mängija.getElud() + " elu");
            } else break;
        }
    }
    private static void Võitlus(Mängija mängija, Vastane vastane) {
        if (mängija.getRelv() == null) {
            if (mängija.isElus()) {
                mängija.ründa(vastane);
                System.out.println(vastane.getNimi() + " elud on " + vastane.getElud());
                if (vastane.isElus()) {
                    vastane.ründa(mängija);
                    System.out.println("Sul on järgi " + mängija.getElud() + " elu");
                }
            }
        }
        else {
            if (mängija.isElus()) {
                mängija.ründa(vastane, mängija.getRelv());
                System.out.println(vastane.getNimi() + " elud on " + vastane.getElud());
                if (vastane.isElus()) {
                    vastane.ründa(mängija);
                    System.out.println("Sul on järgi " + mängija.getElud() + " elu");
                }
            }
        }
    }

    public static String RandElem(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String[] args) {
        /*
        Mängija m1 = new Mängija("Player 1", 20, 10, 5, 2,1);
        Vastane v1 = new Rott(6, 4, 2);
        Ogad lõks1 = new Ogad(8,40, true);


        automaatneVõitlus(m1, v1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");

        m1.puhka();
        System.out.println("Sul on alles " + m1.getElud() + " elu");


        lõks1.aktiveerumine(m1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");
        */


        Mängija m1 = new Mängija("Player 1", 20, 10, 5,2,1);
        m1.setKaitserüü(new Kaitserüü(0,"Tavalised Riided"));

        /** VARUSTUSE LISAMINE */
        Varustus varustuseList = new Varustus();

        varustuseList.lisaAsi(new Relv(3, "Katkine Mõõk"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Kööginuga"));
        varustuseList.lisaAsi(new Relv(1, "Kahvel"));
        varustuseList.lisaAsi(new Relv(10, "Legendaarne Mõõk"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Vibu"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Kirves"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Oda"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Võlurikepp"));

        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Mõrane Raudrüü"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Kantud Nahkvest"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Käekaitsmed"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Maagiline müts"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Sõdurisaapad"));
        varustuseList.lisaAsi(new Kaitserüü(10, "Kuldne Rüü"));

        System.out.println(varustuseList.getKõikAsjad());
        Random suvalineArv = new Random();

        /** VASTASTE NIMED */
        List<String> nimed = Arrays.asList("Rott", "Luukere", "Vampiir");

        /** KOOPA RUUMID */
        List<Koobas> koobas = new ArrayList<>();
        Aarderuum aarderuum = new Aarderuum("Aarderuum");
        Lõksuruum lõksuruum = new Lõksuruum(new Lõks(Juhuslik.randint(5,10),60,false));
        int raskus = 1;
        Võitlusruum võitlusruum1 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20)*raskus,Juhuslik.randint(1,5)*raskus,(raskus-1)*2),"Krüpt");
        Võitlusruum võitlusruum2 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20)*raskus,Juhuslik.randint(1,5)*raskus,(raskus-1)*2),"Vana Raamatukogu");
        Võitlusruum võitlusruum3 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20)*raskus,Juhuslik.randint(1,5)*raskus,(raskus-1)*2),"Labor");
        Lõpuruum lõpuruum = new Lõpuruum(new Vastane("Maailma Hävitaja Võlur",100,30,10),"ViimaneTuba");
        koobas.add(aarderuum);
        koobas.add(võitlusruum1);
        koobas.add(võitlusruum2);
        koobas.add(võitlusruum3);
        koobas.add(lõksuruum);
        Collections.shuffle(koobas);
        koobas.add(lõpuruum);
        /** MÄNG ALGAB */

        boolean käib = true;
        Scanner sc= new Scanner(System.in);
        int ruumiNumber = 0;

        System.out.println("Sisenesid koopasse");
        while (käib) {
            System.out.println("Mida soovid teha?:");
            System.out.println("Liigu edasi: 1");
            System.out.println("Vaata kaarti: 2");
            System.out.println("Vaata oma varustust ja andmeid: 3");
            int tegevus = sc.nextInt();
            if (tegevus == 1) {
                System.out.println("Liikusid ruumi: "+koobas.get(ruumiNumber));
                Vastane vastane = koobas.get(ruumiNumber).getVastane();
                if(vastane != null) {
                    System.out.println("Kohtasid vastast: " + vastane.getNimi() + " Elud=" + vastane.getElud());
                    while (vastane.isElus() && m1.isElus()){
                        System.out.println("Ründa: 1");
                        System.out.println("Põgene (Võtad kahju): 2");
                        tegevus = sc.nextInt();
                        if (tegevus == 1) { Võitlus(m1,vastane); }
                        if (tegevus == 2) {
                            System.out.println("Põgenedes võtsid kahju:");
                            vastane.ründa(m1);
                            System.out.println("Sul on järgi " + m1.getElud() + " elu");
                            break;
                        }
                    }
                    if (!vastane.isElus()) {
                        System.out.println("Võitsid!");
                        vastane.sureb(varustuseList, m1);
                        m1.taastaElusid(4);
                        m1.taastaMana(1);
                        System.out.println("Said natuke elusid ja mana tagasi");
                        System.out.println("Elusid järgi:" + m1.getElud());
                        raskus += 2;
                    }
                    if (!m1.isElus()) {
                        System.out.println("Said surma, mäng läbi!");
                        break;
                    }
                    ruumiNumber++;
                }
                else {
                    if (koobas.get(ruumiNumber) instanceof Lõksuruum) {
                        System.out.println("Sattusid lõksuruumi!");
                        ((Lõksuruum) koobas.get(ruumiNumber)).getLõks().aktiveerumine(m1);
                        ruumiNumber++;
                    }
                    else if (koobas.get(ruumiNumber) instanceof Aarderuum) {
                        aarderuum.lisaVarustus(varustuseList.getKõikAsjad().get(suvalineArv.nextInt(varustuseList.getKõikAsjad().size())));
                        if(((Aarderuum) koobas.get(ruumiNumber)).getAsi() instanceof Kaitserüü) {
                            m1.vahetaRüüd((Kaitserüü) ((Aarderuum) koobas.get(ruumiNumber)).getAsi());
                        }
                        else if (((Aarderuum) koobas.get(ruumiNumber)).getAsi() instanceof Relv) {
                            m1.vahetaRelva((Relv) ((Aarderuum) koobas.get(ruumiNumber)).getAsi());
                        }
                        ruumiNumber++;
                    }
                }



            }
            if (tegevus == 2) {
                System.out.println("Vaatad kaarti ja näed tube:");
                System.out.println(koobas);
            }
            if (tegevus == 3) {
                System.out.println("Vaatad oma varustust:");
                System.out.println("Elud="+m1.getElud()+" Tugevus="+m1.getTugevus()+" Mana="+m1.getMana()+" mis taastub "+m1.getManaTaastumine()+" võrra");
                if (m1.getKaitserüü() == null) System.out.println("Sul on | " + m1.getRelv() + " ja " + "kaitserüü puudub");
                else if (m1.getRelv() == null) System.out.println("Sul on | " + "relv puudub" + " ja " + m1.getKaitserüü());
                else if (m1.getKaitserüü() == null && m1.getRelv() == null) System.out.println("Sul on | " + "relv puudub" + " ja " + "kaitserüü puudub");
                else System.out.println("Sul on | " + m1.getRelv() + " ja " + m1.getKaitserüü());
            }
        }
    }
}
