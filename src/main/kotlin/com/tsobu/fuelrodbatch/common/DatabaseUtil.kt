package com.tsobu.fuelrodbatch.common

import com.tsobu.fuelrodbatch.JobLauncher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.sql.ResultSet
import javax.sql.DataSource

object DatabaseUtil {
    private var embeddedDatabase: EmbeddedDatabase? = null
    private var jdbcTemplate: JdbcTemplate? = null
    private val logger: Logger = LoggerFactory.getLogger(JobLauncher::class.java)

    fun getDataSource(): DataSource? {
        return embeddedDatabase
    }

    fun startEmbeddedDatabase() {
        embeddedDatabase = EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("db/schema.sql")
            .addScript("db/data.sql")
            .build()
        jdbcTemplate = JdbcTemplate(embeddedDatabase)
    }

    fun dumpTweetTable() {
        logger.info("Loading tweets from the database...")
        jdbcTemplate!!.query("select * from tweet") { resultSet: ResultSet ->
            println(
                "Tweet : id= " + resultSet.getString("id") + " | " +
                        "user= " + resultSet.getString("user") + " | " +
                        "message= " + resultSet.getString("message")
            )
        }
    }

    @Throws(IOException::class)
    fun cleanUpWorkingDirectory() {
        //delete hsqldb tmp files
        Files.deleteIfExists(Paths.get("mem.log"))
        Files.deleteIfExists(Paths.get("mem.properties"))
        Files.deleteIfExists(Paths.get("mem.script"))
        Files.deleteIfExists(Paths.get("mem.tmp"))
    }
}