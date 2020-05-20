package com.sofascorezadatak.sofascorezadatak.contributors

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sofascorezadatak.sofascorezadatak.R
import com.sofascorezadatak.sofascorezadatak.model.Repository

class ContributorsFragment : Fragment() {
    private var contributorsViewModel: ContributorsViewModel? = null
    private lateinit var recyclerView: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var contributorsAdapterRV: ContributorsAdapterRV? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contributorsViewModel = ViewModelProvider(this).get(ContributorsViewModel::class.java)

        if (arguments != null) {
            repository = arguments!!.getSerializable("repository") as Repository

            contributorsViewModel!!.getRepositoryContributors(repository.user.login, repository.name)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.contributors_fragment, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        layoutManager = LinearLayoutManager(mContext)
        recyclerView.setLayoutManager(layoutManager)

        contributorsViewModel!!.liveDataRepositoryContributors.observe(viewLifecycleOwner, Observer {
            contributorsAdapterRV = ContributorsAdapterRV(mContext)
            recyclerView.setAdapter(contributorsAdapterRV)
            contributorsAdapterRV!!.updateList(it)
        })

        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE)

        swipeRefreshLayout.setOnRefreshListener {
            contributorsViewModel!!.getRepositoryContributors(repository.user.login, repository.name)

            swipeRefreshLayout.isRefreshing = false
        }

        val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isVisible) {
                    contributorsViewModel!!.getRepositoryContributors(repository.user.login, repository.name)

                    handler.postDelayed(this, 15000)
                }
            }
        }, 15000)

        return root
    }

    companion object {
        private lateinit var mContext: Context

        @JvmStatic
        fun newInstance(mContext: Context, repository: Repository): ContributorsFragment {
            this.mContext = mContext
            val fragment = ContributorsFragment()
            val bundle = Bundle()
            bundle.putSerializable("repository", repository)
            fragment.arguments = bundle
            return fragment
        }
    }
}