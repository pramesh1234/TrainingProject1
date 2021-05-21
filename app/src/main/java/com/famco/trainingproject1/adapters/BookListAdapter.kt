package com.famco.trainingproject1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.famco.trainingproject1.R
import com.famco.trainingproject1.model.Book

class BookListAdapter(val context: Context, val listener: OnRowClickAdapter) :
    RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {
    val bookList: ArrayList<Book> = ArrayList();

    class BookListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_tv)
        val editBtn: CardView = itemView.findViewById(R.id.editBtn)
        val deleteBtn: CardView = itemView.findViewById(R.id.deleteBtn)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookListAdapter.BookListViewHolder {
        val viewHolder = BookListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_post, parent, false)
        )
        viewHolder.editBtn.setOnClickListener {
            listener.onEditItemClick(bookList[viewHolder.adapterPosition])
        }
        viewHolder.deleteBtn.setOnClickListener {
            listener.onDeleteItemClick(bookList[viewHolder.adapterPosition].id)

        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: BookListAdapter.BookListViewHolder, position: Int) {
        holder.title.text = bookList[position].title
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateList(list: List<Book>) {
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }

}

interface OnRowClickAdapter {
    fun onEditItemClick(book: Book)
    fun onDeleteItemClick(id: Int)
}
