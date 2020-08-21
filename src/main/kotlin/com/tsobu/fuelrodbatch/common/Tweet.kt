package com.tsobu.fuelrodbatch.common

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class Tweet {
    private val id = 0

    @NotNull
    private val user: String? = null

    @Size(min = 0, max = 280)
    private val message: String? = null
}