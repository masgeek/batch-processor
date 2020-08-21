package com.tsobu.fuelrodbatch.entities


import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "sms_outbox")
class SmsOutbox : BaseEntity() {

    var userId: Long? = null

    var dateTime: String? = null
    var messageId: String? = null
    var senderId: String? = null
    var campaignId: Long? = null
    var currency: String = "KES"

    @Column(name = "message_hash", columnDefinition = "TEXT")
    var messageHash: String? = null

    var phoneNumber: String? = null

    @Column(name = "sms_text", columnDefinition = "TEXT")
    var smsText: String? = null


    var characterCount: Int = 0
    var smsCount: Int = 0
    var retryCount: Int = 0

    @Column(name = "single_sms_cost", columnDefinition = "decimal", precision = 10, scale = 2)
    var singleSmsCost: Double = 0.0

    @Column(name = "currency_rate", columnDefinition = "decimal", precision = 10, scale = 2)
    var currencyRate: Double = 0.0

    @Column(name = "actual_cost", columnDefinition = "decimal", precision = 10, scale = 2)
    var actualCost: Double = 0.0

    @Column(name = "message_cost", columnDefinition = "decimal", precision = 10, scale = 2)
    var messageCost: Double = 0.0


    @Column(name = "message_string_cost")
    var messageStringCost: String? = null

    var messageDelivered: Boolean = false

    var deliveryStatus: String? = null
    var failureReason: String? = null
}