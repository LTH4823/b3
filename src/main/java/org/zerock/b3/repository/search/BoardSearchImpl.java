package org.zerock.b3.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b3.entity.Board;
import org.zerock.b3.entity.QBoard;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public void search1(Pageable pageable) {

    log.info("search1..................");

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        getQuerydsl().applyPagination(pageable, query);

        query.fetchCount();

        query.fetch();



    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board>query = from(board);

        if (types != null){
            BooleanBuilder booleanBuilder =new BooleanBuilder();

            for (String type:types) {
                if (type.equals("t")){
                    booleanBuilder.or(board.title.contains(keyword));
                }else if (type.equals("c")){
                    booleanBuilder.or(board.content.contains(keyword));
                }else if (type.equals("w")){
                    booleanBuilder.or(board.writer.contains(keyword));
                }
            } // end for
            query.where(booleanBuilder);
        }// end if
        query.where(board.bno.gt(0));

        //페이징 처리
        getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;

    }
}