package com.swj.security;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class Encode {

    //URL编码
    public static String urlEncode(String source) throws UnsupportedEncodingException {
        return URLEncoder.encode(source, "UTF-8");
    }

    public static String urlDecode(String source) throws UnsupportedEncodingException {
        return URLDecoder.decode(source, "UTF-8");
    }

    // Base64
    // 把3字节的二进制数据按6bit一组，用4个int整数表示，然后查表，把int整数用索引对应到字符，得到编码后的字符串。
    public static String base64Encode(byte[] source) {
        return Base64.getEncoder().encodeToString(source);
    }

    public static byte[] base64Decoder(String source) {
        return Base64.getDecoder().decode(source);
    }

    // hash/digest
    // MD5、SHA-1、SHA-256、SHA-512...
    public static byte[] md5(String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(source.getBytes(StandardCharsets.UTF_8));
        return md.digest();
    }

    public static byte[] sha1(String source) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(source.getBytes(StandardCharsets.UTF_8));
        return md.digest();
    }

    // hmac 加盐
    public static byte[] hmacMd5(String source) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();

        byte[] key = secretKey.getEncoded();
        System.out.println(new BigInteger(1, key).toString(16));
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        mac.update(source.getBytes(StandardCharsets.UTF_8));
        return mac.doFinal();
    }

    // 对称加密 密文+明文 指定算法名称、工作模式和填充模式。
    // ECB模式 固定输入则固定输出 不安全 可以使用CBC模式每次生成随机数
    public static byte[] aesEncrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(input);
    }

    public static byte[] aesDecrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(input);
    }

    //口令加密 用户输入+随机口令 => 密钥 这个过程程为PBE： password based encryption 再用这个密钥进行加密


    // 密钥交换算法，为解决如何在网络上安全传输密钥
    // DH算法（Diffie-Hellman）依赖于计算离散对数的难度 Oakley算法是对Diffie-Hellman密钥交换算法的优化


    // 非对称加密 RSA 非对称加密的缺点就是运算速度非常慢，比对称加密要慢很多
    // 在实际应用的时候，非对称加密总是和对称加密一起使用。假设小明需要给小红需要传输加密文件，他俩首先交换了各自的公钥，然后：
    //  1、小明生成一个随机的AES口令，然后用小红的公钥通过RSA加密这个口令，并发给小红；
    //  2、小红用自己的RSA私钥解密得到AES口令；
    //  3、双方使用这个共享的AES口令用AES加密通信。
    // 非对称加密就是加密和解密使用的不是相同的密钥，只有同一个公钥-私钥对才能正常加解密；
    public static byte[] rsaEncrypt(String input) {
        return rsa.encrypt(StrUtil.bytes(input, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
    }

    public static byte[] rsaDecrypt(String input) {
        return rsa.decrypt(Base64.getDecoder().decode(input), KeyType.PrivateKey);
    }


    private static final RSA rsa = new RSA();

    // 签名算法

    /**
     * 数字签名就是用发送方的私钥对原始数据进行签名，只有用发送方公钥才能通过签名验证。
     *
     * 数字签名用于：
     *
     *      防止伪造；
     *      防止抵赖；
     *      检测篡改。
     * 常用的数字签名算法包括：MD5withRSA／SHA1withRSA／SHA256withRSA／SHA1withDSA／SHA256withDSA／SHA512withDSA／ECDSA等。
     *
     */


    /**
     * 数字证书就是集合了多种密码学算法，用于实现数据加解密、身份认证、签名等多种功能的一种安全标准。
     *
     * 数字证书采用链式签名管理，顶级的Root CA证书已内置在操作系统中。
     *
     * 数字证书存储的是公钥，可以安全公开，而私钥必须严格保密。
     *
     * JDK
     * keytool -storepass 123456 -genkeypair -keyalg RSA -keysize 1024 -sigalg SHA1withRSA -validity 3650 -alias mycert -keystore my.keystore -dname "CN=www.sample.com, OU=sample, O=sample, L=BJ, ST=BJ, C=CN"
     */

    public static void main(String[] args) throws UnsupportedEncodingException, GeneralSecurityException {
        System.out.println(urlEncode("中"));
        System.out.println(urlDecode("%E4%B8%AD"));
        System.out.println(base64Encode("中".getBytes()));
        System.out.println(Arrays.toString(base64Decoder("5Lit")));
        System.out.println(new BigInteger(1, md5("HelloWorld")).toString(16));
        System.out.println(new BigInteger(1, sha1("HelloWorld")).toString(16));
        System.out.println(new BigInteger(1, hmacMd5("HelloWorld")).toString(16));

        String source = "HelloWorld";
        System.out.println("原文：" + source);
        String key = "1234567890abcdef";
        System.out.println("密文：" + key);
        byte[] encrypt = aesEncrypt(key.getBytes(StandardCharsets.UTF_8), source.getBytes(StandardCharsets.UTF_8));
        System.out.println("加密后：" + Base64.getEncoder().encodeToString(encrypt));
        byte[] decrypt = aesDecrypt(key.getBytes(StandardCharsets.UTF_8), encrypt);
        System.out.println("解密后：" + new String(decrypt, StandardCharsets.UTF_8));

        System.out.println(urlDecode("%E7%B3%BB%E7%BB%9F%E7%AE%A1%E7%90%86%E5%91%98"));
        byte[] rsaEncrypt = rsaEncrypt("sdas23dsasadsadsad？");
        System.out.println(Arrays.toString(rsaEncrypt));
        System.out.println("rsa加密后：" + Base64.getEncoder().encodeToString(rsaEncrypt));
        byte[] rasDecrypt = rsaDecrypt(Base64.getEncoder().encodeToString(rsaEncrypt));
        System.out.println("rs解密后：" + new String(rasDecrypt, StandardCharsets.UTF_8));

    }
}
