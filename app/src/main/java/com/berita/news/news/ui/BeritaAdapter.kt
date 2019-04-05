package com.berita.news.news.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.berita.news.news.R
import com.berita.news.news.config.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_berita.view.*

class BeritaAdapter (private  val list:MutableList<BeritaModel>, private val context: Context):
    RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_berita,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item=list[p1]
        p0.judul.text=item.judul
        p0.tanggal.text=item.tanggal
        p0.btnread.setOnClickListener{
            val intent= Intent(context, DetailBerita::class.java)
            intent.putExtra(Config.id,item.id)
            intent.putExtra("from","berita")
            context.startActivity(intent)

        }
        Glide.with(context).load(Config.url_gambar + item.gambar ).into(p0.image)
    }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.image
        val judul: TextView =itemView.judul
        val tanggal: TextView =itemView.tanggal
        val btnread: Button =itemView.btnread
    }


}