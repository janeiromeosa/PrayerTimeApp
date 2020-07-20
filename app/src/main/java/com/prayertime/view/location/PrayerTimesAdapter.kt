package com.prayertime.view.location

import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.prayertime.R
import com.prayertime.data.DataPrayerTimes

class PrayerTimesAdapter: RecyclerView.Adapter<ViewHolder>() {

    private var list: ArrayList<DataPrayerTimes> = ArrayList()

    fun addItems(items: List<DataPrayerTimes>) {
        items?.let {
            list.clear()
            list.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stuff: DataPrayerTimes = list[position]
        holder.bind(stuff)
    }
}
    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_prayer_times, parent, false)) {

        private val imageView: ImageView = itemView.findViewById(R.id.iv_prayer_day_time)
        private val names: TextView = itemView.findViewById(R.id.tv_prayer_names)
        private val times: TextView = itemView.findViewById(R.id.tv_prayer_times)

        fun bind(list: DataPrayerTimes) {
            names.text = list.name
            val image = (ResourcesCompat.getDrawable(itemView.context.resources, list.resId, null) as VectorDrawable).toBitmap()
            imageView.setImageBitmap(image)
        }
    }
