/*
Implementacion Máquina Enigma modelo Enigma I 
con rotores de los tipos I, II, III, utilizando reflector B 

Por elssbbboy
 */
package enigmacryptography;

public class Rotor {
    private String contenido;
    private String contPrev;
    private int posicion;
    private int tam;
    private char notch;
    
    /**
     * Creacion de rotor
     * @param con Contenido
     * @param notch Punto de giro
     */
    public Rotor(String con, char notch){
        contenido = con;
        posicion = 0;
        tam = con.length();
        contPrev = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.notch = notch;
    }
    
    /**
     * Creacion de rotor
     * @param con Contenido
     * @param pos Posicion
     * @param cP Contenido a traducir
     * @param notch Punto de giro
     */
    public Rotor(String con, int pos, String cP, char notch){
        contenido = con;
        tam = con.length();
        posicion = pos % tam;
        contPrev = cP;
        this.notch = notch;
    }
    
    /**
     * Giro del rotor
     * @param desplazamiento numero de posiciones a desplazar
     * @param haciadelante booleano que marca si se hace hacia delante
     * @return 
     */
    public Rotor giro(int desplazamiento, boolean haciadelante){
        if(!haciadelante){
            desplazamiento = tam - desplazamiento;
        }
        int nuevaPos = tam + desplazamiento;
        if(desplazamiento > tam){
            desplazamiento = tam % desplazamiento;
            nuevaPos = tam % nuevaPos;
        }
        String aux = "";
        String cP = "";
        for(int i = 0; i < tam; i++){
            if(i + desplazamiento < tam){
                aux += contenido.charAt(i + desplazamiento);
                cP += contPrev.charAt(i + desplazamiento);
            } else{
                aux += contenido.charAt(i + desplazamiento - tam);
                cP += contPrev.charAt(i + desplazamiento - tam);
            }
        }
        return new Rotor(aux, nuevaPos, cP, notch);
    }
    
    /**
     * Obtiene la posicion
     * @return Posicion actual 
     */
    public int obtenerPosicion(){
        return posicion;
    }
    
    /**
     * Obtiene el contenido
     * @return Contenido actual
     */
    public String obtenerContenido(){
        return contenido;
    }
    
    /**
     * Cifra la letra, antes del reflector
     * @param i indice de la letra a cifrar
     * @return indice de la letra cifrada
     */
    public int x_a_y(int i){
        char c = contenido.charAt(i);
        int ind = contPrev.indexOf(c);
        return ind;
    }
    
    /**
     * Cifra la letra, inverso, despues del reflector
     * @param i indice de la letra a cifrar
     * @return indice de la letra cifrada
     */
    public int y_a_x(int i){
        char c = contPrev.charAt(i);
        int ind = contenido.indexOf(c);
        return ind;
    }
    
    /**
     * Obtiene punto de cambio - giro
     * @return Punto de cmabio
     */
    public char obtenerNotch(){
        return notch;
    }
    
}
