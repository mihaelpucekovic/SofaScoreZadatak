package com.sofascorezadatak.sofascorezadatak.contributors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sofascorezadatak.sofascorezadatak.MyDiffUtilCallback
import com.sofascorezadatak.sofascorezadatak.R
import com.sofascorezadatak.sofascorezadatak.model.Contributor
import com.squareup.picasso.Picasso
import java.util.*

class ContributorsAdapterRV(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mDataset: MutableList<Any> = ArrayList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageViewAvatar: ImageView
        var textViewLogin: TextView
        var textViewContributions: TextView

        init {
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar)
            textViewLogin = view.findViewById(R.id.textViewLogin)
            textViewContributions = view.findViewById(R.id.textViewContributions)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewEvent = LayoutInflater.from(parent.context)
            .inflate(R.layout.contributors_view, parent, false)
        return ViewHolder(viewEvent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val contributor = mDataset[position] as Contributor

        Picasso.get().load(contributor.avatarUrl).into(viewHolder.imageViewAvatar)
        viewHolder.textViewLogin.text = "Login: ${contributor.login}"
        viewHolder.textViewContributions.text = "Contributions: ${contributor.contributions}"
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun updateList(newDataset: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallback(mDataset, newDataset))
        mDataset.clear()
        mDataset.addAll(newDataset)
        diffResult.dispatchUpdatesTo(this)
    }
}