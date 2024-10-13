package giuseppeacquaviva.U5S6L4.controllers;

import giuseppeacquaviva.U5S6L4.entities.Author;
import giuseppeacquaviva.U5S6L4.exceptions.BadRequestException;
import giuseppeacquaviva.U5S6L4.services.AuthorService;
import giuseppeacquaviva.U5S6L4.payloads.NewAuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size,
                                   @RequestParam(defaultValue = "name") String sortBy) {
        return authorService.getAuthors(page, size, sortBy);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable long authorId) {
        return authorService.findById(authorId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewAuthorDTO saveAuthor(@RequestBody @Validated NewAuthorDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Author savedAuthor = authorService.save(body);
        return new NewAuthorDTO(savedAuthor.getNome(), savedAuthor.getCognome(), savedAuthor.getEmail(), savedAuthor.getDataDiNascita());
    }

    @PutMapping("/{authorId}")
    public Author findByIdAndUpdate(@PathVariable long authorId, @RequestBody Author body) {
        return authorService.findByIdAndUpdate(authorId, body);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable long authorId) {
        authorService.findByIdAndDelete(authorId);
    }

    @PostMapping("{authorId}/avatar")
    public Author uploadAvatar(@PathVariable long authorId, @RequestParam("avatar") MultipartFile file) {
        try {
            return authorService.uploadAvatar(authorId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
