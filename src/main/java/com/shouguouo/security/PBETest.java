//package com.swj.security;
//
//import com.sun.org.apache.xml.internal.security.utils.Base64;
//import sun.misc.BASE64Encoder;
//
//import javax.crypto.Cipher;
//import javax.crypto.EncryptedPrivateKeyInfo;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.PBEParameterSpec;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.LineNumberReader;
//import java.security.AlgorithmParameters;
//import java.security.Key;
//import java.security.spec.KeySpec;
//import java.util.Arrays;
//import java.util.Random;
//
///**
// * PBE——Password-based encryption（基于密码加密）。 (想了解更多的加密，解密算法和数字签名实现，请游览本人博客)
// * 其特点在于口令由用户自己掌管，不借助任何物理媒体；采用随机数（这里我们叫做盐）杂凑多重加密等方法保证数据的安全性。
// * 是一种简便的加密方式。
// */
//public class PBETest {
//    /**
//     * 支持以下任意一种算法
//     * <p>
//     * <pre>
//     * PBEWithMD5AndDES
//     * PBEWithMD5AndTripleDES
//     * PBEWithSHA1AndDESede
//     * PBEWithSHA1AndRC2_40
//     * </pre>
//     */
//    public static final String ALGORITHM = "PBEWithSHA1AndDESede";
//
//    /**
//     * 盐初始化
//     *
//     * @return
//     * @throws Exception
//     */
//    public static byte[] initSalt() throws Exception {
//        byte[] salt = new byte[8];
//        Random random = new Random();
//        random.nextBytes(salt);
//        return salt;
//    }
//
//    /**
//     * 转换密钥<br>
//     *
//     * @param password
//     * @return
//     * @throws Exception
//     */
//    private static Key toKey(String password) throws Exception {
//        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
//        SecretKey secretKey = keyFactory.generateSecret(keySpec);
//
//        return secretKey;
//    }
//
//    /**
//     * 加密
//     *
//     * @param data     数据
//     * @param password 密码
//     * @param salt     盐
//     * @return
//     * @throws Exception
//     */
//    public static String encrypt(String data, String password, byte[] salt)
//        throws Exception {
//
//        Key key = toKey(password);
//
//        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
//
//        return new BASE64Encoder().encode(cipher.doFinal(data.getBytes("UTF-8")));
//
//    }
//
//    public static void main(String[] args) {
//        try {
//            File file = new File("F:\\checkserver\\etc\\国金证券股份有限公司.key");
//            readPrivateKey(file, "600109");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static byte[] readPrivateKey(File file, String password) {
//
//        byte[] priKey = null;
//        try {
//            byte[] encoded = readPkcs8PrivateKey(file);
//            EncryptedPrivateKeyInfo epki = new EncryptedPrivateKeyInfo(encoded);
//
//            AlgorithmParameters algParams = epki.getAlgParameters();
//
//            SecretKeyFactory sf = SecretKeyFactory.getInstance(epki.getAlgName());
//            KeySpec keySpec = new PBEKeySpec(password.toCharArray());
//            SecretKey secretKey = sf.generateSecret(keySpec);
//
//            Cipher cipher = Cipher.getInstance(epki.getAlgName());
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, algParams);
//            priKey = cipher.doFinal(epki.getEncryptedData());
//            System.out.println(Arrays.toString(priKey));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return priKey;
//
//    }
//
//    public static byte[] readPkcs8PrivateKey(File file) {
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            // Read lines until a "-----BEGIN ENCRYPTED PRIVATE KEY-----" is
//            // found
//            // This tag precedes a PKCS#8 key.
//            LineNumberReader lnr = new LineNumberReader(new InputStreamReader(fis, "ISO-8859-1"));
//            final int NOT_STARTED = 0;
//            final int RUNNING = 1;
//            final int DONE = 2;
//            int readingState = NOT_STARTED;
//            StringBuffer keyBase64DataBuffer = new StringBuffer();
//            {
//                String line;
//                while ((line = lnr.readLine()) != null && readingState != DONE) {
//                    if (readingState == NOT_STARTED) {
//                        if (line.startsWith("-----BEGIN ENCRYPTED PRIVATE KEY-----")) {
//                            readingState = RUNNING;
//                        }
//                    } else {
//                        // readingState == RUNNING
//                        if (line.startsWith("-----END ENCRYPTED PRIVATE KEY-----")) {
//                            readingState = DONE;
//                        } else {
//                            keyBase64DataBuffer.append(line);
//                        }
//                    }
//                }
//            }
//            return Base64.decode(keyBase64DataBuffer.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//
//    }
//}