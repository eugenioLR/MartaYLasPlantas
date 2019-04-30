/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.util.ArrayList;

/**
 *
 * @author EDGENP
 */
public class algoritmoDificultad {

    public static void main(String[] args) {

        int TURNOS = 30;
        int vegRest = 5;
        int vegTablero = 0;
        double auxx;
        ArrayList<Double> prob = new ArrayList<>();

        for (int i = 0; TURNOS > i; TURNOS--) {
            auxx = (double) vegRest / TURNOS;
            System.out.println(auxx);
        //    prob.add(auxx);
            if (auxx >= 0.5){
                vegRest--;
            }
            
        }
        

        /* en 30 turnos han de aparecer 5 veg; es decir, un 100% si lo quieres ver así.
        * en un turno?     30 -- 100
        * x = 3.33333...   1  --  x
        * en el siguiente movimiento pasa a ser:
        *  29 -- 100
        *  1  --  x      x = 3.448...
        *  
        *   30  -- 5
        *   1   -- x   x = 0.1666666
        *
        *   29  -- 5
        *   1   -- x   x = 0.172413
         */
    }
}
