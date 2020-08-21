package com.tsobu.fuelrodbatch

import org.jobrunr.configuration.JobRunr
import org.jobrunr.storage.sql.common.DefaultSqlStorageProvider
import org.jobrunr.storage.sql.common.DefaultSqlStorageProvider.DatabaseOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object JobLauncher {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val logger: Logger = LoggerFactory.getLogger(JobLauncher::class.java)

        JobRunr.configure()
            .useStorageProvider(DefaultSqlStorageProvider(dataSource, DatabaseOptions.SKIP_CREATE))
            .useDefaultBackgroundJobServer()
            .initialize()
    }
}