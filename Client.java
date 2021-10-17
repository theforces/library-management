public abstract class Client implements Comparable<Client>{
   private String cod;
   private String nume;
   private int nrCarti;
   private String dataRetur;
   
   public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNrCarti() {
        return nrCarti;
    }

    public void setNrCarti(int nrCarti) {
        this.nrCarti = nrCarti;
    }

    public String getDataRetur() {
        return dataRetur;
    }

    public void setDataRetur(String dataRetur) {
        this.dataRetur = dataRetur;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof Client) {
            Client c = (Client)o;
            return this.getNume().equals(c.getNume());
        }
        return false;
    }
    
    @Override
    public int compareTo(Client c) {
        return this.getNume().compareTo(c.getNume());
    }
    
    @Override
    public String toString() {
        return "Client " +this.getNume()+" "+this.getCod()+" "+this.getNrCarti()+" "+this.getDataRetur();
    }
    
}