package hu.ait.realme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import hu.ait.realme.data.Post
import hu.ait.realme.databinding.ActivityCommentBinding
import hu.ait.realme.databinding.CommentRowBinding

class CommentActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postKey = intent.getStringExtra("POST_KEY").toString()

        val postDocument = FirebaseFirestore.getInstance().collection(CreatePostActivity.POSTS_COLLECTION)
            .document(postKey)

        postDocument.get().addOnSuccessListener { documentSnapshot ->
            val post = documentSnapshot.toObject<Post>()
            var comments = post!!.comments

            for (comment in comments) {
                val commentRow = CommentRowBinding.inflate(layoutInflater)

                commentRow.tvUser.text = comment.user
                commentRow.tvCommentText.text = comment.text

                if (post.imgUrl.isNotEmpty()) {
                    commentRow.ivPhoto.visibility = View.VISIBLE
                    Glide.with(this@CommentActivity).load(comment.imgUrl).into(commentRow.ivPhoto)
                } else {
                    commentRow.ivPhoto.visibility = View.GONE
                }

                binding.layoutContent.addView(commentRow.root)
            }
        }


    }




}
