/*
Implementacion Máquina Enigma modelo Enigma I 
con rotores de los tipos I, II, III, utilizando reflector B 

Por elssbbboy
 */
package enigmacryptography;

import java.util.ArrayList;

public class ConexionClavijas {
    private ArrayList<Character> clavijas = new ArrayList<Character>(); // Todas las clavijas
    private ArrayList<Character> clavijasLibres = new ArrayList<Character>(); // Clavijas disponibles
    private ArrayList<ParClavijas> conexiones = new ArrayList<ParClavijas>(); // Conexiones realizadas
    
    /**
     * Crear panel de clavijas
     */
    public ConexionClavijas(){
        // el panel de clavijas disponibles, mejor con numeros, pero para ejemplo mas visual
        String panel = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        for(int i=0;i<panel.length();i++){
            char a = panel.charAt(i);
            clavijas.add(a);
            clavijasLibres.add(a);
        }
    }
    
    /**
     * Adicion de conexion al panel
     * @param a Caracter a
     * @param b Caracter b
     * @return Devuelve 0 si exito
     */
    public int establecerConexion(char a, char b){
        if(clavijas.contains(a) && clavijas.contains(b)){
            if(clavijasLibres.contains(a) && clavijasLibres.contains(b)){
                conexiones.add(new ParClavijas(a,b));
                for(int i=0;i<clavijasLibres.size();i++){
                    char aux = clavijasLibres.get(i);
                    if(a == aux || b == aux){
                        clavijasLibres.remove(i);
                    }
                }
            } 
            else {
                System.out.println("Una de las clavijas no esta libre");
                return -1;
            }
        } 
        else {
            System.out.println("Una de las clavijas no existe");
            return -1;
        }
        return 0;
    }
    
    /**
     * Devuelve indice de la conexion
     * @param a Caracter a
     * @param b Caracter b
     * @return Indice de la conexion si exito, -1 si no esta
     */
    public int encontrarConexion(char a, char b){
        for(int i=0;i<conexiones.size();i++){
            ParClavijas aux = conexiones.get(i);
            if((aux.getX() == a && aux.getY() == b) || (aux.getX() == b && aux.getY() == a)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Elimina conexion del panel de clavijas
     * @param a Caracter a
     * @param b Caracter b
     * @return 0 si exito, -1 si no se encuentra
     */
    public int eliminarConexion(char a, char b){
        int index = encontrarConexion(a,b);
        if(index == -1)
            return -1;
        
        if(index!=-1){
            clavijasLibres.add(a);
            clavijasLibres.add(b);
            conexiones.remove(index);
        } else {
            System.out.println("No existe la conexion a eliminar");
            return -1;
        }
        return 0;
    }
}
