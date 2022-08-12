package com.exisdk_container

import androidx.annotation.RequiresApi
import android.os.Build
import android.util.Log
import kotlin.Throws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jws
import io.jsonwebtoken.SignatureAlgorithm
import java.io.IOException
import java.lang.StringBuilder
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

class JwtRsaTest {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Throws(NoSuchAlgorithmException::class)
    fun testJWTWithRsa() : String{
        val keyGenerator = KeyPairGenerator.getInstance("RSA")
        keyGenerator.initialize(1024)
        val kp = keyGenerator.genKeyPair()
        val publicKey = kp.public as PublicKey
        val privateKey = kp.private as PrivateKey
        val encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.encoded)

        println("Public Key:")

        println(convertToPublicKey(encodedPublicKey))

        val encodedPvtKey = Base64.getEncoder().encodeToString(privateKey.encoded)
        val decodePvtKey=Base64.getDecoder().decode("MIIEowIBAAKCAQEAq9YOLJbi7qfIDib5dpRhnoatcH5h07RHHf5WeFYdwey3Xjrl5ZiJw8NfuVeyT1YKBoQp8+CGXSWvIhbOCnNrBfZNPFv6hD/w5mzZJxqP1yDYj9+RSxR08/YKj8Ep4kNPe0s9k76bO5UBKMeBxZl9E2Sg21ioTwCMg8n/76BLLe5nkZMjqpkC+RXwp1Gmhy7dQyvIowMMKUdSzzVJL0E2DhaRgxHDX/1yq4ByVTBNYegTxvxXWzbDlXMAGCKdU576Wfd28EQyVuUETXYbIyclJMZJKp/E8wnjtTyq6t4VRpx0W22EMO9g6sHrtz7KFtOM9tMNkyR+KijP17yL2gdrawIDAQABAoIBACBQglzfSTDRS51htfMXR15c/FvV4IbD9cXNsSwzIah44Cjv0ZECRRIT+TntJIKn7v23t/39YLc+oXc7K6cqeltmLLMnpu5c6ewjVxjz9U3jBubTh8rDP5UrVWQgUsSSnO1WQm+43g9v34ZwMlbbHPrbJybovsoQk9wqzh8dmVW7GyOwMTxsFykbwqms7rZ1y7okumk8U+skXhbqPjldsWioukbZ3yrLqvk2qMtHaVxsAOXOGAImhZGjQAWCMdzpsonhpTUxMcw+vgFb5TKbxedAxyattgdqFlDxGyl3qPdL3M+BR78YapeT0hKdXFhVpUbDTLcZ2kq86KJAoeleKTECgYEA30qd+kfUaO4D3+6YoMOc0j3fZAWlU6HCl9blO5G7ahRSwtm3tAo9OwNNWcrZcpKAiA25NuFXEzUuo1Pj5udfZAtoqWojpUZX7kocLtpzzXRJe64argIo8/dhJQMbUTopIt1EBq5R4AxPwn6dslzoMYiUVIeUm5A22vLf5NHRXnECgYEAxQHf7JsKvEVQ3NsrpzFj0oCZialjTiCWzqfWJD5mkkEc01AP1QPfW9/1UiQzbQEfyz9P58EJaiTSh893sR0Lfi3RYmsa9AqRbAwOgABagyydcoHvCpmYBfTPe9cFxsfwVIc6HNzFaYWQ4Q/Yddalj+l97m4p4L2LJmust/CvjZsCgYBYq3/qiKWv+Xyua0hO5v8aehmA1cEeYektdNfY/PkomeN3nw+VPk+sZJZN5wDMv6Ks5I49XeS/KupXT5P7+LtntiQcyppXIoT+io2AiZ1c5CXeKHhNy2vJ3d3E3eP2MJtvTeCaqpS23Qy7Vp522vAV43/LxX9c+zrdXX9NlYiH8QKBgQCLYy0s+fGZja2N5eA39kD7chOwC9IAOBybcLKZiDEIb9tWQ4JAI/JHzeiDyqs8B5yYMF53yePAXvc6MfcssDEugHpyGPfEDSog+VLVC+r+fnWTzAz2QgAGztRyeYtTvqjzFY5vV2CxrVf5dT2g+lK/xONd7EHjChjHAU3+AuQi2wKBgBlknKxt6SxoVOwXlRtN7EFEHpULc3wnBdcaLK8H85MGccM4hU2n9wTCBtd1mgu+/BLNHUUttLR0KtH/KqVYHxgeMEvGlJe4nHct68SM4xZea/RBfd2peDirRkifovCRSLsltkpzXts//YXOG3Vd6XCpQJ2VUsYhSygsFtxIBeu4")
        val kf = KeyFactory.getInstance("RSA") // or "EC" or whatever

        val privateKey1 = kf.generatePrivate(PKCS8EncodedKeySpec(decodePvtKey))
        //  println(convertToPublicKey(encodedPvtKey))
        val token = generateJwtToken(privateKey1)
        println("TOKEN:")
        println(token)
        //printStructure(token, publicKey)
        return token
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateJwtToken(privateKey: PrivateKey?): String {
        val key: String = "MIIEowIBAAKCAQEAq9YOLJbi7qfIDib5dpRhnoatcH5h07RHHf5WeFYdwey3Xjrl5ZiJw8NfuVeyT1YKBoQp8+CGXSWvIhbOCnNrBfZNPFv6hD/w5mzZJxqP1yDYj9+RSxR08/YKj8Ep4kNPe0s9k76bO5UBKMeBxZl9E2Sg21ioTwCMg8n/76BLLe5nkZMjqpkC+RXwp1Gmhy7dQyvIowMMKUdSzzVJL0E2DhaRgxHDX/1yq4ByVTBNYegTxvxXWzbDlXMAGCKdU576Wfd28EQyVuUETXYbIyclJMZJKp/E8wnjtTyq6t4VRpx0W22EMO9g6sHrtz7KFtOM9tMNkyR+KijP17yL2gdrawIDAQABAoIBACBQglzfSTDRS51htfMXR15c/FvV4IbD9cXNsSwzIah44Cjv0ZECRRIT+TntJIKn7v23t/39YLc+oXc7K6cqeltmLLMnpu5c6ewjVxjz9U3jBubTh8rDP5UrVWQgUsSSnO1WQm+43g9v34ZwMlbbHPrbJybovsoQk9wqzh8dmVW7GyOwMTxsFykbwqms7rZ1y7okumk8U+skXhbqPjldsWioukbZ3yrLqvk2qMtHaVxsAOXOGAImhZGjQAWCMdzpsonhpTUxMcw+vgFb5TKbxedAxyattgdqFlDxGyl3qPdL3M+BR78YapeT0hKdXFhVpUbDTLcZ2kq86KJAoeleKTECgYEA30qd+kfUaO4D3+6YoMOc0j3fZAWlU6HCl9blO5G7ahRSwtm3tAo9OwNNWcrZcpKAiA25NuFXEzUuo1Pj5udfZAtoqWojpUZX7kocLtpzzXRJe64argIo8/dhJQMbUTopIt1EBq5R4AxPwn6dslzoMYiUVIeUm5A22vLf5NHRXnECgYEAxQHf7JsKvEVQ3NsrpzFj0oCZialjTiCWzqfWJD5mkkEc01AP1QPfW9/1UiQzbQEfyz9P58EJaiTSh893sR0Lfi3RYmsa9AqRbAwOgABagyydcoHvCpmYBfTPe9cFxsfwVIc6HNzFaYWQ4Q/Yddalj+l97m4p4L2LJmust/CvjZsCgYBYq3/qiKWv+Xyua0hO5v8aehmA1cEeYektdNfY/PkomeN3nw+VPk+sZJZN5wDMv6Ks5I49XeS/KupXT5P7+LtntiQcyppXIoT+io2AiZ1c5CXeKHhNy2vJ3d3E3eP2MJtvTeCaqpS23Qy7Vp522vAV43/LxX9c+zrdXX9NlYiH8QKBgQCLYy0s+fGZja2N5eA39kD7chOwC9IAOBybcLKZiDEIb9tWQ4JAI/JHzeiDyqs8B5yYMF53yePAXvc6MfcssDEugHpyGPfEDSog+VLVC+r+fnWTzAz2QgAGztRyeYtTvqjzFY5vV2CxrVf5dT2g+lK/xONd7EHjChjHAU3+AuQi2wKBgBlknKxt6SxoVOwXlRtN7EFEHpULc3wnBdcaLK8H85MGccM4hU2n9wTCBtd1mgu+/BLNHUUttLR0KtH/KqVYHxgeMEvGlJe4nHct68SM4xZea/RBfd2peDirRkifovCRSLsltkpzXts//YXOG3Vd6XCpQJ2VUsYhSygsFtxIBeu4"
      /*  val privateKey:PrivateKey = key.bytes()*/
        return Jwts.builder().setSubject("abhishek")
            .setExpiration(Date(2023, 1, 1))
            .setIssuer("abhishekv-bluebik@gmail.com")
            .claim("groups", arrayOf("user", "abhishek")) // RS256 with privateKey
            .signWith(SignatureAlgorithm.RS256,privateKey).compact()
        Log.e("called","called")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(GeneralSecurityException::class, IOException::class)
    fun loadPublicKey(stored: String): Key? {
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(stored.toByteArray())
        } else {
            Log.e("publickey","called")
            TODO("VERSION.SDK_INT < O")
        }
        val spec = X509EncodedKeySpec(data)
        val fact = KeyFactory.getInstance("RSA")
        return fact.generatePublic(spec)
    }
    //Print structure of JWT
   /* fun printStructure(token: String?, publicKey: PublicKey?) {
        val parseClaimsJws: Jws<*> = Jwts.parser().setSigningKey(publicKey)
            .parseClaimsJws(token)
        println("Header     : " + parseClaimsJws.header)
        println("Body       : " + parseClaimsJws.body)
        println("Signature  : " + parseClaimsJws.signature)
    }*/


    /*@RequiresApi(Build.VERSION_CODES.O)
    fun loadPublicKey(stored: String): Key? {
        val data = Base64.getDecoder().decode(stored.toByteArray())
        val spec = X509EncodedKeySpec(data)
        val fact: KeyFactory = KeyFactory.getInstance("RSA")
        return fact.generatePublic(spec)
    }*/

    // Add BEGIN and END comments
    private fun convertToPublicKey(key: String): String {
        val result = StringBuilder()
        result.append("-----BEGIN PUBLIC KEY-----\n")
        result.append(key)
        result.append("\n-----END PUBLIC KEY-----")
        return result.toString()
    }

    // Add BEGIN and END comments
    private fun convertToPrivateKey(key: String): String {
        val result = StringBuilder()
        result.append("-----BEGIN PUBLIC KEY-----\n")
        result.append(key)
        result.append("\n-----END PUBLIC KEY-----")
        return result.toString()
    }
}