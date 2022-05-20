package org.zerock.b3.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b3.entity.Board;

public interface BoardSearch {

    void search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);


}
