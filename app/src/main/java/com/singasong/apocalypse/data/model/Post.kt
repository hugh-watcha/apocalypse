package com.singasong.apocalypse.data.model

import java.util.*

class Post {
    var postId: String? = null
    var targetNumber: String? = null
    var createdNumber: String? = null
    var text: String? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var name: String? = null
    var anonymous: Boolean = true

    override fun equals(other: Any?): Boolean {
        return (other is Post) && (this === other || postId == other.postId)
    }
}