package org.d3if0006.asesment1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "laundry")
data class LaundryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var pengguna: String,
    var berat: Float,
    var jumlah: Float
)
