package org.duoc.TiposDeCuentas;
import org.duoc.Cuenta;

public class CuentaCredito extends Cuenta {
    // Atributos específicos de CuentaCredito
    private int cupoCredito;
    private int saldoDeuda;

    // Constructor
    public CuentaCredito(int cupoCredito) {
        super();
        this.cupoCredito = cupoCredito;
        this.saldoDeuda = 0;
    }

    public int getCupoCredito() {
        return cupoCredito;
    }

    public void setCupoCredito(int cupoCredito) {
        this.cupoCredito = cupoCredito;
    }

    public int getSaldoDeuda() {
        return saldoDeuda;
    }

    // Métodos específicos de CuentaCredito
    public void usarCredito(int monto) {
        if (monto > 0 && (saldoDeuda + monto) <= cupoCredito) {
            saldoDeuda += monto;
            System.out.println("Uso de crédito exitoso. Saldo de deuda: " + saldoDeuda);
        } else if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
        } else {
            System.out.println("El monto excede el cupo de crédito disponible.");
        }
    }

    public void pagarDeuda(int monto) {
        if (monto > 0 && monto <= saldoDeuda) {
            saldoDeuda -= monto;
            System.out.println("Pago de deuda exitoso. Saldo de deuda restante: " + saldoDeuda);
        } else if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
        } else {
            System.out.println("El monto excede el saldo de deuda actual.");
        }
    }
    
}
