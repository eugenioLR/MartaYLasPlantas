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
        int vegRest = 12;
        double auxx;

        for (int i = 0; turnos > i; turnos--) {
            auxx = ((double) vegRest) / ((double) turnos);
            System.out.println(auxx + "-" + vegRest + "-" + turnos);
            //    prob.add(auxx);
            if (auxx >= 0.5) {
                vegRest--;
            } else if (auxx >= 0.7) {
                vegRest -= 2;
            } else if (auxx >= 0.85) {
                vegRest -= 3;
            } else if (auxx >= 1.00) {
                vegRest -= 3 + (int) auxx / 1.00;
            }
        }
    }
}
