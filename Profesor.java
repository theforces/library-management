public class Profesor extends Client {
    private String materie;
    
    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }
    
    @Override
    public String toString() {
        return "Profesor "+this.getNume()+" "+this.getCod()+" "+this.getNrCarti()+" "+this.getDataRetur()+" "+this.getMaterie();
    }
}