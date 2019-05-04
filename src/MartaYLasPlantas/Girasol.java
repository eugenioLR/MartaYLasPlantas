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
public class Girasol extends Planta {

    private static int coste = 20;
    int magia;

    public Girasol(int salud, int ataque, int turno, int magia) {
        super(salud, ataque, turno);
        this.magia = magia;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    
    @Override
    public void actualizar() {
        magia += 1;
    }
}
