package com.raytool.utils

import java.io.File

/**
 *@author Luis Miguel Barcos
 */

/**
 * CONSTANTS
 */
val String.Companion.EMPTY: String
    get() = ""

val String.Companion.ROOT_FOLDER: String
    get() = System.getProperty("user.home")

val String.Companion.CONFIGURATION_FOLDER_NAME: String
    get() = ".raytool"

val String.Companion.CONFIGURATION_FOLDER_PATH: String
    get() = "${String.ROOT_FOLDER}${File.separator}${String.CONFIGURATION_FOLDER_NAME}"

val String.Companion.CONFIGURATION_FILE_NAME: String
    get() = "raytool_configuration.json"
/**
 * FUNCTIONS
 */