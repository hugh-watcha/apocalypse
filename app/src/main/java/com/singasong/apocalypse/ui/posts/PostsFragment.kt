package com.singasong.apocalypse.ui.posts

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.singasong.apocalypse.R
import com.singasong.apocalypse.data.model.NumberKey
import com.singasong.apocalypse.databinding.FragmentPostsBinding
import com.singasong.apocalypse.network.Status
import com.singasong.apocalypse.ui.adapter.PostsAdapter
import com.singasong.apocalypse.ui.base.BaseFragment
import com.singasong.apocalypse.viewmodel.KEY_NUMBER_KEY
import com.singasong.apocalypse.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : BaseFragment<FragmentPostsBinding>() {

    companion object {
        fun newInstance(numberKey: NumberKey): PostsFragment {
            return PostsFragment().apply {
                arguments = bundleOf(KEY_NUMBER_KEY to numberKey)
            }
        }
    }

    override val layoutId: Int = R.layout.fragment_posts
    private val viewModel: PostsViewModel by viewModel(defaultArguments = { arguments })

    override fun bindData(dataBinding: FragmentPostsBinding) {
        dataBinding.viewModel = viewModel
        viewModel.posts.observe(this, Observer {
            if (it.status == Status.SUCCESS) {
                emptyText.visibility = if (it.data.isNullOrEmpty()) View.VISIBLE else View.GONE
            }
        })
    }

    override fun initViews(view: View, savedInstanceState: Bundle?) {
        initListView(view.findViewById(R.id.rvPosts))
    }

    fun getNumberKey(): NumberKey {
        return arguments?.getParcelable(KEY_NUMBER_KEY)
            ?: throw IllegalArgumentException("number key cannot be null")
    }

    private fun initListView(recyclerView: RecyclerView?) {
        recyclerView?.apply {
            val rvAdapter = PostsAdapter()
            adapter = rvAdapter
        }
    }
}