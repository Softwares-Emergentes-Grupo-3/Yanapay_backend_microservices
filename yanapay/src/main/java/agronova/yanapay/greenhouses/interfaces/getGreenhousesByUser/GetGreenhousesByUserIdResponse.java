package agronova.yanapay.greenhouses.interfaces.getGreenhousesByUser;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;

import java.util.List;

public class GetGreenhousesByUserIdResponse {

    public final List<GetGreenhousesByUserIdDTO> data;

    public GetGreenhousesByUserIdResponse(List<Greenhouse> greenhouses) {
        this.data = greenhouses.stream()
                .map(greenhouse -> new GetGreenhousesByUserIdDTO(
                        greenhouse.getId(),
                        greenhouse.getName(),
                        greenhouse.getPlantingDate().toString()
                ))
                .toList();
    }
}

record GetGreenhousesByUserIdDTO(
    Long id,
    String name,
    String plantingDate
) {
}
