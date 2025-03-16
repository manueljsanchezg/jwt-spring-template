package manugtx.jwtexample.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {

    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    @Min(0)
    private Double price;
    @NotNull
    @Min(0)
    private Integer quantity;
}
