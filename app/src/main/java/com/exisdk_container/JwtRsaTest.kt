package com.exisdk_container

import androidx.annotation.RequiresApi
import android.os.Build
import kotlin.Throws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jws
import io.jsonwebtoken.SignatureAlgorithm
import java.lang.StringBuilder
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.PrivateKey
import java.security.PublicKey
import java.util.*

class JwtRsaTest {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Throws(NoSuchAlgorithmException::class)
    fun testJWTWithRsa() {
        val keyGenerator = KeyPairGenerator.getInstance("RSA")
        keyGenerator.initialize(1024)
        val kp = keyGenerator.genKeyPair()
        val publicKey = kp.public as PublicKey
        val privateKey = kp.private as PrivateKey
        val encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.encoded)
        println("Public Key:")
        println(convertToPublicKey(encodedPublicKey))
        val token = generateJwtToken(privateKey)
        println("TOKEN:")
        println(token)
        printStructure(token, publicKey)
    }

    fun generateJwtToken(privateKey: PrivateKey?): String {
        return Jwts.builder().setSubject("abhishek")
            .setExpiration(Date(2023, 1, 1))
            .setIssuer("abhishekv-bluebik@gmail.com")
            .claim("groups", arrayOf("user", "abhishek")) // RS256 with privateKey
            .signWith(SignatureAlgorithm.RS256, privateKey).compact()
    }

    //Print structure of JWT
    fun printStructure(token: String?, publicKey: PublicKey?) {
        val parseClaimsJws: Jws<*> = Jwts.parser().setSigningKey(publicKey)
            .parseClaimsJws(token)
        println("Header     : " + parseClaimsJws.header)
        println("Body       : " + parseClaimsJws.body)
        println("Signature  : " + parseClaimsJws.signature)
    }

    // Add BEGIN and END comments
    private fun convertToPublicKey(key: String): String {
        val result = StringBuilder()
        result.append("-----BEGIN PUBLIC KEY-----\n")
        result.append(key)
        result.append("\n-----END PUBLIC KEY-----")
        return result.toString()
    }
}