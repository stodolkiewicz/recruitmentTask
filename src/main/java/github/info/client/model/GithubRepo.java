package github.info.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@EqualsAndHashCode
public class GithubRepo {
    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private LocalDate createdAt;

}
