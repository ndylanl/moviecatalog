package com.uc.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uc.moviecatalog.R
import com.uc.moviecatalog.helper.const
import com.uc.moviecatalog.model.ProductionCompany
import com.uc.moviecatalog.viewmodel.NowPlayingViewModel


class companyadapter(private val dataSet: List<ProductionCompany>) :
        RecyclerView.Adapter<companyadapter.ViewHolder>() {

    private lateinit var nowPlayingViewModel: NowPlayingViewModel

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.iv_prodcompany)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.prodcompany_movie, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Picasso.get().load(const.Img_url + dataSet[position].logo_path).into(viewHolder.textView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
