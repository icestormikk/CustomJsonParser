# Simple Json parser
Небольшая программа для парсинга json-файлов и преобразования их в двумерный список особого формата, написанная на **Java**. В качестве аргумента программа принимает путь к json-файлу, содержащему необходимую структуру.

Парсинг файла происходит посимвольного с помощью класса PushbackReader в совокупности с BufferedReader. Все парсеры покрыты тестами, дла написания которых использовался фреймворк **JUnit**. 

Преобразование json структуры происходит в двумерный список формата: [название_марки_машины, название_модели, название_комплектации]. На экран выводится список всех возможных таких комбинаций.

#### Примечание
Объект, возвращённый методом **parse()** класса **JsonParser.java**, может быть преобразован в любую другую структуру, описание которой будет в программе (путём добавления новых классов и определения конвертёров в классе **JsonConverters.java**).

### Запуск программы 
Для запуска парсера необходимо иметь установленную на устройстве **Java** и **JDK >= 21**. Для начала работы парсера введите команду:
```shell
java -jar path/to/CustomJsonParser-1.0.jar path/to/target-file.json
```

### Пример работы
Для структуры следующего вида:
```text
[
  {
    "brand": "lada",
    "models": [
      {
        "name": "vesta",
        "trims": [
          {
            "name": "базовая"
          },
          {
            "name": "средняя"
          }
        ]
      },
      {
        "name": "x-ray",
        "trims": [
          {
            "name": "максимальная"
          }
        ]
      }
    ]
  },
  {
    "brand": "kia",
    "models": [
      {
        "name": "rio",
        "trims": [
          {
            "name": "люкс"
          }
        ]
      },
      {
        "name": "sportage",
        "trims": [
          {
            "name": "люкс"
          },
          {
            "name": "супер-люкс"
          }
        ]
      },
      {
        "name": "sorento",
        "trims": [
          {
            "name": "люкс"
          }
        ]
      }
    ]
  }
]
```
Будет выведен следующий двумерный массив:
```text
[lada, vesta, базовая]
[lada, vesta, средняя]
[lada, x-ray, максимальная]
[kia, rio, люкс]
[kia, sportage, люкс]
[kia, sportage, супер-люкс]
[kia, sorento, люкс]
```