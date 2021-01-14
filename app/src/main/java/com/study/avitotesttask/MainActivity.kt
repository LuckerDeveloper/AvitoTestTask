package com.study.avitotesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Integer.getInteger
import java.util.*

class MainActivity : AppCompatActivity(), NumAdapter.OnClickDeleteListener, NumGenerator.ElementReceiver {

    private val VISIBLE_ELEMENTS_KEY = "VISIBLE_ELEMENTS_KEY"
    private val DELETED_ELEMENTS_KEY = "DELETED_ELEMENTS_KEY"
    lateinit var recyclerView: RecyclerView
    private lateinit var visibleElements : TreeSet<Int>
    private lateinit var deletedElements : TreeSet<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState?.getIntArray(VISIBLE_ELEMENTS_KEY)?.isNotEmpty() ==true){
            visibleElements= TreeSet(savedInstanceState.getIntArray(VISIBLE_ELEMENTS_KEY)?.toSortedSet())
            deletedElements = TreeSet(savedInstanceState.getIntArray(DELETED_ELEMENTS_KEY)?.toSortedSet())
        } else{
            visibleElements=  TreeSet((1..15).toSortedSet())
            deletedElements = TreeSet()
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.span_count))
        recyclerView.adapter = NumAdapter(visibleElements, this)

        val numGenerator = NumGenerator(visibleElements, deletedElements, this)
        numGenerator.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntArray(VISIBLE_ELEMENTS_KEY, visibleElements.toIntArray())
        outState.putIntArray(DELETED_ELEMENTS_KEY, deletedElements.toIntArray())
        super.onSaveInstanceState(outState)
    }

    override fun deleteNum(position: Int) {
        val num = visibleElements.elementAt(position)
        deletedElements.add(num)
        visibleElements.remove(num)
        recyclerView.adapter?.notifyItemRemoved(position)
    }

    override fun addElement(num : Int) {
        deletedElements.remove(num)
        visibleElements.add(num)
        val position = visibleElements.indexOf(num)
        recyclerView.adapter?.notifyItemInserted(position)
    }

}
