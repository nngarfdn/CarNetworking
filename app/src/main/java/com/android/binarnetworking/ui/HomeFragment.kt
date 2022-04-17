package com.android.binarnetworking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.binarnetworking.R
import com.android.binarnetworking.data.model.CarResponseItem
import com.android.binarnetworking.data.network.ApiClient
import com.android.binarnetworking.databinding.FragmentHomeBinding
import com.android.binarnetworking.utils.SharedPreference
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HomeViewModel by viewModels()

        viewModel.getCars().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.loading.visibility = View.GONE
                showData(it)
            }
        }

        binding.fab.setOnClickListener {
            val sharedPref = SharedPreference(view.context)
            sharedPref.clearUsername()
            findNavController().navigate(R.id.action_homeFragment_to_loginUserFragment)
        }

    }

    private fun showData(body: List<CarResponseItem>?) {
        val adapter = CarAdapter()
        adapter.submitList(body)
        binding.rvCar.adapter = adapter
        binding.rvCar.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}