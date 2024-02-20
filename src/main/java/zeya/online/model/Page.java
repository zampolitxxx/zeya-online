package zeya.online.model;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Page {
    private String slug;
    private String name;
    private String body;
}
