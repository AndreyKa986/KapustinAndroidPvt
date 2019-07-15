package by.letum8658.homework.dz9

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R
import by.letum8658.homework.dz9.entity.CoordParams
import by.letum8658.homework.dz9.entity.Coordinate
import by.letum8658.homework.dz9.entity.Poi

class Dz9Fragment : Fragment(), CarRepositoryResult, Dz9Adapter.ClickListener {

    private var listener: Listener? = null
    private val poiList: MutableList<Poi> = mutableListOf()
    private var carRepository: CarRepository = provideCarRepository()
    private lateinit var adapter: Dz9Adapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frafment_bottom_sheet_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.dz9RecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val params = CoordParams(Coordinate(0.0, 0.0), Coordinate(0.0, 0.0))
        carRepository.getCarByCoord(params, this)
    }

    override fun onSuccess(list: List<Poi>) {
        poiList.addAll(list)
        adapter = Dz9Adapter(poiList, this)
        recyclerView.adapter = adapter
        listener?.getPoiList(poiList)
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, getText(R.string.sorry2), Toast.LENGTH_SHORT).show()
    }

    override fun onPoiClick(item: Poi) {
        listener?.onPoiClick(item)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface Listener {
        fun getPoiList(poiList: List<Poi>)
        fun onPoiClick(poi: Poi)
    }
}