package com.esei.grvidal.nightTimeApi.services

import com.esei.grvidal.nightTimeApi.repository.BarRepository
import com.esei.grvidal.nightTimeApi.repository.CityRepository
import com.esei.grvidal.nightTimeApi.dto.BarDTOEdit
import com.esei.grvidal.nightTimeApi.exception.NotFoundException
import com.esei.grvidal.nightTimeApi.model.Bar
import com.esei.grvidal.nightTimeApi.projections.BarDetailsProjection
import com.esei.grvidal.nightTimeApi.projections.BarFullProjection
import com.esei.grvidal.nightTimeApi.projections.BarProjection
import com.esei.grvidal.nightTimeApi.serviceInterface.IBarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

/**
 * Bar service, is the implementation of the DAO interface
 *
 */
@Service
class BarService : IBarService {

    /**
     *Dependency injection with autowired
     */
    @Autowired
    lateinit var barRepository: BarRepository

    @Autowired
    lateinit var cityRepository: CityRepository


    /**
     * List all the bars in the given city [cityId]
     */
    override fun listByCity(cityId: Long): List<BarProjection> {
        return barRepository.getBarsByCity_Id(cityId)
    }

    /**
     * Returns the information that is not in [BarProjection] from the [Bar] with id [idBar]
     */
    @Throws(NotFoundException::class)
    override fun getDetails(idBar: Long): BarDetailsProjection {
        return barRepository.getBarDetailsById(idBar)
                .orElseThrow { NotFoundException("No bar with id $idBar have been found") }


    }

    /**
     * This will show one bar, if not it will throw a NotFoundException
     */
    @Throws(NotFoundException::class)
    override fun load(idBar: Long): Bar {
        return barRepository.findById(idBar)
                .orElseThrow { NotFoundException("No bar with id $idBar have been found") }
    }

    /**
     * This will save a new bar
     */
    override fun save(bar: Bar): Long {
        return barRepository.save(bar).id
    }

    /**
     * This will update a bar through [barEdit] and its non-null attributes
     */
    @Throws(NotFoundException::class)
    override fun update(idBar: Long, barEdit: BarDTOEdit) {

        //getting the old Bar
        val barOriginal = load(idBar)

        val cityEdit = barEdit.cityId?.let {
            cityRepository.findById(it)
                    .orElse(barOriginal.city)
        }
                ?: barOriginal.city


        //updating the bar
        barOriginal.apply {
            id = idBar
            name = barEdit.name ?: this.name
            owner = barEdit.owner ?: this.owner
            address = barEdit.address ?: this.address
            city = cityEdit

            // if the parameter [mondaySchedule] is null, it won't update
            this.mondaySchedule = if (barEdit.mondaySchedule == null)
                this.mondaySchedule
            else {
                // if the containing string is null, mondaySchedule will be updated as null
                if (barEdit.mondaySchedule == "null") null
                //else it will update with the right value
                else barEdit.mondaySchedule
            }

            this.tuesdaySchedule = if (barEdit.tuesdaySchedule == null)
                this.tuesdaySchedule
            else {
                if (barEdit.tuesdaySchedule != "null") barEdit.tuesdaySchedule
                else null
            }

            this.wednesdaySchedule = if (barEdit.wednesdaySchedule == null)
                this.wednesdaySchedule
            else {
                if (barEdit.wednesdaySchedule != "null") barEdit.wednesdaySchedule
                else null
            }

            this.thursdaySchedule = if (barEdit.thursdaySchedule == null)
                this.thursdaySchedule
            else {
                if (barEdit.thursdaySchedule != "null") barEdit.thursdaySchedule
                else null
            }

            this.fridaySchedule = if (barEdit.fridaySchedule == null)
                this.fridaySchedule
            else {
                if (barEdit.fridaySchedule != "null") barEdit.fridaySchedule
                else null
            }

            this.saturdaySchedule = if (barEdit.saturdaySchedule == null)
                this.saturdaySchedule
            else {
                if (barEdit.saturdaySchedule != "null") barEdit.saturdaySchedule
                else null
            }

            this.sundaySchedule = if (barEdit.sundaySchedule == null)
                this.sundaySchedule
            else {
                if (barEdit.sundaySchedule != "null") barEdit.sundaySchedule
                else null
            }
        }

        barRepository.save(barOriginal)

    }

    /**
     * This will remove a bars through its [idBar], if it cant find it, it will throw a NotFoundException
     */
    @Throws(NotFoundException::class)
    override fun remove(idBar: Long) {

        val bar = load(idBar)
        barRepository.delete(bar)

    }

    @Throws(NotFoundException::class)
    override fun getFullProjection(idBar: Long): BarFullProjection {

        try {
            return barRepository.getFullBarById(idBar)

        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException("No bar with id $idBar have been found")
        }
    }
}


















