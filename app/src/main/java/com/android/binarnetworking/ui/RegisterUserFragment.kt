package com.android.binarnetworking.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.binarnetworking.R
import com.android.binarnetworking.data.auth.RegisterRequest
import com.android.binarnetworking.data.model.RegisterResponseItem
import com.android.binarnetworking.data.network.ApiClient
import com.android.binarnetworking.databinding.FragmentRegisterUserBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterUserFragment : Fragment() {

    private var _binding: FragmentRegisterUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel  by viewModels<AuthViewModel>()

        binding.apply {
            btnRegister.setOnClickListener {

                val register = RegisterRequest(
                    edtEmail.text.toString(),
                    edtPassword.text.toString(),
                    "admin"
                )

                viewModel.register(register).observe(viewLifecycleOwner) {data ->
                    Log.d("RegisterUserFragment", "${data}")
                    findNavController().navigate(R.id.action_registerUserFragment_to_loginUserFragment)
                    Snackbar.make(binding.root, "User Behasil Dibuat", Snackbar.LENGTH_LONG).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}