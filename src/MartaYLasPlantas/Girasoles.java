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

    public Girasoles(int salud, int ataque, int dificultad, int coste, int magia) {
        super(salud, ataque, dificultad, coste);
        this.magia = magia;
    }

    @Override
    public void actualizar() {
        magia += 1;
    }
}
