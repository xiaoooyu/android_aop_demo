package com.xiaoooyu.demo.android_aspectj_demo

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.hunter.library.debug.HunterDebug


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        printArgs("The", "Quick", "Brown", "Fox")

        Log.i("Fibonacci", "fibonacci's 4th number is " + fibonacci(4))

        val greeter = Greeter("Jake")
        Log.d("Greeting", greeter.sayHello())

        val charmer = Charmer("Jake")
        Log.d("Charming", charmer.askHowAreYou())

        startSleepyThread()
    }

    @HunterDebug
    private fun printArgs(vararg args: String) {
        for (arg in args) {
            Log.i("Args", arg)
        }
    }

    @HunterDebug
    private fun fibonacci(number: Int): Int {
        require(number > 0) { "Number must be greater than zero." }
        return if (number == 1 || number == 2) {
            1
        } else fibonacci(number - 1) + fibonacci(number - 2)
        // NOTE: Don't ever do this. Use the iterative approach!
    }

    private fun startSleepyThread() {
        Thread(object : Runnable {
            private val SOME_POINTLESS_AMOUNT_OF_TIME: Long = 50
            override fun run() {
                sleepyMethod(SOME_POINTLESS_AMOUNT_OF_TIME)
            }

            @HunterDebug
            private fun sleepyMethod(milliseconds: Long) {
                SystemClock.sleep(milliseconds)
            }
        }, "I'm a lazy thr.. bah! whatever!").start()
    }

    @HunterDebug
    internal class Greeter(private val name: String) {
        fun sayHello(): String {
            return "Hello, $name"
        }

    }

    @HunterDebug
    internal class Charmer constructor(private val name: String) {
        fun askHowAreYou(): String {
            return "How are you $name?"
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}