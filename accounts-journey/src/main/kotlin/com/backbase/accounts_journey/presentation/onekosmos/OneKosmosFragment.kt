package com.backbase.accounts_journey.presentation.onekosmos

import android.os.Bundle
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.backbase.accounts_journey.databinding.FragmentOneKosmosBinding
import com.backbase.onekosmos.BiometricCryptoManager
import com.backbase.onekosmos.BiometricCryptoManager.deleteKey
import com.backbase.onekosmos.SecureStorage
import com.onekosmos.blockid.sdk.BIDAPIs.APIManager.ErrorManager
import com.onekosmos.blockid.sdk.BlockIDSDK
import com.onekosmos.blockid.sdk.datamodel.BIDTenant
import com.onekosmos.blockid.sdk.passKey.PasskeyCallback
import com.onekosmos.blockid.sdk.passKey.PasskeyRequest
import com.onekosmos.blockid.sdk.passKey.PasskeyResponse
import java.nio.charset.StandardCharsets
import java.security.Security
import java.util.concurrent.Executor
import javax.crypto.Cipher

class OneKosmosFragment : Fragment() {

    private var _binding: FragmentOneKosmosBinding? = null
    private val binding get() = _binding!!

    private lateinit var executor: Executor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        executor = ContextCompat.getMainExecutor(requireContext())

        Security.getProviders().forEach {
            println(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneKosmosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enableButtons()

        binding.tentantRegisterButton.setOnClickListener {
            tenantRegister()
        }

        binding.passkeyRegistrationButton.setOnClickListener {
            passKeyRegister()
        }

        binding.passkeyAuthenticationButton.setOnClickListener {
            passkeyAuthenticate()
        }

        binding.encryptButton.setOnClickListener {
            encrypt()
        }

        binding.decryptButton.setOnClickListener {
            decrypt()
        }
    }

    private fun tenantRegister() {
        Log.d("tenantRegister", "Tenant registration in progress...")
        if (!BlockIDSDK.getInstance().isReady) {
            BlockIDSDK.getInstance().initiateWallet()
            BlockIDSDK.getInstance().registerTenant(
                AppConstant.defaultTenant,
                BlockIDSDK.b { status: Boolean, errorResponse: ErrorManager.ErrorResponse?, tenant: BIDTenant? ->
                    Log.e("tenantRegister", "tenant register status: $status")
                    if (status) {
                        println("After register tenant:\n" + printProvider())
                        BlockIDSDK.getInstance().commitApplicationWallet()
                        Toast.makeText(context, "Tenant register success", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("tenantRegister", "ready!")
                        enableButtons()
                    } else {
                        Log.e(
                            "tenantRegister",
                            "tenant register error: " + errorResponse!!.code + " : " + errorResponse.message
                        )
                        Toast.makeText(context, "Tenant register failed", Toast.LENGTH_SHORT).show()
                    }
                })
        } else {
            Toast.makeText(context, "SDK is ready", Toast.LENGTH_SHORT).show()
        }
    }

    private fun passkeyAuthenticate() {
        val userName = "bhavesh"

        if (userName.isEmpty() || userName.length <= 3) {
            Toast.makeText(context, "Please enter correct user name", Toast.LENGTH_SHORT).show()
            return
        }
        Log.d("passkeyAuthenticate", "Passkey authenticate in progress...")
        val request = PasskeyRequest(AppConstant.defaultTenant, userName, null, null)
        BlockIDSDK.getInstance().issueJWTOnPasskeyAuthentication(
            requireActivity(),
            request,
            PasskeyCallback { status: Boolean, passkeyResponse: PasskeyResponse?, errorResponse: ErrorManager.ErrorResponse? ->

                Log.e("passkeyAuthenticate", "passkey authenticate status: $status")
                if (status) {
                    BlockIDSDK.getInstance().commitApplicationWallet()
                    Toast.makeText(context, "passkey authenticate success", Toast.LENGTH_SHORT)
                        .show()
                    println("JWT: " + passkeyResponse?.jwt)
                } else {
                    Log.e(
                        "passkeyAuthenticate",
                        "passkey register error: " + errorResponse?.code + " : " + errorResponse?.message
                    )
                    Toast.makeText(
                        context,
                        "passkey authenticate failed: " + errorResponse?.code + " : " + errorResponse?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun passKeyRegister() {
        val request = PasskeyRequest(AppConstant.defaultTenant, "username", null, null)
        BlockIDSDK.getInstance().registerPasskeyWithAccountLinking(
            this.requireActivity(), request,
            PasskeyCallback { status: Boolean, passkeyResponse: PasskeyResponse?, errorResponse: ErrorManager.ErrorResponse? ->
                Log.e("passKeyRegister", "passkey register status: " + status)
                if (status) {
                    BlockIDSDK.getInstance().commitApplicationWallet()
                    Toast.makeText(context, "passkey register success", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.e(
                        "passKeyRegister",
                        "passkey register error: " + errorResponse!!.code + " : " + errorResponse.message
                    )
                    Toast.makeText(
                        context,
                        "passkey register failed: " + errorResponse.code + " : " + errorResponse.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun encrypt() {
        try {
            val cipher = BiometricCryptoManager.getEncryptCipher()
            authenticate(cipher, true)
        } catch (e: KeyPermanentlyInvalidatedException) {
            Log.d("encrypt", "encrypt: KeyPermanentlyInvalidatedException$e")
//            recoverFromBiometricChange()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("encrypt", e.message ?: "")
        }
    }

    private fun decrypt() {
        try {
            val iv = SecureStorage.getIv(requireContext())
            if (iv == null) {
                Log.e("decrypt","No encrypted data found")
                return
            }
            val cipher = BiometricCryptoManager.getDecryptCipher(iv)
            authenticate(cipher, false)
        } catch (e: KeyPermanentlyInvalidatedException) {
            Log.d("decrypt", "decrypt: KeyPermanentlyInvalidatedException$e")
            recoverFromBiometricChange()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.e("decrypt", e.message ?: "")
        }
    }

    private fun authenticate(cipher: Cipher, encrypt: Boolean) {
        val biometricPrompt =
            BiometricPrompt(
                this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {
                        try {
                            val c = result.cryptoObject?.cipher
                                ?: throw IllegalStateException("Cipher is null after authentication")

                            if (encrypt) {
                                val secret = "USER_SESSION_TOKEN"
                                val encrypted = c.doFinal(
                                    secret.toByteArray(StandardCharsets.UTF_8)
                                )

                                SecureStorage.save(
                                    requireContext(),
                                    encrypted,
                                    c.iv
                                )
                            } else {
                                val decrypted = c.doFinal(
                                    SecureStorage.getEncrypted(requireContext())
                                )
                                Log.d("authenticate", "Decrypted: " + String(decrypted))
                            }
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Log.e("authenticate", "Authentication failed")
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Log.e(
                            "authenticate",
                            "Authentication error: $errString ($errorCode)"
                        )
                    }
                })

        val promptInfo =
            PromptInfo.Builder()
                .setTitle("Secure Biometric Authentication")
                .setSubtitle("Authenticate to access secure data")
                .setNegativeButtonText("Cancel")
                .build()

        biometricPrompt.authenticate(
            promptInfo,
            BiometricPrompt.CryptoObject(cipher)
        )
    }

    private fun printProvider(): String {
        val TAG = "printProvider"
        val sb = StringBuilder()
        val providers = Security.getProviders()

        for (i in providers.indices) {
            val provider = providers[i]
            val line = "Position " + (i + 1) +
                    ": " + provider.getName() +
                    " (Version: " + provider.getVersion() + ")"
            Log.e(TAG, line)
            sb.append(line).append("\n")
        }
        return sb.toString()
    }

    private fun recoverFromBiometricChange() {
        // 1. Delete invalid key
        deleteKey()

        // 2. Clear encrypted data
        SecureStorage.clear(requireContext())

        // 3. Recreate key
        try {
            BiometricCryptoManager.generateKeyIfNeeded()
            //txtResult.setText("Biometrics changed. Please re-enroll.")
        } catch (e: java.lang.Exception) {
            //txtResult.setText("Re-enrollment failed")
        }
    }

    private fun enableButtons() {
        if (BlockIDSDK.getInstance().isReady) {
            binding.passkeyRegistrationButton.setEnabled(true)
            binding.passkeyAuthenticationButton.setEnabled(true)
        } else {
            binding.passkeyRegistrationButton.setEnabled(false)
            binding.passkeyAuthenticationButton.setEnabled(false)
        }
    }
}