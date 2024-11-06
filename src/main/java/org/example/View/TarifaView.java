package org.example.View;

import org.example.Controller.TarifaController;
import org.example.Model.Hotel;
import org.example.Model.Tarifa;
import org.example.Model.TipoHabitacion;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class TarifaView {
    private TarifaController tarifaController;
    private Scanner scanner;

    public TarifaView(Scanner scanner) {
        this.scanner = scanner;
        this.tarifaController = new TarifaController();
    }

    public void manejarTarifa() {
        int option = -1;
        do {
            System.out.println("\n----- (: Manejar las tarifas de las habitaciones :) -----");
            System.out.println("1. Ingresar una nueva tarifa");
            System.out.println("2. Elimunar tarifa");
            System.out.println("3. Modficar tarida");
            //System.out.println("4. Listar taridas");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarTarifa();
                    break;
                case 2:
                    eliminarTarifa();
                    break;
                case 3:
                    actualizarTarifa();
                    break;
                //case 4:
                    //listarTarifas();
                    //break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    private void agregarTarifa() {
        System.out.println("Ingrese el precio de la tarifa: ");
        int precio = scanner.nextInt();

        System.out.println("Ingrese la fecha de inicio (AAAA-MM-DD): ");
        Date fechaInicio = Date.valueOf(scanner.next());

        System.out.println("Ingrese la fecha de fin (AAAA-MM-DD): ");
        Date fechaFin = Date.valueOf(scanner.next());

        // verifica a ver que la fecha de inicio no sea mayor que la fecha de fin
        if (fechaInicio.after(fechaFin)) {
            System.out.println("La fecha de inicio no puede ser mayor que la fecha de fin.");
            return;
        }

        System.out.println("Ingrese el tipo de habitacion: ");
        List<TipoHabitacion> tiposHabitacion = tarifaController.obtenerTodosLosTiposHabitacion();
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

        System.out.println("Ingrese el hotel: ");
        List<Hotel> hoteles = tarifaController.obtenerTodosLosHoteles();
        if (hoteles.isEmpty()) {
            System.out.println("No hay hoteles disponibles. Agrega hoteles antes de continuar.");
            return;
        }
        System.out.println("Selecciona el hotel: ");
        for (int i = 0; i < hoteles.size(); i++) {
            System.out.println((i + 1) + ". " + hoteles.get(i).getNombre());
        }
        int hotelIndex = scanner.nextInt() - 1;
        if (hotelIndex < 0 || hotelIndex >= hoteles.size()) {
            System.out.println("Opción de hotel no válida.");
            return;
        }
        Hotel hotel = hoteles.get(hotelIndex);

        Tarifa tarifa = new Tarifa(0, precio, fechaInicio, fechaFin, tipoHabitacion, hotel);
        if (TarifaController.agregarTarifa(tarifa)) {
            System.out.println("Tarifa creada con exito.");
        } else {
            System.out.println("Error al crear la tarifa.");
        }
    }

    private void eliminarTarifa() {
        System.out.print("ID de la tarifa a eliminar: ");
        int idTarifa = scanner.nextInt();
        if (tarifaController.eliminarTarifa(idTarifa)) {
            System.out.println("Tarifa eliminada correctamente.");
        } else {
            System.out.println("Error al eliminar la tarifa");
        }
    }

    private void actualizarTarifa() {
        System.out.println("Actualizar tarifa");
        System.out.println("Ingrese el ID de la tarifa a actualizar:");
        int idTarifa = scanner.nextInt();

        Tarifa tarifaExistente = tarifaController.obtenerTarifaPorId(idTarifa);
        if (tarifaExistente == null) {
            System.out.println("La tarifa con ID " + idTarifa + " no existe.");
            return;
        }

        System.out.println("Ingrese el nuevo precio de la tarifa: ");
        int precio = scanner.nextInt();



        System.out.println("Ingrese la nueva fecha de inicio (YYYY-MM-DD): ");
        Date fechaInicio = Date.valueOf(scanner.next());

        System.out.println("Ingrese la nueva fecha de fin (YYYY-MM-DD): ");
        Date fechaFin = Date.valueOf(scanner.next());

        if (fechaInicio.after(fechaFin)) {
            System.out.println("La fecha de inicio no puede ser mayor que la fecha de fin.");
            return;
        }

        System.out.println("Selecciona el tipo de habitación:");
        List<TipoHabitacion> tiposHabitacion = tarifaController.obtenerTodosLosTiposHabitacion();
        if (tiposHabitacion.isEmpty()) {
            System.out.println("No hay tipos de habitación disponibles. Agrega tipos de habitación antes de continuar.");
            return;
        }
        for (int i = 0; i < tiposHabitacion.size(); i++) {
            System.out.println((i + 1) + ". " + tiposHabitacion.get(i).getNombre());
        }
        int tipoHabitacionIndex = scanner.nextInt() - 1;
        if (tipoHabitacionIndex < 0 || tipoHabitacionIndex >= tiposHabitacion.size()) {
            System.out.println("Opción de tipo de habitación no válida.");
            return;
        }
        TipoHabitacion tipoHabitacion = tiposHabitacion.get(tipoHabitacionIndex);

        System.out.println("Ingrese el hotel: ");
        List<Hotel> hoteles = tarifaController.obtenerTodosLosHoteles();
        if (hoteles.isEmpty()) {
            System.out.println("No hay hoteles disponibles. Agrega hoteles antes de continuar.");
            return;
        }
        for (int i = 0; i < hoteles.size(); i++) {
            System.out.println((i + 1) + ". " + hoteles.get(i).getNombre());
        }
        int hotelIndex = scanner.nextInt() - 1;
        if (hotelIndex < 0 || hotelIndex >= hoteles.size()) {
            System.out.println("Opción de hotel no válida.");
            return;
        }
        Hotel hotel = hoteles.get(hotelIndex);

        Tarifa tarifa = new Tarifa(idTarifa, precio, fechaInicio, fechaFin, tipoHabitacion, hotel);
        if (tarifaController.actualizarTarifa(tarifa)) {
            System.out.println("Tarifa actualizada con éxito.");
        } else {
            System.out.println("Error al actualizar la tarifa.");
        }
    }
}
