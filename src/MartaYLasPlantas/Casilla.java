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

    /**
     *
     */
    public Casilla() {
        entidades = new ArrayList<>();
    }

    /**
     *
     * @param entidad
     */
    public void insertarEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    /**
     *
     * @param entidad
     */
    public void quitarEntidad(Entidad entidad) {
        entidades.remove(entidad);
    }

    /**
     *
     * @return
     */
    public ArrayList<Entidad> getEntidades() {
        return entidades;
    }

    /**
     *
     */
    public void vaciar() {
        this.getEntidades().clear();
    }

    /**
     *  Método que se encarga de que no exista  
     */
    public void actualizar() {
        Object[] arrObjetos = entidades.toArray();
        Entidad arrEntidades[] = new Entidad[entidades.size()], entidadAux;

        int i = 0;
        
        for (Object elemento : arrObjetos) {
            // copiamos el contenido de un array a otro, haciendo un cast simultáneo a un tipo Entidad
            entidadAux = (Entidad) elemento;
            arrEntidades[i] = entidadAux;
            i++;
        }

        // Con el cast creado, si tenemos una entidad con vida menor que 1, la eliminamos.
        for (Entidad entidad : arrEntidades) {
            if (entidad.getSalud() < 1) {
                entidades.remove(entidad);
            }
        }
    }

}
