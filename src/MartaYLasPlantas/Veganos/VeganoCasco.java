/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas.Veganos;

/**
 *
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class VeganoCasco extends Vegano{
    /**
     * Constructor de VeganoCasco
     * {@inheritDoc}
     */
    public VeganoCasco(int turno) {
        super(8, 1, turno, 3);
    }

    public VeganoCasco(int salud,int turno) {
        super(salud, 1, turno, 3);
    }
    
    
}
