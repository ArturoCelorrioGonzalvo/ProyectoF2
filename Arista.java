import java.util.*;
/**
 * Write a description of class Arista here.
 * 
 * @author (your name)
 * @version (a version)
 */
public class Arista
{
    private int nodo1, nodo2;
    private double peso;

    public Arista(int n1, int n2, double p){
        this.nodo1=n1;
        this.nodo2=n2;
        this.peso=p;
    }

    public Arista(Arista a){
        this.nodo1=a.nodo1;
        this.nodo2=a.nodo2;
        this.peso=a.peso;
    }
    
    public Arista(){
        this.nodo1=0;
        this.nodo2=0;
        this.peso=0.0;
    }

    /**
     * Observadores de los parámetros
     */
    public int verN1(){
        return this.nodo1;
    }

    public int verN2(){
        return this.nodo2;
    }

    public double verPeso(){
        return this.peso;
    }

    /**
     * Modificadores de los parámetros
     */

    private void setN1(int n){
        this.nodo1=n;
    }

    private void setN2(int n){
        this.nodo2=n;
    }

    public void setPeso(double p){
        this.peso=p;
    }
    
    public boolean aristaVacia(){
        return this.nodo1==0&&this.nodo2==0;
    }

    /**
     * Metodo para incializar una arista
     */
    public static Arista inicioArista(){
        Random azar = new Random();
        int n1=azar.nextInt(10)+1;
        int n2=azar.nextInt(10)+1;
        double p=azar.nextInt(101)/100.0;
        return new Arista(n1, n2, p);
    }
    
    public static Arista leerArista(){
        int n1, n2;
        double p;
        do n1=Teclado.leerEntero("¿Cuál es el nodo incial?");while(n1<0);
        do n2=Teclado.leerEntero("¿Cuál es el nodo final?");while(n2<0);
        do p=Teclado.leerReal("¿Cuál es el peso? Real mayor o igual que 0");while(p<0);
        return new Arista(n1,n2,p);
    }

    public boolean anteriorA(Arista ar){
        return this.verN1()<ar.verN1()||(this.verN1()==ar.verN1()&&
        this.verN2()<ar.verN2());
    }

    public boolean igualA(Arista ar){
        return this.nodo1==ar.nodo1 && this.nodo2 == ar.nodo2;
    }
    
    public boolean ordenadoRespA(Arista ar){
        return this.igualA(ar)||this.anteriorA(ar);
    }
}
