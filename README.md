# MartaYLasPlantas
Marta y las plantas 2 full hd remastered gold edition Made in java
© Todos los dchos. no reservados, 02/05/2019

Finalidad: la finalidad de éste archivo README, que se encuentra ud. leyendo, es méramente comunicatoria.

*** ÍNDICE:
   1 - Fallos encontrados en el enunciado:
        1.1 - Fallo.
        1.2 - Posibles soluciones.
   2 - Observaciones:
  
  
   3 - Explicaciones:
  
  
1 - Fallos encontrados en el proyecto/enunciado:
# El primer fallo y, el más comentado probablemente
es el "error" que consigo trae el que el usuario pueda variar las longitudes del tablero, anotadas unas cuantas de ellas (no todas) a continuación.
    - Fallos: Al tener que aparecer n número de zombies, y poder restringir la altura a la que el usuario quiera, se puede llegar
              al caso en el cual los 30 turnos de spawn de zombies se agoten y queden zombies sin aparecer.
    - Posibles soluciones: - Eliminar los 30 turnos de spawn de zombies máximos estrictos.
                           - No permitir al usuario seleccionar el tamaño del tablero que éste quiera.
                           - Crear excepciones (nosotros optando por esta última)
                           
# Segundo fallo:
También relacionado con las longitudes del tablero, pero en este caso, también incorporando la dificultad.
  La dificultad se convierte en algo relativo si permitimos al usuario controlar el tamaño del tablero, no es lo mismo controlar
  a 50 entidades en una mapa de 50x50 que en un mapa de 3x3, o simplemente en un mapa de 1x 500, evidentemente, la dificultad 
  es algo ilógico que acaba simplemente controlando, al final, la cantidad de zombies que quieres en el tablero.
      - Posibles soluciones: - No permitir al usuario seleccionar el tamaño del tablero que éste quiera.
                             - Complicar las modificaciones que la dificultad sugiere 
                             *** Explicado en el apartado *** EXPLICACIONES *** de este archivo *** 1.
                             -  

OBSERVACIONES:



*** EXPLICACIONES ***
1. (línea 21): pudimos crear un algorítmo mediante el cual, resolviendo un sistema de ecuaciones, podríamos crear diferentes parámetros 
   a modificar, ejemplo, dependiendo de la dificultad seleccionada, mediante una curva de dificultad (generada con una función polinómica)
   llegamos a crear diferentes ataques que los zombies tendrían, o diferente salud que éstos tendrían, dependiendo de la dificultad
   
        PROS: - Mayor uso del parámetro "dificultad"
              - Mayor ajuste a la dificultad dependiendo de cada opción seleccionada, al tratarse de una curva de dificultad, es mucho
                más justo para el usuario, y no nos encontramos con un juego desequilibrado ni ilógico.
              - Algoritmo portable y reutilizable independientemente de la cantidad de dificultades y de la cantidad de parámetros a modficar
              
        
        
        CONTRAS: - Demasiado tiempo exigido en la implementación de susodicho código, todo para que éste parámetro se vea difuminado al
                   final por el ancho y el alto variable a gusto del usuario, nos reiteramos, una auténtica salvajada la cual impide
                   la aplicación de diferentes algoritmós que tenían su lógica, pero entendemos que en esta práctica, usted es el
                   "cliente", y nosotros hemos de adaptarnos a lo que usted quiera, pero le dejamos comunicado mediante este README
                   lo que nosotros opinamos al respecto y no pudimos comunicarle debido a un tiempo limitado de entrega, nos hubiese
                   gustado hacer todo de un modo más profesional, pero no disponíamos de todo el tiempo necesario.
          


