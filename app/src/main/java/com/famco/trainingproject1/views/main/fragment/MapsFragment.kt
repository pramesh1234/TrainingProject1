package com.famco.trainingproject1.views.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.famco.trainingproject1.R
import com.famco.trainingproject1.views.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : BaseFragment(), GoogleMap.OnMarkerClickListener {
    private var mMarker: Marker? = null
    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)

        mMarker =
            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(
                context?.let { getBitmapFromVectorDrawable(it, R.drawable.ic_car) }
            ))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        googleMap.setOnMarkerClickListener(this)
        setMapLongClick(googleMap)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment: SupportMapFragment? =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener {
            map.addMarker(MarkerOptions().position(it).title("Marker is somewhere").icon(
                context?.let { context ->
                    getBitmapFromVectorDrawable(
                        context,
                        R.drawable.ic_car
                    )
                }
            ))
            map.moveCamera(CameraUpdateFactory.newLatLng(it))
            map.setOnMarkerClickListener(this)
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Toast.makeText(context, "marker location " + marker.position, Toast.LENGTH_SHORT).show()
        return true
    }
}