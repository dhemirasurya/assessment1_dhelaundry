package org.d3if0006.asesment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if0006.asesment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { hitungDheLaundry() }

    }
    private fun hitungDheLaundry() {
        //Log.d("MainActivity", "Tombol diklik!")
        val pengguna = binding.userInp.text.toString()
        if (TextUtils.isEmpty(pengguna)){
            Toast.makeText(this, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val berat = binding.dailyInp.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(this, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectId = binding.radioGroup.checkedRadioButtonId
        if (selectId == -1) {
            Toast.makeText(this, R.string.pelayanan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        //val total = (berat*)

       // val isHarian = selectId == R.id.radioButton3
       // val nilaiJam = 6.000
       // val isEnamJam = selectId == R.id.radioButton4
       // val nilaiEnam = 10.000
       // val isEmpatJam = selectId == R.id.radioButton5
       // val nilaiEmpat = 15.000
       // val kategori = getPelayanan()


        binding.ketTextView.text = getString(R.string.nama_x, pengguna )

    }
    //private fun getPelayanan(berat: Float, isHarian: Boolean): String{
       // val stringRes = if (isHarian ) {
       //     berat * 6.000
        //} else {

       // }
       // return getString(stringRes as Int)
   // }
}