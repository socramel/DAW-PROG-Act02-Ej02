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
*Actividad: Actividad02-Ejercicio02
* Aplicacion para tratar temperaturas de ciudades dadas por el usuario */
public class AppTemperatura {
	
    // Creamos el map para almacenar ciudades y temperaturas 
    private static Map<String, ArrayList<Double>> temperaturaPorCiudad = new HashMap<>();
    
    // Listamos los meses del años
    private static final String[] MESES = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };
    
	// Creamos el objeto de la clase Scanner
    private static Scanner sc = new Scanner(System.in);

    
    // Método main para iniciar la aplicación
    public static void main(String[] args) {
    
        int opcion;

        // Hacemos un do while para el menú de la app
        do {
            mostrarMenu();
            opcion = obtenerOpcionUsuario();

            // Opciones del menú
            switch (opcion) {
                case 1:
                    altaCiudades();
                    break;
                case 2:
                    altaTemperaturas();
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
                    System.out.println("\nSaliendo de la aplicación...");
                    break;
                default:
                    System.out.println("\nOpción incorrecta. Por favor, teclee una opción válida:\n");
            }
        } while (opcion != 7);
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
    
    
    private static int obtenerOpcionUsuario() {
        System.out.print("Elige una opción: ");
        return sc.nextInt();
    }

    
    // 1. Alta de ciudades
    private static void altaCiudades() {
        System.out.print("\nIngrese el nombre de la ciudad: ");
        String ciudad = sc.next();
        
        if (!temperaturaPorCiudad.containsKey(ciudad)) {
            temperaturaPorCiudad.put(ciudad, new ArrayList<>());
            System.out.println("\n¡Ciudad agregada con éxito!\n");
        } else {
            System.out.println("\nEsta ciudad ya está dada de alta\n");
        }
    }

    
    // 2. Alta de temperaturas
    private static void altaTemperaturas() {
    	
    	// Comprobar las ciudades que ya ingresaron temperaturas
        ArrayList<String> ciudadesConTemperaturasIngresadas = new ArrayList<>();
    	
        for (String ciudad : temperaturaPorCiudad.keySet()) {
        	
            // Verificar si la ciudad ya está en la lista de ciudades con temperaturas ingresadas
            if (!ciudadesConTemperaturasIngresadas.contains(ciudad)) {
            	
                // Verificar si ya se han ingresado temperaturas para la ciudad actual
                if (temperaturaPorCiudad.get(ciudad).isEmpty()) {
                    System.out.println("\nIngresar temperaturas para la ciudad de " + ciudad + ":");
                    for (String mes : MESES) {
                        System.out.print("\nTemperatura de " + mes + ": ");
                        double temperatura = sc.nextDouble();
                        temperaturaPorCiudad.get(ciudad).add(temperatura);
                    }
                    
                 	// Agregar a la lista de ciudades con temperaturas ingresadas
                    ciudadesConTemperaturasIngresadas.add(ciudad); 
                }
            }
        }
        if (!ciudadesConTemperaturasIngresadas.isEmpty()) {
            System.out.print("\n¡Temperaturas agregadas con éxito!\n");
        }
    }

    
    // 3. Mostrar todas las ciudades
    private static void mostrarCiudades() {
        System.out.println("\nCiudades: ");
        for (String ciudad : temperaturaPorCiudad.keySet()) {
            System.out.println(ciudad + "\n");
        }
    }


    // 4. Mostrar todos los meses
    private static void mostrarMeses() {
        System.out.println("\nMeses: ");
        for (String mes : MESES) {
            System.out.println(mes + "\n");
        }
    }


    // 5. Mostrar temperaturas mensuales de las ciudades
    private static void mostrarTemperaturasPorCiudad() {

        for (String ciudad : temperaturaPorCiudad.keySet()) {
            System.out.println("\nTemperaturas mensuales en la ciudad de " + ciudad + ":");
            ArrayList<Double> temperaturas = temperaturaPorCiudad.get(ciudad);
            for (int i = 0; i < MESES.length; i++) {
                System.out.println(MESES[i] + ": " + temperaturas.get(i) + " ºC\n");
            }
        }
    }

    
    // 6. Mostrar temperatura media
    private static void mostrarTemperaturaMediaAnual() {
        for (String ciudad : temperaturaPorCiudad.keySet()) {
            ArrayList<Double> temperaturas = temperaturaPorCiudad.get(ciudad);
            double media = calcularMediaAnual(temperaturas);
            System.out.println("\nTemperatura media anual de " + ciudad + ": " + media + " ºC\n");
        }
    }


    // Calcular temperatura media anual para cada ciudad
    private static double calcularMediaAnual(ArrayList<Double> valores) {
        double suma = 0;

        for (double valor : valores) {
            suma += valor;
        }

        return suma / valores.size();
    }
}