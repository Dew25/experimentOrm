# experimentOrm
Для работы с приложением необходимо создать пустую базу данных с именем "experiment_orm".
Для отображения русских букв в базе рекомендуется создать файл persistence.xml, где будет такая строка:
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/experiment_orm?useUnicode=true&amp;characterEncoding=utf8"/>
