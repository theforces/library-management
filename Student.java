public class Student extends Client {
   private String facultate;
   private int anStudiu;
   
    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public int getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(int anStudiu) {
        this.anStudiu = anStudiu;
    }
    
    @Override
    public String toString(){
        return "Student "+this.getNume()+" "+this.getCod()+" "+this.getNrCarti()+" "+this.getDataRetur()+" "+this.getFacultate()+" "+this.getAnStudiu(); 
    }
}