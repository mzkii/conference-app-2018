package io.github.droidkaigi.confsched2018.presentation.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.github.droidkaigi.confsched2018.R
import io.github.droidkaigi.confsched2018.databinding.ActivityDetailBinding
import io.github.droidkaigi.confsched2018.model.Session
import io.github.droidkaigi.confsched2018.presentation.NavigationController
import io.github.droidkaigi.confsched2018.presentation.common.activity.BaseActivity
import javax.inject.Inject

class SessionDetailActivity : BaseActivity(), HasSupportFragmentInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var navigationController: NavigationController

    private val binding: ActivityDetailBinding by lazy {
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        navigationController.navigateToDetail(intent.getStringExtra(EXTRA_SESSION_ID))
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    companion object {
        val EXTRA_SESSION_ID = "EXTRA_SESSION_ID"
        fun start(context: Context, session: Session) {
            context.startActivity(Intent(context, SessionDetailActivity::class.java).apply {
                putExtra(EXTRA_SESSION_ID, session.id)
            })
        }
    }
}