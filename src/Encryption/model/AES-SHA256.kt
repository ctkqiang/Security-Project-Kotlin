package Encryption.model

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

fun main(args: Array<String>) {
    Crypto_암호.aesDecrypt("이것은문자열입니다",
        "GgfIf7TQoL2NWJ1kZCyfm9i0di78nU6a")
}

object Crypto_암호{
    @JvmStatic fun aesEncrypt(v:String, secretKey:String) =
        AES_SHA256_알고리즘.Encrypt_암호화(v, secretKey)
    @JvmStatic fun aesDecrypt(v:String, secretKey:String) =
        AES_SHA256_알고리즘.Decrypt_해독(v, secretKey)
}

private object AES_SHA256_알고리즘 {
    private val encoder_인코더 = Base64.getEncoder()
    private val decoder_디코더 = Base64.getDecoder()

    private fun cipher_암호(opmode: Int, secretKey_비밀키: String): Cipher{
        if (secretKey_비밀키.length != 32)
            throw RuntimeException("SecretKey length is not 32 chars \n 비밀 키 길이는 32자가 아닙니다")
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val sk = SecretKeySpec(secretKey_비밀키.toByteArray(Charsets.UTF_8), "AES")
        val iv = IvParameterSpec(secretKey_비밀키.substring(0, 16).toByteArray(Charsets.UTF_8))
        c.init(opmode, sk, iv)
        return c
    }

    fun Encrypt_암호화(stringToEncrypt_암호화할문자열: String, secretKey_비밀키: String): String {
        val encrypted_암호화 = cipher_암호(
            Cipher.ENCRYPT_MODE,
            secretKey_비밀키
        )
            .doFinal(stringToEncrypt_암호화할문자열.toByteArray(Charsets.UTF_8))
        return String(encoder_인코더.encode(encrypted_암호화))
    }

    fun Decrypt_해독(stringToDecrypt_해독할문자열: String, secretKey_비밀키: String): String {
        val decrypted_해독 = decoder_디코더.decode(stringToDecrypt_해독할문자열.toByteArray(Charsets.UTF_8))
        return String(cipher_암호(Cipher.DECRYPT_MODE, secretKey_비밀키).doFinal(decrypted_해독))
    }
}