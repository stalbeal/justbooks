package com.saba.justbooks.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.saba.categoryselector.CategorySelectorActivity
import com.saba.justbooks.R
import kotlinx.android.synthetic.main.activity_home.*

@ExperimentalStdlibApi
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //startActivity(Intent(this, CategorySelectorActivity::class.java))

    }
}