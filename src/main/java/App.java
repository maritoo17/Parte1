import java.io.IOException;
import java.util.Scanner;

public class App {
    private static Experimento experimento;
    private static FileManager fileManager;

    public static void main(String[] args) {
        experimento = new Experimento();
        fileManager = new FileManager(experimento);
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la ruta del archivo:");
                    String rutaAbrir = scanner.nextLine();
                    try {
                        fileManager.abrirArchivo(rutaAbrir);
                        System.out.println("Experimento cargado exitosamente.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Error al abrir el archivo: " + e.getMessage());
                    }
                    break;
                case 2:
                    experimento = new Experimento();
                    System.out.println("Nuevo experimento creado.");
                    break;
                case 3:
                    System.out.println("Ingrese el nombre de la nueva población:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese la cantidad de bacterias:");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    Poblacion nuevaPoblacion = new Poblacion(nombre, cantidad);
                    experimento.addPoblacion(nuevaPoblacion);
                    System.out.println("Población añadida.");
                    break;
                case 4:
                    System.out.println("Poblaciones en el experimento:");
                    experimento.getPoblacionesNombres().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Ingrese el nombre de la población a borrar:");
                    String nombreBorrar = scanner.nextLine();
                    experimento.removePoblacion(nombreBorrar);
                    System.out.println("Población borrada.");
                    break;
                case 6:
                    System.out.println("Ingrese el nombre de la población para ver detalles:");
                    String nombreDetalle = scanner.nextLine();
                    Poblacion poblacion = experimento.getPoblacion(nombreDetalle);
                    if (poblacion != null) {
                        System.out.println(poblacion);
                    } else {
                        System.out.println("Población no encontrada.");
                    }
                    break;
                case 7:
                    System.out.println("Ingrese la ruta del archivo para guardar:");
                    String rutaGuardar = scanner.nextLine();
                    try {
                        fileManager.guardarArchivo(rutaGuardar);
                        System.out.println("Experimento guardado exitosamente.");
                    } catch (IOException e) {
                        System.out.println("Error al guardar el archivo: " + e.getMessage());
                    }
                    break;
                case 8:
                    System.out.println("Ingrese la ruta del archivo para guardar como:");
                    String rutaGuardarComo = scanner.nextLine();
                    try {
                        fileManager.guardarArchivo(rutaGuardarComo);
                        System.out.println("Experimento guardado como exitosamente.");
                    } catch (IOException e) {
                        System.out.println("Error al guardar el archivo: " + e.getMessage());
                    }
                    break;
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("0. Salir");
        System.out.println("1. Abrir experimento existente");
        System.out.println("2. Crear nuevo experimento");
        System.out.println("3. Añadir población de bacterias");
        System.out.println("4. Listar todas las poblaciones");
        System.out.println("5. Eliminar una población de bacterias");
        System.out.println("6. Ver detalles de una población de bacterias");
        System.out.println("7. Guardar experimento");
        System.out.println("8. Guardar experimento como");
    }
}
