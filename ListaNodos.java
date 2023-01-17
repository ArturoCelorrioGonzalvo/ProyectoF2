import java.util.*;
/**
 * Write a description of class ListaNodos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaNodos
{
    private Nodo [] nodos;
    private int numNodos;

    public ListaNodos(int n){
        this.nodos = new Nodo [n];
        for(int i=0; i<n; i++){
            this.nodos[i]=new Nodo();
        }
        this.numNodos = 0;
    }

    public ListaNodos(ListaNodos lista){
        this.nodos = lista.nodos;
        this.numNodos = lista.numNodos;
    }

    public int ver (int n){
        return this.nodos[n].verIdent();
    }

    public Nodo verNodo(int n){
        return this.nodos[n];
    }

    public int cuantos(){
        return this.numNodos;
    }

    public void anadeNodo(Nodo n){
        if(this.numNodos!=this.nodos.length){
            if(this.numNodos==0){
                this.nodos[0]=new Nodo(n);
                this.numNodos++;
            }else{
                int i = this.numNodos-1;
                int posTeorica = this.busquedaPosDico(n);
                if(posTeorica!=-1&&this.nodos[posTeorica].verPesoAc()>=n.verPesoAc()){
                    this.nodos[posTeorica]=new Nodo(n);
                }else{
                    while(i>=0 && !this.nodos[i].ordenadoRespA(n)){
                        this.nodos[i+1]=this.nodos[i];
                        i=i-1;
                    }
                    this.nodos[i+1]=new Nodo(n);
                    this.numNodos++;   
                }
            }
        }else{
            this.amplia();
            if(this.numNodos==0){
                this.nodos[0]=new Nodo(n);
                this.numNodos++;
            }else{
                int i = this.numNodos-1;
                int posTeorica = this.busquedaPosDico(n);
                if(posTeorica!=-1&&this.nodos[posTeorica].verPesoAc()>=n.verPesoAc()){
                    this.nodos[posTeorica]=new Nodo(n);
                }else{
                    while(i>=0 && !this.nodos[i].ordenadoRespA(n)){
                        this.nodos[i+1]=this.nodos[i];
                        i=i-1;
                    }
                    this.nodos[i+1]=new Nodo(n);
                    this.numNodos++;   
                }
            }
        }
    }

    public void amplia(){
        Nodo [] copia = new Nodo [this.nodos.length + 10];
        for(int i = 0; i < this.numNodos; i++){
            copia [i] = this.nodos[i];
        }
        this.nodos = copia;
    }

    public void anadir(ListaNodos lista){

        //suponiendo que haya hueco
        for(int i = 0; i < lista.numNodos; i++){
            int j = 0;
            boolean puesto = false;
            while(j < this.numNodos && !puesto){
                if (this.nodos[j].verIdent() == lista.nodos[i].verIdent()){
                    if(this.nodos[j].verNumPasos() == lista.nodos[i].verNumPasos()){
                        if(this.nodos[j].verPesoAc() > lista.nodos[i].verPesoAc()){
                            this.nodos[j] = lista.nodos[i];
                        }
                    }else if(this.nodos[j].verNumPasos() > lista.nodos[i].verNumPasos()){
                        this.nodos[j] = lista.nodos[i];
                    }
                    puesto = true;
                }else if(this.nodos[j].verIdent() > lista.nodos[i].verIdent()){
                    puesto = true;
                    this.hazHueco(j);
                    this.nodos[j] = lista.nodos[i];
                    this.numNodos ++;
                }
                j++;
            }
        }
    }
    
    public ListaNodos fusion(ListaNodos otra){
        ListaNodos res = new ListaNodos(this.numNodos+otra.numNodos);
        int i=0, j=0;

        while(i<this.numNodos&&j<otra.numNodos){
            
            if(this.nodos[i].ordenadoRespA(otra.nodos[j])){
                res.anadeNodo(this.nodos[i]);
                i++;
            }else{
                res.anadeNodo(otra.nodos[j]);
                j++;
            }
        }

        for(int l=j; l<otra.numNodos; l++){
            res.anadeNodo(otra.nodos[l]);
        }

        for(int m=i; m<this.numNodos; m++){
            res.anadeNodo(this.nodos[m]);
        }

        return res;
    }

    public boolean anadirPeso (Nodo nodo){
        //suponiendo que haya hueco
        boolean anadido = false, posible = true;
        int i = 0;
        while(posible && i < this.numNodos){
            if (this.nodos[i].verIdent() == nodo.verIdent()){
                if(this.nodos[i].verPesoAc() == nodo.verPesoAc()){
                    if(this.nodos[i].verNumPasos() > nodo.verNumPasos()){
                        this.nodos[i] = nodo;
                        anadido = true;
                    }
                }else if(this.nodos[i].verPesoAc() > nodo.verPesoAc()){
                    this.nodos[i] = nodo;
                    anadido = true;
                }
                posible = false;
            }else if(this.nodos[i].verIdent() > nodo.verIdent()){
                posible = false;
                anadido = true;
                this.hazHueco(i);
                this.nodos[i] = nodo;
                this.numNodos ++;
            }
        }
        return anadido;
    }

    private void hazHueco (int n){
        if (this.nodos.length > this.numNodos){
            for(int i = this.numNodos; i >= n; i--){
                this.nodos[i+1] = this.nodos[i];
            }
        }
    }

    public boolean distinta (ListaNodos otra){
        if(this.numNodos != otra.numNodos){
            return true;
        }else{
            boolean distinto = false;
            int carro = 0;
            while(!distinto && carro < this.numNodos){
                distinto = !this.nodos[carro].igualA(otra.nodos[carro]); 
                carro ++;
            }
            return distinto;
        }
    }

    public boolean distinta (ListaNumeros otra){
        if(this.numNodos != otra.cuantos()){
            return true;
        }else if(this.numNodos==otra.cuantos()&&this.numNodos==0){
            return false;
        }else{
            boolean distinto = false;
            int carro = 0;
            while(!distinto && carro < this.numNodos){
                distinto = !this.nodos[carro].igualA(otra.ver(carro)); 
                carro ++;
            }
            return distinto;
        }
    }

    private void quitar(ListaNodos otra){
        for(int i=0; i<otra.numNodos; i++){
            this.quitar(otra.nodos[i]);
        }
    }

    public void quitar(ListaNumeros otra){
        for(int i = 0; i < otra.cuantos(); i++){
            boolean quitado = false;
            int j = 0;
            while(!quitado && j < this.numNodos){
                if(otra.ver(i) == this.nodos [j].verIdent()){
                    quitado = true;
                    this.quitaPos(j);
                }
                j++;
            }
        }
    }

    public void quitar(Nodo nod){
        int pos = this.busquedaPosDico(nod);
    }

    private void quitaPos (int n){
        for(int i = n; i < this.numNodos; i++){
            this.nodos [i] = this.nodos [i + 1];
        }
        this.numNodos --;
    }

    /**
     * Escribe la lista
     */
    public void escribirLista(){
        for(int i=0; i<this.numNodos; i++){
            System.out.printf(Locale.ENGLISH,
                "El nodo alcanzado es: %1d \t La trayectoria es de: %2d unidades \t El peso acumulado es: %3$.3f \n",
                this.nodos[i].verIdent(),
                this.nodos[i].verNumPasos(), 
                this.nodos[i].verPesoAc());
        }
    }

    public void ordenarLista(){
        Nodo apoyo;
        for(int i=1; i<this.numNodos; i++){
            apoyo=this.nodos[i];
            int j=i;
            while(j>0&&this.nodos[j-1].verIdent()>apoyo.verIdent()){
                this.nodos[j]=this.nodos[j-1];
                j-=1;
            }
            this.nodos[j]=apoyo;
        }
    }

    public int busquedaPosDico(Nodo nodo){
        int indice = -1;
        int i, m, s;

        if (this.numNodos!=0) {
            i = 0 ;
            s = this.numNodos - 1;
            while (i != s) {
                m = ( i + s ) / 2 ;
                if (nodo.ordenadoRespA(this.nodos[m]))
                    s = m ;
                else
                    i = m + 1 ;
            }
            if (nodo.igualA(this.nodos[i].verIdent()))
                indice = i ;
        }

        return indice;
    }

}