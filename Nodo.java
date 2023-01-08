
/**
 * Write a description of class Nodo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nodo{
    private int ident;
    private int numPasos;
    private double pesoAc;
    
    public Nodo(int id, int tra, double pes){
        this.ident=id;
        this.numPasos=tra;
        this.pesoAc=pes;
    }
    
    public Nodo(Nodo n){
        this.ident=n.ident;
        this.numPasos=n.numPasos;
        this.pesoAc=n.pesoAc;
    }
    
    public int verIdent(){
        return this.ident;
    }
    
    public int verNumPasos(){
        return this.numPasos;
    }
    
    public double verPesoAc(){
        return this.pesoAc;
    }
}
