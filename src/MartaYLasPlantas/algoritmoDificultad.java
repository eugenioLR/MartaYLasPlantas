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

        int turnos = 35;
        int vegRest = 5;
        double prioridad = 0.0;
        double prob, pfinal;
        int auxx = vegRest;

        for (int i = 0, k = 0; turnos > i; turnos--, k++) {
            if (auxx != vegRest) {
                k = 0;  // k representa los turnos sin que los zombies hayan aparecido
                prioridad = 0.0;
            }
            if (k > 4) {
                prioridad = Math.random() / 2; // de forma que sumaremos hasta +0.5 de probabilidad.
            }
            auxx = vegRest;
            prob = ((double) vegRest) / ((double) turnos);
            pfinal = prob+prioridad;
            System.out.println(prob + "-" + vegRest + "-" + "prob final: " + pfinal + turnos + " turnos sin zombies " + k);
            if (prob + prioridad >= 1.00) {
                vegRest -= 3 + (int) prob / 1.00;
            } else if (prob + prioridad >= 0.85) {
                vegRest -= 3;
            } else if (prob + prioridad >= 0.7) {
                vegRest -= 2;
            } else if (prob + prioridad >= 0.5) {
                vegRest--;
            }
        }
    }
}
