package ru.uteev.Gelios.repository;

import ru.uteev.Gelios.model.Document;

import java.util.List;

public interface DocumentService {
    /**
     * Создает новый документ
     */
    void create(Document doc);

    /**
     * Возвращает список всех документов
     * @return список документов
     */
    List<Document> readAll();

    /**
     * Возвращает документ по его ID
     * @param id - ID документа
     * @return - документ с заданным ID
     */
    Document read(int id);

    /**
     * Обновляет документ с заданным ID,
     * в соответствии с переданным документом
     * @param doc - документ в соответсвии с которым нужно обновить данные
     * @param id - id документа которого нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Document doc, int id);

    /**
     * Удаляет документ с заданным ID
     * @param id - id документа, которого нужно удалить
     * @return - true если герой был удален, иначе false
     */
    boolean delete(int id);

    /**
     * Возвращает отфильтрованный список всех документов
     * @return список документов
     */
    List<Document> readByFilter(int clientId, int productId);

}

