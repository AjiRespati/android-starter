package com.utek.android.utekapp.homeguest

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.utek.android.utekapp.R
import com.utek.android.utekapp.databinding.FragmentHomeGuestBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeGuest : Fragment() {

    private val viewModel: HomeGuestViewModel by lazy {
        ViewModelProviders.of(this).get(HomeGuestViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeGuestBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_guest, container, false)
        setHasOptionsMenu(true)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.account_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
