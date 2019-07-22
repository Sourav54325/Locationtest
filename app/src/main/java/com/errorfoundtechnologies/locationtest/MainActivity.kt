package com.errorfoundtechnologies.locationtest

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var status = ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_FINE_LOCATION)
        if(status==PackageManager.PERMISSION_GRANTED) {
            getLocation()
        }else{
            ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    123)
        }



    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    fun  getLocation( )
    {
        var lManager = getSystemService(Context.LOCATION_SERVICE)
                as LocationManager
        /*  LocationManager lManager = (LocationManager)
         getSystemService(Context.LOCATION_SERVICE) */

        lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        lManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                100L,1f,
                object : LocationListener {
                    override fun onLocationChanged(p0: Location?) {
                        lat.text = p0!!.latitude.toString()
                        lng.text = p0!!.longitude.toString()
                        //lManager.removeUpdates(this)
                    }
                    override fun onProviderEnabled(p0: String?) {
                    }
                    override fun onProviderDisabled(p0: String?) {
                    }
                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                    }
                })

        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                100L,1f,
                object : LocationListener {
                    override fun onLocationChanged(p0: Location?) {
                        gps_lat.text = p0!!.latitude.toString()
                        gps_lng.text = p0!!.longitude.toString()
                        //lManager.removeUpdates(this)
                    }
                    override fun onProviderEnabled(p0: String?) {
                    }
                    override fun onProviderDisabled(p0: String?) {
                    }
                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                    }
                })

    }
}