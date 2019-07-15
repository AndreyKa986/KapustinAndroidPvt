package by.letum8658.homework.dz9

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import by.letum8658.homework.R
import by.letum8658.homework.dz9.entity.Poi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_dz9.*

class Dz9Activity : FragmentActivity(), OnMapReadyCallback, Dz9Fragment.Listener {

    private lateinit var map: GoogleMap
    private lateinit var poiList: List<Poi>
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    companion object {
        const val PADDING = 100
        const val ZOOM = 18f
    }

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
        val poiPicture = bitmapDescriptorFromVector(this, R.drawable.ic_car)
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
    }

    override fun onPoiClick(poi: Poi) {
        val poiPlace = LatLng(poi.coordinate!!.latitude, poi.coordinate.longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poiPlace, ZOOM))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}