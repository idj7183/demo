package com.example.demo;

import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void 메인페이지_로딩(){
        //when
        String body = this.testRestTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
    }
    
    @Test
    public void 게시글_삽입(){
        //given
        String title = "title";
        String author = "author";
        String content = "content";

        PostsSaveRequestDto saveRequestDto = PostsSaveRequestDto.builder().title(title)
                .author(author).content(content).build();


    }
    
    @Test
    public void 전체조회(){
        //given
        String title = "title";
        String author = "author";
        String content = "content";

        PostsSaveRequestDto saveRequestDto = PostsSaveRequestDto.builder().title(title)
                .author(author).content(content).build();

        //when
        postsRepository.save(saveRequestDto.toEntity());
        String body = this.testRestTemplate.getForObject("/", String.class);

        //then
        System.out.println(body);
        assertThat(body).contains(title);
    }
    
    @Test
    public void 삭제(){

    }

    @Test
    public void 변경(){

    }
}