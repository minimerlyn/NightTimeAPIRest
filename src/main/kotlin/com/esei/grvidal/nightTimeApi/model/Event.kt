package com.esei.grvidal.nightTimeApi.model

import java.time.LocalDate
import javax.persistence.*

/**
 * Entity of the Bar, this holds the data that the DB can save
 * @param description is the description of the [Event]
 * @param date of the [Event]
 */

@Entity
@Table(name = "event")
class Event(
        var description: String,
        var date: LocalDate,

        @ManyToOne
        @JoinColumn(name = "bar_id")
        var bar: Bar,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0
}