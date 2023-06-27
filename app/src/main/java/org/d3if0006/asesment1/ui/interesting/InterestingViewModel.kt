package org.d3if0006.asesment1.ui.interesting

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import org.d3if0006.asesment1.Pakaian
import org.d3if0006.asesment1.R
import org.d3if0006.asesment1.network.UpdateWorker
import java.util.concurrent.TimeUnit

class InterestingViewModel : ViewModel() {

    private val data = MutableLiveData<List<Pakaian>>()

    init {
        data.value = initData()
    }

    private fun initData(): List<Pakaian> {
        return listOf(
            Pakaian("Topi","Hat", R.drawable.cap),
            Pakaian("Gaun","Dress", R.drawable.dress),
            Pakaian("Saputangan","Handkerchief", R.drawable.handkerchief),
            Pakaian("Tudung","Hoodie", R.drawable.hoodie),
            Pakaian("Baju","Shirt", R.drawable.shirt),
            Pakaian("Celana Pendek","Shorts", R.drawable.shorts),
            Pakaian("Rok","Skirt", R.drawable.skirt),
            Pakaian("Kaus Kaki","Socks", R.drawable.socks),
            Pakaian("Setelan","Suit", R.drawable.suit),
            Pakaian("Celana Panjang","Trousers", R.drawable.trousers)
        )
    }
    fun getData(): LiveData<List<Pakaian>> = data

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}