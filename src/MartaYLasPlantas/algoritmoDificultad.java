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
        int vegRest;
        double prob, pfinal;
        int auxx;
        int vegFinal;
        int dificultad = 1;
        int descanso;
        int boost;
        double probabilidad = 0;
        double ajuste; //ajuste controla el rango en el cual nusetra probabilidad random puede generarse

        switch (dificultad) {
            case 1:
                descanso = 10;
                vegRest = 5;
                ajuste = 1.5;
                boost = 5;
                break;
            case 2:
                descanso = 7;
                vegRest = 15;
                ajuste = 2.5;
                boost = 3;
                break;
            case 3:
                descanso = 5;
                vegRest = 25;
                ajuste = 3;
                boost = 2;
                break;
            case 4:
                descanso = 5;
                vegRest = 50;
                ajuste = 1;
                boost = 0;
                break;
            default:
                descanso = 0;
                vegRest = Integer.MAX_VALUE;
                ajuste = 1;
                boost = 5;
        }
        vegFinal = vegRest / 5;
        vegRest -= vegFinal;
        auxx = vegRest;

        for (int k = 0; turnos > 0; turnos--, k++) {
            if (auxx != vegRest) {
                k = 0;  // k representa los turnos sin que los zombies hayan aparecido
                probabilidad = 0;
            }
            if (k >= boost) {
                probabilidad = Math.random() / ajuste; // el ajuste representa entre que numeros se van a generar
            }
            auxx = vegRest;
            prob = ((double) vegRest) / ((double) turnos) * 0.8;
            pfinal = prob + probabilidad;
            System.out.printf("%.2f-vegRest :%d - probFinal: %.2f - turnosSinZombies: %d \n",prob,vegRest,pfinal,k);
            if (turnos > descanso) {
                // turnos iniciales.
                if (pfinal >= 1.00 && vegRest >= 3) {
                    vegRest -= 2 + (int) pfinal;
                } else if (pfinal >= 0.9 && vegRest >= 2) {
                    vegRest -= 2;
                } else if (pfinal >= 0.8 && vegRest >= 1) {
                    vegRest -= 1;
                }

            } else if (turnos == descanso) {
                System.out.println("FINAL WAVE!!!");
                vegRest += vegFinal;
                if (pfinal >= 1.00 && vegRest >= 3) {
                    vegRest -= 2 + (int) pfinal;
                } else if (pfinal >= 0.75 && vegRest >= 2) {
                    vegRest -= 2;
                } else if (pfinal >= 0.6 && vegRest >= 1) {
                    vegRest -= 1;
                }

            } else {
                if (pfinal >= 1.00 && vegRest >= 3) {
                    vegRest -= 2 + (int) pfinal;
                } else if (pfinal >= 0.75 && vegRest >= 2) {
                    vegRest -= 2;
                } else if (pfinal >= 0.6 && vegRest >= 1) {
                    vegRest -= 1;
                }

            }
        }
    }
}
