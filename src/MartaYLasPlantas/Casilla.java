/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Casilla {
    private ArrayList<Entidad> entidades;

    public Casilla() {
        entidades = new ArrayList<>();
    }
    
    public void insertarEntidad(Entidad entidad){
        entidades.add(entidad);
    }

    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }
    
    public void actualizar(){
        for(int i = 0; i<entidades.size(); i++){
            if(entidades.get(i).getSalud() <= 0){
                entidades.remove(i);
            }
        }
    }
}
