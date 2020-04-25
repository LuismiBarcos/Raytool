package com.raytool.configuration.business

import com.raytool.configuration.ConfigurationInfo
import com.raytool.configuration.contracts.IConfiguration
import com.raytool.utils.CONFIGURATION_FOLDER_NAME
import com.raytool.utils.CONFIGURATION_FOLDER_PATH
import com.raytool.utils.ROOT_FOLDER
import java.io.File
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

    override fun createConfigurationFile(configurationInfo: ConfigurationInfo) =
        when(exitsConfigurationFolder()){
            true -> createConfigurationJson()
            false -> {
                if (createConfigurationFolder()) {
                    createConfigurationJson()
                } else {
                    println("Folder not created properly")
                }
            }
        }

    override fun exitsPath(path: String): Boolean {
        return path.isNotEmpty() && Files.exists(FileSystems.getDefault().getPath(path))
    }

    private fun createConfigurationJson() {
        println("Creating JSON")
    }
}