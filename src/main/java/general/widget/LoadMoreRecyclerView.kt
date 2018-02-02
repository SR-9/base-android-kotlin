package general.widget
/*
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet


class LoadMoreRecyclerView @JvmOverloads constructor(context : Context, attr : AttributeSet? = null, defStyle : Int = 0)
    : RecyclerView(context, attr, defStyle) {

    interface EndListReacharListener {
        fun onEndListReachar()
    }

    var loading = false

    fun addOnScrollEndListListener(listener : EndListReacharListener) {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView : RecyclerView?, dx : Int, dy : Int) {
                if (dy > 0 && ! loading) {
                    val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if ((visibleItemCount + pastVisiblesItems + 5) >= totalItemCount) {
                        loading = true
                        listener.onEndListReachar()
                    }
                }
            }
        })
    }
}*/
