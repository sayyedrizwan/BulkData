package com.rizwansayyed.blukdataapp

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.rizwansayyed.bulkdata.BulkData


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayTableName = ArrayList<String>()
        arrayTableName.add("name")
        arrayTableName.add("userdata")
        arrayTableName.add("username")


        val submittext = findViewById<AppCompatEditText>(R.id.submittext)

        submittext.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                BulkData.insertData(this, 50, "'Shabnamssse Sayyed', 'Data of usersss', 'rizwansayyed'")
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }

        BulkData.makeTableColoum(this, arrayTableName)
        BulkData.getColoumsName(this)

//        Toast.makeText(this, BulkData.getColoumsName(this), Toast.LENGTH_SHORT).show()

        //    BulkData.insertData(this, 5000, "'Shabnamssse Sayyed', 'Data of usersss', 'rizwansayyed'")

    }
}