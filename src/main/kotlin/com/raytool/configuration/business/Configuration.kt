package com.raytool.configuration.business

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raytool.configuration.ConfigurationInfo
import com.raytool.configuration.contracts.IConfiguration
import com.raytool.utils.CONFIGURATION_FILE_NAME
import com.raytool.utils.CONFIGURATION_FOLDER_PATH
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.nio.file.FileSystems
import java.nio.file.Files

/**
 *@author Luis Miguel Barcos
 */

class Configuration : IConfiguration {

    override fun exitsPath(path: String): Boolean =
        path.isNotEmpty() && Files.exists(FileSystems.getDefault().getPath(path))

    override fun createConfigurationFile(configurationInfo: ConfigurationInfo) =
        when(exitsConfigurationFolder()){
            true -> createConfigurationJson(configurationInfo)
            false -> {
                if (createConfigurationFolder()) {
                    createConfigurationJson(configurationInfo)
                } else {
                    println("Folder not created properly")
                }
            }
        }

    override fun exitsConfigurationFolder(): Boolean =
        exitsPath(String.CONFIGURATION_FOLDER_PATH)

    override fun getConfigurationInfo(): ConfigurationInfo {
        if(exitsConfiguration()) {
            val configurationInfoJson =
                File(
                    String.CONFIGURATION_FOLDER_PATH + File.separator + String.CONFIGURATION_FILE_NAME
                ).readText()
            val gson = Gson()
            return gson.fromJson(configurationInfoJson, ConfigurationInfo::class.java)
        } else {
            throw FileNotFoundException()
        }
    }

    private fun createConfigurationJson(configurationInfo: ConfigurationInfo) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonConfigurationInfo = gson.toJson(configurationInfo)
        writeInFile(
            jsonConfigurationInfo,
            String.CONFIGURATION_FOLDER_PATH + File.separator + String.CONFIGURATION_FILE_NAME
        )
    }

    private fun writeInFile(text: String, path: String) {
        val fileWriter = FileWriter(path)
        fileWriter.write(text)
        fileWriter.flush()
        fileWriter.close()
    }

    private fun exitsConfiguration(): Boolean =
        File(String.CONFIGURATION_FOLDER_PATH + File.separator + String.CONFIGURATION_FILE_NAME).exists()

    private fun createConfigurationFolder(): Boolean =
        File(String.CONFIGURATION_FOLDER_PATH).mkdir()
}