package com.raytool.configuration.business

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raytool.configuration.contracts.ConfigurationFolderConstants
import com.raytool.configuration.contracts.ConfigurationInfo
import com.raytool.configuration.contracts.IConfiguration
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.nio.file.FileSystems
import java.nio.file.Files

/**
 *@author Luis Miguel Barcos
 */

class Configuration(
    private val folderConstants: ConfigurationFolderConstants = ConfigurationFolderConstants()
) : IConfiguration {

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
        exitsPath(folderConstants.CONFIGURATION_FOLDER_PATH)

    override fun getConfigurationInfo(): ConfigurationInfo {
        if(exitsConfiguration()) {
            val configurationInfoJson =
                File(
                    folderConstants.CONFIGURATION_FOLDER_PATH + File.separator + folderConstants.CONFIGURATION_FILE_NAME
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
            folderConstants.CONFIGURATION_FOLDER_PATH + File.separator + folderConstants.CONFIGURATION_FILE_NAME
        )
    }

    private fun writeInFile(text: String, path: String) {
        val fileWriter = FileWriter(path)
        fileWriter.write(text)
        fileWriter.flush()
        fileWriter.close()
    }

    private fun exitsConfiguration(): Boolean =
        File(folderConstants.CONFIGURATION_FOLDER_PATH + File.separator + folderConstants.CONFIGURATION_FILE_NAME)
        .exists()

    private fun createConfigurationFolder(): Boolean =
        File(folderConstants.CONFIGURATION_FOLDER_PATH).mkdir()
}