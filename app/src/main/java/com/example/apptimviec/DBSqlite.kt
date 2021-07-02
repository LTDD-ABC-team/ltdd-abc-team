package com.example.apptimviec

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

val DATABASE_NAME = "JobDB"
val TABLE_USER = "User"
val TABLE_JOB = "Job"
val TABLE_STAR = "Star"
val TABLE_NEW = "New"
val COL_ID = "id"
val COL_USERNAME = "username"
val COL_EMAIL = "email"
val COL_PASSWORD = "password"
val COL_JOB = "job"
val COL_COMPANY = "comapny"
val COL_ADDRESS = "address"
val COL_SALARY = "salary"
val COL_DETAIL = "detail"
val COL_DATE = "date"
val COL_URL = "url"
val COL_ID_USER = "idUser"
val COL_ID_JOB = "idJob"
//val COL_NEWW = "idUser"
//val COL_DETAILNEW = "idJob"

class DBSqllite(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE " + TABLE_USER + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " VARCHAR(256), " +
                COL_EMAIL + " VARCHAR(256)," +
                COL_PASSWORD + " VARCHAR(256) )";
        val createTableQuery2 = "CREATE TABLE " + TABLE_JOB + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_JOB + " VARCHAR(256), " +
                COL_COMPANY + " VARCHAR(256), " +
                COL_ADDRESS + " VARCHAR(256), " +
                COL_SALARY + " VARCHAR(256), " +
                COL_DETAIL + " VARCHAR(256), " +
                COL_DATE + " VARCHAR(256), " +
                COL_URL + " VARCHAR(256))" ;
        val createTableQuery3 = "CREATE TABLE " + TABLE_STAR + "(" +
                COL_ID_USER + " INTEGER, " +
                COL_ID_JOB + " INTEGER)";
        /*val createTableQuery4 = "CREATE TABLE " + TABLE_NEW + "(" +
                COL_NEWW + " VARCHAR(256), " +
                COL_DETAILNEW + " VARCHAR(256))";*/
        db?.execSQL(createTableQuery)
        db?.execSQL(createTableQuery2)
        db?.execSQL(createTableQuery3)
        //db?.execSQL(createTableQuery4)
    }

    fun checkRegister(name: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE $COL_USERNAME = ?"
        val result = db.rawQuery(query, arrayOf(name))
        if (result.moveToFirst()) {
            return false
        }
        return true
    }

    fun checkLogin(name: String, pass: String): Int {
        val db = this.readableDatabase
        var id = -1
        val query = "SELECT * FROM $TABLE_USER WHERE $COL_USERNAME = ?"
        val result = db.rawQuery(query, arrayOf(name))
        if (result.moveToFirst()) {
            if(pass == result.getString(result.getColumnIndex(COL_PASSWORD))) {
                id = result.getString(result.getColumnIndex(COL_ID)).toInt()
            }
        }
        return id
    }

    fun insertDataUser(user: User) {
        val db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COL_USERNAME, user.username)
        contentValues.put(COL_EMAIL, user.email)
        contentValues.put(COL_PASSWORD, user.password)
        db.insert(TABLE_USER, null, contentValues)
        db.close()
    }

    fun insertDataJob(job: Job) {
        val db = this.writableDatabase
        var cal = Calendar.getInstance()
        var date=cal.time
        var sdf= SimpleDateFormat("dd/MM/yyyy")
        var contentValues = ContentValues()
        contentValues.put(COL_JOB, job.job)
        contentValues.put(COL_COMPANY, job.company)
        contentValues.put(COL_ADDRESS, job.address)
        contentValues.put(COL_SALARY, job.salary)
        contentValues.put(COL_DETAIL, job.detail)
        contentValues.put(COL_DATE, sdf.format((date)))
        contentValues.put(COL_URL, job.url)
        db.insert(TABLE_JOB, null, contentValues)
        db.close()
    }

    /*fun insertDataNew(neww: Neww) {
        val db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COL_NEWW, neww.neww)
        contentValues.put(COL_DETAILNEW, neww.detailnew)
        db.insert(TABLE_NEW, null, contentValues)
        db.close()
    }*/

    fun insertDataHistory(idJob: Int,idUser: Int) {
        val db = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COL_ID_JOB, idJob.toString())
        contentValues.put(COL_ID_USER, idUser.toString())
        db.insert(TABLE_STAR, null, contentValues)
        db.close()
    }

    fun readUser(id: Int): User {

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE $COL_ID = ?"
        val result = db.rawQuery(query, arrayOf(id.toString()))
        val user = User()
        if (result.moveToFirst()) {

                user.username = result.getString(result.getColumnIndex(COL_USERNAME))
                user.email = result.getString(result.getColumnIndex(COL_EMAIL))
                user.password = result.getString(result.getColumnIndex(COL_PASSWORD))
        }
        db.close()
        return user
    }

    fun readDataJob(): ArrayList<Job> {
        var jobs = ArrayList<Job>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_JOB"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val job = Job()
                job.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                job.job = result.getString(result.getColumnIndex(COL_JOB))
                job.company = result.getString(result.getColumnIndex(COL_COMPANY))
                job.address = result.getString(result.getColumnIndex(COL_ADDRESS))
                job.salary = result.getString(result.getColumnIndex(COL_SALARY))
                job.detail = result.getString(result.getColumnIndex(COL_DETAIL))
                job.date = result.getString(result.getColumnIndex(COL_DATE))
                job.url = result.getString(result.getColumnIndex(COL_URL))
                jobs.add(job)
            } while (result.moveToNext())
        }
        db.close()
        return jobs
    }

    /*fun readDataNew(): ArrayList<Neww> {
        var newws = ArrayList<Neww>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NEW"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val neww = Neww()
                neww.neww = result.getString(result.getColumnIndex(COL_NEWW))

                neww.detailnew = result.getString(result.getColumnIndex(COL_DETAILNEW))

                newws.add(neww)
            } while (result.moveToNext())
        }
        db.close()
        return newws

    }*/

    fun checkHistory(idJob: Int,idUser: Int) : Boolean{
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_STAR WHERE $COL_ID_USER = ? AND $COL_ID_JOB = ?"
        val result = db.rawQuery(query, arrayOf(idUser.toString(),idJob.toString()))
        if (result.moveToFirst()) {
            return true
        }
        return false
    }

    fun getJobHistory(id : Int): ArrayList<Job>{
        var jobs = ArrayList<Job>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_STAR WHERE $COL_ID_USER = ?"
        val result = db.rawQuery(query, arrayOf(id.toString()))
        if (result.moveToFirst()) {
            do {
                val query2 = "SELECT * FROM $TABLE_JOB WHERE $COL_ID = ?"
                val result2 = db.rawQuery(query2, arrayOf(result.getString(result.getColumnIndex(
                    COL_ID_JOB))))
                if(result2.moveToFirst()) {
                    val job = Job()
                    job.id = result2.getString(result2.getColumnIndex(COL_ID)).toInt()
                    job.job = result2.getString(result2.getColumnIndex(COL_JOB))
                    job.address = result2.getString(result2.getColumnIndex(COL_ADDRESS))
                    job.detail = result2.getString(result2.getColumnIndex(COL_DETAIL))
                    job.company = result2.getString(result2.getColumnIndex(COL_COMPANY))
                    job.salary = result2.getString(result2.getColumnIndex(COL_SALARY))
                    job.date = result2.getString(result2.getColumnIndex(COL_DATE))
                    jobs.add(job)
                }
            } while (result.moveToNext())
        }
        db.close()
        return jobs
    }

    fun updateDataUser(id: Int, password: String) {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE $COL_ID = $id"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var contentValue = ContentValues()
                contentValue.put(COL_PASSWORD, id)
                db.update(
                    TABLE_USER, contentValue, "$COL_ID = ?",
                    arrayOf(result.getString(result.getColumnIndex(COL_ID)))
                )
            } while (result.moveToNext())
        }
        db.close()
    }

    fun deleteData(username: String) {
        val db = this.writableDatabase
        db.delete(TABLE_USER, "$COL_USERNAME = ?", arrayOf(username))
        db.close()
    }
    fun deleteDataJob(username: String) {
        val db = this.writableDatabase
        db.delete(TABLE_JOB, "$COL_JOB = ?", arrayOf(username))
        db.close()
    }

    fun deleteDataHistory(idJob: Int,idUser: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_STAR, "$COL_ID_JOB = ? AND $COL_ID_USER = ?", arrayOf(idJob.toString(),idUser.toString()))
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}
