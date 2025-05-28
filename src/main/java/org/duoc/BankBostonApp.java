package org.duoc;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Scanner;

public class BankBostonApp {
    private ArrayList<Cliente> clientes;

    public BankBostonApp() {
        clientes = new ArrayList<>();
    }

    public void registrarCliente(Scanner sc){
        System.out.println("Ingrese su RUT(Con puntos y con guíon)");
        String rut = sc.nextLine();

        Cliente cliente = buscarCliente(rut);
        if(cliente == null){
            // Registrar nuevo cliente
            System.out.println("Ingrese solo su nombre");
            String nombre = sc.nextLine();
            System.out.println("Ingrese su apellido paterno");
            String apellidoPaterno = sc.nextLine();
            System.out.println("Ingrese su apellido materno");
            String apellidoMaterno = sc.nextLine();
            System.out.println("Ingrese su domicilio");
            String domicilio = sc.nextLine();
            System.out.println("Ingrese su comuna");
            String comuna = sc.nextLine();
            System.out.println("Ingrese su teléfono");
            String telefono = sc.nextLine();

            try {
                cliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna, telefono);
                clientes.add(cliente);
                System.out.println("Cliente registrado con éxito.");
            } catch(IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Cliente ya existe. Se agregará una nueva cuenta.");
        }

        // Registrar una nueva cuenta para el cliente
        System.out.println("Seleccione el tipo de cuenta:");
        System.out.println("1. Cuenta Ahorro");
        System.out.println("2. Cuenta Corriente");
        System.out.println("3. Cuenta Crédito");
        int tipoCuenta = Integer.parseInt(sc.nextLine());

        Cuenta cuenta = null;
        Class<?> tipoClaseCuenta = null;
        switch (tipoCuenta) {
            case 1 -> {
                tipoClaseCuenta = org.duoc.TiposDeCuentas.CuentaAhorro.class;
                if (cliente.tieneCuentaDeTipo(tipoClaseCuenta)) {
                    System.out.println("El cliente ya tiene una Cuenta Ahorro.");
                    return;
                }
                System.out.print("Ingrese la tasa de interés (%): ");
                double tasa = Double.parseDouble(sc.nextLine());
                cuenta = new org.duoc.TiposDeCuentas.CuentaAhorro(tasa);
            }
            case 2 -> {
                tipoClaseCuenta = org.duoc.TiposDeCuentas.CuentaCorriente.class;
                if (cliente.tieneCuentaDeTipo(tipoClaseCuenta)) {
                    System.out.println("El cliente ya tiene una Cuenta Corriente.");
                    return;
                }
                System.out.print("Ingrese el sobregiro permitido: ");
                double sobregiro = Double.parseDouble(sc.nextLine());
                cuenta = new org.duoc.TiposDeCuentas.CuentaCorriente(sobregiro);
            }
            case 3 -> {
                tipoClaseCuenta = org.duoc.TiposDeCuentas.CuentaCredito.class;
                if (cliente.tieneCuentaDeTipo(tipoClaseCuenta)) {
                    System.out.println("El cliente ya tiene una Cuenta Crédito.");
                    return;
                }
                System.out.print("Ingrese el cupo de crédito: ");
                int cupo = Integer.parseInt(sc.nextLine());
                cuenta = new org.duoc.TiposDeCuentas.CuentaCredito(cupo);
            }
            default -> {
                System.out.println("Tipo de cuenta no válido. Se creará una Cuenta Ahorro por defecto con 0% interés.");
                tipoClaseCuenta = org.duoc.TiposDeCuentas.CuentaAhorro.class;
                if (cliente.tieneCuentaDeTipo(tipoClaseCuenta)) {
                    System.out.println("El cliente ya tiene una Cuenta Ahorro.");
                    return;
                }
                cuenta = new org.duoc.TiposDeCuentas.CuentaAhorro(0);
            }
        }

        cliente.agregarCuenta(cuenta);
        System.out.println("Cuenta registrada con éxito. Su número de cuenta es: " + cuenta.getNumeroCuenta());
    }

    public void verDatosCliente(Scanner sc){
        Cliente cliente = solicitarCliente(sc);
        if (cliente != null) cliente.mostrarDatos();
    }

    public void realizarDeposito(Scanner sc) {
        Cliente cliente = solicitarCliente(sc);
        if (cliente != null) {
            System.out.print("Ingrese el número de cuenta: ");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = cliente.buscarCuentaPorNumero(numeroCuenta);
            if (cuenta == null) {
                System.out.println("Cuenta no encontrada.");
                return;
            }
            System.out.print("Ingrese monto a depositar: ");
            int monto = sc.nextInt();
            sc.nextLine();
            cuenta.depositar(monto);
            System.out.println("Deposito realizado con éxito.");
            System.out.println("Su saldo actual es de: $" + cuenta.getSaldo());
        }
    }

    public void realizarGiro(Scanner sc) {
        Cliente cliente = solicitarCliente(sc);
        if (cliente != null) {
            System.out.print("Ingrese el número de cuenta: ");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = cliente.buscarCuentaPorNumero(numeroCuenta);
            if (cuenta == null) {
                System.out.println("Cuenta no encontrada.");
                return;
            }
            System.out.print("Ingrese monto a girar: ");
            int monto = sc.nextInt();
            sc.nextLine();
            cuenta.girar(monto);
            System.out.println("Su saldo actual es de: $" + cuenta.getSaldo());
        }
    }

    public void consultarSaldo(Scanner sc) {
        Cliente cliente = solicitarCliente(sc);
        if (cliente != null) {
            System.out.print("Ingrese el número de cuenta: ");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = cliente.buscarCuentaPorNumero(numeroCuenta);
            if (cuenta != null) {
                System.out.println("Saldo actual: $" + cuenta.getSaldo());
            } else {
                System.out.println("Cuenta no encontrada.");
            }
        }
    }

    private Cliente solicitarCliente(Scanner sc) {
        System.out.print("Ingrese RUT del cliente: ");
        System.out.print("Con puntos y con guíon (ej: 12.345.678-9): ");
        String rut = sc.nextLine();
        Cliente cliente = buscarCliente(rut);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        }
        return cliente;
    }

    private @Nullable Cliente buscarCliente(String rut) {
        for (Cliente c : clientes) {
            if (c.getRut().equalsIgnoreCase(rut)) {
                return c;
            }
        }
        return null;
    }

}
