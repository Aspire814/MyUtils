package lombok.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class GameInfo {
    private String game_name;
    private Integer platform;

}
