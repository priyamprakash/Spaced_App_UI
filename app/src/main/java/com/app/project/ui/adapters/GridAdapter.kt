package com.app.project.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.project.R
import com.app.project.interfaces.CellClickListener
import com.app.project.model.GridItem

class GridAdapter(private val items: List<GridItem>, private val cellClickListener: CellClickListener) : BaseAdapter() {

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_item, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.textView)

        val item = getItem(position)
        imageView.setImageResource(item.imageRes)
        textView.text = item.text

        imageView.setOnClickListener {
            cellClickListener.onCellClickListener(
                position,
                "Open Dialog"
            )
        }

        return view
    }
}
