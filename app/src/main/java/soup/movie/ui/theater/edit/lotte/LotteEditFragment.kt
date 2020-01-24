package soup.movie.ui.theater.edit.lotte

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import soup.movie.model.Theater
import soup.movie.databinding.TheaterEditChildFragmentBinding
import soup.movie.ui.theater.edit.TheaterEditChildFragment
import soup.movie.ui.theater.edit.TheaterEditChildListAdapter

class LotteEditFragment : TheaterEditChildFragment() {

    override val title: String = "롯데시네마"

    private val viewModel: LotteEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = TheaterEditChildFragmentBinding.inflate(inflater, container, false)
        binding.initViewState(viewModel)
        return binding.root
    }

    private fun TheaterEditChildFragmentBinding.initViewState(viewModel: LotteEditViewModel) {
        val listAdapter = TheaterEditChildListAdapter(object : TheaterEditChildListAdapter.Listener {

            override fun add(theater: Theater): Boolean {
                return viewModel.add(theater)
            }

            override fun remove(theater: Theater) {
                viewModel.remove(theater)
            }
        })
        listView.adapter = listAdapter
        viewModel.uiModel.observe(viewLifecycleOwner) {
            listAdapter.submitList(it.areaGroupList, it.selectedTheaterIdSet)
        }
    }
}
