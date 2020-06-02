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

object MySQLCommands : ShellCommands() {
    fun getDropDatabaseCommand(databaseName: String, mysqlUsername: String, mysqlPassword: String) : String {
        val dropDatabaseSQLSyntax = "DROP DATABASE IF EXISTS $databaseName".replace(' ', '&')
        return "mysql -u $mysqlUsername -p$mysqlPassword -e $dropDatabaseSQLSyntax"
    }

    fun getCreateDatabaseCommand(databaseName: String, mysqlUsername: String, mysqlPassword: String) : String {
        val createDatabaseSQLSyntax =
            "CREATE DATABASE $databaseName CHARACTER SET UTF8mb4 COLLATE utf8mb4_bin".replace(' ', '&')
        return "mysql -u $mysqlUsername -p$mysqlPassword -e $createDatabaseSQLSyntax"
    }
}

object DockerCommands : ShellCommands()