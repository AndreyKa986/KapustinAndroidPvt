package by.letum8658.homework.dz13

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.letum8658.homework.dz9.entity.CoordParams
import by.letum8658.homework.dz9.entity.Poi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz13CarViewModel : ViewModel() {

    private var poiList: List<Poi> = emptyList()

    private val repository = provideCarRepository()
    private var disposable: Disposable? = null

    val state: MutableLiveData<Dz13CarState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Dz13CarState>()
    }

    fun loadPoiList(params: CoordParams) {
        if (poiList.isEmpty()) {
            disposable = repository
                .getCarByCoord(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    poiList = it.poiList
                    state.value = Dz13CarState.AllData(poiList)
                }, {
                    state.value = Dz13CarState.Error
                })
        } else {
            state.value = Dz13CarState.AllData(poiList)
        }
    }

    fun getPoiList(): List<Poi> = poiList

    fun setPoi(poi: Poi) {
        state.value = Dz13CarState.OnePoiOfData(poi)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}