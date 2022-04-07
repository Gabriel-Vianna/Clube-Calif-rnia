package br.edu.infnet.califrniaclube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.califrniaclube.memberModel.Member
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null
    val db = Firebase.firestore
    private var TAG = "DataBase"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val members = arrayListOf<Member>()
        layoutManager = LinearLayoutManager(this)
        db.collection("members")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data["nome"]}")
                    var member = Member(document.data["nome"].toString(), document.data["idade"].toString(),
                                document.data["telefone"].toString(), document.data["email"].toString(),
                                document.data["cep"].toString(), document.data["rua"].toString(),
                                document.data["bairro"].toString(), document.data["cidade"].toString())
                    members.add(member)
                    Log.d(TAG, "$members")
                }

                recyclerView.layoutManager = layoutManager
                adapter = RecyclerViewAdapter(members, this)
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }


    }
}