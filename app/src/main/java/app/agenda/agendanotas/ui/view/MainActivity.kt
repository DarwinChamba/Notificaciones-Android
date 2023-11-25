package app.agenda.agendanotas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import app.agenda.agendanotas.R
import app.agenda.agendanotas.databinding.ActivityMainBinding
import app.agenda.agendanotas.ui.viewmodel.NotaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var model:NotaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tool)
        changeColor()
        model=ViewModelProvider(this).get(NotaViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        navController=findNavController(R.id.fragmentContainer)
        NavigationUI.setupWithNavController(binding.bottonView,navController)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }
    private fun changeColor(){
        val window=this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor=getColor(R.color.colorPantalla)
    }
}