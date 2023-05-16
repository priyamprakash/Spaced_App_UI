package com.app.project.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.app.project.R
import com.app.project.interfaces.CellClickListener

class SliderAdapter(private val items: List<Int>,private val cellClickListener: CellClickListener) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.cardview_item, container, false)
        val imageView = view.findViewById<ImageView>(R.id.image_view)
        val button = view.findViewById<Button>(R.id.button)

        imageView.setImageResource(items[position])

        imageView.setOnClickListener {
            cellClickListener.onCellClickListener(
                items[position],
               "Open Dialog"
            )
        }

        button.setOnClickListener {
            cellClickListener.onCellClickListener(
                items[position],
               "Open Dialog"
            )
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount() = items.size

    override fun isViewFromObject(view: View, `object`: Any) = view === `object`

}
