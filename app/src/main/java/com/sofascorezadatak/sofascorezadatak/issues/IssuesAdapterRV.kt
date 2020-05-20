package com.sofascorezadatak.sofascorezadatak.issues

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
import com.sofascorezadatak.sofascorezadatak.model.Issue
import com.squareup.picasso.Picasso
import java.util.*

class IssuesAdapterRV(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mDataset: MutableList<Any> = ArrayList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageViewAvatar: ImageView
        var textViewLogin: TextView
        var textViewTitle: TextView
        var textViewBody: TextView
        var textViewCreatedAt: TextView
        var textViewUpdatedAt: TextView
        var textViewState: TextView

        init {
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar)
            textViewLogin = view.findViewById(R.id.textViewLogin)
            textViewTitle = view.findViewById(R.id.textViewTitle)
            textViewBody = view.findViewById(R.id.textViewBody)
            textViewCreatedAt = view.findViewById(R.id.textViewCreatedAt)
            textViewUpdatedAt = view.findViewById(R.id.textViewUpdatedAt)
            textViewState = view.findViewById(R.id.textViewState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewEvent = LayoutInflater.from(parent.context)
            .inflate(R.layout.issues_view, parent, false)
        return ViewHolder(viewEvent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val issue = mDataset[position] as Issue

        Picasso.get().load(issue.user.avatarUrl).into(viewHolder.imageViewAvatar)
        viewHolder.textViewLogin.text = "Login: ${issue.user.login}"
        viewHolder.textViewTitle.text = "Title: ${issue.title}"

        if (issue.body.length > 50)
            viewHolder.textViewBody.text = "Body: ${issue.body.take(50)}... Show more"
        else
            viewHolder.textViewBody.text = "Body: ${issue.body}"

        viewHolder.textViewCreatedAt.text = "Created at: ${issue.created_at}"
        viewHolder.textViewUpdatedAt.text = "Updated at: ${issue.updated_at}"
        viewHolder.textViewState.text = "State: ${issue.state}"

        viewHolder.textViewBody.setOnClickListener {
            viewHolder.textViewBody.text = "Body: ${issue.body}"
        }
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