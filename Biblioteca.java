import java.util.*;
import java.time.*;
import java.time.format.*;
public class Biblioteca extends Thread{
    private List<Carte> carti = new ArrayList<>();
    private List<Client> clienti = new ArrayList<>();
    private Client clientCurent;

    public Client getClientCurent() {
        return clientCurent;
    }

    public void setClientCurent(Client clientCurent) {
        this.clientCurent = clientCurent;
    }

    private static Biblioteca SINGLETON;
    
    private Biblioteca() {}
    
    public static Biblioteca getInstance() {
        if(SINGLETON == null) {
            SINGLETON = new Biblioteca();
        }
        return SINGLETON;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Numarul de carti la acest moment este: " + carti.size());
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void aflaClient(String nume) {
        if(nume.equals(null)){
            System.out.println("Utilizatorul trebuie sa fie logat");
        } else{
            clientCurent = clienti.stream().filter(t -> t.getNume().equals(nume)).findFirst().get();
        }
    }
    
    public void adaugaClient(Client c) {
        try {
            if(clienti.stream().filter(t -> t.getNume().equals(c.getNume())).findFirst().isPresent()) {
                throw new NumeDejaExistentException("Clientul cu acest nume deja exista!!!");
            } else {
                clienti.add(c);
            }
        } catch (Exception e) {
            System.out.println("Se incearca introducerea unui utilizator deja existent");
        }
    }
    
    public void afiseazaClienti() {
        if(clienti.size() == 0) {
            System.out.println("Nu exista clienti de afisat");
        }
        clienti.forEach(System.out::println);
    }
    
    public void afiseazaStudenti() {
        if(clienti.size() == 0) {
            System.out.println("Nu exista studenti de afisat");
        }
        for(Client c: clienti) {
            if(c instanceof Student) {
                System.out.println(c);
            }
        }
    }
    
    public void adaugaCarte(Carte c) {
        carti.add(c);
    }
    
    public void afiseazaCarti() {
        if(carti.size() == 0) {
            System.out.println("Nu exista carti de afisat");
        }
        carti.forEach(System.out::println);
    }
    
    public void afiseazaCartiDisponibile() {
        if(carti.size() == 0) {
            System.out.println("Nu exista carti de afisat");
        }
        for(Carte c: carti) {
            if(c.isImprumutata() == false) {
                System.out.println(c);
            }
        }
    }
    
    public void cautaCarte(String titlu) {
        if(carti.size() == 0) {
            System.out.println("Nu exista carti de cautat");
        }
        for(Carte c: carti) {
            if(c.getTitlu().equals(titlu)) {
                System.out.println(c);
                break;
            }
        }
    }
    
    public void filtreazaCartiDupaGen(String gen) {
        if(carti.size() == 0) {
            System.out.println("Nu exista carti de filtrat");
        }
        List<Carte> cartiDupaGen = new ArrayList<>();
        for(Carte c: carti) {
            if(c.getGen().compareTo(gen) == 0) {
                cartiDupaGen.add(c);
            }
        }
        
        cartiDupaGen.forEach(System.out::println);
       
    }
    
    public void sorteazaCarti() {
        if(carti.size() == 0) {
            System.out.println("Nu exista carti de sortat");
        }
        Collections.sort(carti);
        carti.forEach(System.out::println);
    }
    
    public void sorteazaClienti() {
        if(clienti.size() == 0) {
            System.out.println("Nu exista clienti de sortat");
        }
        Collections.sort(clienti);
        clienti.forEach(System.out::println);
    }
    
    public void celMaiFidelCititor() {
        if(clienti.size() == 0) {
            System.out.println("Nu exista cel mai fidel cititor");
        }
        Client cititor = Collections.max(clienti, Comparator.comparing(c -> c.getNrCarti()));
        System.out.println("Cel mai fidel cititor este: " + cititor);
    }
    
    public void imprumutaCarte(String cod) throws CarteIndisponibilaException{

        if(carti.size() == 0) {
            System.out.println("Nu exista carti de imprumutat");
            CarteIndisponibilaException e = new CarteIndisponibilaException();
            throw e;
        }

        if(cod.equals(null) || cod.length() == 0 || cod.equals("")) {
            CarteIndisponibilaException e = new CarteIndisponibilaException();
            throw e;
        }

        int ok = 0;

        for(Carte c: carti) {

            if(c.getCod().equals(cod)) {
                ok = 1;
                if(c.isImprumutata() == false) {
                    clientCurent.setDataRetur(genereazaDataRandom(2021,2030).toString());
                    c.getProprietari().add(clientCurent);
                    c.setImprumutata(true);
                    break;
                } else {
                CarteIndisponibilaException e = new CarteIndisponibilaException();
                throw e;
               
                }    
            }

        }

        if(ok == 0) {
            CarteIndisponibilaException e = new CarteIndisponibilaException();
            throw e;
        }
    }
    
    public static int genereazaDataRandomIntre(int inceput, int sfarsit) {
        return inceput + (int) Math.round(Math.random() * (sfarsit - inceput));
    }
    
    public static LocalDate genereazaDataRandom(int anInceput, int anSfarsit) {
        int zi = genereazaDataRandomIntre(1, 28);
        int luna = genereazaDataRandomIntre(1, 12);
        int an = genereazaDataRandomIntre(anInceput, anSfarsit);
        return LocalDate.of(an, luna, zi);
    }
    
    public void returneazaCarte(String cod) {
        for(Carte c: carti) {
            if(c.getCod().equals(cod)) {
                c.setImprumutata(false);
                break;
            }
        }
    }
    
    public void stergeCarte(String titlu) {
        for(int i = 0; i < carti.size(); i++) {
            if(carti.get(i).getTitlu().equals(titlu)) {
                carti.remove(carti.get(i));
            }
        }
    }
    
    public void stergeClient(String nume) {
        for(int i = 0; i < clienti.size(); i++) {
            if(clienti.get(i).getNume().equals(nume)) {
                clienti.remove(clienti.get(i));
            }
        }
    }
    
    public boolean arePenalitati(String nume) {
        boolean existaPenalitati = false;
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(Client c: clienti) {
            if(c.getNume().equals(nume)) {
                if(date.format(formatter).compareTo(c.getDataRetur()) > 0) {
                    existaPenalitati = true;
                    System.out.println("Are penalitati");
                    break;
                } else {
                    System.out.println("Nu are penalitati");
                    break;
                }
            }
        }
        
        return existaPenalitati;
    }

    public void verificareIstoricCarte(String cod) {
        for(Carte carte: carti) {
            if(carte.getCod().equals(cod)) {
                System.out.println("Numarul de proprietari este: " + carte.getProprietari().size() + " Acestia sunt: " + carte.getProprietari());
            }
        }

    }
}