package com.utek.android.utekapp.screen.signin

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.utek.android.utekapp.databinding.FragmentSignInBinding
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel

    private fun isUserSignInValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel: SignInViewModel by lazy {
            ViewModelProvider(this).get(SignInViewModel::class.java)
        }

        val binding = FragmentSignInBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val text = binding.userSignIn.text

        binding.signInInputField.error= null

        if (isUserSignInValid(text!!)) {
            // Clear the error.
            binding.signInInputField.error= null
        }
        else {
            binding.signInInputField.error= "ERROR ERROR"
        }

//        binding.userSignIn.setOnKeyListener { _, _, _ ->
//            if (isUserSignInValid(user_sign_in.text!!)) {
//                // Clear the error.
//                user_sign_in.error = null
//            }
//            binding.signInInputField.error= "ERROR ERROR"
//            false
//        }


        return binding.root
    }

}
