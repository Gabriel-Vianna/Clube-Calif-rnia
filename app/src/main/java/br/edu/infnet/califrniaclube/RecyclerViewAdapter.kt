package br.edu.infnet.califrniaclube

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.califrniaclube.memberModel.Member
import kotlinx.android.synthetic.main.row.view.*

class RecyclerViewAdapter(private val arrayList: ArrayList<Member>, val context: Context):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindItems(member: Member) {
            itemView.nome.text = member.nome
            itemView.email.text = member.email
            itemView.telefone.text = member.telefone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}