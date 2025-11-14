package VistaInicioSesion.VistaCliente;

import java.util.Calendar;

public class ValidadorPagos {
    
    public static String validarTarjeta(String numero, String nombre, int mes, int anio, String cvv) {
        numero = numero.replaceAll("\\s", "");
        
        if (numero.isEmpty()) return "Ingrese el número de tarjeta";
        if (numero.length() < 13 || numero.length() > 16) return "Número de tarjeta inválido (13-16 dígitos)";
        if (!validarLuhn(numero)) return "Número de tarjeta inválido";
        
        nombre = nombre.trim();
        if (nombre.isEmpty()) return "Ingrese el nombre del titular";
        if (nombre.length() < 3) return "Nombre muy corto";
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) return "Solo letras en el nombre";
        
        Calendar hoy = Calendar.getInstance();
        int mesActual = hoy.get(Calendar.MONTH) + 1;
        int anioActual = hoy.get(Calendar.YEAR);
        if (anio < anioActual || (anio == anioActual && mes < mesActual)) {
            return "Tarjeta vencida";
        }
        
        cvv = cvv.trim();
        if (cvv.isEmpty()) return "Ingrese el CVV";
        if (cvv.length() < 3 || cvv.length() > 4) return "CVV debe tener 3 o 4 dígitos";
        
        return null;
    }
    
    public static String validarEfectivo(String monto, double total) {
        if (monto.trim().isEmpty()) return "Ingrese el monto";
        
        try {
            double valor = Double.parseDouble(monto);
            if (valor < total) return "Monto insuficiente";
            return null;
        } catch (NumberFormatException e) {
            return "Monto inválido";
        }
    }
    
    public static String validarNequi(String celular, String nombre) {
        celular = celular.trim();
        if (celular.isEmpty()) return "Ingrese el número de celular";
        if (celular.length() != 10) return "Celular debe tener 10 dígitos";
        if (!celular.startsWith("3")) return "Celular debe iniciar con 3";
        
        nombre = nombre.trim();
        if (nombre.isEmpty()) return "Ingrese el nombre";
        if (nombre.length() < 3) return "Nombre muy corto";
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) return "Solo letras en el nombre";
        
        return null;
    }
    
    private static boolean validarLuhn(String numero) {
        int suma = 0;
        boolean alternar = false;
        
        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            
            if (alternar) {
                digito *= 2;
                if (digito > 9) digito = (digito / 10) + (digito % 10);
            }
            
            suma += digito;
            alternar = !alternar;
        }
        
        return (suma % 10 == 0);
    }
}