package zeya.online.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zeya.online.model.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PageController {
    private List<Page> pages = new ArrayList<>();

    @Value("${myoption.key}")
    private String myValue;

    @GetMapping("/pages")
    public ResponseEntity<List<Page>> index(@RequestParam(defaultValue = "10") Integer limit) {
        List<Page> result = pages.stream().limit(limit).toList();
        return ResponseEntity.ok()
                .header("My Header", "lskdf")
                .header("My Header2", myValue)
                .body(result);
    }

    @PostMapping("/pages")
    public ResponseEntity<Page> create(@RequestBody Page page) {
        pages.add(page);
        return ResponseEntity.status(201)
                .header("Created", "CREATED_HEADER")
                .body(page);
    }

    @GetMapping("/pages/{id}")
    public ResponseEntity<Page> show(@RequestParam String id) {
        Optional<Page> page = pages.stream()
                .filter(x -> x.getSlug().equals(id))
                .findFirst();
        return ResponseEntity.of(page);
    }

    @DeleteMapping("/pages/{id}")
    public void delete(@PathVariable String id) {
        pages.removeIf(x -> x.getSlug().equals(id));
    }

    @PutMapping("/pages/{id}")
    public Page update(@PathVariable String id, @RequestBody Page data) {
        Optional<Page> maybePage = pages.stream()
                .filter(x -> x.getSlug().equals(id))
                .findFirst();
        if(maybePage.isPresent()) {
            Page page = maybePage.get();
            page.setSlug(data.getSlug());
            page.setBody(data.getBody());
            page.setName(data.getName());
        }
        return data;
    }
}
