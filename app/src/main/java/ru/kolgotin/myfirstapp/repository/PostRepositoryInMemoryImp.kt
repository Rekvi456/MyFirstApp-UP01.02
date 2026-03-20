package ru.kolgotin.myfirstapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.kolgotin.myfirstapp.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    // Исходные данные
    private var post = Post(
        id = 1,
        author = "Автозапчасти. Универсальный магазин автозапчастей",
        content = "Добро пожаловать в наш универсальный магазин автозапчастей! У нас широкий ассортимент оригинальных запчастей и аналогов для всех марок автомобилей. Мы предлагаем запчасти для двигателей, ходовой части, тормозной системы, электрооборудования и многое другое. Наши консультанты готовы помочь вам подобрать нужные детали и аксессуары для вашего автомобиля.",
        published = "21 мая в 18:36",
        likedByMe = false,
        likes = 999,
        shares = 25,
        views = 5700
    )

    // MutableLiveData, который можно изменять
    private val _data = MutableLiveData(post)

    // Внешний доступ только для чтения (LiveData, а не MutableLiveData)
    override fun get(): LiveData<Post> = _data

    override fun like() {
        // Меняем состояние лайка на противоположное
        post = post.copy(
            likedByMe = !post.likedByMe,
            likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
        )
        // Оповещаем подписчиков об изменении
        _data.value = post
    }

    override fun share() {
        post = post.copy(
            shares = post.shares + 1
        )
        _data.value = post
    }

    override fun increaseViews() {
        // Можно будет реализовать позже
        post = post.copy(
            views = post.views + 1
        )
        _data.value = post
    }
}