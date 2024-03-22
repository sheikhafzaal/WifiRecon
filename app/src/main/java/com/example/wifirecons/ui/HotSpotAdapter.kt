package com.example.wifirecons.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wifirecons.databinding.ItemHotspotBinding
import com.example.wifirecons.room.models.Hotspot

class HotSpotAdapter : RecyclerView.Adapter<HotSpotAdapter.ViewHolder>() {

    private var _itemList: List<Hotspot> = ArrayList()

    fun setItemList(itemList: List<Hotspot>) {
        this._itemList = itemList
        notifyDataSetChanged()
    }

    fun itemList(): List<Hotspot> {
        return _itemList
    }


    class ViewHolder(binding: ItemHotspotBinding) : RecyclerView.ViewHolder(binding.root) {

        private val _binding: ItemHotspotBinding

        init {
            _binding = binding
        }

        fun bind(hotspot: Hotspot) {
            _binding.tvSsid.text = hotspot.ssid
            _binding.tvBssid.text = hotspot.bssids!![0].toString()
            _binding.tvPswd.text = hotspot.password ?: "n/a"
            _binding.tvUser.text = hotspot.user_name ?: "n/a"
            _binding.tvCountry.text = hotspot.country

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemHotspotBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return _itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_itemList[position])
    }
}