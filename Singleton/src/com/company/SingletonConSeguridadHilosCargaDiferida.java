package com.company;

//Singleton con seguridad en los hilos y carga diferida
//Para arreglar el problema, debes sincronizar hilos durante la primera creación del objeto Singleton.


public class SingletonConSeguridadHilosCargaDiferida {

    // El campo debe declararse volátil para que funcione el bloqueo de verificación doble correctamente
    private static volatile SingletonConSeguridadHilosCargaDiferida instance;
    public String value;

    private SingletonConSeguridadHilosCargaDiferida(String value){
        this.value = value;
    }

    public static SingletonConSeguridadHilosCargaDiferida getInstance(String value) {
        // El enfoque adoptado aquí se llama bloqueo de doble verificación (DCL). Eso
        // existe para evitar una condición de carrera entre varios subprocesos que pueden
        // intenta obtener una instancia de singleton al mismo tiempo, creando una instancia separada
        // instancias como resultado.
        // Puede parecer que tener la variable `result` aquí es completamente
        // inútil. Sin embargo, hay una salvedad muy importante cuando
        // implementación de bloqueo de doble verificación en Java, que se resuelve
        // introduciendo esta variable local.
        // Puede leer más información sobre problemas de DCL en Java aquí:
        // https://refactoring.guru/java-dcl-issue

        SingletonConSeguridadHilosCargaDiferida result = instance;

        if (result != null) {
            return result;
        }
        synchronized (SingletonConSeguridadHilosCargaDiferida.class){
            if (instance == null){
                instance = new SingletonConSeguridadHilosCargaDiferida(value);
            }
            return instance;
        }
    }
}

class DemoMultiThread2 {
    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        Thread threadFoo2 = new Thread(new ThreadFoo2());
        Thread threadBar2 = new Thread(new ThreadBar2());
        threadFoo2.start();
        threadBar2.start();
    }
}

class ThreadFoo2 implements Runnable {
    @Override
    public void run() {
        SingletonConSeguridadHilosCargaDiferida singleton = SingletonConSeguridadHilosCargaDiferida.getInstance("FOO");
        System.out.println(singleton.value);
    }
}

class ThreadBar2 implements Runnable {
    @Override
    public void run() {
        SingletonConSeguridadHilosCargaDiferida singleton = SingletonConSeguridadHilosCargaDiferida.getInstance("BAR");
        System.out.println(singleton.value);
    }
}