package com.kfd.reflecta.core.datastore

import android.content.Context
import android.util.Base64
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import com.google.crypto.tink.aead.AeadKeyTemplates
import androidx.core.content.edit

class TokenStore(context: Context) {

    private val prefs = context.getSharedPreferences("secure_tokens", Context.MODE_PRIVATE)

    private val aead: Aead

    init {
        AeadConfig.register()

        val keysetHandle: KeysetHandle = AndroidKeysetManager.Builder()
            .withSharedPref(context, "tink_keyset", "secure_tokens")
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri("android-keystore://tink_master_key")
            .build()
            .keysetHandle

        aead = keysetHandle.getPrimitive(Aead::class.java)
    }

    private fun encrypt(plainText: String): String {
        val cipherText = aead.encrypt(plainText.toByteArray(), null)
        return Base64.encodeToString(cipherText, Base64.DEFAULT)
    }

    private fun decrypt(encoded: String?): String? {
        if (encoded == null) return null
        return try {
            val decoded = Base64.decode(encoded, Base64.DEFAULT)
            val plain = aead.decrypt(decoded, null)
            String(plain)
        } catch (e: Exception) {
            null
        }
    }

    fun saveTokens(accessToken: String, refreshToken: String, sessionId: String) {
        prefs.edit() {
            putString("accessToken", encrypt(accessToken))
            putString("refreshToken", encrypt(refreshToken))
            putString("sessionId", encrypt(sessionId))
        }
    }

    fun getAccessToken(): String? = decrypt(prefs.getString("accessToken", null))

    fun getRefreshToken(): String? = decrypt(prefs.getString("refreshToken", null))

    fun getSessionId(): String? = decrypt(prefs.getString("sessionId", null))

    fun clearTokens() {
        prefs.edit() { clear() }
    }
}
