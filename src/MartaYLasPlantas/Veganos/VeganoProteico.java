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
public class VeganoProteico extends Vegano {

    /**
     * Constructor de VeganosProteicos (Deportistas) {@inheritDoc}
     */
    public VeganoProteico(int turno) {
        super(2, 1, turno, 1);
    }
    public VeganoProteico(int salud,int turno) {
        super(salud, 1, turno, 1);
    }

}
