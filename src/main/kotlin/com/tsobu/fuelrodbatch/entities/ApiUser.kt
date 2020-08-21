package com.tsobu.fuelrodbatch.entities


import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*


@Entity
@Table(name = "api_users")
class ApiUser : BaseEntity() {

    @Column(unique = true)
    var userName: String? = null

    var userEmail: String? = null

    var userPassword: String? = null

    @Column(name = "message_cost", columnDefinition = "decimal", precision = 10, scale = 2)
    var messageCost: Double = 0.0

    //    @OneToMany(fetch = FetchType.LAZY, targetEntity = ApiUserServices::class, mappedBy = "apiUser", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OneToMany(targetEntity = ApiUserServices::class, mappedBy = "apiUser", cascade = [CascadeType.ALL], orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    var userServices: Set<ApiUserServices>? = null

//    @OneToMany(targetEntity = SmsCredit::class, mappedBy = "apiUser", cascade = [CascadeType.ALL], orphanRemoval = true)
//    @Fetch(FetchMode.JOIN)
//    var credits: Set<SmsCredit>? = null

    @Column(name = "active")
    var active: Boolean? = false

    @Column(name = "overdraft_allowed")
    var overdraftAllowed: Boolean = false

    @Transient
    var validCredentials: Boolean? = false


    fun hasEnoughCredits(loadedCredits: Double, totalSpend: Double): Boolean {
        val balance = loadedCredits - totalSpend
        return balance > this.messageCost
    }

    fun addUserApiServices(apiUser: ApiUser) {
        var size = apiUser.userServices?.size
        if (size == null) {
            size = 0
        }
        val apiUserServiceSet: HashSet<ApiUserServices> = HashSet<ApiUserServices>(size)

        apiUser.userServices!!.forEach { apiUserService ->
            apiUserService.apiUser = apiUser
            apiUserServiceSet.add(apiUserService)
        }
        this.userServices = apiUserServiceSet
    }
}