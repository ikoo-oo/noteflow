package com.note.site;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/notes") @CrossOrigin
public class NoteController {
    @Autowired NoteRepository repo;

    @GetMapping           public List<Note> getAll() { return repo.findAll(); }
    @PostMapping          public Note create(@RequestBody Note note) { return repo.save(note); }
    @PutMapping("/{id}")  public Note update(@PathVariable Long id, @RequestBody Note note) {
        note.setId(id); return repo.save(note);
    }
    @PatchMapping("/{id}") public Note partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Note note = repo.findById(id).orElseThrow();
        if (updates.containsKey("pinned")) note.setPinned((Boolean) updates.get("pinned"));
        return repo.save(note);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { repo.deleteById(id); }
}