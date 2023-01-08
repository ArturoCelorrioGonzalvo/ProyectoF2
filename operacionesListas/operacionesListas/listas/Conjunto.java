package listas;

/**
 * Write a description of class Conjunto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Conjunto{
    private TpDato[] datos;
    private int num;

    public Conjunto(int n){
        this.datos=new TpDato[n];
        this.num=0;
    }

    public Conjunto(Conjunto conj){
        this.datos=new TpDato[conj.datos.length];
        for(int i=0; i<conj.num; i++){
            this.datos[i]=new TpDato(conj.datos[i]);
        }
        this.num=conj.num;
    }

    public int numDatos(){
        return this.num;
    }

    public boolean datosLleno(){
        return this.num==this.datos.length;
    }

    public boolean datosVacio(){
        return this.num==0;
    }

    public boolean existeElemento(TpDato dato){
        boolean existe=false;
        int i,s,m;
        if(!this.datosVacio()){
            i=0; s=this.datos.length-1;
            while(i!=s){
                m=(i+s)/2;
                if(dato.ordenadoRespA(this.datos[m]))s=m;
                else i=m;
            }
            existe=dato.igualA(this.datos[i]);
        }
        return existe;
    }

    public void anadeElem(TpDato dato){
        if(!this.datosLleno()&&!this.existeElemento(dato)){
            int i=this.num-1;
            while(i>=0&&!this.datos[i].ordenadoRespA(dato)){
                this.datos[i+1]=this.datos[i];
            }
            this.datos[i+1]=new TpDato(dato);
            this.num+=1;
        }
    }

    public void quitaElem(TpDato dato){
        if(!this.datosVacio()){
            boolean encontrado=false, puedeExistir=true;
            int i=0;
            while(i<this.num&&!encontrado&&puedeExistir){
                encontrado=dato.igualA(this.datos[i]);
                puedeExistir=dato.ordenadoRespA(this.datos[i]);
                i=i-1;
            }
            if(encontrado){
                for(int j=i-1; j<this.num-1; j++){
                    this.datos[j]=this.datos[j+1];
                }
            }
            this.num-=1;
        }
    }

    public Conjunto intersec(Conjunto conj){
        Conjunto res;
        if(this.num>=conj.num){
            res = new Conjunto(this.numDatos());
            for(int i=0; i<this.numDatos(); i++){
                if(conj.existeElemento(this.datos[i])){
                    res.anadeElem(this.datos[i]);
                }
            }
        }
        else {
            res = new Conjunto(conj.numDatos());
            for(int i=0; i<conj.numDatos(); i++){
                if(this.existeElemento(conj.datos[i])){
                    res.anadeElem(conj.datos[i]);
                }
            }
        }
        return res;
    }
    
    public Conjunto unionCon(Conjunto conj){
        Conjunto res = new Conjunto(this);
        res.num=this.numDatos()+conj.numDatos();
        for(int i=0; i<conj.numDatos(); i++)res.anadeElem(conj.datos[i]);
        return res;
    }
}
