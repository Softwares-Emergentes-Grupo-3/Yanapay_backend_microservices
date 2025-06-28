package agronova.yanapay.greenhouses.interfaces.getGreenhousesByUser;

import agronova.yanapay.greenhouses.domain.services.getGreenhousesByUser.GetGreenhousesByUserIdQuery;
import agronova.yanapay.greenhouses.domain.services.getGreenhousesByUser.IGetGreenhousesByUserIdQueryHandler;
import agronova.yanapay.greenhouses.interfaces.GreenhouseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetGreenhouseByUserIdController extends GreenhouseController {
    private final IGetGreenhousesByUserIdQueryHandler getGreenhousesByUserIdQueryHandler;

    public GetGreenhouseByUserIdController(IGetGreenhousesByUserIdQueryHandler getGreenhousesByUserIdQueryHandler) {
        this.getGreenhousesByUserIdQueryHandler = getGreenhousesByUserIdQueryHandler;
    }

    @GetMapping("by-user/{userId}")
    public ResponseEntity<GetGreenhousesByUserIdResponse> getGreenhousesByUserId(@PathVariable("userId") Long userId) {
        var getGreenhousesByUserIdQuery = new GetGreenhousesByUserIdQuery(userId);

        var result = getGreenhousesByUserIdQueryHandler.handle(getGreenhousesByUserIdQuery);

        var response = new GetGreenhousesByUserIdResponse(result);

        return ResponseEntity.ok(response);
    }
}
