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
    
    public Lanzadora(int salud, int ataque) {
        super(salud, ataque);
        setCoste(50);
    }
    
    @Override
    public void actualizar(){
        //disparar
    }
}
