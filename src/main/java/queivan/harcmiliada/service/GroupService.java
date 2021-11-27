package queivan.harcmiliada.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.harcmiliada.domain.Group;
import queivan.harcmiliada.domain.GroupDto;
import queivan.harcmiliada.domain.LogDto;
import queivan.harcmiliada.domain.enums.GroupActions;
import queivan.harcmiliada.domain.enums.LogType;
import queivan.harcmiliada.mapper.GroupMapper;
import queivan.harcmiliada.service.repository.GroupRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repository;
    private final GroupMapper mapper;
    private final LogService log;

    public List<GroupDto> getAllUsingUserId(String userId) {
        return mapper.mapToGroupDtoList(repository.findByUsersContains(userId));
    }

    public GroupDto createGroup(GroupDto groupDto, String userId) {
        Group group = repository.save(mapper.mapToGroup(groupDto));
        log.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Użytkownik o id %s stworzył grupę o id %s", userId, group.getId()))
                .type(LogType.INFO)
                .build());
        return mapper.mapToGroupDto(group);
    }

    public GroupDto updateGroup(GroupDto groupDto, String userId) {
        doesGroupExist(groupDto.getId());
        Group group = repository.save(mapper.mapToGroup(groupDto));
        log.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Użytkownik o id %s zaktualizował grupę o id %s", userId, group.getId()))
                .type(LogType.INFO)
                .build());
        if(group.getUsers().isEmpty()) {
            deleteGroup(group.getId(), userId);
            return GroupDto.builder().build();
        }else{
            return mapper.mapToGroupDto(group);
        }
    }

    public void deleteGroup(UUID groupId, String userId) {
        doesGroupExist(groupId);
        repository.deleteById(groupId);
        log.log(LogDto.builder()
                .userId(userId)
                .message(String.format("Użytkownik o id %s usunął grupę o id %s", userId, groupId))
                .type(LogType.INFO)
                .build());
    }

    public void doesGroupExist(UUID groupId) {
        if (!repository.existsById(groupId)) {
            throw new RuntimeException("Nie ma takiej grupy");
        }
    }

}
