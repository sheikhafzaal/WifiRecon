package com.example.wifirecons

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.blackhat.wifipasswords.WifiMaster
import com.example.wifirecons.databinding.ActivityMainBinding
import com.example.wifirecons.models.Hotspot
import com.example.wifirecons.models.ReconData
import com.example.wifirecons.room.Data
import com.example.wifirecons.ui.HotSpotAdapter
import com.example.wifirecons.utils.Converter
import com.example.wifirecons.viewmodel.AppViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val appViewModel: AppViewModel by viewModels()
    private val foregroundLocationPermission = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private val locationCode = 9001
    private val foregroundAndBackgroundLocationPermission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter = HotSpotAdapter()
    val wm = WifiMaster()

    private val location = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {

            for (location in locationResult.locations) {
                val latitude = location.latitude
                val longitude = location.longitude

                wm.listWifiListByLatLong(
                    latitude.toString(),
                    longitude.toString(),
                    onCompleted = {
                        appViewModel.insetData(Data(it))
                    },
                    onError = {
                        println("Error:: $it")
                    })

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = adapter

        appViewModel.fetchList().observe(this) {
            it?.forEach { recon ->
                val convertFrom = Converter.fromJson(recon.data, ReconData::class.java)
                adapter.setItemList(convertFrom.hotspots)
            }
        }


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.buttonFab.setOnClickListener {
            startLocationUpdates()
        }



        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val str = p0.toString()
                val filterList: ArrayList<Hotspot> = ArrayList()

                adapter.itemList().forEach {
                    if (it.ssid.lowercase(Locale.ENGLISH).contains(str)) {
                        filterList.add(it)
                    }
                }


                runOnUiThread {
                    adapter.setItemList(filterList)
                }

            }

        })





    }

    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onStop() {
        super.onStop()
        stopLocationUpdates()
    }

    private fun startLocationUpdates() {

        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 100000 // Interval in milliseconds for receiving updates
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {


            if (Build.VERSION.SDK_INT >= 30) {
                ActivityCompat.requestPermissions(
                    this,
                    foregroundLocationPermission,
                    locationCode
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    foregroundAndBackgroundLocationPermission,
                    locationCode
                )
            }

            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, location, null)

    }


    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(location)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        startLocationUpdates()

    }
}

