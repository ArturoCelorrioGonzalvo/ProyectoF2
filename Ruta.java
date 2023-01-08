
/**
 * provisional.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ruta
{
    private Arista [] pasos;
    private int numPasos, objetivo;
    private double peso;
    
    public Ruta (){
        this.pasos = new Arista [10];
        this.numPasos = 0;
        this.objetivo = 0;
        this.peso = 0;
    }
    
    public void anade (Arista arista){
        if(this.numPasos < pasos.length){
            this.pasos [numPasos] = arista;
            this.numPasos ++;
            this.objetivo = arista.verN2();
            this.peso = this.peso + arista.verPeso();
        }else{
            Arista [] copia = new Arista [this.pasos.length + 10];
            for(int i = 0; i < this.numPasos; i++){
                copia [i] = this.pasos [i];
            }
            this.pasos = copia;
            this.pasos [numPasos] = arista;
            this.numPasos ++;
            this.objetivo = arista.verN2();
            this.peso = this.peso + arista.verPeso();
        }
    }
    
    public void atras (){
        this.numPasos --;
    }
    
    public boolean menor (Ruta otra){
        if (this.numPasos == otra.numPasos){
            return this.peso <= otra.peso;
        }else{
            return this.numPasos < otra.numPasos;
        }
    }
    
    public int verNumPasos (){
        return this.numPasos;
    }
    
    public int verObjetivo (){
        return this.objetivo;
    }
    
    public double verPeso (){
        return this.peso;
    }
}
