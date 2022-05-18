package hu.ait.realme.data

data class Post (
    var uid: String = "",
    var user: String = "",
    var caption: String = "",
    var imgUrl: String = "",
    var location: String? = "",
    var comments: List<Comment> = emptyList<Comment>()
)