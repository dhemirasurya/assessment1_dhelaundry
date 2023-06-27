package org.d3if0006.asesment1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0006.asesment1.Pakaian
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "dhemirasurya/galeri-pakaian/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PakaianApiService {
    @GET("galeri-pakaian.json")
    suspend fun getPakaian(): List<Pakaian>
}
object PakaianApi {
    val service: PakaianApi by lazy {
        retrofit.create(PakaianApi::class.java)
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }

fun getPakaianUrl(imageResId: String): String {
    return "$BASE_URL$imageResId.jpg"
}
