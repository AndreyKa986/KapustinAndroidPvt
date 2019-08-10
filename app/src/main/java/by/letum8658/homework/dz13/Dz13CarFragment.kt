package by.letum8658.homework.dz13

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.letum8658.homework.R
import by.letum8658.homework.dz9.Dz9Adapter
import by.letum8658.homework.dz9.entity.Poi

class Dz13CarFragment : Fragment(), Dz9Adapter.ClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: Dz13CarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frafment_bottom_sheet_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.dz9RecyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(Dz13CarViewModel::class.java)

        viewModel
            .state
            .observe(this, Observer {
                when (it) {
                    is Dz13CarState.AllData -> {
                        recyclerView.adapter = Dz9Adapter(it.poiList, this)
                    }
                }
            })
    }

    override fun onPoiClick(item: Poi) {
        viewModel.setPoi(item)
    }
}