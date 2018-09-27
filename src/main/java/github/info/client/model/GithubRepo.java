package github.info.client.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@EqualsAndHashCode
public class GithubRepo {
    private String fullName;
    @ApiModelProperty(value = "Description of a project can be null.")
    private String description;
    private String cloneUrl;
    private int stars;
    private LocalDateTime createdAt;
}
