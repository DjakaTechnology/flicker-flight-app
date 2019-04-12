package id.djaka.flicker.ui.search

import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import kotlinx.android.synthetic.main.rv_ticket_search.*

class SearchActivity : BaseActivity<SearchPresenter>(), SearchView{
    override fun instantiatePresenter(): SearchPresenter {
        return SearchPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        prepareButton()
    }

    private fun prepareButton() {
        card_item.setOnClickListener { presenter.launchDetailOrderActivity(this) }
    }
}
