package com.utek.android.utekapp.homeguest

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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

        val viewModel: HomeGuestViewModel by lazy {
            ViewModelProvider(this).get(HomeGuestViewModel::class.java)
        }

        val binding = FragmentHomeGuestBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.memberItem.adapter = MemberItemAdapter(MemberItemAdapter.OnClickListener {
            viewModel.displayAppMemberDetails(it)
        })

        viewModel.navigateToSelectedAppMember.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(HomeGuestDirections.actionHomeGuestToAppMemberDetailFragment(it))
                viewModel.displayAppMemberDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

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
