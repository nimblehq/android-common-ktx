package co.nimblehq.common.extensions

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.internal.Streams
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.lang.Appendable
import java.lang.reflect.Type

inline fun <reified T : Any> SharedPreferences.getObject(key: String): T? {
    return try {
        Gson().fromJson<T>(getString(key, null), T::class.java)
    } catch (e: Exception) {
        null
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T {
    return when (T::class) {
        Boolean::class -> getBoolean(key, defaultValue as? Boolean? ?: false) as T
        Float::class -> getFloat(key, defaultValue as? Float? ?: 0.0f) as T
        // We need convert toString() before toDouble() to ensure the exact value
        Double::class -> getFloat(key, (defaultValue as? Double?)?.toFloat() ?: 0.0f).toString().toDouble() as T
        Int::class -> getInt(key, defaultValue as? Int? ?: 0) as T
        Long::class -> getLong(key, defaultValue as? Long? ?: 0L) as T
        String::class -> getString(key, defaultValue as? String? ?: "") as T
        else -> {
            if (defaultValue is Set<*>) {
                getStringSet(key, defaultValue as Set<String>) as T
            } else {
                val typeName = T::class.java.simpleName
                throw Exception("Unable to get shared preference with value type '$typeName'. Use getObject")
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
@Throws(Exception::class)
inline operator fun <reified T : Any> SharedPreferences.set(key: String, value: T) {
    with(edit()) {
        when (T::class) {
            Boolean::class -> putBoolean(key, value as Boolean)
            Float::class -> putFloat(key, value as Float)
            // We need convert toString() before toDouble() to ensure the exact value
            Double::class -> putFloat(key, (value as Double).toString().toFloat())
            Int::class -> putInt(key, value as Int)
            Long::class -> putLong(key, value as Long)
            String::class -> putString(key, value as String)
            else -> {
                if (value is Set<*>) {
                    putStringSet(key, value as Set<String>)
                } else {
                    val json = Gson().toJson(value)
                    putString(key, json)
                }
            }
        }
        commit()
    }
}

fun SharedPreferences.clearAll() {
    with(edit()) {
        clear()
    }.apply()
}
