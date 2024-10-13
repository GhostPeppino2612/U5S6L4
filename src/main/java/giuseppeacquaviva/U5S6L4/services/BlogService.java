package giuseppeacquaviva.U5S6L4.services;

import giuseppeacquaviva.U5S6L4.entities.Author;
import giuseppeacquaviva.U5S6L4.entities.BlogPost;
import giuseppeacquaviva.U5S6L4.exceptions.NotFoundException;
import giuseppeacquaviva.U5S6L4.payloads.NewAuthorDTO;
import giuseppeacquaviva.U5S6L4.payloads.NewBlogDTO;
import giuseppeacquaviva.U5S6L4.payloads.NewBlogPost;
import giuseppeacquaviva.U5S6L4.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AuthorService authorsService;

    public BlogPost save(NewBlogDTO body) {
        Author autore = authorsService.findById(body.authorId());
        BlogPost blog = new BlogPost();
        blog.setAutore(autore);
        blog.setTempoDiLettura(body.tempoDiLettura());
        blog.setCategoria(body.categoria());
        blog.setTitolo(body.titolo());
        blog.setCover("http://picsum.photos/200/300");
        blog.setContent(body.content());
        return blogPostRepository.save(blog);
    }

    public List<BlogPost> getBlogs() {
        return blogPostRepository.findAll();
    }

    public BlogPost findById(long id) {
        return blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        BlogPost found = this.findById(id);
        blogPostRepository.delete(found);
    }

    public BlogPost findByIdAndUpdate(long id, NewBlogPost body) {
        BlogPost found = this.findById(id);

        if(found.getAutore().getId() != body.getAuthorId()) {
            Author newAuthor = authorsService.findById(body.getAuthorId());
            found.setAutore(newAuthor);
        }

        found.setCategoria(body.getCategory());
        found.setTitolo(body.getTitle());
        found.setTempoDiLettura(body.getReadingTime());

        return blogPostRepository.save(found);
    }

    public List<BlogPost> findByAuthor(long id) {
        Author author = authorsService.findById(id);
        return blogPostRepository.findByAuthor(author);
    }
}
