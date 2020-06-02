package com.raytool.configuration.contracts

/**
 *@author Luis Miguel Barcos
 */
interface IBundleConfiguration {
    fun getDatabaseUsername(): String

    fun getDatabasePassword(): String

    fun getDatabaseName(): String
}