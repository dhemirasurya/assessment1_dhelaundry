package org.d3if0006.asesment1.ui.interesting

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.d3if0006.asesment1.Pakaian
import org.d3if0006.asesment1.R
import org.d3if0006.asesment1.databinding.ItemInterestingBinding

class InterestingAdapter : RecyclerView.Adapter<InterestingAdapter.ViewHolder>(){

    private val data = mutableListOf<Pakaian>()

    fun updateData(newData: List<Pakaian>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemInterestingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pakaian: Pakaian) = with(binding) {
            namaTextView.text = pakaian.nama
            inggrisTextView.text = pakaian.namaInggris
            imageView.setImageResource(pakaian.imageResId)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, pakaian.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemInterestingBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}