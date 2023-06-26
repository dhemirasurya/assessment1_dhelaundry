package org.d3if0006.asesment1.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0006.asesment1.db.LaundyDao

class HistoriViewModel(private val db: LaundyDao) : ViewModel() {
    val data = db.getLastLaundry()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}