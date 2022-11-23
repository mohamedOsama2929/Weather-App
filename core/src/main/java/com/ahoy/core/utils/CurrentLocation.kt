package com.ahoy.core.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.checkCallingOrSelfPermission
import androidx.lifecycle.LiveData
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*

class CurrentLocation private constructor(private val appContext: Activity) : LiveData<Location>() {

    private val mFusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(appContext)

    private var weakActivity: WeakReference<Activity>? = null


    private var mLocationRequest: LocationRequest? = null

    private var mLocationCallback: LocationCallback? = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                if (location != null)
                    value = location
            }
        }
    }

    init {
        startInit()
    }

    fun startInit() {
        weakActivity = WeakReference(appContext)

        Dexter.withContext(weakActivity!!.get())
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report!!.areAllPermissionsGranted()) {
                        getLocation()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token!!.continuePermissionRequest()
                }
            })
            .onSameThread().check()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

        mFusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                value = location
                mFusedLocationClient.removeLocationUpdates(mLocationCallback!!)
            }
        }
        createLocationRequest()
        checkDeviceSettings()
    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest!!.interval = 10000
        mLocationRequest!!.fastestInterval = 5000
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()

        if (checkLocationPermission()) {
            if (value == null) mFusedLocationClient.requestLocationUpdates(
                mLocationRequest!!,
                mLocationCallback!!,
                Looper.myLooper()!!
            )
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (mLocationCallback != null)
            mFusedLocationClient.removeLocationUpdates(mLocationCallback!!)
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: CurrentLocation? = null
        fun getInstance(appContext: Activity): CurrentLocation {
            if (instance == null) {
                instance = CurrentLocation(appContext)
            } else {
                instance?.startInit()
            }
            return instance as CurrentLocation
        }
    }


    private fun checkDeviceSettings() {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest!!)
        builder.setAlwaysShow(true)
        val client: SettingsClient = LocationServices.getSettingsClient(appContext)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnCompleteListener {

            try {
                task.getResult(ApiException::class.java)
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        showDialog(exception)
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {

                    }
                    LocationSettingsStatusCodes.SUCCESS -> {
                        getLocation()
                    }

                }
            }
        }

    }

    private fun showDialog(exception: ApiException) {
        try {
            val resolvable = exception as ResolvableApiException
            // Show the dialog by calling startResolutionForResult(),
            // and check the result in onActivityResult().
            resolvable.startResolutionForResult(weakActivity!!.get()!!, 100)
            // weakActivity!!.get()!!.startActivityForResult(weakActivity!!.get()!!.intent,100)
        } catch (e: IntentSender.SendIntentException) {
            // Ignore the error.
        }
    }

    private fun checkLocationPermission(): Boolean {
        val result: Int =
            checkCallingOrSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION)
        if (result != PackageManager.PERMISSION_GRANTED) {
            onPermission()
        }
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun onPermission() {
        val perms = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
        )

        ActivityCompat.requestPermissions(
            appContext,
            perms,
            100
        )
    }

    fun terminate() {
        instance = null
    }

    fun getAddressName(context: Context, location: LatLng): String? {
        val addresses = getGeoCoderAddress(context, location)

        return if (addresses != null && addresses.isNotEmpty()) {
            val address = addresses[0]
            address.getAddressLine(0)
        } else {
            ""
        }
    }

    private fun getGeoCoderAddress(context: Context?, location: LatLng?): MutableList<Address>? {
        if (location != null) {
            val geocoder = context?.let { Geocoder(it, Locale.forLanguageTag("en")) }
            try {
                /*
             * Geocoder.getFromLocation - Returns an array of Addresses
             * that are known to describe the area immediately surrounding the given latitude and longitude.
             */
                if (geocoder != null) {
                    return geocoder.getFromLocation(
                        location.latitude,
                        location.longitude,
                        1
                    )
                }
            } catch (e: IOException) {
                //e.printStackTrace();
            }
        }
        return null
    }

}