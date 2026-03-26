package ru.kolgotin.myfirstapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.kolgotin.myfirstapp.db.PostContract.Columns

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "myfirstapp.db"
        private const val DATABASE_VERSION = 1

        // SQL для создания таблицы
        private const val SQL_CREATE_POSTS =
            "CREATE TABLE ${PostContract.TABLE_NAME} (" +
                    "${Columns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Columns.AUTHOR} TEXT NOT NULL," +
                    "${Columns.AUTHOR_ID} INTEGER NOT NULL," +
                    "${Columns.CONTENT} TEXT NOT NULL," +
                    "${Columns.PUBLISHED} TEXT NOT NULL," +
                    "${Columns.LIKED_BY_ME} INTEGER NOT NULL DEFAULT 0," +
                    "${Columns.LIKES} INTEGER NOT NULL DEFAULT 0," +
                    "${Columns.SHARES} INTEGER NOT NULL DEFAULT 0," +
                    "${Columns.VIEWS} INTEGER NOT NULL DEFAULT 0," +
                    "${Columns.VIDEO} TEXT" +
                    ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Создаем таблицу при первом запуске
        db.execSQL(SQL_CREATE_POSTS)

        // Здесь можно добавить начальные данные
        insertInitialData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // При обновлении версии удаляем старую таблицу и создаем новую
        // В реальном проекте здесь должна быть миграция данных
        db.execSQL("DROP TABLE IF EXISTS ${PostContract.TABLE_NAME}")
        onCreate(db)
    }

    private fun insertInitialData(db: SQLiteDatabase) {
        // Вставляем начальные посты для демонстрации
        val contentValues = android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автозапчасти. Универсальный магазин автозапчастей")
            put(Columns.AUTHOR_ID, 2)
            put(Columns.CONTENT, "Добро пожаловать в наш универсальный магазин автозапчастей! У нас широкий ассортимент оригинальных запчастей и аналогов для всех марок автомобилей. Мы предлагаем запчасти для двигателей, ходовой части, тормозной системы, электрооборудования и многое другое. Наши консультанты готовы помочь вам подобрать нужные детали и аксессуары для вашего автомобиля.")
            put(Columns.PUBLISHED, "21 мая в 18:36")
            put(Columns.LIKED_BY_ME, 0)
            put(Columns.LIKES, 999)
            put(Columns.SHARES, 25)
            put(Columns.VIEWS, 5700)
            putNull(Columns.VIDEO)
        }
        db.insert(PostContract.TABLE_NAME, null, contentValues)

        // Второй пост с видео
        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автозапчасти. Универсальный магазин автозапчастей")
            put(Columns.AUTHOR_ID, 2)
            put(Columns.CONTENT, "\uD83D\uDD25 Только до конца мая: скидка 20% на масла и фильтры всех известных брендов! Успейте подготовить авто к летнему сезону по выгодной цене. Ждём вас в нашем магазине или заказывайте онлайн с доставкой!")
            put(Columns.PUBLISHED, "22 мая в 10:15")
            put(Columns.LIKED_BY_ME, 0)
            put(Columns.LIKES, 1245)
            put(Columns.SHARES, 189)
            put(Columns.VIEWS, 8300)
            put(Columns.VIDEO, "https://rutube.ru/video/4636892a7d0e33249db80955af71071e")
            db.insert(PostContract.TABLE_NAME, null, this)
        }

        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автосервис 'Мастер'")
            put(Columns.AUTHOR_ID, 3)
            put(Columns.CONTENT, "⚠️ Заметили, что машину стало уводить в сторону при торможении? Пора проверить тормозные колодки и диски! Проведём бесплатную диагностику тормозной системы при заказе ремонта. Записывайтесь по телефону или через сообщения группы.")
            put(Columns.PUBLISHED, "22 мая в 14:40")
            put(Columns.LIKED_BY_ME, 1)
            put(Columns.LIKES, 567)
            put(Columns.SHARES, 45)
            put(Columns.VIEWS, 4120)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }

        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автозапчасти. Универсальный магазин автозапчастей")
            put(Columns.AUTHOR_ID, 2)
            put(Columns.CONTENT, "\uD83D\uDE97 Поступление новых аналогов ходовой части для корейских и японских автомобилей! Высокое качество по доступным ценам. Срок службы не уступает оригиналу. Подбор по VIN-коду в комментариях или в личных сообщениях.")
            put(Columns.PUBLISHED, "23 мая в 09:30")
            put(Columns.LIKED_BY_ME, 0)
            put(Columns.LIKES, 2300)
            put(Columns.SHARES, 310)
            put(Columns.VIEWS, 15400)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }
        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автоблогер 'За рулём'")
            put(Columns.AUTHOR_ID, 4)
            put(Columns.CONTENT, "\uD83D\uDCE2 Друзья, сегодня разберём, как выбрать моторное масло: синтетика, полусинтетика или минералка? Какая вязкость подходит для вашего региона? Полный гид в моём новом видео. Ссылка в профиле. А вы какое масло заливаете?")
            put(Columns.PUBLISHED, "24 мая в 11:20")
            put(Columns.LIKED_BY_ME, 0)
            put(Columns.LIKES, 3420)
            put(Columns.SHARES, 890)
            put(Columns.VIEWS, 27800)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }
        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автозапчасти. Универсальный магазин автозапчастей")
            put(Columns.AUTHOR_ID, 2)
            put(Columns.CONTENT, "\uD83C\uDF1F Акция для такси и каршеринга! Оптовые цены на расходные материалы: масла, фильтры, свечи, тормозные колодки. Работаем с юридическими лицами и ИП. Подробности у менеджеров.")
            put(Columns.PUBLISHED, "25 мая в 16:45")
            put(Columns.LIKED_BY_ME, 1)
            put(Columns.LIKES, 987)
            put(Columns.SHARES, 156)
            put(Columns.VIEWS, 7100)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }
        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Станция техобслуживания 'Колесо'")
            put(Columns.AUTHOR_ID, 5)
            put(Columns.CONTENT, "⚙\uFE0F Начинается сезон отпусков! Проверьте ходовую, шины и кондиционер перед дальней поездкой. Комплексная диагностика всего за 1000 рублей. Приезжайте, сделаем ваш автомобиль безопасным и надёжным.")
            put(Columns.PUBLISHED, "26 мая в 12:10")
            put(Columns.LIKED_BY_ME, 0)
            put(Columns.LIKES, 876)
            put(Columns.SHARES, 98)
            put(Columns.VIEWS, 5400)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }
        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автозапчасти. Универсальный магазин автозапчастей")
            put(Columns.AUTHOR_ID, 2)
            put(Columns.CONTENT, "\uD83C\uDF81 Розыгрыш! Участвуйте в конкурсе: подпишитесь на нас, поставьте лайк этому посту и отметьте двух друзей. Главный приз — набор инструментов для авто. Итоги подведём 5 июня.")
            put(Columns.PUBLISHED, "27 мая в 18:00")
            put(Columns.LIKED_BY_ME, 0)
            put(Columns.LIKES, 5210)
            put(Columns.SHARES, 1240)
            put(Columns.VIEWS, 34600)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }
        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автоэлектрик Дмитрий")
            put(Columns.AUTHOR_ID, 6)
            put(Columns.CONTENT, "\uD83D\uDD0B Как понять, что аккумулятор пора менять? Рассказываю основные признаки: машина плохо заводится, свет тускнеет, напряжение ниже 12,5 В. Приезжайте на бесплатную проверку аккумулятора в наш сервис. Запись по телефону.")
            put(Columns.PUBLISHED, "28 мая в 09:05")
            put(Columns.LIKED_BY_ME, 1)
            put(Columns.LIKES, 2140)
            put(Columns.SHARES, 320)
            put(Columns.VIEWS, 16900)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }
        android.content.ContentValues().apply {
            put(Columns.AUTHOR, "Автозапчасти. Универсальный магазин автозапчастей")
            put(Columns.AUTHOR_ID, 2)
            put(Columns.CONTENT, "\uD83D\uDCE6 Доставка автозапчастей по всему городу и области! Оформите заказ на сайте или по телефону, и курьер привезёт детали в удобное для вас время. Оплата при получении. Экономьте своё время с нами!")
            put(Columns.PUBLISHED, "30 мая в 14:50")
            put(Columns.LIKED_BY_ME, 0)
            put(Columns.LIKES, 1567)
            put(Columns.SHARES, 203)
            put(Columns.VIEWS, 11200)
            putNull(Columns.VIDEO)
            db.insert(PostContract.TABLE_NAME, null, this)
        }
    }
}
