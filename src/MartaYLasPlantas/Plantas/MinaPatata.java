/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas.Plantas;

/**
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class MinaPatata extends Planta {

    private boolean Enterrado;

    /**
     * Constructor de MinaPatatas {@inheritDoc}
     */
    public MinaPatata(int turno) {
        super(1, 90, turno);
        this.Enterrado = true;
        //1800 de daño es el oficial que la wikia proporciona, haciendo una 
        //regla de 3 con el daño base que tienen los lanzaguisantes, se obtiene el siguiente  
    }

    public MinaPatata(int salud, int turno) {
        super(salud, 90, turno);
    }
    

    /**
     * Get the value of Enterrado
     *
     * @return the value of Enterrado
     */
    public boolean isEnterrado() {
        return Enterrado;
    }

    /**
     * Set the value of Enterrado
     *
     * @param Enterrado new value of Enterrado
     */
    public void setEnterrado(boolean Enterrado) {
        this.Enterrado = Enterrado;
    }

}
