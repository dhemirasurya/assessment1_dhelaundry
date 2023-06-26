package org.d3if0006.asesment1.model

import org.d3if0006.asesment1.db.LaundryEntity

fun LaundryEntity.hitunglaundry(): HasilLaundry {
    val pengguna = pengguna
    val keseluruhan = berat * jumlah

    return HasilLaundry(keseluruhan, pengguna)
}