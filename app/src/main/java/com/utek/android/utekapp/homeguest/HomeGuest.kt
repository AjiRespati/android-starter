package com.utek.android.utekapp.homeguest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.utek.android.utekapp.R
import com.utek.android.utekapp.databinding.FragmentHomeGuestBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeGuest : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeGuestBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_guest, container, false)
        return binding.root
    }

}
