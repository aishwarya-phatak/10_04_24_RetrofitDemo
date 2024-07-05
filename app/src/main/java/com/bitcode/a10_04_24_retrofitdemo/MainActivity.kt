package com.bitcode.a10_04_24_retrofitdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitcode.a10_04_24_retrofitdemo.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val usersService = UsersService.getInstance()

        activityMainBinding.btnFetchUsers.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val userModel = usersService.fetchUsers(
                    activityMainBinding.edtUserId.text.toString().toInt()
                )

                withContext(Dispatchers.Main) {
                    activityMainBinding.txtEmail.text = userModel.data.email
                    activityMainBinding.txtUsername.text =
                        userModel.data.firstName + "  " + userModel.data.lastName

                    Glide.with(this@MainActivity)
                        .load(userModel.data.avatar)
                        .into(activityMainBinding.imgUser)
                }
            }
        }
    }
}