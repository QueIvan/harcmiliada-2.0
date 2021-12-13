package queivan.harcmiliada.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.harcmiliada.domain.Group;
import queivan.harcmiliada.domain.GroupDto;
import queivan.harcmiliada.domain.enums.GroupActions;
import queivan.harcmiliada.facade.GroupFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "https://www.harcmiliada.pl/")
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupFacade facade;

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public List<GroupDto> getAllUsingUserId(@PathVariable("userId") String userId) {
        return facade.getAllUsingUserId(userId);
    }

    @PostMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GroupDto createNewGroup(@RequestBody GroupDto groupDto,@PathVariable("userId") String userId) {
        return facade.createGroup(groupDto, userId);
    }

    @PutMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto, @PathVariable("userId") String userId) {
        return facade.updateGroup(groupDto, userId);
    }

    @DeleteMapping(value = "/{groupId}/{userId}")
    public void deleteGroup(@PathVariable("groupId") UUID groupId, @PathVariable("userId") String userId) {
        facade.deleteGroup(groupId, userId);
    }

}
