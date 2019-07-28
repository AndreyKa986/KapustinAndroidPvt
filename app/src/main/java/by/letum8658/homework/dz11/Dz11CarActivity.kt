package by.letum8658.homework.dz11

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.letum8658.homework.R
import by.letum8658.homework.dz9.entity.CoordParams
import by.letum8658.homework.dz9.entity.Coordinate
import by.letum8658.homework.dz9.entity.Poi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_dz9.*

class Dz11CarActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var poiPicture: BitmapDescriptor
    private var isOnePoiOnScreenNow = false
    private lateinit var viewModel: Dz11CarViewModel
    private var isMapReady = false

    companion object {
        const val PADDING = 100
        const val ZOOM = 18f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        viewModel = ViewModelProviders.of(this).get(Dz11CarViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.dz9BottomSheetContainer, Dz11CarFragment())
                .commit()
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.dz9mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = BottomSheetBehavior.from(dz9BottomSheetContainer)

        poiPicture = bitmapDescriptorFromVector(this, R.drawable.ic_car)!!

        val params = CoordParams(Coordinate(0.0, 0.0), Coordinate(0.0, 0.0))

        viewModel
            .state
            .observe(this, Observer {
                when (it) {
                    is Dz11CarState.AllData -> {
                        if (isMapReady) showPoi(it.poiList)
                    }
                    is Dz11CarState.OnePoiOfData -> {
                        if (isMapReady) onPoiClick(it.poi)
                    }
                    is Dz11CarState.Error -> {
                        Toast.makeText(this, getText(R.string.sorry2), Toast.LENGTH_SHORT).show()
                    }
                }
            })

        viewModel.loadPoiList(params)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        isMapReady = true
        viewModel
            .state
            .observe(this, Observer {
                when (it) {
                    is Dz11CarState.AllData -> {
                        showPoi(it.poiList)
                    }
                }
            })
    }

    private fun showPoi(poiList: List<Poi>) {
        var place: LatLng
        val builder = LatLngBounds.Builder()
        map.clear()
        poiList.forEach {
            place = LatLng(it.coordinate!!.latitude, it.coordinate.longitude)
            map.addMarker(
                MarkerOptions()
                    .position(place)
                    .icon(poiPicture)
                    .rotation(it.heading!!.toFloat())
            )
            builder.include(place)
        }
        val bounds = builder.build()
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, PADDING))
        isOnePoiOnScreenNow = false
    }

    private fun onPoiClick(poi: Poi) {
        val poiPlace = LatLng(poi.coordinate!!.latitude, poi.coordinate.longitude)
        map.clear()
        map.addMarker(
            MarkerOptions()
                .position(poiPlace)
                .icon(poiPicture)
                .rotation(poi.heading!!.toFloat())
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poiPlace, ZOOM))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        isOnePoiOnScreenNow = true
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    override fun onBackPressed() = if (isOnePoiOnScreenNow) showPoi(viewModel.getPoiList()) else super.onBackPressed()
}