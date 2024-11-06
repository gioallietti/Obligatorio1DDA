package org.example.View;
import org.example.Controller.HuespedController;
import org.example.Model.Pais;
import org.example.Model.Huesped;

import org.example.Model.TipoDocumento;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class HuespedView {
    private final HuespedController huespedController;
    private final Scanner scanner;

    public HuespedView(Scanner scanner) {
        this.huespedController = new HuespedController();
        this.scanner = scanner;
    }

    public void manejarHuesped(){
        int option;
        do {
            System.out.println("\n----- (: Manejar los Huéspedes :) -----");
            System.out.println("1. Ingresar un nuevo huesped");
            System.out.println("2. Eliminar un huesped");
            System.out.println("3. Actualizar huesped");
            System.out.println("4. Listar todos los huespedes :)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarHuesped();
                    break;
                case 2:
                    eliminarHuesped();
                    break;
                case 3:
                    actualizarHuesped();
                    break;
                case 4:
                    listarHuespedes();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    private LocalDate obtenerFechaNacimiento() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = null;

        while (true) {
            System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
            String fechaInput = scanner.nextLine();

            try {
                fechaNacimiento = LocalDate.parse(fechaInput, formatoFecha);
                if (!fechaNacimiento.isAfter(LocalDate.now())) {
                    break;
                } else {
                    System.out.println("La fecha de nacimiento no puede ser mayor a la fecha actual. Intente nuevamente.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, use el formato YYYY-MM-DD.");
            }
        }
        return fechaNacimiento;
    }

    private void agregarHuesped() {
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Ingrese el apellido materno: ");
        String apellidoMaterno = scanner.nextLine();

        List<TipoDocumento> tiposDocumento = huespedController.obtenerTodosLosTiposDocumentos();
        System.out.println("Seleccione el tipo de documento:");
        for (int i = 0; i < tiposDocumento.size(); i++) {
            System.out.println((i + 1) + ". " + tiposDocumento.get(i).getDescripcion());
        }
        System.out.print("Ingrese el número de la opción: ");
        int opcionTipoDocumento = scanner.nextInt();
        scanner.nextLine();
        TipoDocumento tipoDocumento = tiposDocumento.get(opcionTipoDocumento - 1);

        System.out.print("Ingrese el número de documento: ");
        String numeroDocumento = scanner.nextLine();


        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        List<Pais> paises = huespedController.obtenerTodosLosPaises();
        System.out.println("Seleccione el país:");
        for (int i = 0; i < paises.size(); i++) {
            System.out.println((i + 1) + ". " + paises.get(i).getNombre());
        }
        System.out.print("Ingrese el número de la opción: ");
        int opcionPais = scanner.nextInt();
        scanner.nextLine();
        Pais pais = paises.get(opcionPais - 1);

        System.out.print("Ingrese la fecha de nacimiento (formato: YYYY-MM-DD): ");
        LocalDate fechaNacimiento1 = obtenerFechaNacimiento();

        Date fechaNacimiento = Date.valueOf(fechaNacimiento1);

        Huesped huesped = new Huesped(0, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, telefono, pais, fechaNacimiento);
        if (huespedController.agregarHuesped(huesped)) {
            System.out.println("Huésped agregado correctamente.");
        } else {
            System.out.println("Error al agregar el huésped.");
        }
    }

    private void eliminarHuesped() {
        System.out.print("Ingrese el ID del huésped a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (huespedController.eliminarHuesped(id)) {
            System.out.println("Huésped eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún huésped con el ID especificado.");
        }
    }

    private void actualizarHuesped() {
        System.out.print("ID del huésped a actualizar: ");
        int idHuesped = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();
        System.out.print("Ingrese el apellido materno: ");
        String apellidoMaterno = scanner.nextLine();

        List<TipoDocumento> tiposDocumento = huespedController.obtenerTodosLosTiposDocumentos();
        System.out.println("Seleccione el tipo de documento:");
        for (int i = 0; i < tiposDocumento.size(); i++) {
            System.out.println((i + 1) + ". " + tiposDocumento.get(i).getDescripcion());
        }
        System.out.print("Ingrese el número de la opción: ");
        int opcionTipoDocumento = scanner.nextInt();
        scanner.nextLine();
        TipoDocumento tipoDocumento = tiposDocumento.get(opcionTipoDocumento - 1);

        System.out.print("Ingrese el número de documento: ");
        String numeroDocumento = scanner.nextLine();

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        List<Pais> paises = huespedController.obtenerTodosLosPaises();
        System.out.println("Seleccione el país:");
        for (int i = 0; i < paises.size(); i++) {
            System.out.println((i + 1) + ". " + paises.get(i).getNombre());
        }
        System.out.print("Ingrese el número de la opción: ");
        int opcionPais = scanner.nextInt();
        scanner.nextLine();
        Pais pais = paises.get(opcionPais - 1);

        System.out.print("Ingrese la fecha de nacimiento (formato: YYYY-MM-DD): ");
        LocalDate fechaNacimiento1 = obtenerFechaNacimiento();
        Date fechaNacimiento = Date.valueOf(fechaNacimiento1);

        Huesped huesped = new Huesped(idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, telefono, pais, fechaNacimiento);
        if (huespedController.actualizarHuesped(huesped)) {
            System.out.println("Huésped actualizado correctamente.");
        } else {
            System.out.println("No se encontró ningún huésped con el ID especificado.");
        }
    }

    private void listarHuespedes() {
        List<Huesped> huespedes = huespedController.obtenerTodosLosHuespedes();

        if (huespedes.isEmpty()) {
            System.out.println("No hay huéspedes registrados.");
        } else {
            System.out.println("\n--- Lista de Huéspedes ---");
            for (Huesped huesped : huespedes) {
                System.out.println("ID: " + huesped.getIdHuesped());
                System.out.println("Nombre: " + huesped.getNombre() + " " + huesped.getApellidoPaterno() + " " + huesped.getApellidoMaterno());
                System.out.println("Tipo de Documento: " + huesped.getTipoDocumento().getDescripcion());
                System.out.println("Número de Documento: " + huesped.getNumDocumento());
                System.out.println("Teléfono: " + huesped.getTelefono());
                System.out.println("País: " + huesped.getPais().getNombre());
                System.out.println("Fecha de Nacimiento: " + huesped.getFechaNacimiento());
                System.out.println("------------------------------");
            }
        }
    }
}
