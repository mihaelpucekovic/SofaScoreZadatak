package com.sofascorezadatak.sofascorezadatak.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sofascorezadatak.sofascorezadatak.DetailsActivity
import com.sofascorezadatak.sofascorezadatak.MyDiffUtilCallback
import com.sofascorezadatak.sofascorezadatak.R
import com.sofascorezadatak.sofascorezadatak.model.Repository
import com.squareup.picasso.Picasso
import java.util.*

class MainActivityAdapterRV(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mDataset: MutableList<Any> = ArrayList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageViewAvatar: ImageView
        var textViewName: TextView
        var textViewDescription: TextView
        var textViewCreatedAt: TextView
        var textViewUpdatedAt: TextView
        var linearLayoutContainer: LinearLayout

        init {
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar)
            textViewName = view.findViewById(R.id.textViewName)
            textViewDescription = view.findViewById(R.id.textViewDescription)
            textViewCreatedAt = view.findViewById(R.id.textViewCreatedAt)
            textViewUpdatedAt = view.findViewById(R.id.textViewUpdatedAt)
            linearLayoutContainer = view.findViewById(R.id.linearLayoutContainer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        val viewEvent = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_view, parent, false)
        return ViewHolder(
            viewEvent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val repository = mDataset[position] as Repository

        viewHolder.textViewName.text = repository.name
        Picasso.get().load(repository.user.avatarUrl).into(viewHolder.imageViewAvatar)
        viewHolder.textViewDescription.text = "${repository.description}"
        viewHolder.textViewCreatedAt.text = "Created at: ${repository.createdAt}"
        viewHolder.textViewUpdatedAt.text = "Updated at: ${repository.updatedAt}"

        viewHolder.linearLayoutContainer.setOnClickListener {
            DetailsActivity.start(context, repository)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun updateList(newDataset: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(
            MyDiffUtilCallback(
                mDataset,
                newDataset
            )
        )
        mDataset.clear()
        mDataset.addAll(newDataset)
        diffResult.dispatchUpdatesTo(this)
    }
}