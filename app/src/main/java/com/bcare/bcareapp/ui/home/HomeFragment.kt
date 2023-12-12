package com.bcare.bcareapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcare.bcareapp.R
import com.bcare.bcareapp.databinding.FragmentHomeBinding
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.bcare.bcareapp.data.local.result.Result
import com.bcare.bcareapp.data.remote.response.artikel.DataItem
import com.bcare.bcareapp.ui.artikel.ArtikelActivity

class HomeFragment : Fragment() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArtikelViewModel by viewModels{
        ArtikelViewModelFactory.getInstance(requireContext(),requireContext().dataStore)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        getSlide(binding.root)
        return binding.root

    }

    private fun getSlide(view: View) {
        val imageSlider: ImageSlider = view.findViewById(R.id.slider)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.slider1))
        imageList.add(SlideModel(R.drawable.slider2))
        imageList.add(SlideModel(R.drawable.slider3))
        imageList.add(SlideModel(R.drawable.slider4))
        imageList.add(SlideModel(R.drawable.slider5))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.getListArticle().observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val listArticle = result.data
                    setArticleData(listArticle)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireActivity(),
                        "Failed to load article",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun setArticleData(listArticle: List<DataItem>) {
        val adapter = ListArtikelAdapter(listArticle)

//        adapter.setOnItemClickCallback(object : ListArtikelAdapter.OnItemClickCallback {
//            override fun onItemArticleClicked(item: DataItem) {
//                navigateToDetailArticle(item)
//            }
//        })

        binding.apply {
            rvArtikel.adapter = adapter

            val layoutManager = LinearLayoutManager(requireContext())
            binding.rvArtikel.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
            binding.rvArtikel.addItemDecoration(itemDecoration)
        }

    }

//    private fun navigateToDetailArticle(article: DataItem) {
//        val moveToDetail = Intent(requireActivity(), ArtikelActivity::class.java)
//        moveToDetail.putExtra(ArtikelActivity.ID_ARTICLE, article.id)
//        startActivity(moveToDetail)
//    }
}