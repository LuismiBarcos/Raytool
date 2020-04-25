package com.raytool.configuration.business

import com.google.gson.GsonBuilder
import com.raytool.configuration.ConfigurationInfo
import com.raytool.configuration.contracts.IConfiguration
import com.raytool.utils.CONFIGURATION_FILE_NAME
import com.raytool.utils.CONFIGURATION_FOLDER_PATH
import java.io.File
import java.io.FileWriter
import java.nio.file.FileSystems
import java.nio.file.Files

/**
 *@author Luis Miguel Barcos
 */

class Configuration : IConfiguration {

    override fun create() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exitsConfigurationFolder(): Boolean =
        exitsPath(String.CONFIGURATION_FOLDER_PATH)

    override fun createConfigurationFolder(): Boolean =
        File(String.CONFIGURATION_FOLDER_PATH).mkdir()

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
}