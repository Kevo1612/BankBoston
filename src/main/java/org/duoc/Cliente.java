package org.duoc;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    //Atributos
    private String rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private String comuna;
    private String telefono;
    private List<Cuenta> cuentas; // Lista de cuentas asociadas al cliente

    //Constructor
    public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio,
                   String comuna, String telefono) {
        if(rut.length() < 11 || rut.length() > 12){
            throw new IllegalArgumentException("Escriba un rut válido");
        }
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        this.cuentas = new ArrayList<>();
    }

    public String getRut(){
        return rut;
    }

    public List<Cuenta> getCuentas(){
        return cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public Cuenta buscarCuentaPorNumero(int numeroCuenta) {
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta() == numeroCuenta) {
                return c;
            }
        }
        return null;
    }

    public boolean tieneCuentaDeTipo(Class<?> tipoCuenta) {
        for (Cuenta c : cuentas) {
            if (c.getClass().equals(tipoCuenta)) {
                return true;
            }
        }
        return false;
    }

    public void mostrarDatos() {
        System.out.println("Rut: " + rut);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido Paterno: " + apellidoPaterno);
        System.out.println("Apellido Materno: " + apellidoMaterno);
        System.out.println("Domicilio: " + domicilio);
        System.out.println("Comuna: " + comuna);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Cuentas:");
        for (Cuenta cuenta : cuentas) {
            System.out.println("  Número de Cuenta: " + cuenta.getNumeroCuenta() + " | Saldo: $" + cuenta.getSaldo());
        }
    }
}
