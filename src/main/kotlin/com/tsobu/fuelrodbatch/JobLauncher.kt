package com.tsobu.fuelrodbatch

import org.jeasy.batch.core.job.JobBuilder
import org.jeasy.batch.core.job.JobExecutor
import org.jeasy.batch.core.writer.StandardOutputRecordWriter
import org.jeasy.batch.flatfile.FlatFileRecordReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Paths


object JobLauncher {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val logger: Logger = LoggerFactory.getLogger(JobLauncher::class.java)
        val dataSource = Paths.get("tweets.csv")


        // Build a batch job
        val job = JobBuilder()
            .named("fuelrod-message-queue")
            .reader(FlatFileRecordReader(dataSource))
            .writer(StandardOutputRecordWriter())
            .build()

        //Execute the job
        val jobExecutor = JobExecutor()
        val report = jobExecutor.execute(job)
        jobExecutor.shutdown()
    }
}