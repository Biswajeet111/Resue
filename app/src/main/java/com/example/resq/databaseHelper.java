//package com.example.resq;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//// Define User Data Class
//data class User(val id: Int, val name: String, val email: String, val password: String);
//
//        class DatabaseHelper(context: Context);
//        SQLiteOpenHelper(context, "UsersDB", null, 1); {
//
//    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL("CREATE TABLE Users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT UNIQUE, password TEXT)")
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL("DROP TABLE IF EXISTS Users")
//        onCreate(db)
//    }
//
//    // Insert user data
//    fun insertUser(name: String, email: String, password: String): Boolean {
//        val db = writableDatabase
//        val values = ContentValues().apply {
//            put("name", name)
//            put("email", email)
//            put("password", password)
//        }
//
//        val result = db.insert("Users", null, values)
//        db.close() // Close database to avoid leaks
//        return result != -1L
//    }
//
//    // Check user credentials for login
//    fun checkUser(email: String, password: String): Boolean {
//        val db = readableDatabase
//        val cursor = db.rawQuery("SELECT * FROM Users WHERE email=? AND password=?", arrayOf(email, password))
//
//        val exists = cursor.count > 0
//        cursor.close()
//        db.close() // Close database to free resources
//        return exists
//    }
//
//    // Fetch user details
//    fun getUser(email: String): User? {
//            val db = readableDatabase
//            val cursor = db.rawQuery("SELECT * FROM Users WHERE email=?", arrayOf(email))
//
//    return if (cursor.moveToFirst()) {
//        val user = User(
//                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
//                cursor.getString(cursor.getColumnIndexOrThrow("name")),
//                cursor.getString(cursor.getColumnIndexOrThrow("email")),
//                cursor.getString(cursor.getColumnIndexOrThrow("password"))
//        )
//        cursor.close()
//        db.close()
//        user
//    } else {
//        cursor.close()
//        db.close()
//        null
//    }
//    }
//}