package com.tsobu.fuelrodbatch.entities

import com.tsobu.fuelrodbatch.enums.EnumStatus

import javax.persistence.*

@Entity
@Table(name = "sms_campaign")
class SmsCampaign : BaseEntity() {


    @Column(name = "user_id")
    var userId: Long? = null

    @Column(name = "campaign_name", nullable = false)
    var campaignName: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "campaign_status", columnDefinition = "TEXT")
    var campaignStatus: EnumStatus? = null

    @Column(name = "is_draft")
    var isDraft: Boolean? = false

}