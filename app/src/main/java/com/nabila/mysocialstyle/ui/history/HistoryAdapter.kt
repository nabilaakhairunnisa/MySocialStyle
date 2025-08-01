package com.nabila.mysocialstyle.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nabila.mysocialstyle.R
import com.nabila.mysocialstyle.data.QuizResult
import com.nabila.mysocialstyle.databinding.ItemHistoryBinding

class ResultAdapter(private val list: List<QuizResult>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var expandedPosition = -1

    inner class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = list[position]
        with(holder.binding) {
            name.text = result.name
            socialStyleName.text = result.socialStyleName
            amiableScore.text = result.amiableScore.toString()
            analyticalScore.text = result.analyticalScore.toString()
            driverScore.text = result.driverScore.toString()
            expressiveScore.text = result.expressiveScore.toString()
            timestamp.text = result.timeStamp

            teksDetail.text = HistoryUtils.getStyleDescription(result.socialStyleName)

            // Tampilkan atau sembunyikan detail tergantung posisi
            val isExpanded = position == expandedPosition
            detail.visibility = if (isExpanded) android.view.View.VISIBLE else android.view.View.GONE
            expand.setImageResource(if (isExpanded) R.drawable.up else R.drawable.down)

            expand.setOnClickListener {
                // Expand atau collapse item
                val previousExpanded = expandedPosition
                expandedPosition = if (isExpanded) -1 else position

                // Hanya update item yang berubah
                notifyItemChanged(previousExpanded)
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int = list.size

}
