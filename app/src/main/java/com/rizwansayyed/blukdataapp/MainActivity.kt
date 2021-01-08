package com.rizwansayyed.blukdataapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rizwansayyed.bulkdata.BulkData


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayTableName = ArrayList<String>()
        arrayTableName.add("name")
        arrayTableName.add("userdata")
        arrayTableName.add("username")

        val InsertData = ArrayList<String>()
        arrayTableName.add("name")
        arrayTableName.add("userdata")
        arrayTableName.add("username")

        BulkData.makeTableColoum(this, arrayTableName)

        BulkData.insertData(this, 500)

    }
}