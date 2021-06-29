package com.esei.grvidal.nightTimeApi.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*



/**
 * Entity of the Bar, this holds the data that the DB can save
 * @param owner is the owner of the bar
 * @param address is the address of the bar
 *
 *
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator::class,
        property = "id")
@Table(name = "bar")
class Bar(
        var name: String,
        var owner: String,
        var address: String,
        var description: String?,

        var mondaySchedule : String? = null,
        var tuesdaySchedule : String? = null,
        var wednesdaySchedule : String? = null,
        var thursdaySchedule : String? = null,
        var fridaySchedule : String? = null,
        var saturdaySchedule : String? = null,
        var sundaySchedule : String? = null,

        @ManyToOne
        @JoinColumn(name = "city_id")
        var city: City,

) {
    
    @OneToMany(mappedBy = "bar",cascade = [CascadeType.REMOVE])
    var events : List<Event>? = null

    @OneToMany(mappedBy = "bar",cascade = [CascadeType.ALL])
    var photos : List<PhotoURL>? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

}