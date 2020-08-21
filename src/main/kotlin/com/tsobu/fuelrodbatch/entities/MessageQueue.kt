package com.tsobu.fuelrodbatch.entities

import com.tsobu.fuelrodbatch.enums.EnumStatus
import javax.persistence.*

@Entity
@Table(name = "message_queue")
class MessageQueue : BaseEntity() {

    @Column(name = "campaign_id")
    var campaignId: Long? = null

    @Column(name = "message_hash", columnDefinition = "TEXT")
    var messageHash: String? = null

    @Column(name = "message", columnDefinition = "TEXT")
    var message: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "TEXT")
    var messageStatus: EnumStatus? = null

    var phoneNumber: String? = null

    @Column(name = "sms_count")
    var smsCount: Int = 0

    @Column(name = "message_length")
    var messageLength: Int = 0

    var messageSent: Boolean = false


    @Column(name = "number_valid")
    var numberValid: Boolean = true

    @Column(name = "message_cost", columnDefinition = "decimal", precision = 10, scale = 2)
    var messageCost: Double = 0.0
}