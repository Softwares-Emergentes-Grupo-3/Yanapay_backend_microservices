package agronova.yanapay.greenhouses.interfaces.getAllGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;

import java.time.LocalDate;
import java.util.List;

public class GetAllGreenhouseResponse {
    public List<GetAllGreenhouseDTO> data;

    public GetAllGreenhouseResponse(List<Greenhouse> greenhouses) {
        this.data = greenhouses.stream()
                .map(x ->
                        new GetAllGreenhouseDTO(
                                x.getId(),
                                x.getName(),
                                x.getPlantingDate()
                        )
                )
                .toList();
    }
}

record GetAllGreenhouseDTO(
    Long id,
    String name,
    LocalDate plantingDate
){
}
