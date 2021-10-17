import java.util.List;

public class Carte implements Comparable<Carte>{
    private String cod;
    private String titlu;
    private String autor;
    private String gen;
    private int nrPagini;
    private boolean imprumutata;
    private List<Client> proprietari;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public int getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(int nrPagini) {
        this.nrPagini = nrPagini;
    }

    public boolean isImprumutata() {
        return imprumutata;
    }

    public void setImprumutata(boolean imprumutata) {
        this.imprumutata = imprumutata;
    }

    public List<Client> getProprietari() {
        return proprietari;
    }

    public void setProprietari(List<Client> proprietari) {
        this.proprietari = proprietari;
    }

    @Override
    public int compareTo(Carte c) {
        return this.nrPagini - c.nrPagini;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof Carte) {
            Carte c = (Carte)o;
            return this.getTitlu().equals(c.getTitlu());
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Carte "+titlu+" "+cod+" "+autor+" "+gen+" "+nrPagini+" "+imprumutata;
    }
}