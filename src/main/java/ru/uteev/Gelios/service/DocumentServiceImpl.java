package ru.uteev.Gelios.service;

import org.springframework.stereotype.Component;
import ru.uteev.Gelios.model.Document;
import ru.uteev.Gelios.repository.DocumentService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class DocumentServiceImpl implements DocumentService {

    private static final Map<Integer, Document> DOC_REPOSITORY_MAP = new ConcurrentHashMap<>();

    // Переменная для генерации ID
    private static final AtomicInteger DOC_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Document doc) {
        final int docId = DOC_ID_HOLDER.incrementAndGet();
        doc.setAgreementId(docId);
        DOC_REPOSITORY_MAP.put(docId, doc);
    }

    @Override
    public List<Document> readAll() {
        return new ArrayList<>(DOC_REPOSITORY_MAP.values());
    }

    @Override
    public List<Document> readByFilter(int clientId, int productId) {
        return DOC_REPOSITORY_MAP
            .values().stream()
                .filter(document -> document.getProductId() == productId)
                .filter(document -> document.getClientId() == clientId)
                .collect(Collectors.toList());
    }

    @Override
    public Document read(int id) {
        return DOC_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Document doc, int id) {
        if (DOC_REPOSITORY_MAP.containsKey(id)) {
            doc.setAgreementId(id);
            DOC_REPOSITORY_MAP.put(id, doc);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return DOC_REPOSITORY_MAP.remove(id) != null;
    }

    public Collection<Document> setData() {
        return DOC_REPOSITORY_MAP.values();
    }
}


