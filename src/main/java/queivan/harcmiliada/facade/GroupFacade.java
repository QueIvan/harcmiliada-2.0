package queivan.harcmiliada.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.harcmiliada.domain.Group;
import queivan.harcmiliada.domain.GroupDto;
import queivan.harcmiliada.domain.enums.GroupActions;
import queivan.harcmiliada.service.GroupService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GroupFacade {
    private final GroupService service;

    public List<GroupDto> getAllUsingUserId(String userId) {
        return service.getAllUsingUserId(userId);
    }

    public GroupDto createGroup(GroupDto groupDto, String userId) {
        return service.createGroup(groupDto, userId);
    }

    public GroupDto updateGroup(GroupDto groupDto, String userId) {
        return service.updateGroup(groupDto, userId);
    }

    public void deleteGroup(UUID groupId, String userId) {
        service.deleteGroup(groupId, userId);
    }
}
