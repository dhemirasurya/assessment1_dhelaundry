package org.d3if0006.asesment1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.d3if0006.asesment1.R
import org.d3if0006.asesment1.databinding.FragmentSaranBinding
import org.d3if0006.asesment1.model.SaranLaundry

class SaranFragment : Fragment() {

    private lateinit var binding: FragmentSaranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(SaranLaundry.SARAN)
    }

    private fun updateUI(saran: SaranLaundry) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when(saran) {
            SaranLaundry.SARAN ->{
                actionBar?.title = getString(R.string.judul_saran)
                binding.imageView.setImageResource(R.drawable.clothes_rack)
                binding.textView.text = getString(R.string.saran_laundry)
            }
        }
    }
}