package com.android.binarnetworking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.binarnetworking.R
import com.android.binarnetworking.data.auth.LoginRequest
import com.android.binarnetworking.data.model.LoginResponseItem
import com.android.binarnetworking.data.network.ApiClient
import com.android.binarnetworking.databinding.FragmentLoginUserBinding
import com.android.binarnetworking.utils.SharedPreference
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginUserFragment : Fragment() {

    private var _binding: FragmentLoginUserBinding? = null
    private val binding get() = _binding!!
    private var sharedPref: SharedPreference? = null
    private var status = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : AuthViewModel by viewModels()
        sharedPref = SharedPreference(view.context)
        status = sharedPref?.getPrefKeyStatus("login_status") == true
        if (status){
            findNavController().navigate(R.id.action_loginUserFragment_to_homeFragment)
        }
        binding.apply {
            btnLogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                if (binding.edtEmail.text.isNullOrBlank() ||  binding.edtPassword.text.isNullOrBlank()){
                    Snackbar.make(binding.root, "Lengkapi Field diatas", Snackbar.LENGTH_LONG).show()
                } else {
                    val login = LoginRequest(email, password)
                    viewModel.login(login).observe(viewLifecycleOwner){ data ->
                        sharedPref?.saveKey(data?.email!!, data.password!!)
                        sharedPref?.saveKeyState(true)
                        findNavController().navigate(R.id.action_loginUserFragment_to_homeFragment)
                    }
                }
            }

            txtRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginUserFragment_to_registerUserFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}