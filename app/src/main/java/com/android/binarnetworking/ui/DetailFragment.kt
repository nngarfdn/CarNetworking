package com.android.binarnetworking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.binarnetworking.R
import com.android.binarnetworking.data.model.CarResponseItem
import com.android.binarnetworking.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<CarResponseItem>("car")

        val viewModel: DetailViewModel by viewModels()

        viewModel.getCarById(data?.id.toString()).observe(viewLifecycleOwner){ car ->
            val dec = NumberFormat.getNumberInstance(Locale.US)
            val price = dec.format(car?.price)
            binding.apply {
                txtName.text = car?.name
                txtCategory.text = getString(R.string.category, car?.category)
                txtPrice.text = getString(R.string.price, price)
                Glide.with(view.context).load(car?.image).into(imgCar)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}