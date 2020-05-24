package com.raytool.configuration.contracts

import java.io.File

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

data class ConfigurationFolderConstants(
    val ROOT_FOLDER: String = System.getProperty("user.home"),
    val CONFIGURATION_FOLDER_NAME: String = ".raytool",
    val CONFIGURATION_FOLDER_PATH: String = "${ROOT_FOLDER}${File.separator}${CONFIGURATION_FOLDER_NAME}",
    val CONFIGURATION_FILE_NAME: String = "raytool_configuration.json"
)