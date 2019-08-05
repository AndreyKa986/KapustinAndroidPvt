package by.letum8658.homework.dz13

import by.letum8658.homework.dz9.entity.CarResponse
import by.letum8658.homework.dz9.entity.CoordParams
import io.reactivex.Single

class CarRepositoryRemote(private val api: Api) : CarRepository {
    override fun getCarByCoord(params: CoordParams): Single<CarResponse> {
        return api.getCarsByCoord(
            params.coord1.latitude,
            params.coord1.longitude,
            params.coord2.latitude,
            params.coord2.longitude
        )
    }
}