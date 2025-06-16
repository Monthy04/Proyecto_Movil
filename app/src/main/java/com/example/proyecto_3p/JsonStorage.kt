package com.example.proyecto_3p

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

object JsonStorage {

    val gson = Gson()

    inline fun <reified T> loadData(context: Context, fileName: String): T? {
        return try {
            val file = File(context.filesDir, fileName)
            if (!file.exists()) return null

            val bufferedReader = BufferedReader(FileReader(file))
            val json = bufferedReader.use { it.readText() }
            gson.fromJson(json, object : TypeToken<T>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun <T> saveData(context: Context, fileName: String, data: T) {
        try {
            val json = gson.toJson(data)
            val file = File(context.filesDir, fileName)
            val writer = BufferedWriter(FileWriter(file))
            writer.use { it.write(json) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
