package org.example.View;

import org.example.Controller.ReservaController;
import org.example.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservaView {
    private ReservaController reservaController;
    private Scanner scanner;

    public ReservaView(Scanner scanner) {
        this.reservaController = new ReservaController();
        this.scanner = scanner;
    }

    public void manejarReserva() {
        int option = -1;
        do {
            System.out.println("\n----- (: Manejar Reservas :) -----");
            System.out.println("1. Crear una nueva reserva");
            System.out.println("2. Eliminar reserva");
            System.out.println("3. Actualizar reserva");
            System.out.println("4. Consultar reservas por filtros");
            System.out.println("5. Agregar habitaciones a reservas");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarReserva();
                    break;
                case 2:
                    eliminarReserva();
                    break;
                case 3:
                    actualizarReserva();
                    break;
                case 4:
                   consultarReservas();
                    break;
                case 5:
                    agregarHabitacionAReserva();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    public void agregarReserva() {
        System.out.println("Agregar nueva reserva");

        List<Huesped> huespedes = reservaController.obtenerTodosLosHuespedes();
        System.out.println("Selecciona el huésped para la reserva:");
        for (int i = 0; i < huespedes.size(); i++) {
            System.out.println((i + 1) + ". " + huespedes.get(i).getNombre());
        }

        int seleccionHuesped = scanner.nextInt() - 1;
        Huesped huesped = huespedes.get(seleccionHuesped);

        System.out.println("Cantidad de personas:");
        int cantidadPersonas = scanner.nextInt();

        System.out.print("Nueva fecha de inicio (YYYY-MM-DD): ");
        scanner.nextLine();
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Nueva fecha de fin (YYYY-MM-DD): ");
        LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

        System.out.print("Nueva fecha de reserva (YYYY-MM-DD): ");
        LocalDate fechaReserva = LocalDate.parse(scanner.nextLine());

        // verigicaciones de las fechas
        if (fechaInicio.isAfter(fechaFin)) {
            System.out.println("La fecha de inicio no puede ser mayor que la fecha de fin.");
            return;
        }
        if (fechaReserva.isAfter(LocalDate.now())) {
            System.out.println("La fecha de reserva no puede ser en el futuro.");
            return;
        }

        Reserva reserva = new Reserva(0, huesped, null, cantidadPersonas, fechaInicio, fechaFin, fechaReserva);
        if (reservaController.agregarReserva(reserva)) {
            System.out.println("Reserva agregada con éxito. ID de reserva: " + reservaController.obtenerUltimoIdReserva());
            agregarHabitacionAReserva();
        } else {
            System.out.println("Error al agregar la reserva.");
        }
    }

    public void eliminarReserva() {
        System.out.println("Eliminar reserva");
        System.out.println("Ingrese el ID de la reserva a eliminar:");
        int idReserva = scanner.nextInt();

        if (reservaController.eliminarReserva(idReserva)) {
            System.out.println("Reserva eliminada con éxito.");
        } else {
            System.out.println("Error al eliminar la reserva o ID no encontrado.");
        }
    }

    public void agregarHabitacionAReserva() {
        System.out.println("Agregar habitación a la reserva");
        System.out.println("Ingrese el ID de la reserva:");
        int idReserva = scanner.nextInt();

        List<Habitacion> habitaciones = reservaController.obtenerTodasLasHabitaciones();
        System.out.println("Selecciona la habitación para agregar a la reserva:");
        for (int i = 0; i < habitaciones.size(); i++) {
            System.out.println((i + 1) + ". " + habitaciones.get(i).getIdHabitacion());
        }

        int seleccionHabitacion = scanner.nextInt() - 1;
        Habitacion habitacion = habitaciones.get(seleccionHabitacion);

        if (reservaController.agregarReservaHabitacion(idReserva, habitacion)) {
            System.out.println("Habitación agregada a la reserva con éxito.");
        } else {
            System.out.println("Error al agregar habitación a la reserva.");
        }
    }

    private void actualizarReserva() {
        System.out.print("ID de la reserva a actualizar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        // Obtener la reserva existente para acceder a sus habitaciones
        Reserva reservaExistente = reservaController.obtenerReservaPorId(idReserva);
        if (reservaExistente == null) {
            System.out.println("No se encontró la reserva con el ID proporcionado.");
            return;
        }

        // Actualizar datos
        List<Huesped> huespedes = reservaController.obtenerTodosLosHuespedes();
        System.out.println("Seleccione un huésped:");
        for (int i = 0; i < huespedes.size(); i++) {
            System.out.println((i + 1) + ". " + huespedes.get(i).getNombre());
        }
        int seleccionHuesped = scanner.nextInt() - 1;
        Huesped huesped = huespedes.get(seleccionHuesped);

        System.out.print("Nueva cantidad de personas: ");
        int nuevaCantidadPersonas = scanner.nextInt();

        scanner.nextLine(); // Consume nueva línea
        System.out.print("Nueva fecha de inicio (YYYY-MM-DD): ");
        LocalDate nuevaFechaInicio = LocalDate.parse(scanner.nextLine());

        System.out.print("Nueva fecha de fin (YYYY-MM-DD): ");
        LocalDate nuevaFechaFin = LocalDate.parse(scanner.nextLine());

        System.out.print("Nueva fecha de reserva (YYYY-MM-DD): ");
        LocalDate nuevaFechaReserva = LocalDate.parse(scanner.nextLine());

        List<Reserva_Habitacion> habitacionesReservadas = new ArrayList<>(reservaExistente.getHabitacionesReservadas());

        Reserva nuevaReserva = new Reserva(idReserva, huesped, habitacionesReservadas, nuevaCantidadPersonas, nuevaFechaInicio, nuevaFechaFin, nuevaFechaReserva);

        if (reservaController.actualizarReserva(nuevaReserva)) {
            System.out.println("Reserva actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar la reserva.");
        }
    }


    public void listarHabitacionesConReserva() {
        System.out.println("Listar habitaciones con reservas");
        List<Habitacion> habitacionesConReserva = reservaController.obtenerHabitacionesConReserva();

        if (habitacionesConReserva.isEmpty()) {
            System.out.println("No hay habitaciones ocupadas.");
        } else {
            for (Habitacion habitacion : habitacionesConReserva) {
                System.out.println("ID de habitación: " + habitacion.getIdHabitacion());
            }
        }
    }

    public void listarHabitacionesSinReserva() {
        System.out.println("Listar habitaciones sin reservas");
        List<Habitacion> habitacionesSinReserva = reservaController.obtenerHabitacionesSinReserva();

        if (habitacionesSinReserva.isEmpty()) {
            System.out.println("No hay habitaciones disponibles sin reserva.");
        } else {
            for (Habitacion habitacion : habitacionesSinReserva) {
                System.out.println("ID de habitación: " + habitacion.getIdHabitacion());
            }
        }
    }

    public void consultarReservas() {
        System.out.println("=== Consultar Reservas ===");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Consultar habitaciones con reserva");
        System.out.println("2. Consultar habitaciones sin reserva");
        System.out.println("3. Consultar reservas por fecha");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                listarHabitacionesConReserva();
                break;
            case 2:
                listarHabitacionesSinReserva();
                break;
            case 3:
                buscarReservasPorFecha();
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
                break;
        }
    }

    private void buscarReservasPorFecha() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("fecha de inicio para buscar (YYYY-MM-DD): ");
        LocalDate fechaInicio = LocalDate.parse(scanner.nextLine());
        System.out.print("fecha limite en el que buscar (YYYY-MM-DD): ");
        LocalDate fechaFin = LocalDate.parse(scanner.nextLine());

        List<Reserva> reservas = reservaController.obtenerReservaPorFecha(fechaInicio, fechaFin);

        if (reservas.isEmpty()) {
            System.out.println("No se encontraron reservas para las fechas proporcionadas.");
        } else {
            System.out.println("\n*** Reservas Encontradas ***");
            for (Reserva reserva : reservas) {
                System.out.println("ID Reserva: " + reserva.getIdReserva());
                System.out.println("Huésped: " + reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellidoPaterno());
                System.out.println("Fecha Inicio: " + reserva.getFechaInicio());
                System.out.println("Fecha Fin: " + reserva.getFechaFin());
                System.out.println("Cantidad de Personas: " + reserva.getCantidadPersonas());
                System.out.println("Habitaciones Reservadas: ");
                for (Reserva_Habitacion reservaHabitacion : reserva.getHabitacionesReservadas()) {
                    System.out.println("  - Habitación ID: " + reservaHabitacion.getHabitacion().getIdHabitacion());
                }
                System.out.println();
            }
        }
    }
}
