package by.letum8658.homework.dz11

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.letum8658.homework.dz9.CarRepositoryResult
import by.letum8658.homework.dz9.entity.CoordParams
import by.letum8658.homework.dz9.entity.Poi
import by.letum8658.homework.dz9.provideCarRepository

class Dz11CarViewModel : ViewModel(), CarRepositoryResult {

    private var poiList: List<Poi> = emptyList()

    val state: MutableLiveData<Dz11CarState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Dz11CarState>()
    }

    fun loadPoiList(params: CoordParams) {
        provideCarRepository().getCarByCoord(params, this)
    }

    fun getPoiList(): List<Poi> = poiList

    fun setPoi(poi: Poi) {
        state.value = Dz11CarState.OnePoiOfData(poi)
    }

    override fun onSuccess(list: List<Poi>) {
        poiList = list
        state.value = Dz11CarState.AllData(poiList)
    }

    override fun onError(throwable: Throwable) {
        state.value = Dz11CarState.Error
    }
}