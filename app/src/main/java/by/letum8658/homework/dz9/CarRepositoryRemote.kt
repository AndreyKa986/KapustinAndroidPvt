package by.letum8658.homework.dz9

import by.letum8658.homework.dz9.entity.CarResponse
import by.letum8658.homework.dz9.entity.CoordParams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarRepositoryRemote(private val api: Api) : CarRepository {

    override fun getCarByCoord(params: CoordParams, listener: CarRepositoryResult) {
        api.getCarsByCoord(
            params.coord1.latitude,
            params.coord1.longitude,
            params.coord2.latitude,
            params.coord2.longitude
        )
            .enqueue(object : Callback<CarResponse> {
                override fun onResponse(call: Call<CarResponse>, response: Response<CarResponse>) {
                    if (response.body() != null) {
                        listener.onSuccess(response.body()!!.poiList)
                    } else {
                        listener.onError(Throwable("response.body == null"))
                    }
                }

                override fun onFailure(call: Call<CarResponse>, t: Throwable) {
                    listener.onError(t)
                }
            })
    }
}