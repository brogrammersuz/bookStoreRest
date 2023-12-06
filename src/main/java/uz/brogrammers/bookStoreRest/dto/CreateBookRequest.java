package uz.brogrammers.bookStoreRest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBookRequest {

    @NonNull
    private String title;
    @NonNull
    private String isbn;
    @NonNull
    private Long publisherId;
    @NonNull
    private Set<Long> authorsIds = new HashSet<>();

}
