package giuseppeacquaviva.U5S6L4.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuseppeacquaviva.U5S6L4.entities.Author;
import giuseppeacquaviva.U5S6L4.exceptions.BadRequestException;
import giuseppeacquaviva.U5S6L4.exceptions.NotFoundException;
import giuseppeacquaviva.U5S6L4.payloads.NewAuthorDTO;
import giuseppeacquaviva.U5S6L4.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AuthorService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(NewAuthorDTO body) {
        authorRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
        });
        Author newAuthor = new Author();
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        newAuthor.setNome(body.nome());
        newAuthor.setCognome(body.cognome());
        newAuthor.setEmail(body.email());
        newAuthor.setDataDiNascita(body.dataDiNascita());

        return authorRepository.save(newAuthor);
    }

    public Page<Author> getAuthors(int page, int size, String sort) {
        return authorRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }

    public Author findById(long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Author found = this.findById(id);
        authorRepository.delete(found);
    }

    public Author findByIdAndUpdate(long id, Author body) {

        Author found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setDataDiNascita(body.getDataDiNascita());
        found.setAvatar(body.getAvatar());
        return authorRepository.save(found);
    }

    public Author uploadAvatar(long id, MultipartFile file) throws IOException {
        Author found = this.findById(id);
        String avatarUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(avatarUrl);
        return authorRepository.save(found);
    }
}
