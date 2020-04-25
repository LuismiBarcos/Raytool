package com.raytool.configuration.business

import com.raytool.configuration.ConfigurationInfo
import com.raytool.configuration.contracts.IConfiguration
import java.nio.file.FileSystems
import java.nio.file.Files

/**
 *@author Luis Miguel Barcos
 */

class Configuration : IConfiguration {

    override fun create() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exitsConfigurationFolder() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createConfigurationFolder() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createConfigurationFile(configurationInfo: ConfigurationInfo) {
        println("Saving configuration")
    }

    override fun exitsPath(path: String): Boolean {
        return path.isNotEmpty() && Files.exists(FileSystems.getDefault().getPath(path))
    }
}