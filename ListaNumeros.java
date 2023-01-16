
/**
 * Write a description of class ListaNumeros here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaNumeros
{
    private int [] numeros;
    private int numElementos;
    
    public ListaNumeros (){
        this.numeros = new int [10];
        this.numElementos = 0;
    }
    
    public ListaNumeros (ListaNumeros otra){
        this.numeros = new int [otra.numeros.length];
        this.numElementos = otra.numElementos;
        for(int i = 0; i < otra.numElementos; i++){
            this.numeros [i] = otra.numeros [i];
        }
    }
    
    public void anadir (int n){
        if(this.lleno()){
            this.amplia();
            this.anadeOrdenado(n);
        }else{
            this.anadeOrdenado(n);
        }
    }
    
    private boolean lleno (){
        return this.numElementos == this.numeros.length;
    }
    
    private void amplia (){
        int [] copia = new int [this.numeros.length + 10];
        for(int i = 0; i < this.numElementos; i++){
            copia [i] = this.numeros [i];
        }
        this.numeros = copia;
    }
    
    /**
     * Fusion
     */
    public void anadir (ListaNumeros otra){
        if(this.distinta(otra)){
            for(int i = 0; i < otra.numElementos; i++){
                this.anadir(otra.numeros [i]);
            }
        }
    }
    
    private void anadeOrdenado (int n){
        int pos = this.numElementos;
        this.numeros [pos] = n;
        boolean duplicado = false;
        while(pos > 0 && !duplicado && this.numeros [pos - 1] > n){
            if (this.numeros [pos - 1] == n){
                duplicado = true;
            }else{
                this.numeros [pos] = this.numeros [pos - 1];
                this.numeros [pos - 1] = n;
                pos --;
            }
        }
        this.numElementos ++;
        if(duplicado){
            this.quitaPos(pos);
        }
    }
    
    public void anadeSinRep (int n){
        int i = 0;
        boolean puesto = false;
        while(i < this.numElementos && !puesto){
            puesto = this.numeros[i] == n;
            if(this.numeros[i] > n){
                this.hazHueco(i);
                this.numeros[i] = n;
                this.numElementos++;
            }
        }
    }
    
    private void hazHueco (int n){
        if(!this.lleno()){
            for(int i = this.numElementos + 1; i > n; i--){
                this.numeros[i] = this.numeros [i - 1];
            }
        }else{
            this.amplia();
            for(int i = this.numElementos + 1; i > n; i--){
                this.numeros[i] = this.numeros [i - 1];
            }
        }
    }
    
    private void quitaPos (int n){
        for(int i = n; i < this.numElementos; i++){
                this.numeros [i] = this.numeros [i + 1];
        }
        this.numElementos --;
    }
    
    public void quitar (int n){
        boolean encontrado = false;
        int carro = -1;
        while (!encontrado && carro < this.numElementos){
            carro ++;
            encontrado = this.numeros [carro] == n;
        }
        if(encontrado){
            this.quitaPos(carro);
        }
    }
    
    /**
     * Filtro
     */
    public ListaNumeros diferencia (ListaNumeros otra){
        ListaNumeros res = new ListaNumeros(this);
        res.quitar(otra);
        return res;
    }
    
    public void quitar (ListaNumeros otra){
        for(int i = 0; i < otra.numElementos; i++){
            this.quitar(otra.numeros[i]);
        }
    }
    
    public boolean distinta (ListaNumeros otra){
        if(this.numElementos != otra.numElementos){
            return true;
        }else{
            boolean distinto = false;
            int carro = 0;
            while(!distinto && carro < this.numElementos){
                distinto = this.numeros [carro] != otra.numeros [carro]; 
                carro ++;
            }
            return distinto;
        }
    }
    
    public int cuantos (){
        return this.numElementos;
    }
    
    public int ver(int pos){
        if (this.numElementos >= pos){
            return this.numeros[pos];
        }else{
            return -1;
        }
    }
}






