package com.nabila.mysocialstyle.ui.history

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nabila.mysocialstyle.data.QuizResult
import com.nabila.mysocialstyle.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: ResultAdapter
    private val resultList = mutableListOf<QuizResult>()

    private lateinit var valueEventListener: ValueEventListener
    private val databaseRef = FirebaseDatabase.getInstance().reference.child("QuizResult")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ResultAdapter(resultList)
        binding.rvResult.layoutManager = LinearLayoutManager(this)
        binding.rvResult.adapter = adapter

        setupRealtimeListener()
    }

    private fun setupRealtimeListener() {
        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                resultList.clear()
                for (data in snapshot.children) {
                    val result = data.getValue(QuizResult::class.java)
                    result?.let { resultList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HistoryActivity, "Gagal ambil data: ${error.message}", Toast.LENGTH_SHORT).show()

            }
        }
        databaseRef.addValueEventListener(valueEventListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseRef.removeEventListener(valueEventListener)
    }
}
