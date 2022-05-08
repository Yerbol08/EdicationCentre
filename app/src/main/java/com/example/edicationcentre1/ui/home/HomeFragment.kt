package com.example.edicationcentre1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.edicationcentre1.R
import com.example.edicationcentre1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val data = arrayListOf<ItemsHome>()
        data.add(ItemsHome(R.string.kurs1, R.drawable.matem))
        data.add(ItemsHome(R.string.kurs2, R.drawable.robots))
        data.add(ItemsHome(R.string.kurs3, R.drawable.phisics))
        data.add(ItemsHome(R.string.kurs4, R.drawable.prog))
        data.add(ItemsHome(R.string.kurs5, R.drawable.risunok))



        val adapter = context?.let { HomeAdapter(data, it) }
        binding.recyclerView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}