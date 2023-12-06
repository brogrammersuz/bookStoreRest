package uz.brogrammers.bookStoreRest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse implements Serializable {

    private Long id;
    private String title;
    private String isbn;
    private Long publisherId;
    private Set<Long> authorsIds = new HashSet<>();

}
