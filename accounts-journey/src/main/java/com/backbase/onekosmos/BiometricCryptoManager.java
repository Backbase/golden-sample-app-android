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
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class BiometricCryptoManager {

    private static final String KEY_ALIAS = "biometric_secure_key";
    private static final String AES_KEY_ALIAS = "biometric_aes_key";
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

        if (!keyStore.containsAlias(KEY_ALIAS)) {
            final KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_PAIR_ALG, ANDROID_KEYSTORE); // mobsf-ignore: hardcoded_api_key
            generator.initialize(new KeyGenParameterSpec.Builder(KEY_ALIAS, KEY_PAIR_PURPOSES)
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setAlgorithmParameterSpec(new ECGenParameterSpec(CURVE_NAME))
                    .setUserAuthenticationRequired(true)
                    .setInvalidatedByBiometricEnrollment(true)
                    .build());

            generator.generateKeyPair();
        }

        generateAesKeyIfNeeded();
    }

    private static void generateAesKeyIfNeeded() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);

        if (keyStore.containsAlias(AES_KEY_ALIAS)) return;

        KeyGenerator keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                ANDROID_KEYSTORE
        );

        keyGenerator.init(new KeyGenParameterSpec.Builder(
                AES_KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .setUserAuthenticationRequired(true)
                .setInvalidatedByBiometricEnrollment(true)
                .build());

        keyGenerator.generateKey();
    }

    public static Cipher getEncryptCipher() throws Exception {
        generateAesKeyIfNeeded();
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, getAesSecretKey());
        return cipher;
    }

    public static Cipher getDecryptCipher(byte[] iv) throws Exception {
        generateAesKeyIfNeeded();
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, getAesSecretKey(), spec);
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
    private static SecretKey getAesSecretKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return (SecretKey) keyStore.getKey(AES_KEY_ALIAS, null);
    }

    public static void deleteKey() {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
            keyStore.load(null);
            keyStore.deleteEntry(KEY_ALIAS);
            keyStore.deleteEntry(AES_KEY_ALIAS);
        } catch (Exception ignored) {}
    }

}
