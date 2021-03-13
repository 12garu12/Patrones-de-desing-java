package com.company;

public class Singleton {

    private static Singleton instance;
    public String value;

    // Hacer privado el constructor por defecto para evitar que otros objetos
    // utilicen el operador new con la clase Singleton.
    private Singleton(String value) {
        // The following code emulates slow initialization.
        // hilos en java
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    // Crear un método de creación estático que actúe como constructor. Tras bambalinas, este método invoca al
    // constructor privado para crear un objeto y lo guarda en un campo estático. Las siguientes llamadas a este
    // método devuelven el objeto almacenado en caché.
    public static Singleton getInstance(String value){
        if (instance == null){
            instance = new Singleton(value);
        }
        return instance;
    }

}

class DemoSingleThread {

    public static void main(String[] args) {

        System.out.println("Si ve el mismo valor, entonces se reutilizó singleton (¡yay!)" + "\n" +
                "Si ve valores diferentes, entonces se crearon 2 singletons (booo!!)" + "\n\n" +
                "RESULTADO:" + "\n");
        Singleton singleton = Singleton.getInstance("FOO");
        Singleton anotherSingleton = Singleton.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);
    }
}
