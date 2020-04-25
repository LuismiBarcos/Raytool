package com.raytool.configuration

/**
 *@author Luis Miguel Barcos
 */

data class ConfigurationInfo(
    val liferayPortalCEPath: String,
    val bundle: Bundle
)

data class Bundle(
    val path: String,
    val port: Number = 8080,
    val sessionTime: Number = 200
)