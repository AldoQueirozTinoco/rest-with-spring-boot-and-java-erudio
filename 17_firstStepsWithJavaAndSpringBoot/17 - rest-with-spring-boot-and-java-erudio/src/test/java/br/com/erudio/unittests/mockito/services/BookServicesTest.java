package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;
	
	@InjectMocks
	private BookServices service; //O Teste acessa um mock do repositório por conta do Inject
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();
		
		
		when(repository.findAll()).thenReturn(list);
		//Quando o repository.findById ele não vai ao banco, e sim retorna um mock
		
		var book = service.findAll();
		
		assertNotNull(book);
		
		assertEquals(14, book.size());
		
		
		var BookOne = book.get(1);
		
		assertNotNull(BookOne);
		assertNotNull(BookOne.getKey());
		assertNotNull(BookOne.getLinks());
		
		assertTrue(BookOne.toString().contains("links: [</api/Book/v1/1>;rel=\"self\"]"));
		assertEquals("Author test1",BookOne.getAuthor());
		assertEquals("Title test1",BookOne.getTitle());
		assertEquals(25F,BookOne.getPrice());
		assertNotNull(BookOne.getLaunchDate());//Não dá pra ter certeza da data, então só verifica se ela é não nula
		
		var BookFour = book.get(4);
		
		assertNotNull(BookFour);
		assertNotNull(BookFour.getKey());
		assertNotNull(BookFour.getLinks());
		
		assertTrue(BookFour.toString().contains("links: [</api/Book/v1/4>;rel=\"self\"]"));
		assertEquals("Author test4",BookFour.getAuthor());
		assertEquals("Title test4",BookFour.getTitle());
		assertEquals(25F,BookFour.getPrice());
		assertNotNull(BookFour.getLaunchDate());
		
		var BookSeven = book.get(7);
		
		assertNotNull(BookSeven);
		assertNotNull(BookSeven.getKey());
		assertNotNull(BookSeven.getLinks());
		
		assertTrue(BookSeven.toString().contains("links: [</api/Book/v1/7>;rel=\"self\"]"));
		assertEquals("Author test7",BookSeven.getAuthor());
		assertEquals("Title test7",BookSeven.getTitle());
		assertEquals(25F,BookSeven.getPrice());
		assertNotNull(BookSeven.getLaunchDate());
	}
	

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		//Quando o repository.findById ele não vai ao banco, e sim retorna um mock
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/Book/v1/1>;rel=\"self\"]"));
		assertEquals("Author test1",result.getAuthor());
		assertEquals("Title test1",result.getTitle());
		assertEquals(25F,result.getPrice());
		assertNotNull(result.getLaunchDate());
	}

	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		//Quando salva o mockito intercepta e devolve a entidade mockada
		//Tradução: Quando repository save então retorne persisted(entidade mockada)
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/Book/v1/1>;rel=\"self\"]"));
		assertEquals("Author test1",result.getAuthor());
		assertEquals("Title test1",result.getTitle());
		assertEquals(25F,result.getPrice());
		assertNotNull(result.getLaunchDate());
	}
	
	@Test
	void testCreateWithNullBook() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class,()->{
			service.create(null);
			});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	
	}

	@Test
	void testUpdate() {
Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		//Quando salva o mockito intercepta e devolve a entidade mockada
		//Tradução: Quando repository save então retorne persisted(entidade mockada)
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/Book/v1/1>;rel=\"self\"]"));
		assertEquals("Author test1",result.getAuthor());
		assertEquals("Title test1",result.getTitle());
		assertEquals(25F,result.getPrice());
		assertNotNull(result.getLaunchDate());
	}
	
	@Test
	void testUpdateWithNullBook() {
		
		Exception exception = assertThrows(RequiredObjectIsNullException.class,()->{
			service.update(null);
			});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	
	}

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		//Quando o repository.findById ele não vai ao banco, e sim retorna um mock
		
		service.delete(1L);
	}

}
