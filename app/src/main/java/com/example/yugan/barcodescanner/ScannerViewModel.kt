package com.example.yugan.barcodescanner

class ScannerViewModel(var scannerView: ScannerView,var scannerDataModel: ScannerDataModel) {

    // Calling the scanner view...
    fun clickScanner()
    {
        scannerView.clickScanner()
    }

}