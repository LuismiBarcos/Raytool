package com.raytool.configuration.business

import com.raytool.configuration.Bundle
import com.raytool.configuration.ConfigurationInfo
import com.raytool.configuration.contracts.IConfiguration

/**
 *@author Luis Miguel Barcos
 */

class Configuration : IConfiguration {

    var configurationInfo = ConfigurationInfo("", bundle = Bundle("", -1, -1))

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun exitsPath() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}