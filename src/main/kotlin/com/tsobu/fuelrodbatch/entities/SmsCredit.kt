package com.tsobu.fuelrodbatch.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "sms_credits")
class SmsCredit : BaseEntity() {

    @Column(name = "user_id")
    var userId: Long? = null

    @Column(name = "remarks")
    var remarks: String? = null

    @Column(name = "credit_amount", columnDefinition = "decimal", precision = 10, scale = 2)
    var creditAmount: Double = 0.0
}