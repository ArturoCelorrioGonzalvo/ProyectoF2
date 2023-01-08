package listas;

/**
 * clase para representar un tipo de dato genérico para ser
 * utilizado como tipo de dato base de array en la lista.
 * 
 * @author Alberto Ciriano 
 * @version abril 2018
 */
public class TpDato
{
    private int clave;
    private String info;

    /**
     * Constructores para objetos de clase TpDato
     */
    public TpDato()
    {
        this.clave = 0;
        this.info = "";
    }
    
    
    public TpDato(int c, String inf)
    {
        this.clave = c;
        this.info = new String(inf);
    }
    
    
    public TpDato(TpDato dato)
    {
        this.clave = dato.clave;
        this.info = new String(dato.info);
    }
    
    
    /*
     * Métodos observadores
     */
    public int getC(){
        return this.clave ;
    }
    
    
    public String getI(){
        return this.info ;        
    }
    
    
    /*
     * Métodos modificadores
     */
    public void setC(int c){
        this.clave = c ;
    }
    
    
    public void setI(String i){
        this.info = i ;
    }
    
    
    public String toString(){
        return "Valor clave: " + this.clave +
                " Información: " + this.info;
    }
    
        
    public static TpDato leerDato (){
        int clave ;
        String info;        
        clave = Teclado.leerEntero("Valor clave válido: ");
        info = Teclado.leerCadena("Información adicional: ");        
        return new TpDato(clave,info);
    }
    
    
    public void mostrarDato(){
        System.out.printf("Dato de clave %10d = %20s \n",
                this.getC(), this.getI());
    }
    
    
    /*
     * MÉTODOS PARA COMPARAR DATOS CON ORDEN
     * SEGUN VALOR DE CAMPO CLAVE EN SENTIDO
     * CRECIENTE
     */
    
    public boolean anteriorA (TpDato dato){
        return this.getC() < dato.getC();
        //return this.clave < dato.clave;
    }
    
    
    public boolean igualA (TpDato dato){
        return this.getC() == dato.getC();
        //return this.clave == dato.clave;
    }
    
    //Orden creciente
    public boolean ordenadoRespA (TpDato dato){
        return this.anteriorA(dato) ||
                this.igualA(dato);
    }
    
    //este solo vale si es orden decreciente
    public boolean posteriorA (TpDato dato){
        return this.getC() > dato.getC();
        //return this.clave > dato.clave;
    }
    
    //Orden decreciente
    public boolean ordenadoRespADec(TpDato dato){
        return this.posteriorA(dato) ||
                this.igualA(dato);
    }
    
    
    /*
     * VERSIONES STATIC DE LO ANTERIOR
     */
    
    /**
     * Method anterior --> devuelve el valor true si dato1 va 
     * delante de dato2 segun el criterio de orden de interés
     * y false en caso contrario.
     */
    public static boolean anterior(TpDato dato1,TpDato dato2){
        return dato1.getC() < dato2.getC() ;
        //equivale a:
        //return dato1.clave < dato2.clave ;
    }
    
    
    /**
     * Method iguales --> devuelve un valor booleano que indica
     * si dos datos de los almacenados son iguales o no.
     */
    public static boolean iguales(TpDato dato1,TpDato dato2){
        return dato1.getC() == dato2.getC() ;
        //equivale a:
        //return dato1.clave == dato2.clave ;
    }
    
    
    /**
     * Method ordenados --> devuelve un valor booleano que 
     * indica si dos datos de los almacenados están ordenados
     * o no segun el criterio de orden de interés.
     */
    public static boolean ordenados(TpDato dato1,TpDato dato2){
        return anterior(dato1,dato2) || 
                iguales(dato1,dato2) ;
    }
}
