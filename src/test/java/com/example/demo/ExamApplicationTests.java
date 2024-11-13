package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.BookStoreDTO;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BookStoreRepository;

@SpringBootTest
class ExamApplicationTests {
	
	@Autowired
	private BookStoreRepository bookStoreRepository;
	@Autowired
	private BookRepository bookRepository;

	@Test
	void t1() {
		bookStoreRepository.truncateTable();
		bookRepository.truncateTable();
	}


	// 서점 두개를 만들어주세요.
	// 서점은 각각 이름과 보유서적으로 구성됩니다.
	// 각각 코리아서점, 아이티문고
	@Test
	void t2() {
		bookStoreRepository.makeStore("코리아서점");
		bookStoreRepository.makeStore("아이티문고");
	}
	
	
	// 모든 서점 정보 조회
	// 출력 : 코리아서점, 아이티문고
	@Test
	void t3() {
		List<BookStoreDTO> stores = bookStoreRepository.findAllStoreName();
		System.out.println("=== 모든 서점 정보 조회 결과 ===");
		for(int i = 0; i < stores.size(); i++) {
			BookStoreDTO store = stores.get(i);
			System.out.printf("%d: %s\n", i + 1, store.getName());
		}
		System.out.println();
	}
	

	// 아이티문고의 이름을 IT문고로 변경해주세요.
	// 변경 후엔 t3() 메서드를 실행해 확인해주세요.
	// 출력 : 코리아서점, IT문고
	@Test
	void t4() {
		bookStoreRepository.updateStoreName("아이티문고", "IT문고");
		t3();
	}
	
	 
	// 책 5권을 만들어주세요.
	// 책은 제목과 저자, 보유 서점으로 구성됩니다.
	// 책은 각각
	// 어린왕자, 생떽쥐페리, 코리아서점
	// 해리포터, 조앤 롤링, 코리아서점
	// 죄와벌, 도스토예프스키, 코리아서점
	// 점프 투 스프링부트, 박응용, 아이티문고
	// 수학의 정석, 홍성대, 아이티문고
	// 로 만들어주세요.
	@Test
	void t5() {
		int koreaStoreId = bookStoreRepository.findStoreIdByName("코리아서점");
		int itStoreId = bookStoreRepository.findStoreIdByName("IT문고");
		
		bookRepository.makeBook("어린왕자", "생떽쥐페리", koreaStoreId);
		bookRepository.makeBook("해리포터", "조앤 롤링", koreaStoreId);
		bookRepository.makeBook("죄와벌", "도스토예프스키", koreaStoreId);
		bookRepository.makeBook("점프 투 스프링부트", "박응용", itStoreId);
		bookRepository.makeBook("수학의 정석", "홍성대", itStoreId);
	}
	
	 
	// 모든 책의 제목을 출력해주세요.
	@Test
	void t6() {
		List<BookDTO> books = bookRepository.findAllBookTitle();
		System.out.println("=== 모든 책 제목 조회 결과 ===");
		for(int i = 0; i < books.size(); i++) {
			BookDTO book = books.get(i);
			System.out.printf("%d: %s\n", i + 1, book.getTitle());
		}
		System.out.println();
	}
	
	 
	// 코리아서점에서 판매하는 책의 제목을 출력해주세요.
	@Test
	void t7() {
		List<BookDTO> books = bookRepository.findBookTitlesByStoreName("코리아서점");
		System.out.println("=== 해당하는곳에서 판매하는 책 제목 조회 결과 ===");
		for(int i = 0; i < books.size(); i++) {
			BookDTO book = books.get(i);
			System.out.printf("%d: %s\n", i + 1, book.getTitle());
		}
		System.out.println();
	}
	
	 
	// 박응용이 쓴 책을 삭제해주세요.
	// 삭제 후 t6 메서드를 이용해 확인해주세요.
	@Test
	void t8() {
		bookRepository.deleteBookByAuthor("박응용");
		t6();
	}

}
