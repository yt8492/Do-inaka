package com.yt8492.do_inaka.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.yt8492.do_inaka.R
import com.yt8492.do_inaka.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        if (savedInstanceState == null) {
            val fragment = LoginFragment.newInstance()
            supportFragmentManager.commit {
                replace(R.id.loginContainer, fragment)
            }
        }
    }
}
