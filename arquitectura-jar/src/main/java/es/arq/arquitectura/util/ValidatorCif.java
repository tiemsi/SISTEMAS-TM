package es.arq.arquitectura.util;

public class ValidatorCif {
	 
     /**
      * Realiza la validacion si la cadena representa un CIF
      * 
      * @param strCadena
      *            la cadena a comprobar
      * @return true si la cadena representa un CIF del tipo indicado
      */
     public static boolean isCifValido(String cif) {

     boolean resultado = false;

     try {
         String vCif = cif.trim();

         int suma = 0;
         int contador = 0;
         int temporal = 0;
         int codigoControl = 0;
         String cadenaTemporal = null;
         String valoresCif = "ABCDEFGHJKLMNPQRSUVW";
         String letraControlCIF = "0123456789";
         String letraSociedadNumerica = "KLMNPQRSW";
         String primeraLetra = null;
         String ultimaLetra = null;

         // Comprueba la longitud correcta del CIF.
         if (!(vCif.length() == 9))
             return false;

         // Si encuentra alg�n caracter que no sea una letra o un n�mero, el cif
         // no es valido.
         if (vCif.matches("[^A-Za-z0-9]"))
             return false;

         // Convierte a may�sculas la cadena.
         vCif = vCif.toUpperCase();

         // Obtiene la primera letra (letra de la sociedad) y la �ltima letra del
         // CIF (letra de control).
         primeraLetra = vCif.substring(0, 1);

         // Obtiene la �ltima letra del CIF, para comprobar si es v�lida.
         ultimaLetra = vCif.substring(8, 9);

         // Comprueba si la primera letra es v�lida.
         if (valoresCif.indexOf(primeraLetra) < 0)
             return false;

         // Obtiene el c�digo de control.
         // Sumamos las cifras pares
         suma = suma + Integer.parseInt(vCif.substring(2, 3)) + Integer.parseInt(vCif.substring(4, 5))
                 + Integer.parseInt(vCif.substring(6, 7));

         // Ahora cada cifra impar la multiplicamos por dos y sumamos las cifras
         // del resultado.
         for (contador = 1; contador < 8; contador = contador + 2) {
             // Multiplica por 2
             temporal = (Integer.parseInt(vCif.substring(contador, contador + 1)) * 2);

             // Suma los digitos.
             // Diferencia si tiene una cifra, por ejemplo: 8 = 8
             // o si tiene varias, por ejemplo: 16 -> 6 + 1 = 7
             if (temporal < 10)
                 suma = suma + temporal;
             else {
                 cadenaTemporal = String.valueOf(temporal);
                 suma = suma + (Integer.parseInt(cadenaTemporal.substring(0, 1)))
                         + (Integer.parseInt(cadenaTemporal.substring(1, 2)));
             }
         }

         // Obtiene las unidades de la suma y se las resta a 10, para obtener el
         // d�gito de control.
         codigoControl = ((10 - (suma % 10)) % 10);

         // Si la letra es K, L, M, N, P, Q � S entonces al codigo de control le
         // suma 64 y
         // obtengo su ASCII para ver si coincide con la ultima letra del cif.
         if (letraSociedadNumerica.indexOf(primeraLetra) >= 0) {
             byte[] ascii = new byte[1];

             // Obtiene el c�digo ASCII asociado, al sumar 64 al c�digo de
             // control.
             if (codigoControl == 0)
                 codigoControl = 10;
             codigoControl = codigoControl + 64;
             ascii[0] = (Integer.valueOf(codigoControl)).byteValue();

             // El �ltimo d�gito tiene que coincidir con el d�gito de control
             // obtenido
             resultado = (ultimaLetra.equals(new String(ascii)));
         } else {
             // Para el resto de letras de comienzo de CIF el �ltimo d�gito debe ser
             // num�rico,
             // y coincidir con el c�digo de control.
             resultado = (codigoControl == letraControlCIF.indexOf(ultimaLetra));
         }
     } catch (Exception e) {
         // Si ha habido alg�n error es porque hay alg�n parseo que tira bien.
         resultado = false;
     }
     return resultado;
 }

}
