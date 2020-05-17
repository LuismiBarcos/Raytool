package com.raytool.main

import com.raytool.commands.*

/**
 *@author Luis Miguel Barcos
 */

fun main(args: Array<String>) {
    when(val command = Commands.create(args[0], args.toList())){
        is Command.Init -> Init(command).execute()
        is Command.Daily -> Daily(command).execute()
        is Command.Docker -> Docker(command).execute()
        is Command.Change -> Change(command).execute()
        is Command.Help -> Help(command).execute()
    }
}