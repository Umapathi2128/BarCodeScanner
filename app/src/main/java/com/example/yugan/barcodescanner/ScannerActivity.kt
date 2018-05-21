package com.example.yugan.barcodescanner

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Toast
import com.example.yugan.barcodescanner.databinding.ActivityScannerBinding
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(),ScannerView, ZXingScannerView.ResultHandler {

    private lateinit var activityScannerBinding: ActivityScannerBinding
    private lateinit var zXingScannerView: ZXingScannerView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityScannerBinding=DataBindingUtil.setContentView(this,R.layout.activity_scanner)
        val scannerDataModel=ScannerDataModel("Scanner")
        val scannerViewModel=ScannerViewModel(this,scannerDataModel)
        activityScannerBinding.setVariable(BR.scanner,scannerViewModel)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK && requestCode==100)
        {
            Toast.makeText(this,"SUCCESS: Verified user authentication...",Toast.LENGTH_LONG).show()
        }else Toast.makeText(this," FAILURE :  unable to verify user authentication...",Toast.LENGTH_LONG).show()
    }

    override fun handleResult(p0: Result?) {

        val intent=Intent(Intent.ACTION_VIEW)
//        intent.data= Uri.parse(p0!!.text.toString())
        //this line of code for navigating to browser with QR_code website....
        startActivity(intent)
        Toast.makeText(this,p0!!.text.toString(),Toast.LENGTH_LONG).show()
    }

    override fun clickScanner() {
        zXingScannerView= ZXingScannerView(applicationContext)
        setContentView(zXingScannerView)
        zXingScannerView.setResultHandler(this)
        zXingScannerView.startCamera()
    }

    override fun onResume() {
        super.onResume()
//        activityScannerBinding=DataBindingUtil.setContentView(this,R.layout.activity_scanner)
//        zXingScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        zXingScannerView.stopCamera()
    }
}
