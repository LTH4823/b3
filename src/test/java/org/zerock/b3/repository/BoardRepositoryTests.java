package org.zerock.b3.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b3.entity.Board;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
//스프링과 다르게 일단 이것만 추가하면됨
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository repository;
    //지금 구현체가 없엇지만 boot에서 만든듯


    @Test
    public void testSearchAll(){
        String[] types = new String[]{"t","c","w"};
        String keyword = "5";

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        repository.searchAll(types,keyword,pageable);


    }




    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());

        repository.search1(pageable);
    }

    @Test
    public void testInsert(){

        log.info("--------------------");
        log.info("--------------------");
        log.info(repository);
        log.info("--------------------");

        IntStream.rangeClosed(1, 100).forEach(i->{

            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content..."+i)
                    .writer("user"+(i%10))
                    .build();

            repository.save(board);
        });

    }
    @Test
    public void testRead(){
        Integer bno = 100;
        Optional<Board> result = repository.findById(bno);
        //반환 타입이 신기하네 ,null point Excrption 안나오게 결과를 감싼다네
        Board board = result.orElseThrow();
        //만약 값이 null이면 알랴줘
        log.info(board);
    }

    @Test
    public void testUpdate(){

//        Board board = Board.builder()
//                .bno(100)
//                .title("100 Title")
//                .writer("user 11")
//                .build();
//
//        repository.save(board);

        Integer bno = 100;
        Optional<Board> result = repository.findById(bno);
        //반환 타입이 신기하네 ,null point Excrption 안나오게 결과를 감싼다네
        Board board = result.get();
        board.changeTitle("100Title...updated");
        board.changeContent("100Contnet..update");

        repository.save(board);

        log.info(board);
        //원본 복사하고
        // 수정할 내용만 수정하고
        //insert 처리한데 ,, 일단 조회가 필수고 복사해서 필요한것만

        //또는 iD만 같으면 같게 생각하니까 ~~
//
    }

    @Test
    public void testDelete(){
        //없는 번호 삭제 해볻자
        Integer bno = 100;
        repository.deleteById(bno);
        //DB와의 동기화를 목적이라 일단 조회후 작업한다
    }

    @Test
    public void testPage1(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
//        repository.findAll(pageable);
        Page<Board> result = repository.findAll(pageable);

        log.info("Total " +result.getTotalElements());
        log.info("TotalPages "+result.getTotalPages());
        log.info("Current: "+ result.getNumber());
        log.info("Size :" + result.getSize());

        result.getContent().forEach(board -> log.info(board));
        //뒤에 데이터 가 더 있다고 생각되면 count쿼리 날림 PageRequest.of(0,10)
        //안할때도 잇네
        //Sort.by("bno").descending() << desc 로 정렬
        //페이지는 0부터 시작한다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111

    }
    @Test
    public void testQueryMethod(){

        String keyword = "5";
        List<Board> list = repository.findByTitleContaining(keyword);
        //모든 메서드의 끝을 pageable 하고 리턴을 page로 받으면 다 페이지 처리함
        log.info(list);

    }

    @Test
    public void testQueryMethod2(){

        String keyword = "5";

        Pageable pageable = PageRequest.of(0,5,Sort.by("bno").descending());
        Page<Board> list = repository.findByTitleContaining(keyword,pageable);
        //모든 메서드의 끝을 pageable 하고 리턴을 page로 받으면 다 페이지 처리함
        //만약  가져올게 많으면 이름도 엄청 길어지겟지 [쓸일없다 ㅋ]
        log.info(list);

    }
}