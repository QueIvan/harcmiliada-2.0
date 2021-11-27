package queivan.harcmiliada.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.harcmiliada.domain.Group;
import queivan.harcmiliada.domain.GroupDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {
    Group mapToGroup(GroupDto groupDto);

    GroupDto mapToGroupDto(Group group);

    List<GroupDto> mapToGroupDtoList(List<Group> groups);
}
