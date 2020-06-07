package com.utek.android.utekapp.screen.memberdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.utek.android.utekapp.databinding.FragmentAppMemberDetailBinding

class AppMemberDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val appMember = AppMemberDetailFragmentArgs.fromBundle(arguments!!).selectedAppMember

        val application = requireNotNull(activity).application

        val viewModelFactory = AppMemberDetailViewModelFactory(appMember, application)

        val binding = FragmentAppMemberDetailBinding.inflate(inflater)

        val viewModel: AppMemberDetailViewModel by lazy {
            ViewModelProvider(this, viewModelFactory).get(AppMemberDetailViewModel::class.java)
        }

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root

    }


}
