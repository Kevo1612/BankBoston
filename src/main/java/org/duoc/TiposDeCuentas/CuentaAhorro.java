package org.duoc.TiposDeCuentas;

import org.duoc.Cuenta;

public class CuentaAhorro extends Cuenta  {
    // Atributos específicos de CuentaAhorro
    private double tasaInteres;

    // Constructor que inicializa el número de cuenta y la tasa de interés
    public CuentaAhorro(double tasaInteres) {
        super(); // Llama al constructor de la clase base Cuenta
        this.tasaInteres = tasaInteres;
    }

    // Método para calcular y aplicar intereses al saldo
    public void aplicarIntereses() {
        int interes = (int) (getSaldo() * tasaInteres / 100);
        depositar(interes);
        System.out.println("Intereses aplicados: " + interes);
    }

    // Getter para la tasa de interés
    public double getTasaInteres() {
        return tasaInteres;
    }
}
