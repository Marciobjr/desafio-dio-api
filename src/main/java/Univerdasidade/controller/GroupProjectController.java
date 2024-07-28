package Univerdasidade.controller;

import Univerdasidade.model.GroupProject;
import Univerdasidade.repository.GroupProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group_projects")
public class GroupProjectController {

    @Autowired
    private GroupProjectRepository groupProjectRepository;

    @GetMapping
    public List<GroupProject> getAllGroupProjects() {
        return groupProjectRepository.findAll();
    }

    @PostMapping
    public GroupProject createGroupProject(@RequestBody GroupProject groupProject) {
        return groupProjectRepository.save(groupProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupProject> updateGroupProject(@PathVariable Long id, @RequestBody GroupProject groupProjectDetails) {
        return groupProjectRepository.findById(id).map(groupProject -> {
            groupProject.setTitle(groupProjectDetails.getTitle());
            groupProject.setMembers(groupProjectDetails.getMembers());
            groupProject.setDeadline(groupProjectDetails.getDeadline());
            groupProject.setStatus(groupProjectDetails.getStatus());
            GroupProject updatedGroupProject = groupProjectRepository.save(groupProject);
            return ResponseEntity.ok(updatedGroupProject);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroupProject(@PathVariable Long id) {
        return groupProjectRepository.findById(id).map(groupProject -> {
            groupProjectRepository.delete(groupProject);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

