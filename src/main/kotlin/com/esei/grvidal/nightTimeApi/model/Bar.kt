package com.esei.grvidal.nightTimeApi.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*



/**
 * Entity of the Bar, this holds the data that the DB can save
 * @param owner is the owner of the bar
 * @param address is the address of the bar
 *
 * //TODO LOOK https://www.baeldung.com/jpa-cascade-types
 * https://stackoverflow.com/questions/7197181/jpa-unidirectional-many-to-one-and-cascading-delete
 *
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator::class,
        property = "id")//todo no se como funcionara
@Table(name = "bar")
class Bar(
        var name: String,
        var owner: String,
        var address: String,

        @ManyToOne//(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "city_id")
        var city: City,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "schedule_id")
        var schedule: Schedule? = null
) {
    
    @OneToMany(mappedBy = "bar",cascade = [CascadeType.REMOVE])
    var events : List<Event>? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

}

/**
 * Entity of the Schedule, this holds the data that the DB can save
 */
@Entity
@Table(name = "schedule")
class Schedule(
        var monday: Boolean,
        var tuesday: Boolean,
        var wednesday: Boolean,
        var thursday: Boolean,
        var friday: Boolean,
        var saturday: Boolean,
        var sunday: Boolean,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}