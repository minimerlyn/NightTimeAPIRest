package com.esei.grvidal.nightTimeApi.projections

import com.esei.grvidal.nightTimeApi.model.Bar
import java.time.LocalDate

interface EventProjection {
    fun getId(): Long
    fun getDescription() : String
    fun getDate() : LocalDate?
    fun getBarName(): String
}