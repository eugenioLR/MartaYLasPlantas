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

    public void insertarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    public void quitarEntidad(Entidad entidad) {
        entidades.remove(entidad);
    }

    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }

    public void vaciar() {
        this.getEntidades().clear();
    }

    public void actualizar() {
        Object[] arrObjetos = entidades.toArray();
        Entidad arrEntidades[] = new Entidad[entidades.size()], entidadAux;
        
        int i = 0;
        for(Object elemento:arrObjetos){
            entidadAux = (Entidad) elemento;
            arrEntidades[i] = entidadAux;
            i++;
        }
        
        for (Entidad entidad:arrEntidades) {
            if (entidad.getSalud() < 1) {
                entidades.remove(entidad);
            }
        }
    }

}
