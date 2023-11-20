package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
*@author Marcos Lema
*@version v1.0
*DNI: 47374401J
*Modulo: programacion DAW2324
*Email: marcos.lema@formacionchios.es
*Actividad: Actividad02-Ejercicio01
 */

// Clase principal de la aplicación
public class AppTemperatura {
	
    // Creamos las estructuras de almacenamiento 
    private static ArrayList<String> ciudades = new ArrayList<>();
    private static HashMap<String, HashMap<String, Double>> temperaturasPorCiudad = new HashMap<>();

    // Método main para iniciar la aplicación
    public static void main(String[] args) {
    	
    	// Creamos el objeto de la clase scanner
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Hacemos un do while para el menú de la app
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            // Opciones del menú
            switch (opcion) {
                case 1:
                    altaCiudades(sc);
                    break;
                case 2:
                    altaTemperaturas(sc);
                    break;
                case 3:
                    mostrarCiudades();
                    break;
                case 4:
                    mostrarMeses();
                    break;
                case 5:
                    mostrarTemperaturasPorCiudad();
                    break;
                case 6:
                    mostrarTemperaturaMediaAnual();
                    break;
                case 7:
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 7);

        // Cerrar el scanner al salir de la aplicación
        sc.close();
    }

    // Nos muestra el menú en la consola
    private static void mostrarMenu() {
        System.out.println("============ Menú ============");
        System.out.println("1. Alta de ciudades");
        System.out.println("2. Alta de temperaturas");
        System.out.println("3. Mostrar todas las ciudades");
        System.out.println("4. Mostrar todos los meses");
        System.out.println("5. Mostrar temperaturas mensuales de las ciudades");
        System.out.println("6. Mostrar la temperatura media anual de cada ciudad");
        System.out.println("7. Salir de la aplicación");
        System.out.println("==============================");
    }

    /**
     * Realiza el alta de ciudades.
     *
     * @param scanner Scanner para la entrada de datos.
     */
    private static void altaCiudades(Scanner scanner) {
        System.out.print("Ingrese el nombre de la ciudad: ");
        String ciudad = scanner.nextLine();
        ciudades.add(ciudad);
        temperaturasPorCiudad.put(ciudad, new HashMap<>());
        System.out.println("Ciudad agregada con éxito.");
    }

    /**
     * Realiza el alta de temperaturas para una ciudad y mes específicos.
     *
     * @param scanner Scanner para la entrada de datos.
     */
    private static void altaTemperaturas(Scanner scanner) {
        System.out.print("Ingrese el nombre de la ciudad: ");
        String ciudad = scanner.nextLine();

        if (ciudades.contains(ciudad)) {
            System.out.print("Ingrese el mes (por ejemplo, enero): ");
            String mes = scanner.nextLine();

            System.out.print("Ingrese la temperatura para " + ciudad + " en " + mes + ": ");
            double temperatura = scanner.nextDouble();

            temperaturasPorCiudad.get(ciudad).put(mes, temperatura);
            System.out.println("Temperatura registrada con éxito.");
        } else {
            System.out.println("Ciudad no encontrada. Primero realice el alta de la ciudad.");
        }
    }

    /**
     * Muestra todas las ciudades almacenadas.
     */
    private static void mostrarCiudades() {
        System.out.println("\nCiudades registradas:");
        for (String ciudad : ciudades) {
            System.out.println(ciudad);
        }
    }

    /**
     * Muestra todos los meses almacenados.
     */
    private static void mostrarMeses() {
        System.out.println("\nMeses registrados:");
        for (String ciudad : ciudades) {
            System.out.println("Meses para " + ciudad + ": " + temperaturasPorCiudad.get(ciudad).keySet());
        }
    }

    /**
     * Muestra las temperaturas de cada mes para cada ciudad.
     */
    private static void mostrarTemperaturasPorCiudad() {
        System.out.println("\nTemperaturas por ciudad:");
        for (Map.Entry<String, HashMap<String, Double>> entry : temperaturasPorCiudad.entrySet()) {
            String ciudad = entry.getKey();
            System.out.println("Ciudad: " + ciudad);
            System.out.println("Temperaturas: " + entry.getValue());
        }
    }

    /**
     * Muestra la temperatura media anual para cada ciudad.
     */
    private static void mostrarTemperaturaMediaAnual() {
        System.out.println("\nTemperatura media anual por ciudad:");
        for (String ciudad : ciudades) {
            double media = calcularMediaAnual(ciudad);
            System.out.println("Ciudad: " + ciudad + ", Temperatura media anual: " + media);
        }
    }

    /**
     * Calcula la temperatura media anual para una ciudad.
     *
     * @param ciudad Ciudad para la cual se calculará la temperatura media anual.
     * @return Temperatura media anual.
     */
    private static double calcularMediaAnual(String ciudad) {
        HashMap<String, Double> temperaturas = temperaturasPorCiudad.get(ciudad);
        double suma = 0;

        for (double temperatura : temperaturas.values()) {
            suma += temperatura;
        }

        return suma / temperaturas.size();
    }
}

