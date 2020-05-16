package com.raytool.configuration.contracts

import com.raytool.configuration.ConfigurationInfo

/**
 *@author Luis Miguel Barcos
 */

interface IConfiguration{
    fun create()

    fun exitsConfigurationFolder(): Boolean

    fun createConfigurationFolder(): Boolean

    fun createConfigurationFile(configurationInfo: ConfigurationInfo)

    fun exitsPath(path: String): Boolean

    fun getConfigurationInfo(): ConfigurationInfo

}