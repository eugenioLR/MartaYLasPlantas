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
public class algoritmoDificultad {

    public static void main(String[] args) {

        int turnos = 30;
        int vegRest = 5;
        double prioridad = 0.0;
        double prob, pfinal;
        int auxx = vegRest;

        for (int i = 0, k = 0; turnos > i; turnos--, k++) {
            if (auxx != vegRest) {
                k = 0;  // k representa los turnos sin que los zombies hayan aparecido
                prioridad = 0.0;
            }
            if (k > 0) {
                prioridad = Math.random(); // de forma que sumaremos hasta +0.5 de probabilidad.
            }
            auxx = vegRest;
            prob = ((double) vegRest) / ((double) turnos)/2;
            pfinal = prob + prioridad;
            System.out.println(prob + "-" + "vegRest " + vegRest + "-" + "prob final: " + pfinal + " turnos sin zombies " + k);
            if (pfinal >= 1.00 && vegRest >= 4) {
                vegRest -= 3 + (int) pfinal;
            } else if (pfinal >= 0.85 && vegRest >= 3) {
                vegRest -= 3;
            } else if (pfinal >= 0.7 && vegRest >= 2) {
                vegRest -= 2;
            } else if (pfinal >= 0.5 && vegRest >= 1) {
                vegRest--;
            }
        }
    }
}
