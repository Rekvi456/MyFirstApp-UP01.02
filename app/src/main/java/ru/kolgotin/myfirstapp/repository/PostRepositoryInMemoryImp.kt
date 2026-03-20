package ru.kolgotin.myfirstapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.kolgotin.myfirstapp.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    // Теперь это список, а не один пост
    private var posts = listOf(
        Post(
            id = 1,
            author = "Автозапчасти. Универсальный магазин автозапчастей",
            content = "Добро пожаловать в наш универсальный магазин автозапчастей! У нас широкий ассортимент оригинальных запчастей и аналогов для всех марок автомобилей. Мы предлагаем запчасти для двигателей, ходовой части, тормозной системы, электрооборудования и многое другое. Наши консультанты готовы помочь вам подобрать нужные детали и аксессуары для вашего автомобиля.",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 999,
            shares = 25,
            views = 5700
        ),
        Post(
            id = 2,
            author = "Автозапчасти. Универсальный магазин автозапчастей",
            content = "Мы знаем, насколько важно иметь качественные запчасти для вашего автомобиля. Именно поэтому мы предлагаем широкий ассортимент оригинальных деталей и аксессуаров по доступным ценам. Не рискуйте своей безопасностью — выбирайте проверенные товары и доверяйте профессионалам!",
            published = "22 мая в 10:15",
            likedByMe = false,
            likes = 342,
            shares = 89,
            views = 2300
        ),
        Post(
            id = 3,
            author = "Автозапчасти. Универсальный магазин автозапчастей",
            content = "Пришло время освежить внешний вид своего железного коня? У нас вы найдёте всё необходимое для тюнинга и модернизации вашего автомобиля. Подберите новые диски, аксессуары и детали интерьера прямо сейчас! Пусть ваш автомобиль будет таким же уникальным, как и вы сами.",
            published = "23 мая в 09:42",
            likedByMe = true,
            likes = 1250,
            shares = 420,
            views = 8900
        ),
        Post(
            id = 4,
            author = "Автозапчасти. Универсальный магазин автозапчастей",
            content = "Нужна определённая запчасть, но не знаете, где её искать? Мы поможем разобраться! Профессиональные консультанты нашего магазина оперативно подберут необходимую деталь, сэкономив ваше время и нервы.",
            published = "20 мая в 20:00",
            likedByMe = false,
            likes = 5678,
            shares = 1234,
            views = 45000
        )
    )

    private val _data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = _data

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
                )
            } else {
                post
            }
        }
        _data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(shares = post.shares + 1)
            } else {
                post
            }
        }
        _data.value = posts
    }

    override fun increaseViews(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(views = post.views + 1)
            } else {
                post
            }
        }
        _data.value = posts
    }
}
