package queivan.harcmiliada.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.harcmiliada.domain.Button;
import queivan.harcmiliada.domain.ButtonDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ButtonMapper {
    ButtonDto mapToButtonDto(Button byQrCode);
    Button mapToButton(ButtonDto buttonDto);
}
