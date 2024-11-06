package org.example.View;
import org.example.Controller.HabitacionController;
import org.example.Main;
import org.example.Model.*;
import org.example.Model.TipoHabitacion;
import java.util.List;
import java.util.Scanner;

public class HabitacionView {
    private HabitacionController habitacionController;
    private Scanner scanner;

    public HabitacionView(Scanner scanner) {
        this.habitacionController = new HabitacionController();
        this.scanner = scanner;
    }

    public void manejarHabitacion(){
        int option = -1;
        do {
            System.out.println("\n----- (: Manejar los Habitacion :) -----");
            System.out.println("1. Ingresar un nuevo habitacion");
            System.out.println("2. Eliminar habitacion");
            System.out.println("3. Actualizar habitacion");
            System.out.println("4. Ocupar habitación");
            System.out.println("5. Desocupar habitación");
            System.out.println("6. Listar habitaciones ;)");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarHabitacion();
                    break;
                case 2:
                    eliminarHabitacion();
                    break;
                case 3:
                    actualizarHabitacion();
                    break;
                case 4:
                    ocuparHabitacion();
                    break;
                case 5:
                    desocuparHabitacion();
                    break;
                case 6:
                    listarHabitaciones();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    public void agregarHabitacion() {
        System.out.println("Crear nueva habitación");

        // Seleccionar el hotel
        List<Hotel> hoteles = habitacionController.obtenerTodosLosHoteles();
        System.out.println("Selecciona el hotel para la habitación:");
        for (int i = 0; i < hoteles.size(); i++) {
            System.out.println((i + 1) + ". " + hoteles.get(i).getNombre());
        }

        int seleccionHotel = scanner.nextInt() - 1;
        Hotel hotel = hoteles.get(seleccionHotel);


        // Seleccionar el tipo de habitación
        List<TipoHabitacion> tiposHabitacion = habitacionController.obtenerTodosLosTiposHabitacion();
        if (tiposHabitacion.isEmpty()) {
            System.out.println("No hay tipos de habitación disponibles. Agrega tipos de habitación antes de continuar.");
            return;
        }
        System.out.println("Selecciona el tipo de habitación:");
        for (int i = 0; i < tiposHabitacion.size(); i++) {
            System.out.println((i + 1) + ". " + tiposHabitacion.get(i).getNombre());
        }
        int tipoHabitacionIndex = scanner.nextInt() - 1;
        if (tipoHabitacionIndex < 0 || tipoHabitacionIndex >= tiposHabitacion.size()) {
            System.out.println("Opción de tipo de habitación no válida.");
            return;
        }
        TipoHabitacion tipoHabitacion = tiposHabitacion.get(tipoHabitacionIndex);

        System.out.println("Cantidad de camas:");
        int cantidadCamas = scanner.nextInt();

        System.out.println("¿Tiene cama doble? (true/false):");
        boolean camaDoble = scanner.nextBoolean();

        System.out.println("¿Tiene aire acondicionado? (true/false):");
        boolean aireAcondicionado = scanner.nextBoolean();

        System.out.println("¿Tiene balcón? (true/false):");
        boolean balcon = scanner.nextBoolean();

        System.out.println("¿Tiene vista? (true/false):");
        boolean vista = scanner.nextBoolean();

        System.out.println("¿Está ocupada? (true/false):");
        boolean ocupada = scanner.nextBoolean();

        Habitacion habitacion = new Habitacion(0, hotel, cantidadCamas, camaDoble, aireAcondicionado, balcon, vista, ocupada, tipoHabitacion);
        if (HabitacionController.agregarHabitacion(habitacion)) {
            System.out.println("Habitación creada con éxito.");
        } else {
            System.out.println("Error al crear la habitación.");
        }
    }

    public void eliminarHabitacion() {
        System.out.println("Eliminar habitación");
        System.out.println("Ingrese el ID de la habitación a eliminar:");
        int idHabitacion = scanner.nextInt();

        if (habitacionController.eliminarHabitacion(idHabitacion)) {
            System.out.println("Habitación eliminada con éxito.");
        } else {
            System.out.println("Error al eliminar la habitación o ID no encontrado.");
        }
    }

    public void actualizarHabitacion() {
        System.out.println("Actualizar habitación");
        System.out.println("Ingrese el ID de la habitación a actualizar:");
        int idHabitacion = scanner.nextInt();

        List<Hotel> hoteles = habitacionController.obtenerTodosLosHoteles();
        System.out.println("Selecciona el hotel para la habitación:");
        for (int i = 0; i < hoteles.size(); i++) {
            System.out.println((i + 1) + ". " + hoteles.get(i).getNombre());
        }

        int seleccionHotel = scanner.nextInt() - 1;
        Hotel hotel = hoteles.get(seleccionHotel);

        List<TipoHabitacion> tiposHabitacion = habitacionController.obtenerTodosLosTiposHabitacion();
        if (tiposHabitacion.isEmpty()) {
            System.out.println("No hay tipos de habitación disponibles. Agrega tipos de habitación antes de continuar.");
            return;
        }
        System.out.println("Selecciona el tipo de habitación:");
        for (int i = 0; i < tiposHabitacion.size(); i++) {
            System.out.println((i + 1) + ". " + tiposHabitacion.get(i).getNombre());
        }
        int tipoHabitacionIndex = scanner.nextInt() - 1;
        if (tipoHabitacionIndex < 0 || tipoHabitacionIndex >= tiposHabitacion.size()) {
            System.out.println("Opción de tipo de habitación no válida.");
            return;
        }
        TipoHabitacion tipoHabitacion = tiposHabitacion.get(tipoHabitacionIndex);

        System.out.println("Cantidad de camas:");
        int cantidadCamas = scanner.nextInt();

        System.out.println("¿Tiene cama doble? (true/false):");
        boolean camaDoble = scanner.nextBoolean();

        System.out.println("¿Tiene aire acondicionado? (true/false):");
        boolean aireAcondicionado = scanner.nextBoolean();

        System.out.println("¿Tiene balcón? (true/false):");
        boolean balcon = scanner.nextBoolean();

        System.out.println("¿Tiene vista? (true/false):");
        boolean vista = scanner.nextBoolean();

        System.out.println("¿Está ocupada? (true/false):");
        boolean ocupada = scanner.nextBoolean();

        Habitacion habitacion = new Habitacion(idHabitacion, hotel, cantidadCamas, camaDoble, aireAcondicionado, balcon, vista, ocupada, tipoHabitacion);
        if (habitacionController.actualizarHabitacion(habitacion)) {
            System.out.println("Habitación actualizada con éxito.");
        } else {
            System.out.println("Error al actualizar la habitación o ID no encontrado.");
        }
    }

    public void ocuparHabitacion() {
        System.out.println("\n--- Ocupar habitación ---");
        System.out.println("Ingrese el ID de la habitación a marcar como ocupada:");
        int idHabitacion = scanner.nextInt();

        if (habitacionController.ocuparHabitacion(idHabitacion)) {
            System.out.println("Habitación marcada como ocupada.");
        } else {
            System.out.println("Error al marcar la habitación como ocupada o ID no encontrado.");
        }
    }

    public void desocuparHabitacion() {
        System.out.println("\n--- Desocupar habitación ---");
        System.out.println("Ingrese el ID de la habitación a marcar como desocupada:");
        int idHabitacion = scanner.nextInt();

        if (habitacionController.desocuparHabitacion(idHabitacion)) {
            System.out.println("Habitación marcada como desocupada.");
        } else {
            System.out.println("Error al marcar la habitación como desocupada o ID no encontrado.");
        }
    }

    public void listarHabitaciones() {
        System.out.println("\n--- Lista de Habitaciones ---");
        List<Habitacion> habitaciones = habitacionController.obtenerTodasLasHabitaciones();

        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
        } else {
            for (Habitacion habitacion : habitaciones) {
                System.out.println("ID: " + habitacion.getIdHabitacion());
                System.out.println("Hotel: " + habitacion.getHotel().getNombre());
                System.out.println("Cantidad de camas: " + habitacion.getCantidadCamas());
                System.out.println("Cama doble: " + (habitacion.isCamaDoble() ? "Sí" : "No"));
                System.out.println("Aire acondicionado: " + (habitacion.isAireAcondicionado() ? "Sí" : "No"));
                System.out.println("Balcón: " + (habitacion.isBalcon() ? "Sí" : "No"));
                System.out.println("Vista: " + (habitacion.isVista() ? "Sí" : "No"));
                System.out.println("Ocupada: " + (habitacion.isOcupada() ? "Sí" : "No"));
                System.out.println("Tipo de habitación: " + habitacion.getTipoHabitacion().getNombre());
                System.out.println("------");
            }
        }
    }
}
