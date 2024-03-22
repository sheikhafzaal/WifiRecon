package com.example.wifirecons

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wifirecons.app.WifiRecon
import com.example.wifirecons.data.ApiClient
import com.example.wifirecons.data.ApiService
import com.example.wifirecons.databinding.ActivityMainBinding
import com.example.wifirecons.room.models.Hotspot
import com.example.wifirecons.room.models.ReconData
import com.example.wifirecons.ui.HotSpotAdapter
import com.example.wifirecons.utils.GPSHelper
import com.example.wifirecons.utils.LocManager
import com.example.wifirecons.utils.Progress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val LOCATION_PERMISSION_REQ_CODE = 100
    private val adapter = HotSpotAdapter()

    private var dataList: MutableList<Hotspot> = ArrayList()
    private val gpsHelper by lazy { GPSHelper(this) }
    private lateinit var progress: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        progress = Progress(this).create()

        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = adapter



        checkAndRequestPermission()



        binding.buttonFab.setOnClickListener {

            checkAndRequestPermission()



            progress.show()

            val gps = LocManager(this)
            val lat = gps.latitude
            val lon = gps.longitude
            val url =
                "/api3/search/?&lon=${lon}&limit_top=10&filter_blacklisted_networks=true&quality=1&token=ib_bkQIsE%2Bsfhaks%2FO%2FaIhRy%2FVe4zw%3D&locale=en_US&limit_middle=30&max_distance=10050&network=1&platform=android&version=1452&lat=${lat}&limit=75"
            val client = ApiClient.createService(url).create(ApiService::class.java)

            client.fetchWifiListByLatLong(url)
                .enqueue(object : retrofit2.Callback<ReconData> {
                    override fun onResponse(
                        call: Call<ReconData>,
                        response: Response<ReconData>
                    ) {

                        progress.dismiss()

                        if (!response.isSuccessful && response.body() == null) {
                            return
                        }

                        val reconData = response.body()
                        reconData?.let {

//                            dataList.apply {
//                                clear()
//                                addAll(it.hotspots!!)
//                            }

                            CoroutineScope(Dispatchers.IO).launch {
                                WifiRecon.appDao.insertData(it)
                            }

                        }

                    }

                    override fun onFailure(call: Call<ReconData>, t: Throwable) {
                        t.printStackTrace()
                        progress.dismiss()
                    }

                })

        }


        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val filteredList = ArrayList<Hotspot>()

                dataList.forEach {
                    if (it.ssid!!.lowercase(Locale.ENGLISH).contains(p0.toString())) {
                        filteredList.add(it)
                    }
                }

                runOnUiThread {
                    adapter.setItemList(filteredList)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        WifiRecon.appDao.fetchReconData().observe(this) {
            it.forEach { recon ->
                dataList = recon.hotspots as MutableList<Hotspot>
                dataList.let {
                    adapter.setItemList(recon.hotspots)
                }
            }
        }

    }

    private fun checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQ_CODE
            )

        } else {
            if (!gpsHelper.isGPSEnabled) {
                GPSHelper.enableLocation(this, this)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQ_CODE) {
            if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("m TAG", "granted")


                    if (!gpsHelper.isGPSEnabled) {
                        GPSHelper.enableLocation(this, this)
                    }


                } else {
                    Toast.makeText(
                        this,
                        "Please allow this permission to use app!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}

