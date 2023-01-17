package listas;


/**
 * Write a description of class COLA here.
 * Primer dato en entrar y primero en salir
 * FIFO
 * @author (your name) 
 * @version (a version number or a date)
 */
public class COLA{
    private TpDato [] datos;
    private int num;
    
    public COLA(int n){
        this.datos=new TpDato[n];
        this.num=0;
    }
    
    public boolean colaVacia(){
        return this.num==0;
    }

    public boolean colaLlena(){
        return this.num==this.datos.length;
    }

    public void meter(TpDato dato){
        if(!this.colaLlena()){
            this.datos[this.num]=dato;
            this.num+=1;
        }
    }

    public TpDato sacar(){
        TpDato apoyo = null;
        if(!this.colaVacia()){
            apoyo=this.datos[0];
            for(int i=0; i<this.num-1; i++){
                this.datos[i]=this.datos[i+1];
            }
            this.num-=1;
        }
        return apoyo;
    }
}
