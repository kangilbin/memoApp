package com.kang.memo

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView




@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity(), OndeleteListnere {

    lateinit var db : MemoDatabase
    var memoList = listOf<MemoEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = MemoDatabase.getInstance(this)!!

        button_add.setOnClickListener {
            val memo = MemoEntity(null, editText_memo.text.toString())
            editText_memo.setText("")
            insertMemo(memo)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)

        getAllMemos()
    }

    //1. Insert Data
    fun insertMemo(memo:MemoEntity){
        val insertTask = object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.memoDAO().insert(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemos()
            }
        }
        insertTask.execute()
    }
    //2. Get Data
    fun getAllMemos(){
        val getTask = object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                memoList = db.memoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecycleView(memoList)
            }
        }
        getTask.execute()
    }
    //3. delete Data
    fun deleteMEmo(memo: MemoEntity){
        val deleteTask = object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.memoDAO().delete(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemos()
            }
        }
        deleteTask.execute()
    }
    fun setRecycleView(memoList: List<MemoEntity>){
        recyclerView.adapter = MyAdapter(this, memoList, this)
    }

    override fun onDeleteListener(memo: MemoEntity) {
        deleteMEmo(memo)
    }

}