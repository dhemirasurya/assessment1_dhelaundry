package org.d3if0006.asesment1.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0006.asesment1.R
import org.d3if0006.asesment1.databinding.ItemHistoriBinding
import org.d3if0006.asesment1.db.LaundryEntity
import org.d3if0006.asesment1.model.SaranLaundry
import org.d3if0006.asesment1.model.hitunglaundry
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<LaundryEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK)  {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<LaundryEntity>() {
                override fun areItemsTheSame(
                    oldData: LaundryEntity, newData: LaundryEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: LaundryEntity, newData: LaundryEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemHistoriBinding
        ): RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: LaundryEntity) = with(binding) {
            val hasilLaundry = item.hitunglaundry()
            saranTextView.text = hasilLaundry.pengguna.toString().substring(0, 1)
            val colorRes = when (hasilLaundry.pengguna) {
                //SaranLaundry.SARAN -> R.color.blue_700
                else -> R.color.blue_500
            }
            val circleBg = saranTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            laundryTextView.text = root.context.getString(
                R.string.hasil_x,
                hasilLaundry.keseluruhan, hasilLaundry.pengguna.toString()
            )
            dataTextView.text = root.context.getString(
                R.string.data_x,
                item.berat, item.jumlah
            )
        }
    }
}