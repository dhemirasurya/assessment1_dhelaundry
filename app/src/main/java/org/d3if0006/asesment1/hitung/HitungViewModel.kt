package org.d3if0006.asesment1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0006.asesment1.db.LaundryEntity
import org.d3if0006.asesment1.db.LaundyDao
import org.d3if0006.asesment1.model.HasilLaundry
import org.d3if0006.asesment1.model.hitunglaundry

class HitungViewModel(private val db: LaundyDao) : ViewModel() {

    private val hasilLaundry = MutableLiveData<HasilLaundry?>()

    fun hitungLaundry(pengguna: String, berat: Float, jumlah: Float){

        val dataLaundry = LaundryEntity(
            pengguna = pengguna,
            berat = berat,
            jumlah = jumlah
        )
        hasilLaundry.value = dataLaundry.hitunglaundry()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataLaundry)
            }
        }
    }
    fun getHasilLaundry(): LiveData<HasilLaundry?> = hasilLaundry
}