
/**
 * Provisional;
 * Esta clase sirve para crear una lista con las rutas para alcanzar todos los nodos posibles desde uno dado,
 * las rutas guardadas seran las mas optimas, (menor longitud > menor peso)
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaRutas
{
    private Ruta [] rutas;
    private int numAlcanzables;
    
    public ListaRutas (){
        this.rutas = new Ruta [10];
        this.numAlcanzables = 0;
    }
    
    public void anade (Ruta ruta){
        boolean repe = false;
        int i = -1;
        while (!repe && i > this.numAlcanzables){
            i++;
            repe = this.rutas[i].verObjetivo() == ruta.verObjetivo();
        }
        if (repe){
            if (!rutas[i].menor(ruta)){
                this.rutas[i] = ruta;
            }
        }else if (this.numAlcanzables < this.rutas.length){
            this.rutas[this.numAlcanzables] = ruta;
            this.numAlcanzables ++;
        }else{
            Ruta [] copia = new Ruta [this.rutas.length + 10];
            for(int j = 0; j < this.numAlcanzables; j++){
                copia [j] = this.rutas [j];
            }
            this.rutas = copia;
            this.rutas [this.numAlcanzables] = ruta;
            this.numAlcanzables ++;
        }
    }
}
