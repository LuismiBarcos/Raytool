package com.raytool.configuration.contracts

import com.raytool.configuration.ConfigurationInfo

/**
 *@author Luis Miguel Barcos
 */

interface IConfiguration{

    fun exitsPath(path: String): Boolean

    fun exitsConfigurationFolder(): Boolean

    fun createConfigurationFile(configurationInfo: ConfigurationInfo)

    fun getConfigurationInfo(): ConfigurationInfo

}