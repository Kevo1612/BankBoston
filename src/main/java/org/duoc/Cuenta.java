package org.duoc;

public abstract class Cuenta {
    //Atributos
    private static int contadorCuentas = 100000001; // Número de cuenta inicial (se puede cambiar)
    private int numeroCuenta;
    private int saldo = 0;

    //Constructor sin parámetros que genera número de cuenta automáticamente
    public Cuenta() {
        this.numeroCuenta = contadorCuentas++;
        this.saldo = 0;
    }

    //Getters y Setters

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    //Métodos
    public void depositar(int monto){
        if(monto > 0){
            saldo += monto;
        } else {
            System.out.println("El monto debe ser mayor que 0");
        }
    }

    public void girar(int monto){
        if(monto > 0 && monto <= saldo){
            saldo -= monto;
            System.out.println("Giro existoso.");
        } else if( monto > saldo ) {
            System.out.println("El monto no puede ser superior al saldo disponible en la cuenta.");
        } else {
            System.out.println("El monto debe ser mayor que cero.");
        }
    }
}
