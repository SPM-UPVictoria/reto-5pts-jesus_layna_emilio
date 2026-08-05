package com.ecopay.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CuentaBaseTest {
    @Test
    public void crearCuentaCorrectamente() throws TransaccionInvalidaException{
        CuentaBilletera cuentaBase =new CuentaBilletera(
            "1927273890",
            "jesus",
            500.0,
            100

        );
        assertEquals(500.0, cuentaBase.getSaldo());
        assertEquals("jesus",cuentaBase.getTitular());
        assertEquals("1927273890", cuentaBase.getNumeroCuenta());

    }
    @Test 
    public void numeroCuentaInvalido(){
        assertThrows(
            TransaccionInvalidaException.class, 
            ()->{
                new CuentaBilletera(
                    "12132",
                    "Jose",
                    700.0,
                    10
                );
            }
        );
    }
    @Test 
    public void saldoNegativo (){
        assertThrows(
            TransaccionInvalidaException.class, 
            ()->{
                new CuentaBilletera(
                    "1291292190",
                    "Cesia",
                    -700.0,
                    0
                ); 
            }
        );
    }
    @Test
    public void titularVacio(){
        assertThrows(
            TransaccionInvalidaException.class, 
            ()->{
                new CuentaBilletera("1112222121", "", 300, 10);
            });
    }
    @Test 
    public void titularSoloEspacion(){
        assertThrows(
            TransaccionInvalidaException.class, 
            ()->{
                new CuentaBilletera("1111111111", "       ", 100, 20);
            }
        );
    }
    @Test
    public void titularnumeros(){
        assertThrows(
            TransaccionInvalidaException.class, 
            ()->{
                new CuentaBilletera("121121EE112", "Isamar", 300, 50);
            });
    }
    @Test
    public void titualar9(){
        assertThrows(
            TransaccionInvalidaException.class, 
            ()->{
                new CuentaBilletera("121212121", "Luis", 900, 90);
            });
    } 
    @Test 
    public void titular11(){
        assertThrows(
            TransaccionInvalidaException.class, 
            ()->{
                new CuentaBilletera("12121875672", "Lety", 1000, 80);

            });
    }
    @Test
    public void saldo0() throws TransaccionInvalidaException {
        CuentaBilletera cuenta = new CuentaBilletera(
            "1212121212",
            "Jesus",
            0.0,
            90
        );

        assertEquals(0.0, cuenta.getSaldo());
    }
}