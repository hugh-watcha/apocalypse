package com.singasong.apocalypse.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.singasong.apocalypse.R
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.databinding.FragmentMainBinding
import com.singasong.apocalypse.ui.adapter.*
import com.singasong.apocalypse.ui.base.BaseFragment
import com.singasong.apocalypse.ui.posts.PostsFragment
import com.singasong.apocalypse.viewmodel.KEY_CHOSEN_NUMBER
import com.singasong.apocalypse.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_main_bottom_tab.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    companion object {
        private const val TAG_CURRENT_FRAGMENT = "current_fragment"
    }

    private val viewModel: MainViewModel by viewModel()
    private val fragmentsBundle: Bundle by lazy { Bundle() }

    override val layoutId: Int = R.layout.fragment_main

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_CHOSEN_NUMBER, viewModel.chosenNumber)
        (viewBottomTabs.rvTabs.adapter as? BaseSelectionRecyclerAdapter<*, *>)?.tracker?.onSaveInstanceState(
            outState
        )
    }

    override fun bindData(dataBinding: FragmentMainBinding) {
        dataBinding.viewModel = viewModel
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        initToolbar(view.findViewById(R.id.toolbar))
        initBottomTabLayout(view.findViewById(R.id.viewBottomTabs), savedInstanceState)
    }

    private fun initToolbar(toolbar: Toolbar) {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

    }

    private fun initBottomTabLayout(layout: View, savedInstanceState: Bundle?) {
        layout.findViewById<RecyclerView>(R.id.rvTabs)?.apply {
            val rvAdapter = MainBottomTabLayoutAdapter()
            adapter = rvAdapter
            rvAdapter.tracker = SelectionTracker.Builder<String>(
                "select-number",
                this,
                MainTabKeyProvider(rvAdapter),
                MainTabDetailsLookup(this),
                StorageStrategy.createStringStorage()
            ).withSelectionPredicate(AlwaysSelectSinglePredicate())
                .build()
            rvAdapter.tracker?.addObserver(object : SelectionTracker.SelectionObserver<String>() {
                override fun onItemStateChanged(key: String, selected: Boolean) {
                    super.onItemStateChanged(key, selected)
                    switchPostsFragment(key)
                }
            })

            rvAdapter.tracker?.onRestoreInstanceState(savedInstanceState)
        }
    }

    private fun switchPostsFragment(key: String) {
        val numberKey = viewModel.numberKeys.value?.find {
            it.number == key
        }
        if (numberKey != null) {
            val stored = childFragmentManager.getFragment(fragmentsBundle, key) as? PostsFragment
            val to = stored ?: PostsFragment.newInstance(numberKey)
            val currFrag =
                childFragmentManager.findFragmentByTag(TAG_CURRENT_FRAGMENT) as? PostsFragment
            val transaction = childFragmentManager.beginTransaction()
            if (currFrag != null) {
                val numKey = currFrag.getNumberKey()
                childFragmentManager.putFragment(fragmentsBundle, numKey.number, currFrag)
                transaction.remove(currFrag)
            }
            transaction.replace(R.id.fragmentContainer, to, key).commit()

            toolbar.title = numberKey.name
                ?: if (numberKey.number == NumberKey.NUMBER_NOTICE) getString(R.string.notice) else numberKey.number
        }
    }
}