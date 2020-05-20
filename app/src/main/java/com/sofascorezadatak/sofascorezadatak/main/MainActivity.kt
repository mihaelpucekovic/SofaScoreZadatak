package com.sofascorezadatak.sofascorezadatak.main

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sofascorezadatak.sofascorezadatak.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var mainActivityAdapterRV: MainActivityAdapterRV? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private lateinit var editTextOrganizacija: EditText
    private var org: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextOrganizacija = findViewById(R.id.editTextOrganizacija)
        val buttonDohvati = findViewById<Button>(R.id.buttonDohvati)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)

        mainActivityViewModel.liveDataRepositories.observe(this, Observer {
            mainActivityAdapterRV = MainActivityAdapterRV(this)
            recyclerView.setAdapter(mainActivityAdapterRV)
            mainActivityAdapterRV!!.updateList(it)
        })

        buttonDohvati.setOnClickListener {
            org = editTextOrganizacija.getText().toString()
            mainActivityViewModel.getRepositories(org)
        }

        refresh()
    }

    private fun refresh() {
        val handler = Handler()

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!org.equals(""))
                    mainActivityViewModel.getRepositories(org)

                handler.postDelayed(this, 15000)
            }
        }, 15000)
    }
}
