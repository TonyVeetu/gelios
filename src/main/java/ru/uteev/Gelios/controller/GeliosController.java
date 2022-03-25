package ru.uteev.Gelios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.uteev.Gelios.model.Document;
import ru.uteev.Gelios.model.Statistics;
import ru.uteev.Gelios.service.DocumentServiceImpl;
import ru.uteev.Gelios.service.StatisticService;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class GeliosController {

    @Autowired
    private DocumentServiceImpl documentService;

    @Autowired
    private StatisticService statisticService;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);

    @PostMapping(value="/agreements")
    public ResponseEntity<?> create(
            @RequestParam("clientId") int clientId,
            @RequestParam("productId") int productId,
            @RequestParam("amount") BigDecimal amount,
            @RequestParam("startDate") String start) throws ParseException {
        Date dateStart = formatter.parse(start);
        Document document = new Document(clientId, productId, amount, dateStart);
        documentService.create(document);

        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @GetMapping(value = "/agreements/{id}")
    public ResponseEntity<Document> read(@PathVariable(name = "id") int id)  {
        final Document doc = documentService.read(id);

        return doc != null
                ? new ResponseEntity<>(doc, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/agreements")
    public ResponseEntity<List<Document>> readAll(
            @RequestParam(name = "clientId", required = false) String clientId,
            @RequestParam(name = "productId", required = false) String productId)  {

        List<Document> list;
        if (clientId == null || productId == null) {
            list = documentService.readAll();
        } else {
            int clientIdInt = Integer.parseInt(clientId);
            int productIdInt = Integer.parseInt(productId);
            list = documentService.readByFilter(clientIdInt, productIdInt);
        }
        return list != null &&  !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/agreements/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = documentService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/statistics")
    public ResponseEntity<Statistics> statisticFilter
            (@RequestParam(name = "clientId", required = false) String clientId,
             @RequestParam(name = "productId", required = false) String productId) {
        Statistics stat;
        if (clientId != null && productId != null) {
            int clientIdInt = Integer.parseInt(clientId);
            int productIdInt = Integer.parseInt(productId);
            statisticService.getData(documentService.setData());
            stat = statisticService.getStatistic(clientIdInt, productIdInt);
        } else {
            stat = statisticService.getStatistic(0, 0);
        }
        return stat != null
            ? new ResponseEntity<>(stat, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
