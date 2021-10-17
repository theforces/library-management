import java.util.*;
public class Aplicatie {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = Biblioteca.getInstance();
        biblioteca.start();
        while(true) {
            System.out.println("Introdu comanda: ");
            String cmd = scanner.nextLine();
            String [] cuv = cmd.split("\\s+");
            switch(cuv[0]) {
                case "exit": System.out.println("se inchide aplicatia");
                             System.exit(0);
                case "autentificaUser": try {
                                                biblioteca.aflaClient(cuv[1]);
                                                System.out.println("A fost autentificat user-ul pentru imprumutare si verificare istoric");
                                                break;
                                        } catch (Exception e){
                                                System.out.println("Trebuie introdus un user valid");
                                                break;
                                         }
                case "adaugaStudent": Student s = new Student();
                                      s.setCod(cuv[1]);
                                      s.setNume(cuv[2]);
                                      s.setNrCarti(Integer.parseInt(cuv[3]));
                                      s.setDataRetur(cuv[4]);
                                      s.setFacultate(cuv[5]);
                                      s.setAnStudiu(Integer.parseInt(cuv[6]));
                                      biblioteca.adaugaClient(s);
                                      System.out.println("A fost adaugat un student!");
                                      break;
                case "adaugaProfesor": Profesor p = new Profesor();
                                       p.setCod(cuv[1]);
                                       p.setNume(cuv[2]);
                                       p.setNrCarti(Integer.parseInt(cuv[3]));
                                       p.setDataRetur(cuv[4]);
                                       p.setMaterie(cuv[5]);
                                       biblioteca.adaugaClient(p);
                                       System.out.println("A fost adaugat un profesor!");
                                       break;
                case "afiseazaClienti": biblioteca.afiseazaClienti();
                                        break;
                case "afiseazaStudenti": biblioteca.afiseazaStudenti();
                                        break;
                case "adaugaCarte": Carte c = new Carte();
                                    c.setCod(cuv[1]);
                                    c.setTitlu(cuv[2]);
                                    c.setAutor(cuv[3]);
                                    c.setGen(cuv[4]);
                                    c.setNrPagini(Integer.parseInt(cuv[5]));
                                    c.setImprumutata(Boolean.parseBoolean(cuv[6]));
                                    c.setProprietari(new ArrayList<>());
                                    biblioteca.adaugaCarte(c);
                                    System.out.println("A fost adaugata o carte!");
                                    break;
                case "afiseazaCarti": biblioteca.afiseazaCarti();
                                      break;
                case "afiseazaCartiDisponibile" : biblioteca.afiseazaCartiDisponibile();
                                                  break;
                case "cautaCarte": biblioteca.cautaCarte(cuv[1]);
                                   System.out.println("A fost cautata o carte!");
                                   break;
                case "filtreazaCartiDupaGen": biblioteca.filtreazaCartiDupaGen(cuv[1]);
                                              System.out.println("Au fost filtrate cartile dupa gen!");
                                              break;
                case "sorteazaCarti": biblioteca.sorteazaCarti(); 
                                      System.out.println("Au fost sortate cartile dupa nr de pagini");
                                      break;
                case "sorteazaClienti": biblioteca.sorteazaClienti();
                                        System.out.println("Au fost sortati clientii dupa nume!");
                                        break;
                case "celMaiFidelCititor": biblioteca.celMaiFidelCititor();
                                           System.out.println("S-a afisat cel mai fidel cititor");
                                           break;
                case "imprumutaCarte": try{
                                            try {
                                                if(cuv[1].equals(null) || cuv[1].length() == 0 || cuv[1].equals("")) {
                                                    System.out.println("Codul introdus nu e valid");
                                                    break;
                                                }
                                            } catch (ArrayIndexOutOfBoundsException e) {
                                                System.out.println("Se acceseaza ceva ce nu exista");
                                                break;
                                            }

                                            if(biblioteca.getClientCurent() == null) {
                                                System.out.println("Clientul nu e autentificat");
                                                break;
                                            } else {
                                                biblioteca.imprumutaCarte(cuv[1]);
                                                System.out.println("A fost imprumutata o carte");
                                                break;
                                            }

                                       } catch(CarteIndisponibilaException e) {
                                           System.out.println("Cartea aceasta nu se poate imprumuta");
                                           break;
                                       }
                case "returneazaCarte": biblioteca.returneazaCarte(cuv[1]);
                                        System.out.println("A fost returnata cartea");
                                        break;
                case "stergeCarte": biblioteca.stergeCarte(cuv[1]);
                                    System.out.println("A fost stearsa o carte");
                                    break;
                case "stergeClient": biblioteca.stergeClient(cuv[1]);
                                     System.out.println("A fost sters un client");
                                     break;
                case "arePenalitati": biblioteca.arePenalitati(cuv[1]);
                                      System.out.println("S-a verificat daca are penalitati");
                                      break;
                case "verificareIstoricCarte": biblioteca.verificareIstoricCarte(cuv[1]);
                                               System.out.println("S-a verificat istoricul cartii");
                                               break;
                default:  System.out.println("A fost introdusa o comanda necunoscuta!");
            }
    }
    }
    
}