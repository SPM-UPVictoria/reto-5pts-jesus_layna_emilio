    package com.ecopay.core;
    import com.ecopay.core.*;
    import org.junit.jupiter.api.Test;

    import static org.junit.jupiter.api.Assertions.*;

    public class CuentaBilleteraTest {

    @Test
        public void deposito() throws TransaccionInvalidaException {
            CuentaBilletera cuenta = new CuentaBilletera(
                "1212121212",
                "Jesus",
                500,
                100
            );
            cuenta.depositar(300);

            assertEquals(800, cuenta.getSaldo());
        }
        @Test
        public void depositoNegativo(){
            assertThrows(
                TransaccionInvalidaException.class,
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                    "1212121212", 
                    "Jesus",
                    500,
                    100
                );
                    cuenta.depositar(-100);
                }
            );
        }
        @Test 
        public void retirar () throws TransaccionInvalidaException{
            CuentaBilletera cuenta = new CuentaBilletera(
                "1112121212",
                "Jesus",
                500, 
                90
            );
            cuenta.retirar(200);
            assertEquals(300, cuenta.getSaldo());

        }
        @Test
        public void retiroNegativo  () throws TransaccionInvalidaException{
            assertThrows(
                TransaccionInvalidaException.class, 
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                        "1212121212", "Jesus", 600, 90
                    );
                    cuenta.retirar(-100);

                }
            );
        }
        @Test
        public void retiroMayorSaldo () throws TransaccionInvalidaException{
            assertThrows(TransaccionInvalidaException.class, 
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                        "1212121212", "Jesus", 900, 90
                    );
                    cuenta.retirar(1000);

                });
        }

        @Test 
        public void depositar0 () throws TransaccionInvalidaException{
            assertThrows(TransaccionInvalidaException.class, 
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                        "1212121212",
                        "Jesus",
                        900,
                        90
                    );
                    cuenta.depositar(0);
                }
            );
        }
        @Test
        public void puntoslealtaInvalidos(){
            assertThrows(
                TransaccionInvalidaException.class, 
                ()->{
                    new CuentaBilletera("1212121212", "Jesus", 90, -10);
                });
        }
        @Test 
        public void limiteMaximo ()throws TransaccionInvalidaException{
            CuentaBilletera cuenta = new CuentaBilletera (
                "1212121212",
                "Jesus",
                49900,
                90
            );
            assertThrows(
                TransaccionInvalidaException.class, 
                ()->{
                    cuenta.depositar(200);
                });
        }
        @Test
        public void compra() throws TransaccionInvalidaException {
            CuentaBilletera cuenta = new CuentaBilletera(
                "1212211212",
                "Jesus",
                1000,
                90
            );

            cuenta.realizarCompra(200);

            assertEquals(800, cuenta.getSaldo());
            assertEquals(110, cuenta.getPuntosLealtad());
        }
        @Test 
        public void compranegativa() throws TransaccionInvalidaException{
            assertThrows(
                TransaccionInvalidaException.class,
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                        "1212121212", 
                        "Jesus", 
                        900, 
                        90
                    );
                    cuenta.realizarCompra(-200);


                }
            );
        }
        @Test 
        public void realizarTransferencia ()throws TransaccionInvalidaException{
            CuentaBilletera cuenta1 = new CuentaBilletera(
                "1212121212", 
                "Jesus", 
                900, 
                80
            );
            CuentaBilletera cuenta2 = new CuentaBilletera(
                "1212212121", 
                "Jose", 
                500, 
                90
            );
            cuenta1.transferir(cuenta2, 400);
            assertEquals(500, cuenta1.getSaldo());
            assertEquals(900, cuenta2.getSaldo());

        }
        @Test
        public void tranferiMismaCuenta () throws TransaccionInvalidaException{
            CuentaBilletera cuenta = new CuentaBilletera(
                "1212121212", 
                "Jesus", 
                900, 
                90
            );
            assertThrows(
                TransaccionInvalidaException.class, 
                ()->{
                    cuenta.transferir(cuenta, 100);

                }
            );
        }
        @Test
        public void canjearPuntos() throws TransaccionInvalidaException{
            CuentaBilletera cuenta = new CuentaBilletera(
            "1212121212", 
            "Jesus", 
            900, 
            100
        );
        cuenta.canjearPuntosPorSaldo(50);
        assertEquals(905, cuenta.getSaldo());
        assertEquals(50, cuenta.getPuntosLealtad());

        }

        @Test 
        public void canjeraPuntosInvalidos(){
            assertThrows(
                TransaccionInvalidaException.class, 
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                        "1122112211", 
                        "Jesus", 
                        900, 
                        50
                    );
                    cuenta.canjearPuntosPorSaldo(100);
                });
        }
        @Test
        public void canjearPuntosNoMultiplos10(){
            assertThrows(
                TransaccionInvalidaException.class,
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                    "1212121212",
                    "Jesus", 
                    900, 
                    90
                );
                cuenta.canjearPuntosPorSaldo(15);
                    
                    
                 
            });
        }
        @Test 
        public void agregarPuntos () throws TransaccionInvalidaException{
                CuentaBilletera cuenta = new CuentaBilletera(
                    "1212121212", 
                    "Jesus", 
                    900, 
                    70
                );
                cuenta.agregarPuntos(10);
                assertEquals(80, cuenta.getPuntosLealtad());

            }
        @Test 
        public void agregarPuntosNegativos() throws TransaccionInvalidaException{
            assertThrows(
                TransaccionInvalidaException.class, 
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                    "1212121212", 
                    "Jesus", 
                    900, 
                    90
                );
                cuenta.agregarPuntos(-20);
                }
            );
        }
        @Test
        public void compraVIP () throws TransaccionInvalidaException{
            CuentaBilletera cuenta = new CuentaBilletera(
                "1212121212", 
                "Jesus", 
                2000, 
                10
            );
            cuenta.realizarCompra(1000);
            assertEquals(1000,cuenta.getSaldo() );
            assertEquals(160, cuenta.getPuntosLealtad());
        }
        @Test
        public void hacerTranferenciaClienteNulo() throws TransaccionInvalidaException{
            assertThrows(
                TransaccionInvalidaException.class,
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                    "1212121212", 
                    "Jesus", 
                    900, 
                    90
                );
                    cuenta.transferir(null, 200);
                }
            );
        }
        @Test 
        public void ingrearPuntoLimite() throws TransaccionInvalidaException{
            assertThrows(
                TransaccionInvalidaException.class, 
                ()->{
                    CuentaBilletera cuenta = new CuentaBilletera(
                    "1122112211", 
                    "Jesus", 
                    900, 
                    99999
                );
                cuenta.agregarPuntos(10);
                }
            );
        }
}

