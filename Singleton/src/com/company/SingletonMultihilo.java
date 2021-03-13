package com.company;

/*
Singleton en Java
Singleton es un patrón de diseño creacional que garantiza que tan solo exista un objeto de su tipo y proporciona un
único punto de acceso a él para cualquier otro código.


El patrón tiene prácticamente los mismos pros y contras que las variables globales. Aunque son muy útiles, rompen la
modularidad de tu código.


No se puede utilizar una clase que dependa del Singleton en otro contexto. Tendrás que llevar también la clase Singleton.
La mayoría de las veces, esta limitación aparece durante la creación de pruebas de unidad.

Ejemplos de uso: Muchos desarrolladores consideran el patrón Singleton un antipatrón. Por este motivo, su uso está
en declive en el código Java.


A pesar de ello, existen muchos ejemplos del patrón Singleton en las principales bibliotecas de Java:

Identificación: El patrón Singleton se puede reconocer por un método de creación estático, que devuelve el mismo objeto
guardado en caché.
 */

/*
Singleton ingenuo (multihilo)
La misma clase se comporta de forma incorrecta en un entorno de múltiples hilos. Los múltiples hilos pueden llamar al
método de creación de forma simultánea y obtener varias instancias de la clase Singleton.
 */


public class SingletonMultihilo {

    private static SingletonMultihilo instance;
    public String value;

    private SingletonMultihilo(String value){
        // El siguiente codigo emula una inicialización lenta
        try {
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static SingletonMultihilo getInstance(String value){
        if (instance == null){
            instance = new SingletonMultihilo(value);
        }
        return instance;
    }
}

class DemoMultiThread{

    public static void main(String[] args) {
        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");

        Thread threadFoo = new Thread(new ThreadFoo());
        Thread treadBar = new Thread(new ThreadBar());
        threadFoo.start();
        treadBar.start();

    }
}

class ThreadFoo implements Runnable{

    @Override
    public void run() {

        SingletonMultihilo singletonFoo = SingletonMultihilo.getInstance("FOO");
        System.out.println(singletonFoo.value);
    }

}

class ThreadBar implements Runnable{

    @Override
    public void run() {
        SingletonMultihilo singletonBar = SingletonMultihilo.getInstance("Bar");
        System.out.println(singletonBar.value);
    }

}