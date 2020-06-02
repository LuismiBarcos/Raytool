package com.raytool.configuration.business

import com.raytool.configuration.contracts.ConfigurationInfo
import com.raytool.configuration.contracts.IBundleConfiguration
import com.raytool.configuration.contracts.IConfiguration
import java.io.File
import java.io.FileInputStream
import java.lang.RuntimeException
import java.util.*

/**
 *@author Luis Miguel Barcos
 */
class BundleConfiguration(
    private val configuration: IConfiguration = Configuration()
) : IBundleConfiguration {

    private val configurationInfo: ConfigurationInfo = configuration.getConfigurationInfo()
    private val liferayBundlePath: String = configurationInfo.bundle.path

    override fun getDatabaseUsername(): String {
        val portalExtProperties = getPortalExtPropertiesFile()
        return readFromPropertiesFile(portalExtProperties,"jdbc.default.username")
    }

    override fun getDatabasePassword(): String {
        val portalExtProperties = getPortalExtPropertiesFile()
        return readFromPropertiesFile(portalExtProperties,"jdbc.default.password")
    }

    override fun getDatabaseName(): String {
        val portalExtProperties = getPortalExtPropertiesFile()
        val dbUrl = readFromPropertiesFile(portalExtProperties, "jdbc.default.url")
        return dbUrl.split('?').first().split('/').last()
    }

    private fun getPortalExtPropertiesFile(): File = File("$liferayBundlePath/portal-ext.properties")

    private fun readFromPropertiesFile(propertiesFile: File, propertyToRead: String): String {
        val properties = Properties()
        val inputStream = FileInputStream(propertiesFile)
        properties.load(inputStream)
        return try {
            properties.getProperty(propertyToRead)
        }catch (ex: Exception) {
            throw RuntimeException("Property $propertyToRead not found in ${propertiesFile.absoluteFile}")
        } finally {
            properties.clear()
            inputStream.close()
        }
    }
}