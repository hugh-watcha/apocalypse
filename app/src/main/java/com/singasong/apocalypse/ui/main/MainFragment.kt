package com.singasong.apocalypse.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.singasong.apocalypse.R
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.databinding.FragmentMainBinding
import com.singasong.apocalypse.ui.adapter.BaseSelectionRecyclerAdapter
import com.singasong.apocalypse.ui.adapter.MainBottomTabLayoutAdapter
import com.singasong.apocalypse.ui.adapter.MainTabDetailsLookup
import com.singasong.apocalypse.ui.adapter.MainTabKeyProvider
import com.singasong.apocalypse.ui.base.BaseFragment
import com.singasong.apocalypse.viewmodel.KEY_CHOSEN_NUMBER
import com.singasong.apocalypse.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.layout_main_bottom_tab.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initBottomTabLayout(view!!.findViewById(R.id.viewBottomTabs), savedInstanceState)
        return view
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
            ).withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
                .build()
            rvAdapter.tracker?.onRestoreInstanceState(savedInstanceState)
        }
    }
}