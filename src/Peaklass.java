import java.util.*;

public class Peaklass {
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

    /**
     * Meetod paneb tegelase vastasega võitlema üheks käiguks
     * @param mängija - mängija tegelane
     * @param vastane - hetke vastane
     */
    private static void Võitlus(Mängija mängija, Vastane vastane) {
        if (mängija.getRelv() == null) { // Kui mängijal ei ole relva, siis ründab käega
            if (mängija.isElus()) { // kui mängija on elus, siis ründa
                mängija.ründa(vastane); // rünnak
                System.out.println("Vastasel on järgi " + vastane.getElud() + " elu");
                if (vastane.isElus()) { // Kui vastane on elus, siis ründab vastu
                    vastane.ründa(mängija);
                    System.out.println("Sul on järgi " + mängija.getElud() + " elu");
                }
            }
        }
        else { // Kui mängijal on relv, siis ründa relvaga
            if (mängija.isElus()) {
                mängija.ründa(vastane, mängija.getRelv()); // rünnak relvaga
                System.out.println(vastane.getNimi() + " elud on " + vastane.getElud());
                if (vastane.isElus()) {
                    vastane.ründa(mängija);
                    System.out.println("Sul on järgi " + mängija.getElud() + " elu");
                }
            }
        }
    }

    /**
     * Leiab suvalise elemendi sõnelistis
     * @return - tagastab elemendi
     */
    public static String RandElem(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String[] args) {
        /** MÄNGIJA LOOMINE */
        Mängija m1 = new Mängija("Player 1", 40, 20, 5,4,3);
        m1.setKaitserüü(new Kaitserüü(0,"Tavalised Riided"));

        /** VARUSTUSE LOOMINE JA LISAMINE VARUSTUSE LISTI */
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

        Random suvalineArv = new Random();

        /** VASTASTE NIMEDE LOOMINE */
        List<String> nimed = Arrays.asList("Rott", "Luukere", "Vampiir", "Orc", "Bandiit", "Maag", "Maasööjauss");

        /** KOOPA RUUMIDE LOOMINE JA SUVALISES JÄRJESTUSES KOOPASE PAIGUTAMINE*/
        List<Koobas> koobas = new ArrayList<>();
        Aarderuum aarderuum = new Aarderuum("Aarderuum");
        Lõksuruum lõksuruum = new Lõksuruum(new Lõks(Juhuslik.randint(5,10),60,false));
        int raskus = 1;
        Võitlusruum võitlusruum1 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Krüpt");
        Võitlusruum võitlusruum2 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Vana Raamatukogu");
        Võitlusruum võitlusruum3 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Labor");
        Lõpuruum lõpuruum = new Lõpuruum(new Vastane("Maailma Hävitaja Võlur",100,13,5),"Viimane Tuba");
        koobas.add(aarderuum);
        koobas.add(võitlusruum1);
        koobas.add(võitlusruum2);
        koobas.add(võitlusruum3);
        koobas.add(lõksuruum);
        Collections.shuffle(koobas);
        koobas.add(lõpuruum);
        /** MÄNG ALGAB */

        Scanner sc= new Scanner(System.in); // Skänner sisendi küsimiseks
        int ruumiNumber = 0; // Ruuminumber koopas liikumiseks

        System.out.println("Sisenesid koopasse");
        while (true) {
            System.out.println("Mida soovid teha?:");
            System.out.println("Liigu edasi: 1");
            System.out.println("Vaata kaarti: 2");
            System.out.println("Vaata oma varustust ja andmeid: 3");
            int tegevus = sc.nextInt();
            if (tegevus == 1) { // Algab tegevustega, saad edasi liikuda, kaarti vaadata ja oma varustust vaadata
                System.out.println("Liikusid ruumi: "+koobas.get(ruumiNumber));
                Vastane vastane = koobas.get(ruumiNumber).getVastane(); // Hetkel oleva ruumi vastane
                if(vastane != null) { // Kui vastane on olemas, siis algab temaga võitlus
                    if (ruumiNumber != koobas.size()-1) { // Suurendab vastase raskust peale igat võidetud vastast, kuid mitte lõpubossi raskust
                        vastane.setElud(vastane.getElud() * raskus);
                        vastane.setTugevus(vastane.getTugevus() * raskus);
                        vastane.setKaitse((raskus - 1));
                    }
                    System.out.println("Kohtasid vastast: " + vastane.getNimi() + " Elud=" + vastane.getElud());
                    while (vastane.isElus() && m1.isElus()){ // Võitlus käib nii kaua, kuni vastane või mängija sureb
                        System.out.println("Ründa: 1");
                        if (ruumiNumber != koobas.size()-1) System.out.println("Põgene (Võtad kahju): 2"); // Variandid: ründa, põgene, ründa maagiaga
                        System.out.println("Ründa maagiaga (Kindel tabamus, kasutab mana): 3");
                        tegevus = sc.nextInt();
                        if (tegevus == 1) {
                            Võitlus(m1,vastane); // Üks käik mängija vs vastane
                            m1.taastaMana(3); // Taastab 3 mana peale käiku
                        }
                        else if (tegevus == 2) { // Põgenemine, vastane ründab sind korra ja põgened ruumist
                            System.out.println("Põgenedes võtsid kahju:");
                            vastane.ründa(m1);
                            System.out.println("Sul on järgi " + m1.getElud() + " elu");
                            break;
                        }
                        else if (tegevus == 3) { // Maagiaga rünnak, läheb kindlalt pihta
                            System.out.println("Sisesta kui palju mana soovid kasutada rünnakuks: " + "praegu on sul "+ m1.getMana() + " mana");
                            tegevus = sc.nextInt();
                            m1.ründaMaagiaga(vastane, tegevus);
                            System.out.println("Vastasel on järgi " + vastane.getElud() + " elu");
                            System.out.println("Sul on järgi " + m1.getElud() + " elu");
                        }
                    }
                    if (!vastane.isElus()) { // Kui vastane saab surma, siis saad elusi ja mana tagasi ja saad vastaselt varustust
                        System.out.println("Võitsid!");
                        vastane.sureb(varustuseList, m1);
                        m1.taastaElusid(4);
                        m1.taastaMana(3);
                        System.out.println("Said natuke elusid ja mana tagasi");
                        System.out.println("Elusid järgi:" + m1.getElud());
                        raskus++; // raskus suureneb
                    }
                    if (!m1.isElus()) { // Mängija surm
                        System.out.println("Said surma, mäng läbi!");
                        break;
                    }
                    ruumiNumber++;
                }
                else {
                    if (koobas.get(ruumiNumber) instanceof Lõksuruum) { // Kui sattusid lõksuruumi, siis võimalus, et lõks aktiveerub
                        System.out.println("Sattusid lõksuruumi!");
                        ((Lõksuruum) koobas.get(ruumiNumber)).getLõks().aktiveerumine(m1);
                        ruumiNumber++;
                    }
                    else if (koobas.get(ruumiNumber) instanceof Aarderuum) { // Kui sattusid aarderuumi, siis saad ühe asja juurde ja saad valida, kas soovid oma hetkel olevat varustust muuta
                        System.out.println("Liikusid ruumi, mis on täis erinevat varustust ja kirste!");
                        aarderuum.lisaVarustus(varustuseList.getKõikAsjad().get(suvalineArv.nextInt(varustuseList.getKõikAsjad().size())));
                        if(((Aarderuum) koobas.get(ruumiNumber)).getAsi() instanceof Kaitserüü) { // Kaitserüü muutmine
                            System.out.println("Leidsid ruumist" + ((Aarderuum) koobas.get(ruumiNumber)).getAsi());
                            m1.vahetaRüüd((Kaitserüü) ((Aarderuum) koobas.get(ruumiNumber)).getAsi());
                        }
                        else if (((Aarderuum) koobas.get(ruumiNumber)).getAsi() instanceof Relv) { // Relva muutmine
                            System.out.println("Leidsid ruumist" + ((Aarderuum) koobas.get(ruumiNumber)).getAsi());
                            m1.vahetaRelva((Relv) ((Aarderuum) koobas.get(ruumiNumber)).getAsi());
                        }
                        ruumiNumber++;
                    }
                }
            }
            else if (tegevus == 2) { // Kaardi vaatamine
                System.out.println("Vaatad kaarti ja näed tube:");
                System.out.println(koobas);
            }
            else if (tegevus == 3) { // Varustuse vaatamine. Erinevad tingimused, et kui mingi varustus on olemas
                System.out.println("Vaatad oma varustust:");
                if (m1.getRelv() != null) System.out.println("Elud="+m1.getElud()+" Tugevus="+(m1.getTugevus()+m1.getRelv().getRünnak())+" Mana="+m1.getMana()+" mis taastub "+m1.getManaTaastumine()+" võrra");
                else System.out.println("Elud="+m1.getElud()+" Tugevus="+m1.getTugevus()+" Mana="+m1.getMana()+" mis taastub "+m1.getManaTaastumine()+" võrra");
                if (m1.getKaitserüü() == null) System.out.println("Varustus | " + m1.getRelv() + " ja " + "kaitserüü puudub");
                else if (m1.getRelv() == null) System.out.println("Varustus | " + "relv puudub" + " ja " + m1.getKaitserüü());
                else if (m1.getKaitserüü() == null && m1.getRelv() == null) System.out.println("Varustus | " + "relv puudub" + " ja " + "kaitserüü puudub");
                else System.out.println("Varustus | " + m1.getRelv() + " ja " + m1.getKaitserüü());
            }
            if (ruumiNumber == koobas.size()-1) { // Enne bossi ruumi saad elud tagasi
                System.out.println("Enne viimast tuba puhkad ja saad kõik elud tagasi");
                m1.setElud(40);
            }
            if (ruumiNumber == koobas.size() && !lõpuruum.bossElus()) { // Kui boss on surnud ja on viimane ruum, siis mäng läbi
                break;
            }
        }
        if (m1.isElus()) System.out.println("Palju õnne! Oled võitnud!!!");
    }
}
