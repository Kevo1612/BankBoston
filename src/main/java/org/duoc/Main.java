package org.duoc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankBostonApp app = new BankBostonApp();
        int opcion;

        do{
            mostrarMenu();
            while (!sc.hasNextInt()) {
                System.out.println("Por favor, ingrese una opcion válida.");
                sc.next(); // descarta la opcion inválida
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1 -> app.registrarCliente(sc);
                case 2 -> app.verDatosCliente(sc);
                case 3 -> app.realizarDeposito(sc);
                case 4 -> app.realizarGiro(sc);
                case 5 -> app.consultarSaldo(sc);
                case 6 -> System.out.println("Gracias por usar Bank Boston.");
                default -> System.out.println("Opción no válida");
            }
        } while(opcion != 6);
        sc.close();
    }

    public static void mostrarMenu(){
        String border = "\n==============================";
        System.out.println(border);
        System.out.println("== Bienvenido a Bank Boston ==");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Ver datos de cliente");
        System.out.println("3. Depositar");
        System.out.println("4. Girar");
        System.out.println("5. Consultar saldo");
        System.out.println("6. Salir");
        System.out.println(border);
        System.out.println("\n>Seleccione una opción");
    }
}