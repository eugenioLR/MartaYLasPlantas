/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas.Veganos;

/**
 *
 * @author EDGENP Eugenio Lorente Darius Tamas
 */
public class VeganoComun extends Vegano {

    //cambio. Nueva clase padre
    
    /**
     * Constructor de Vegano. {@inheritDoc}
     */
    public VeganoComun(int turno) {
        super(5, 1, turno, 2);
    }
    
    public VeganoComun(int salud,int turno) {
        super(salud, 1, turno, 2);
    }

}
