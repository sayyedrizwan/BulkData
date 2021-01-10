package com.rizwansayyed.blukdataapp

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rizwansayyed.bulkdata.BulkData


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val deleteall = findViewById<AppCompatTextView>(R.id.deleteall)

        deleteall.setOnClickListener { v ->
            Toast.makeText(this, "Deleting whole data.", Toast.LENGTH_SHORT)
                .show()

            if (BulkData.clearAllData(this) == "0") {
                Toast.makeText(this, "Deleted successfully", Toast.LENGTH_LONG).show()
            }
        }


        val dropall = findViewById<AppCompatTextView>(R.id.dropall)

        dropall.setOnClickListener { v ->
            Toast.makeText(this, "Droping whole data and resetting unique id.", Toast.LENGTH_SHORT)
                .show()

            if (BulkData.dropData(this) == "0") {
                Toast.makeText(this, "Dropped successfully", Toast.LENGTH_LONG).show()
            }
        }

        val getwholedataasc = findViewById<AppCompatTextView>(R.id.getwholedataasc)

        getwholedataasc.setOnClickListener { v ->

            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomcustomlayout)

            val showtext = dialog.findViewById<AppCompatTextView>(R.id.showtext);

            val data = BulkData.getData(this, "ASC").toString()

            Log.d("TAG", "onCreate: ondata" + data)
            showtext!!.text = "" + data


            dialog.show()

        }


        val getwholedatadesc = findViewById<AppCompatTextView>(R.id.getwholedatadesc)

        getwholedatadesc.setOnClickListener { v ->
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomcustomlayout)

            val showtext = dialog.findViewById<AppCompatTextView>(R.id.showtext);

            showtext!!.text = BulkData.getData(this, "DESC")

            dialog.show()
        }

        val inqueryformat = findViewById<AppCompatTextView>(R.id.inqueryformat)

        inqueryformat.setOnClickListener { v ->
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomcustomlayout)

            val showtext = dialog.findViewById<AppCompatTextView>(R.id.showtext);

            showtext!!.text = BulkData.getData(this, "ORDER BY emailid LIMIT 50")

            dialog.show()
        }

        val getcoloumname = findViewById<AppCompatTextView>(R.id.getcoloumname)

        getcoloumname.setOnClickListener { v ->
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomcustomlayout)

            val showtext = dialog.findViewById<AppCompatTextView>(R.id.showtext);

            showtext!!.text = BulkData.getColoumsName(this)

            dialog.show()
        }

        val getwholedata = findViewById<AppCompatTextView>(R.id.getwholedata)

        getwholedata.setOnClickListener { v ->
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(R.layout.bottomcustomlayout)

            val showtext = dialog.findViewById<AppCompatTextView>(R.id.showtext);

            showtext!!.text = BulkData.getData(this)

            dialog.show()
        }


        val makecoloum = findViewById<AppCompatTextView>(R.id.makecoloum)

        makecoloum.setOnClickListener { v ->

            val arrayTableName = ArrayList<String>()
            arrayTableName.add("name")
            arrayTableName.add("emailid")
            arrayTableName.add("place")
            arrayTableName.add("interest")


            BulkData.makeNewTableColoum(this, arrayTableName)
        }

        val removecoloum = findViewById<AppCompatTextView>(R.id.removecoloum)

        removecoloum.setOnClickListener { v ->
            BulkData.dropColoum(this, "place")
        }


        val submittext = findViewById<AppCompatEditText>(R.id.submittext)

        submittext.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                BulkData.insertData(
                    this,
                    999,
                    "'Rizwan Sayyed', 'sayyedrizwanahmed@gmail.com', 'mumbai India', 'skating'"
                )
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }

    }
}