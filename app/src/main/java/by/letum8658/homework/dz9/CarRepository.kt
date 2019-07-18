package by.letum8658.homework.dz9

import by.letum8658.homework.dz9.entity.CoordParams
import by.letum8658.homework.dz9.entity.Poi

interface CarRepository {

    fun getCarByCoord(params: CoordParams, listener: CarRepositoryResult)
}

interface CarRepositoryResult {

    fun onSuccess(list: List<Poi>)
    fun onError(throwable: Throwable)
}