package co.gov.cundinamarca.sisvan.utilSiiweb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

/**
 * Esta classe maneja la codificación de algoritmos de hashing como SHA-256 y
 * MD5
 *
 * @author ANDRES_CANO
 */
public class CodificacionDigest {

    /**
     * Codigicar una cadena con el algoritmo pasado com parámetro
     *
     * @param valor Valor a codificar
     * @param algortimoDigest Algoritno de codificación
     * @return Una arrya de bytes con la cadena codificada
     * @throws NoSuchAlgorithmException Algorito de hashing no encontrado
     */
    public static byte[] codificar(String valor, String algortimoDigest) throws NoSuchAlgorithmException {
        if (valor != null) {
            MessageDigest messageDigest = java.security.MessageDigest.getInstance(algortimoDigest);
            return messageDigest.digest(valor.getBytes());

        } else {
            throw new NullPointerException("La cadena pasada como parámetro es nula, no se puede codificar");
        }
    }

    /**
     * Codifica la cadena pasada como parámetro en el agoritmo descrito
     *
     * @param valor Valor a codificar
     * @param algortimoDigest Algoritmo de hashing a ejecutar
     * @return Una cadena en base64 con el valor codificado
     * @throws NoSuchAlgorithmException Si el algoritmo de hashing no es
     * encontrado
     */
    public static String codificarBase64(String valor, String algortimoDigest) throws NoSuchAlgorithmException {
        return Base64.encodeBase64String(codificar(valor, algortimoDigest));
    }

}
