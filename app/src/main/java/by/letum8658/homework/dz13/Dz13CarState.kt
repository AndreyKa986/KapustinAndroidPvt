package by.letum8658.homework.dz13

import by.letum8658.homework.dz9.entity.Poi

sealed class Dz13CarState {
    class AllData(val poiList: List<Poi>) : Dz13CarState()
    class OnePoiOfData(val poi: Poi) : Dz13CarState()
    object Error : Dz13CarState()
}