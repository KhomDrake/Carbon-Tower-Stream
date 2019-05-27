package com.carbontower.stream.application.web

import com.carbontower.stream.application.web.controllers.GamesController
import com.carbontower.stream.application.web.controllers.StreamsController
import com.carbontower.stream.application.web.controllers.UsersController
import com.carbontower.stream.common.koin.auxiliaryModule
import com.carbontower.stream.common.koin.controllerModule
import com.carbontower.stream.common.koin.repositoryModule
import com.carbontower.stream.common.koin.serviceModule
import com.carbontower.stream.resources.database.Connection
import io.javalin.Javalin
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject

class CarbonTowerStream : KoinComponent {
    private lateinit var javalin: Javalin

    private val gamesController: GamesController by inject()
    private val streamsController: StreamsController by inject()
    private val usersController: UsersController by inject()
    private val connection: Connection by inject()

    fun startServer() {
        StandAloneContext.startKoin(
            listOf(
                auxiliaryModule,
                repositoryModule,
                serviceModule,
                controllerModule
            )
        )
        connection.connectWithCarbonTowerDatabase()

        javalin = Javalin.create()
        javalin.apply {
            //            exception(Exception::class.java, Handler())
            routes {
                gamesController.routes()
                streamsController.routes()
                usersController.routes()
            }
        }
        javalin.start(3000)
    }

    fun stopServer() {
        javalin.stop()
    }
}