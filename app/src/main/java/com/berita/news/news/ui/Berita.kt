package com.berita.news.news.ui

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.media.audiofx.DynamicsProcessing
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.view.View

import com.berita.news.news.R
import com.berita.news.news.config.Config
import kotlinx.android.synthetic.main.activity_berita.*
import kotlinx.android.synthetic.main.item_berita.*
import org.json.JSONObject

class Berita : AppCompatActivity() {

    private var list:MutableList<BeritaModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berita)
        list= mutableListOf()
        get_data_berita().execute()
    }




    inner class get_data_berita : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Berita,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_berita)
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= BeritaModel()
                model.id=data.getString("id_berita")
                model.judul=data.getString("judul")
                model.isi=data.getString("isi")
                model.gambar=data.getString("gambar")
                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let {
                    BeritaAdapter(
                        it,
                        this@Berita
                    )
                }
                rc.layoutManager= LinearLayoutManager(this@Berita)
                rc.adapter=adapter
            }
            super.onPostExecute(result)

        }

    }
}
