package org.zerock.b3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.b3.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    //JpaRepository<> 내부에 이미 CRUD 가 잡혀있다네
    // 그 외에도 많다네 ㅎ허ㅓㅓ허허허
    //인터페이스만 잇고 구현체가 없네??
    //mybatis에서도 구현체 없이 interface썻지? 똑같다네

    List<Board> findByTitleContaining(String keyword);

    Page<Board> findByTitleContaining(String keyword, Pageable pageable);
    //파라메터 끝에 pageable 넣고 return을 Page로 받으면 페이징 처리도 됨
}