package com.rizwansayyed.bulkdata

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteException
import android.util.Log
import android.widget.Toast


class BulkData() {

    companion object {

        fun makeTableColoum(context: Context, arrayList: ArrayList<String>) {

            val mydatabase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS bulkdataforapp('id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY');");

            for (i in arrayList) {
                try {
                    mydatabase.execSQL("ALTER TABLE bulkdataforapp ADD COLUMN $i LONGTEXT(1000000);");
                } catch (e: SQLiteException) {
                    Log.d("Bulk", "makeTableColoum: columnexists")
                }
            }

        }

        fun insertData(context: Context, itemLenght: Int): String {
            var returnVal = String()
            if (itemLenght > 0) {
                for (i in 0..itemLenght) {
                    Log.d("TAG", "insertData: " + i)
                }

            } else {
                returnVal = "itemLenght should be more than 0"
            }
            return returnVal;
        }
    }
}
