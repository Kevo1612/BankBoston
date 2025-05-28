package org.duoc.TiposDeCuentas;
import org.duoc.Cuenta;

public class CuentaCorriente extends Cuenta {
    // Atributos específicos de CuentaCorriente
    private double sobregiroPermitido;

    // Constructor que inicializa el número de cuenta y el sobregiro permitido
    public CuentaCorriente(double sobregiroPermitido) {
        super(); // Llama al constructor de la clase base Cuenta
        this.sobregiroPermitido = sobregiroPermitido;
    }

    // Método para girar dinero, considerando el sobregiro permitido
    @Override
    public void girar(int monto) {
        if (monto > 0 && (getSaldo() + sobregiroPermitido) >= monto) {
            super.girar(monto);
        } else if (monto > (getSaldo() + sobregiroPermitido)) {
            System.out.println("El monto no puede ser superior al saldo más el sobregiro permitido.");
        } else {
            System.out.println("El monto debe ser mayor que cero.");
        }
    }

    // Getter para el sobregiro permitido
    public double getSobregiroPermitido() {
        return sobregiroPermitido;
    }

}
