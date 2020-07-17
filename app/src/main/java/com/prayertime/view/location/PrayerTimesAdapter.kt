package com.prayertime.view.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prayertime.R
import com.prayertime.data.DataPrayerTimes
import com.squareup.picasso.Picasso

class PrayerTimesAdapter(): RecyclerView.Adapter<PrayerTimesAdapter.ViewHolder>() {

    private var list: ArrayList<DataPrayerTimes> = ArrayList()

    fun addItems(items: ArrayList<DataPrayerTimes>) {
        items?.let {
            list.addAll(items)
            list.clear()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_prayer_times, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val random = list[position]

        holder.bind(random)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.iv_prayer_day_time)
        private val names: TextView = view.findViewById(R.id.tv_prayer_names)
        private val times: TextView = view.findViewById(R.id.tv_prayer_times)

        fun bind(list: DataPrayerTimes) {
            names.text = list.name
            Picasso.get().load(list.resId).into(imageView)
        }
    }


}