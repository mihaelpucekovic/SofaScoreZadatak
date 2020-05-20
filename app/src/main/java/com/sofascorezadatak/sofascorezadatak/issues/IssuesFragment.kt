package com.sofascorezadatak.sofascorezadatak.issues

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

class IssuesFragment : Fragment() {
    private var issuesViewModel: IssuesViewModel? = null
    private lateinit var recyclerView: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var issuesAdapterRV: IssuesAdapterRV? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        issuesViewModel = ViewModelProvider(this).get(IssuesViewModel::class.java)

        if (arguments != null) {
            repository = arguments!!.getSerializable("repository") as Repository

            issuesViewModel!!.getRepositoryIssues(repository.user.login, repository.name)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.issues_fragment, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout)
        layoutManager = LinearLayoutManager(mContext)
        recyclerView.setLayoutManager(layoutManager)

        issuesViewModel!!.liveDataRepositoryIssues.observe(viewLifecycleOwner, Observer {
            issuesAdapterRV = IssuesAdapterRV(mContext)
            recyclerView.setAdapter(issuesAdapterRV)
            issuesAdapterRV!!.updateList(it)
        })

        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE)

        swipeRefreshLayout.setOnRefreshListener {
            issuesViewModel!!.getRepositoryIssues(repository.user.login, repository.name)

            swipeRefreshLayout.isRefreshing = false
        }

        val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isAdded && isVisible && userVisibleHint) {
                    issuesViewModel!!.getRepositoryIssues(repository.user.login, repository.name)

                    handler.postDelayed(this, 15000)
                }
            }
        }, 15000)

        return root
    }

    companion object {
        private lateinit var mContext: Context

        @JvmStatic
        fun newInstance(mContext: Context, repository: Repository): IssuesFragment {
            this.mContext = mContext
            val fragment = IssuesFragment()
            val bundle = Bundle()
            bundle.putSerializable("repository", repository)
            fragment.arguments = bundle
            return fragment
        }
    }
}