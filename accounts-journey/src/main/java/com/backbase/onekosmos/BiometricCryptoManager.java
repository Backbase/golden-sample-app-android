package com.backbase.onekosmos;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;

public class BiometricCryptoManager {

    private static final String KEY_ALIAS = "biometric_secure_key";
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final int GCM_TAG_LENGTH = 128;
    private static final String KEY_PAIR_ALG = "EC";
    public static final String CURVE_NAME = "secp256r1";
    private static final int KEY_PAIR_PURPOSES =
            KeyProperties.PURPOSE_SIGN |
                    KeyProperties.PURPOSE_VERIFY |
                    KeyProperties.PURPOSE_DECRYPT |
                    KeyProperties.PURPOSE_ENCRYPT;

    public static void generateKeyIfNeeded() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);

        if (keyStore.containsAlias(KEY_ALIAS)) return;

//        KeyGenerator keyGenerator = KeyGenerator.getInstance(
//                KEY_PAIR_ALG,
//                ANDROID_KEYSTORE
//        );
//
//        KeyGenParameterSpec.Builder builder =
//                new KeyGenParameterSpec.Builder(
//                        KEY_ALIAS,
//                        KEY_PAIR_PURPOSES)
//                        .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
//                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
//                        .setUserAuthenticationRequired(true)
//                        .setUserAuthenticationValidityDurationSeconds(-1);
//
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            builder.setInvalidatedByBiometricEnrollment(true);
////        }
//
//        keyGenerator.init(builder.build());
//        keyGenerator.generateKey();

//        try {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_PAIR_ALG, ANDROID_KEYSTORE); // mobsf-ignore: hardcoded_api_key
            generator.initialize(new KeyGenParameterSpec.Builder(KEY_ALIAS, KEY_PAIR_PURPOSES)
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setAlgorithmParameterSpec(new ECGenParameterSpec(CURVE_NAME))
                    .setUserAuthenticationRequired(true)
                    .setInvalidatedByBiometricEnrollment(true)
                    .build());

            generator.generateKeyPair();

//        } catch (final GeneralSecurityException e) {
//            Log.d("Bhavesh", "Unable to generate the KeyPair"+e);
//        }
    }

    public static Cipher getEncryptCipher() throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
        return cipher;
    }

    public static Cipher getDecryptCipher(byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), spec);
        return cipher;
    }

    public static Signature getSignatureForEncrypt() throws Exception {
        Security.removeProvider("SC");
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(getPrivateKey());
        return signature;
    }

    public static Signature getSignatureForDecrypt() throws Exception {
        Security.removeProvider("SC");
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(getPublicKey());
        return signature;
    }

    private static PrivateKey getPrivateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return (PrivateKey) keyStore.getKey(KEY_ALIAS, null);
    }

    private static java.security.PublicKey getPublicKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return keyStore.getCertificate(KEY_ALIAS).getPublicKey();
    }
    private static PrivateKey getSecretKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return (PrivateKey) keyStore.getKey(KEY_ALIAS, null);
    }

    public static void deleteKey() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            keyStore.deleteEntry(KEY_ALIAS);
        } catch (Exception ignored) {}
    }

}
