package com.utek.android.utekapp.screen.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utek.android.utekapp.R
import com.utek.android.utekapp.databinding.FragmentAccountBinding
import kotlinx.android.synthetic.main.fragment_account.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AccountFragment : Fragment() {

    private lateinit var retrofit: Retrofit
    private lateinit var api: DataApi
    lateinit var list: ArrayList<Data>
    lateinit var adapter: RecyclerViewAdapter
    var notLoading = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        val viewModel: AccountFragmentViewModel by lazy {
            ViewModelProvider(this).get(AccountFragmentViewModel::class.java)
        }
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        retrofit = RetrofitInstance.getRetrofitInstance()
        api = retrofit.create(DataApi::class.java)
        list = ArrayList()
        adapter = RecyclerViewAdapter(list)
        binding.myRecyclerView.adapter = adapter
        load()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        status_recycler.visibility = View.VISIBLE
//        status_recycler.setImageResource(R.drawable.loading_animation)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        my_recycler_view.layoutManager = layoutManager
        my_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager.itemCount
                if (totalItemCount > 0) {
                    status_recycler.visibility = View.GONE
                }
                val lastComplete = layoutManager.findLastCompletelyVisibleItemPosition()
                if (notLoading && lastComplete == totalItemCount - 1) {
                    // Call your API to load more items
                    list.add(Data("progress"))
                    notLoading = false
                    adapter.notifyItemInserted(totalItemCount - 1)

                    val call: Call<List<Data>> = api.getData(totalItemCount)

                    call.enqueue(object : Callback<List<Data>> {
                        override fun onResponse(call: Call<List<Data>>?, response: Response<List<Data>>?) {
                            list.removeAt(list.size - 1)
                            adapter.notifyItemRemoved(list.size)

                            if (response!!.body()?.isNotEmpty()!!) {
                                response.body()?.let {
                                    list.addAll(it)
                                    adapter.notifyDataSetChanged()
                                    notLoading = true
                                }
                            } else {
                                Toast.makeText(
                                    my_recycler_view.context,
                                    "End of data reached",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<List<Data>>?, t: Throwable?) {
                            if (t != null) {
                                Log.e("FAIL", t.message.toString())
                            }
                        }
                    })
                }
            }
        })
    }

    private fun load() {
        val call: Call<List<Data>> = api.getData(0)
        call.enqueue(object : Callback<List<Data>> {
            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Log.e("FAIL", t.message.toString())
            }

            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                response.body()?.let {
                    status_recycler.visibility = View.GONE
                    list.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

}
