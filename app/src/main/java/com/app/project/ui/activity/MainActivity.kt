package com.app.project.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.GridView
import androidx.viewpager.widget.ViewPager
import com.app.project.R
import com.app.project.ui.adapters.GridAdapter
import com.app.project.ui.adapters.SliderAdapter
import com.app.project.ui.bottomsheets.MyBottomSheetDialogFragment
import com.app.project.helper.StackManager
import com.app.project.interfaces.CellClickListener
import com.app.project.model.GridItem
import java.util.*

class MainActivity : AppCompatActivity() , CellClickListener {
    private lateinit var stackManager: StackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val adapter = SliderAdapter(
            listOf(
                R.drawable.location_one,
                R.drawable.location_two,
                R.drawable.location_three
            ), this
        )
        viewPager.adapter = adapter

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    viewPager.currentItem = (viewPager.currentItem + 1) % adapter.count
                }
            }
        }, 2000, 2000)


        setUpGridView()

        stackManager = StackManager()

    }

    private fun setUpGridView() {
        val gridView = findViewById<GridView>(R.id.gridView)
        val items = listOf(
            GridItem(R.drawable.location_one, "Nature"),
            GridItem(R.drawable.location_two, "Futuristic"),
            GridItem(R.drawable.location_three, "Party"),
            GridItem(R.drawable.location_one, "Green"),
            GridItem(R.drawable.location_two, "Thriller"),
            GridItem(R.drawable.location_three, "Club")
        )

        val adapter = GridAdapter(items, this)
        gridView.numColumns = 2
        gridView.adapter = adapter
    }

    override fun onBackPressed() {
        if (!stackManager.onBackPressed()) {
            super.onBackPressed()
        }
        else{
            finish()
        }
    }

    override fun onCellClickListener(picId: Int, operation: String) {
        Log.d("MainActivity", "Button clicked!")
        val bottomSheet1 = MyBottomSheetDialogFragment()
        stackManager.add(bottomSheet1)

        Handler(Looper.getMainLooper()).post {
            stackManager.expand(bottomSheet1)
        }

        bottomSheet1.show(supportFragmentManager, bottomSheet1.tag)
    }

}

