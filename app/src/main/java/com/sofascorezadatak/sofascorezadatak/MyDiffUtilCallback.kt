package com.sofascorezadatak.sofascorezadatak

import androidx.recyclerview.widget.DiffUtil
import com.sofascorezadatak.sofascorezadatak.model.Contributor
import com.sofascorezadatak.sofascorezadatak.model.Issue
import com.sofascorezadatak.sofascorezadatak.model.Pull
import com.sofascorezadatak.sofascorezadatak.model.Repository

class MyDiffUtilCallback(var oldlist: List<Any>, var newlist: List<Any>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val objectOld = oldlist[oldItemPosition]
        val objectNew = oldlist[newItemPosition]

        return if (objectOld is Repository && objectNew is Repository) {
            objectOld.id == objectNew.id
        }
        else if (objectOld is Contributor && objectNew is Contributor) {
            objectOld.id == objectNew.id
        }
        else if (objectOld is Issue && objectNew is Issue) {
            objectOld.id == objectNew.id
        }
        else if (objectOld is Pull && objectNew is Pull) {
            objectOld.id == objectNew.id
        }
        else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

}