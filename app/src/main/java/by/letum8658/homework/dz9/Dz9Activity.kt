package by.letum8658.homework.dz9

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import by.letum8658.homework.R
import by.letum8658.homework.dz9.entity.Poi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_dz9.*

class Dz9Activity : FragmentActivity(), OnMapReadyCallback, Dz9Fragment.Listener {

    private lateinit var map: GoogleMap
    private lateinit var poiList: List<Poi>
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.dz9BottomSheetContainer, Dz9Fragment())
                .commit()
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.dz9mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = BottomSheetBehavior.from(dz9BottomSheetContainer)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    override fun getPoiList(poiList: List<Poi>) {
        this.poiList = poiList
        showPoi(this.poiList)
    }

    private fun showPoi(poiList: List<Poi>) {
        var place: LatLng
        val builder = LatLngBounds.Builder()
        poiList.forEach {
            place = LatLng(it.coordinate!!.latitude, it.coordinate.longitude)
            map.addMarker(MarkerOptions().position(place))
            builder.include(place)
        }
        val bounds = builder.build()
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }

    override fun onPoiClick(poi: Poi) {
        val poiPlace = LatLng(poi.coordinate!!.latitude, poi.coordinate.longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poiPlace, 18f))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}