package ru.uteev.Gelios.service;

import org.springframework.stereotype.Component;
import ru.uteev.Gelios.model.Document;
import ru.uteev.Gelios.model.Statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticService {
    private List<Document> documentList;

    public static Statistics STAT_ZERO = new Statistics(0, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));

    public void getData(Collection<Document> collection) {
        documentList = new ArrayList<>(collection);
    }

    public Statistics getStatistic(int clientId, int productId) {
        if (documentList != null) {//защита от самого первого запроса для статистики
            List<BigDecimal> bigDecimals = new ArrayList<>();
            System.out.println("getStatistic = " + clientId + ", " + productId);
            if ((clientId > 0) || (productId > 0)) {//фильтр есть
                for (Document doc : documentList) {
                    if (doc.getClientId() == clientId && doc.getProductId() == productId) {
                        bigDecimals.add(doc.getAmount());
                    }
                }
            } else {//фильтра нет
                for (Document doc : documentList) {
                    bigDecimals.add(doc.getAmount());
                }
            }
            if (bigDecimals.size() > 0) {
                BigDecimal sum = bigDecimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal min = bigDecimals.stream().min(BigDecimal::compareTo).get();
                BigDecimal max = bigDecimals.stream().max(BigDecimal::compareTo).get();
                int count = documentList.size();
                return new Statistics(count, min, max, sum);
            } else {
                return STAT_ZERO;
            }
        }
        return STAT_ZERO;
    }
}
