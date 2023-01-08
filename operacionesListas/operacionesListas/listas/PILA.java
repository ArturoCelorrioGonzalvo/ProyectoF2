package listas;

/**
 * Write a description of class PILA here.
 * Equivalente a una pila de platos
 * LIFO o Stack
 * LIFO:Last In First Out: Los últimos serán los primeros
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PILA{
    private TpDato [] datos;
    private int num;

    public PILA(int n){
        this.datos=new TpDato[n];
        this.num=0;
    }

    public boolean pilaVacia(){
        return this.num==0;
    }

    public boolean pilaLlena(){
        return this.num==this.datos.length;
    }

    public void apilar(TpDato dato){
        if(!this.pilaLlena()){
            this.datos[this.num]=dato;
            this.num+=1;
        }
    }

    public TpDato desapilar(){
        TpDato apoyo = null;
        if(!this.pilaVacia()){
            apoyo=this.datos[this.num-1];
            this.num-=1;
        }
        return apoyo;
    }
}
