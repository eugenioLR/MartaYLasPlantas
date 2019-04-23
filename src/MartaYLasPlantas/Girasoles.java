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
public class Girasoles extends Planta {

    private int magia;

    public Girasoles(int salud, int ataque, int magia) {
        super(salud, ataque);
        this.magia = magia;
        setCoste(20);
    }

    @Override
    public void actualizar() {
        magia += 1;
    }
}
