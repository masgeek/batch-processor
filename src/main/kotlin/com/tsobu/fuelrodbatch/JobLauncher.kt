package com.tsobu.fuelrodbatch

import com.tsobu.fuelrodbatch.common.Tweet
import org.jeasy.batch.core.filter.HeaderRecordFilter
import org.jeasy.batch.core.job.JobBuilder
import org.jeasy.batch.core.job.JobExecutor
import org.jeasy.batch.core.writer.StandardOutputRecordWriter
import org.jeasy.batch.flatfile.DelimitedRecordMapper
import org.jeasy.batch.flatfile.FlatFileRecordReader
import org.jeasy.batch.validation.BeanValidationRecordValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Paths


object JobLauncher {

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val logger: Logger = LoggerFactory.getLogger(JobLauncher::class.java)
        val dataSource = Paths.get("tweets.csv")


        val job = JobBuilder()
            .named("fuelrod-message-queue")
            .reader(FlatFileRecordReader(dataSource))
            .mapper(DelimitedRecordMapper(Tweet::class.java, "id", "name", "message"))
            .validator(BeanValidationRecordValidator())
            .writer(StandardOutputRecordWriter())
            .build()

        val jobExecutor = JobExecutor()
        val report = jobExecutor.execute(job)
        jobExecutor.shutdown()
    }
}