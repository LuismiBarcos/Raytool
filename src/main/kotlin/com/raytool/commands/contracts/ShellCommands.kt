package com.raytool.commands.contracts

/**
 *@author Luis Miguel Barcos
 */

open class ShellCommands {
    companion object CommonCommands
}

object GitCommands : ShellCommands() {
    const val GIT_CHECKOUT_MASTER = "git checkout master"
    const val GIT_PULL_UPSTREAM = "git pull --ff-only upstream master"
    const val GIT_PUSH_ORIGIN_MASTER = "git push origin master"
}

object PortalCECommands : ShellCommands() {
    const val ANT_ALL = "ant all"
}

object MySQLCommands : ShellCommands()

object DockerCommands : ShellCommands()