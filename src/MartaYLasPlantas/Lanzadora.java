/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

/**
 *
 * @author EDGENP
 */
public class Lanzadora extends Planta{
    
    public Lanzadora(int salud, int ataque,int dificultad , int coste) {
        super(salud, ataque, dificultad, coste);
    }
    
    @Override
    public void actualizar(){
        //disparar
    }
}
