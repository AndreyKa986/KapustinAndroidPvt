package by.letum8658.homework.dz11

import by.letum8658.homework.dz9.entity.Poi

sealed class Dz11CarState {
    class AllData(val poiList: List<Poi>) : Dz11CarState()
    class OnePoiOfData(val poi: Poi) : Dz11CarState()
    object Error : Dz11CarState()
}