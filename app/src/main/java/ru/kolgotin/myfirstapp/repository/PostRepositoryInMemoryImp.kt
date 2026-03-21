package ru.kolgotin.myfirstapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.kolgotin.myfirstapp.dto.Post
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostRepositoryInMemoryImpl : PostRepository {

    // Счетчик для генерации ID
    private var nextId = 5L

    // Текущий пользователь (для демонстрации)
    private val currentUserId = 1L
    private val currentUserName = "Я"

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
            id=2,
            author="Автозапчасти. Универсальный магазин автозапчастей",
            content="🔥 Только до конца мая: скидка 20% на масла и фильтры всех известных брендов! Успейте подготовить авто к летнему сезону по выгодной цене. Ждём вас в нашем магазине или заказывайте онлайн с доставкой!",
            published="22 мая в 10:15",
            likedByMe=false,
            likes=1245,
            shares=189,
            views=8300
        ),
        Post(
            id=3,
            author="Автосервис 'Мастер'",
            content="⚠️ Заметили, что машину стало уводить в сторону при торможении? Пора проверить тормозные колодки и диски! Проведём бесплатную диагностику тормозной системы при заказе ремонта. Записывайтесь по телефону или через сообщения группы.",
            published="22 мая в 14:40",
            likedByMe=true,
            likes=567,
            shares=45,
            views=4120
        ),
        Post(
            id=4,
            author="Автозапчасти. Универсальный магазин автозапчастей",
            content="🚗 Поступление новых аналогов ходовой части для корейских и японских автомобилей! Высокое качество по доступным ценам. Срок службы не уступает оригиналу. Подбор по VIN-коду в комментариях или в личных сообщениях.",
            published="23 мая в 09:30",
            likedByMe=false,
            likes=2300,
            shares=310,
            views=15400
        ),
        Post(
            id=5,
            author="Автоблогер 'За рулём'",
            content="📢 Друзья, сегодня разберём, как выбрать моторное масло: синтетика, полусинтетика или минералка? Какая вязкость подходит для вашего региона? Полный гид в моём новом видео. Ссылка в профиле. А вы какое масло заливаете?",
            published="24 мая в 11:20",
            likedByMe=false,
            likes=3420,
            shares=890,
            views=27800
        ),
        Post(
            id=6,
            author="Автозапчасти. Универсальный магазин автозапчастей",
            content="🌟 Акция для такси и каршеринга! Оптовые цены на расходные материалы: масла, фильтры, свечи, тормозные колодки. Работаем с юридическими лицами и ИП. Подробности у менеджеров.",
            published="25 мая в 16:45",
            likedByMe=true,
            likes=987,
            shares=156,
            views=7100
        ),
        Post(
            id=7,
            author="Станция техобслуживания 'Колесо'",
            content="⚙️ Начинается сезон отпусков! Проверьте ходовую, шины и кондиционер перед дальней поездкой. Комплексная диагностика всего за 1000 рублей. Приезжайте, сделаем ваш автомобиль безопасным и надёжным.",
            published="26 мая в 12:10",
            likedByMe=false,
            likes=876,
            shares=98,
            views=5400
        ),
        Post(
            id=8,
            author="Автозапчасти. Универсальный магазин автозапчастей",
            content="🎁 Розыгрыш! Участвуйте в конкурсе: подпишитесь на нас, поставьте лайк этому посту и отметьте двух друзей. Главный приз — набор инструментов для авто. Итоги подведём 5 июня.",
            published="27 мая в 18:00",
            likedByMe=false,
            likes=5210,
            shares=1240,
            views=34600
        ),
        Post(
            id=9,
            author="Автоэлектрик Дмитрий",
            content="🔋 Как понять, что аккумулятор пора менять? Рассказываю основные признаки: машина плохо заводится, свет тускнеет, напряжение ниже 12,5 В. Приезжайте на бесплатную проверку аккумулятора в наш сервис. Запись по телефону.",
            published="28 мая в 09:05",
            likedByMe=true,
            likes=2140,
            shares=320,
            views=16900
        ),
        Post(
            id=10,
            author="Автозапчасти. Универсальный магазин автозапчастей",
            content="📦 Доставка автозапчастей по всему городу и области! Оформите заказ на сайте или по телефону, и курьер привезёт детали в удобное для вас время. Оплата при получении. Экономьте своё время с нами!",
            published="30 мая в 14:50",
            likedByMe=false,
            likes=1567,
            shares=203,
            views=11200
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

    override fun save(post: Post) {
        if (post.id == 0L) {
            // Создание нового поста
            val newPost = post.copy(
                id = nextId++,
                author = currentUserName,
                authorId = currentUserId,
                published = formatDate(Date()),
                likedByMe = false,
                likes = 0,
                shares = 0,
                views = 0
            )
            posts = listOf(newPost) + posts
        } else {
            // Обновление существующего поста
            posts = posts.map { existingPost ->
                if (existingPost.id == post.id) {
                    // Сохраняем автора, дату и счетчики, обновляем только контент
                    existingPost.copy(content = post.content)
                } else {
                    existingPost
                }
            }
        }
        _data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        _data.value = posts
    }

    private fun formatDate(date: Date): String {
        val format = SimpleDateFormat("d MMM в HH:mm", Locale("ru"))
        return format.format(date)
    }
}