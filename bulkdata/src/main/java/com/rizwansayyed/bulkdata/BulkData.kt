package com.rizwansayyed.bulkdata

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteException
import android.os.Looper
import android.util.Log
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule


class BulkData() {

    companion object {

        fun makeNewTableColoum(context: Context, arrayList: ArrayList<String>) {

            clearAllData(context)

            Timer().schedule(1500) {
                makeTableColoum(context, arrayList)
            }
        }

        fun makeTableColoum(context: Context, arrayList: ArrayList<String>) {

            arrayList.add(0, "bkid")
            val arrayListColoums = arrayList.toString().replace("[", "").replace("]", "")

            val mydatabase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS bulkdataforapp(bkid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");


            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS bulkdataforappcoloums(bkcoloums LONGTEXT(1500000) NOT NULL)");

            mydatabase.execSQL("DELETE FROM bulkdataforappcoloums");
            mydatabase.execSQL("INSERT INTO bulkdataforappcoloums (bkcoloums) VALUES ('$arrayListColoums')");


            for (i in arrayList) {
                try {
                    mydatabase.execSQL("ALTER TABLE bulkdataforapp ADD COLUMN $i LONGTEXT(1500000);");
                } catch (e: SQLiteException) {
                    Log.d("Bulk", "makeTableColoum: columnexists")
                }
            }

        }

        fun getColoumsName(context: Context): String {

            val myDataBase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            val searchQuery = "SELECT * FROM bulkdataforapp LIMIT 1"

            val cursor = myDataBase.rawQuery(searchQuery, null)


            val resultSet = JSONArray()

            cursor.moveToFirst()

            while (!cursor.isAfterLast) {
                val totalColumn = cursor.columnCount
                val rowObject = JSONObject()
                for (i in 0 until totalColumn) {
                    if (cursor.getColumnName(i) != null) {
                        try {
                            if (cursor.getString(i) != null) {
                                Log.d("TAG_NAME", cursor.getString(i))
                                rowObject.put(cursor.getColumnName(i), "")
                            } else {
                                rowObject.put(cursor.getColumnName(i), "")
                            }
                        } catch (e: Exception) {
                            Log.d("TAG_NAME", e.message!!)
                        }
                    }
                }
                resultSet.put(rowObject)
                cursor.moveToNext()
            }
            cursor.close()

            if (resultSet.toString() == "[]") {
                val newsearchQuery = "SELECT * FROM bulkdataforappcoloums LIMIT 1"

                val newcursor = myDataBase.rawQuery(newsearchQuery, null)
                if (newcursor.moveToFirst()) {
                    return newcursor.getString(newcursor.getColumnIndex("bkcoloums"));
                }

                newcursor.close();


            }
            return resultSet.toString().replace(":\"\"", "")
        }


        fun insertData(context: Context, itemLenght: Int, data: String): String {
            var returnVal: String
            if (itemLenght > 0) {
                val mydatabase =
                    context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

                val coloumName = getColoumsName(context).replace("[{\"", "")
                    .replace("\"}]", "").replace("\",\"", ",")

                try {
                    mydatabase.execSQL("INSERT INTO bulkdataforapp ($coloumName) VALUES (NULL, $data)");
                } catch (e: SQLiteException) {
                    returnVal = "Table or Coloum not avaliable, Please run makeTableColoum."
                }

                for (i in 2..itemLenght) {
                    mydatabase.execSQL("INSERT INTO bulkdataforapp ($coloumName) VALUES (NULL, $data)");
                }

                returnVal = "1"

            } else {
                returnVal = "itemLenght should be more than 0"
            }
            return returnVal;
        }

        fun getData(context: Context, query: String): String {
            val myDataBase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            var searchQuery = "SELECT * FROM bulkdataforapp $query"

            if (query == "ASC" || query == "asc" || query == "DESC" || query == "desc") {
                searchQuery = "SELECT * FROM bulkdataforapp ORDER BY bkid $query"
            }
            try {
                val cursor = myDataBase.rawQuery(searchQuery, null)

                val resultSet = JSONArray()

                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val totalColumn = cursor.columnCount
                    val rowObject = JSONObject()
                    for (i in 0 until totalColumn) {
                        if (cursor.getColumnName(i) != null) {
                            try {
                                if (cursor.getString(i) != null) {
                                    rowObject.put(cursor.getColumnName(i), cursor.getString(i))
                                } else {
                                    rowObject.put(cursor.getColumnName(i), "")
                                }
                            } catch (e: Exception) {
                                Log.d("TAG_NAME", e.message!!)
                            }
                        }
                    }
                    resultSet.put(rowObject)
                    cursor.moveToNext()
                }
                cursor.close()

                return resultSet.toString()

            } catch (e: SQLiteException) {
                return "notablefound use makeTableColoum"
            }
        }

        fun clearAllData(context: Context): String {
            val myDataBase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            myDataBase.execSQL("DELETE FROM bulkdataforapp");

            myDataBase.execSQL("DELETE FROM bulkdataforappcoloums");

            return "0"
        }

        fun dropData(context: Context): String {
            val myDataBase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            myDataBase.execSQL("DROP TABLE bulkdataforapp");

            myDataBase.execSQL("DROP TABLE bulkdataforappcoloums");

            return "0"
        }

        fun dropColoum(context: Context, coloumNmae: String): String {
            val myDataBase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            myDataBase.execSQL("ALTER TABLE bulkdataforapp DROP COLUMN $coloumNmae");

            return "0"
        }


        fun getData(context: Context): String {
            val myDataBase =
                context.openOrCreateDatabase("bulkdataforapp", MODE_PRIVATE, null)

            var searchQuery = "SELECT * FROM bulkdataforapp"

            val cursor = myDataBase.rawQuery(searchQuery, null)

            val resultSet = JSONArray()

            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val totalColumn = cursor.columnCount
                val rowObject = JSONObject()
                for (i in 0 until totalColumn) {
                    if (cursor.getColumnName(i) != null) {
                        try {
                            if (cursor.getString(i) != null) {
                                Log.d("TAG_NAME", cursor.getString(i))
                                rowObject.put(cursor.getColumnName(i), cursor.getString(i))
                            } else {
                                rowObject.put(cursor.getColumnName(i), "")
                            }
                        } catch (e: Exception) {
                            Log.d("TAG_NAME", e.message!!)
                        }
                    }
                }
                resultSet.put(rowObject)
                cursor.moveToNext()
            }
            cursor.close()
            return resultSet.toString()
        }

    }


}
