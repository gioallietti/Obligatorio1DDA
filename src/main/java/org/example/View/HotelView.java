package org.example.View;
import org.example.Controller.HotelController;
import org.example.Model.Hotel;
import org.example.Model.Pais;
import org.example.Model.Ciudad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelView {
    private HotelController hotelController;
    private Scanner scanner;



    public HotelView(Scanner scanner) {
        this.hotelController = new HotelController();
        this.scanner = scanner;
    }

    public void manejarHotel(){
        int option = -1;
        do {
            System.out.println("\n----- (: Manejar los Hotel :) -----");
            System.out.println("1. Ingresar un nuevo hotel");
            System.out.println("2. Eliminar hotel");
            System.out.println("3. Modficar hotel");
            System.out.println("4. Consultar hoteles por filtros");
            System.out.println("5. Listar todos los hoteles");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarHotel();
                    break;
                case 2:
                    eliminarHotel();
                    break;
                case 3:
                    actualizarHotel();
                    break;
                case 4:
                    consultarHoteles();
                    break;
                case 5:
                    listarTodosLosHoteles();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    private void agregarHotel() {
        System.out.print("Nombre del hotel: ");
        String nombre = scanner.nextLine();

        List<Pais> paises = hotelController.obtenerTodosLosPaises();
        System.out.println("Seleccione un país:");

        for (int i = 0; i < paises.size(); i++) {
            System.out.println((i + 1) + ". " + paises.get(i).getNombre());
        }

        int seleccionPais = scanner.nextInt() - 1;
        Pais pais = paises.get(seleccionPais);

        List<Ciudad> ciudades = hotelController.obtenerCiudadesPorPais(pais.getIdPais());
        System.out.println("Seleccione una ciudad:");

        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println((i + 1) + ". " + ciudades.get(i).getNombre());
        }

        int seleccionCiudad = scanner.nextInt() - 1;
        Ciudad ciudad = ciudades.get(seleccionCiudad);

        System.out.print("Cantidad de estrellas: ");
        int estrellas = scanner.nextInt();


        Hotel hotel = new Hotel(0, nombre, ciudad, pais, estrellas);
        if (hotelController.agregarHotel(hotel)) {
            System.out.println("Hotel agregado correctamente.");
        } else {
            System.out.println("Error al agregar el hotel.");
        }
    }

    private void eliminarHotel() {
        System.out.print("ID del hotel a eliminar: ");
        int idHotel = scanner.nextInt();
        if (hotelController.eliminarHotel(idHotel)) {
            System.out.println("Hotel eliminado correctamente.");
        } else {
            System.out.println("Error al eliminar el hotel.");
        }
    }

    private void actualizarHotel() {
        System.out.print("ID del hotel a actualizar: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre del hotel: ");
        String nombre = scanner.nextLine();

        List<Pais> paises = hotelController.obtenerTodosLosPaises();
        System.out.println("Seleccione un país:");

        for (int i = 0; i < paises.size(); i++) {
            System.out.println((i + 1) + ". " + paises.get(i).getNombre());
        }

        int seleccionPais = scanner.nextInt() - 1;
        Pais pais = paises.get(seleccionPais);


        List<Ciudad> ciudades = hotelController.obtenerCiudadesPorPais(pais.getIdPais());
        System.out.println("Seleccione una ciudad:");

        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println((i + 1) + ". " + ciudades.get(i).getNombre());
        }

        int seleccionCiudad = scanner.nextInt() - 1;
        Ciudad ciudad = ciudades.get(seleccionCiudad);

        System.out.print("Nueva cantidad de estrellas: ");
        int estrellas = scanner.nextInt();

        Hotel hotel = new Hotel(idHotel, nombre, ciudad, pais, estrellas);
        if (hotelController.actualizarHotel(hotel)) {
            System.out.println("Hotel actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar el hotel.");
        }
    }

    private void consultarHoteles() {
        System.out.println("\n----- Consultar Hoteles -----");
        System.out.println("1. Por pai");
        System.out.println("2. Por nombre");
        System.out.println("3. Por cantidad de estrellas");
        System.out.print("Seleccione un filtro: ");
        int filtro = scanner.nextInt();
        scanner.nextLine();

        List<Hotel> hoteles = new ArrayList<>();
        switch (filtro) {
            case 1:
                System.out.print("Ingrese el nombre del país: ");
                String nombrePais = scanner.nextLine();
                hoteles = hotelController.obtenerHotelesPorPais(nombrePais);
                break;
            case 2:
                System.out.print("Ingrese el nombre del hotel: ");
                String nombreHotel = scanner.nextLine();
                hoteles = hotelController.obtenerHotelesPorNombre(nombreHotel);
                break;
            case 3:
                System.out.print("Ingrese la cantidad de estrellas: ");
                int estrellas = scanner.nextInt();
                hoteles = hotelController.obtenerHotelesPorEstrellas(estrellas);
                break;
            default:
                System.out.println("Filtro no válido.");
                return;
        }

        if (hoteles.isEmpty()) {
            System.out.println("No se encontraron hoteles con el filtro seleccionado.");
        } else {
            System.out.println("\nHoteles encontrados:");
            for (Hotel hotel : hoteles) {
                System.out.println("- " + hotel.getNombre() + " en " + hotel.getCiudad().getNombre() +
                        ", " + hotel.getPais().getNombre() + " con " + hotel.getEstrellas() + " estrellas.");
            }
        }
    }

    private void listarTodosLosHoteles() {
        List<Hotel> hoteles = hotelController.obtenerTodosLosHoteles();
        if (hoteles.isEmpty()) {
            System.out.println("No se encontraron hoteles.");
        } else {
            System.out.println("\nHoteles disponibles:");
            for (Hotel hotel : hoteles) {
                System.out.println("- " + hotel.getIdHotel() + " Nombre:" + hotel.getNombre() + " en " + hotel.getCiudad().getNombre() + ", "
                        + hotel.getPais().getNombre() + " con "
                        + hotel.getEstrellas() + " estrellas.");
            }
        }
    }
}
