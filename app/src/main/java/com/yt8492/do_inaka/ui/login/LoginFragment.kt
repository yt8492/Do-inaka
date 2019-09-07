package com.yt8492.do_inaka.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yt8492.do_inaka.databinding.FragmentLoginBinding
import com.yt8492.do_inaka.domain.model.UserType
import com.yt8492.do_inaka.usecase.login.LoginResult
import com.yt8492.do_inaka.util.toast
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.math.log

class LoginFragment : Fragment() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )
        val viewModel = ViewModelProvider(
            this,
            loginViewModelFactory
        ).get(LoginViewModel::class.java)

        binding.viewModel = viewModel
        binding.loginButton.setOnClickListener {
            val username = binding.loginUsernameEditText.text.toString()
            val password = binding.loginPasswordEditText.text.toString()
            viewModel.login(username, password)
        }

        viewModel.error.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                LoginResult.Failure.EmptyUsername -> {
                    toast("ユーザー名を入力してください")
                }
                LoginResult.Failure.EmptyPassword -> {
                    toast("パスワードを入力してください")
                }
                LoginResult.Failure.InvalidPassword -> {
                    toast("パスワードが間違っています")
                }
            }
        })
        viewModel.loginUser.observe(viewLifecycleOwner, Observer { loginUser ->
            when (loginUser.userType) {
                UserType.Requester -> {
                    toast("TODO: Requester画面に遷移")
                }
                UserType.Driver -> {
                    toast("TODO: Driver画面に遷移")
                }
            }
        })
        viewModel.checkLogin()

        return binding.root
    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}