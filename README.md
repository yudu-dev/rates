# rates

Данное приложение обращается к сервису курсов валют, и отображает GIF-файл с сайта www.giphy.com:
- если курс RUB по отношению к USD за сегодня стал выше вчерашнего, отображается рандомный GIF-файл отсюда https://giphy.com/search/rich
- если ниже - отсюда https://giphy.com/search/broke


## Запуск приложения

1. Очистить и собрать каталог сборки `./gradlew clean build`
2. Собрать docker образ `docker build -t rates:latest .`
3. Запустить приложение из docker образа `docker run -p 9090:9090 rates -n rates`
