package org.d3if0006.asesment1.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if0006.asesment1.R
import org.d3if0006.asesment1.data.SettingDataStore
import org.d3if0006.asesment1.data.dataStore
import org.d3if0006.asesment1.databinding.FragmentHitungBinding
import org.d3if0006.asesment1.db.LaundryDb
import org.d3if0006.asesment1.model.HasilLaundry
import org.d3if0006.asesment1.ui.HitungViewModel

class HitungFragment : Fragment() {

    private val layoutDataStore: SettingDataStore by lazy {
        SettingDataStore(requireContext().dataStore)
    }

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = LaundryDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungLaundry() }
        binding.saranButton.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_saranFragment
            )
        }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilLaundry().observe(requireActivity(), { showResult(it) })
    }

    private fun shareData() {

        val message = getString(R.string.bagikan_template,
        binding.dailyInp.text,
        binding.jumlahInp.text,
        binding.totalTextView.text,
        binding.userInp
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment)
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun hitungLaundry() {
        //Log.d("MainActivity", "Tombol diklik!")

        val pengguna = binding.userInp.text.toString()
        if (TextUtils.isEmpty(pengguna)){
            Toast.makeText(context, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val berat = binding.dailyInp.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val jumlah = binding.jumlahInp.text.toString()
        if (TextUtils.isEmpty(jumlah)) {
            Toast.makeText(context, R.string.jumlah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungLaundry(
            pengguna.toString(),
            berat.toFloat(),
            jumlah.toFloat()
            )

        binding.ketTextView.text = getString(R.string.nama_x, pengguna +
                ", dapat diambil dalam rentang waktu pelayanan yang dipilih" )
    }

    private fun showResult(result: HasilLaundry?) {
        if (result == null) return

        binding.totalTextView.text = getString(R.string.total_x, result.keseluruhan)
        binding.buttonGroup.visibility = View.VISIBLE
    }


}
