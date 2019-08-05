package by.letum8658.homework.dz13

import by.letum8658.homework.dz9.entity.CarResponse
import by.letum8658.homework.dz9.entity.CoordParams
import io.reactivex.Single

interface CarRepository {
    fun getCarByCoord(params: CoordParams): Single<CarResponse>
}