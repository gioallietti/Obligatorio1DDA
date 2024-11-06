package org.example;

import org.example.View.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HotelView hotelView;
        HabitacionView habitacionView;
        TarifaView tarifaView;
        HuespedView huespedView;
        ReservaView reservaView;
        Scanner scanner = new Scanner(System.in);

        int option = -1;

        do {
            System.out.println("\n----- Menú principal -----");
            System.out.println("1. Gestionar Hoteles");
            System.out.println("2. Gestionar Habitaciones");
            System.out.println("3. Gestionar tarifas");
            System.out.println("4. Gestionar huespedes");
            System.out.println("5. Gestionar reservas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    hotelView = new HotelView(scanner);
                    hotelView.manejarHotel();
                case 2:
                    habitacionView = new HabitacionView(scanner);
                    habitacionView.manejarHabitacion();
                case 3:
                    tarifaView = new TarifaView(scanner);
                    tarifaView.manejarTarifa();
                case 4:
                    huespedView = new HuespedView(scanner);
                    huespedView.manejarHuesped();
                case 5:
                    reservaView = new ReservaView(scanner);
                    reservaView.manejarReserva();
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }
}