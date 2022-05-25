package org.zerock.b3.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b3.dto.BoardListReplyCountDTO;
import org.zerock.b3.entity.Board;
import org.zerock.b3.entity.QBoard;
import org.zerock.b3.entity.QReply;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public void search1(Pageable pageable) {

    log.info("search1..................");

//        QBoard board = QBoard.board;
//
//        JPQLQuery<Board> query = from(board);
//
//        getQuerydsl().applyPagination(pageable, query);
//
//        query.fetchCount();
//
//        query.fetch();

        QBoard board = QBoard.board;
        QReply reply =QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));


        JPQLQuery<Tuple> query1 = query.select(board.bno, board.title,
                board.writer, reply.count());
        query1.groupBy(board);

        getQuerydsl().applyPagination(pageable,query1);

        List<Tuple> tupleList = query1.fetch();

        List<Object[]> arr =
                tupleList.stream().map(tuple ->
                        tuple.toArray()).collect(Collectors.toList());
//        long totalCount = query1.fetchCount();

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

        return new PageImpl<>(list,pageable,count);

    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply =QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        query.groupBy(board);

        JPQLQuery<BoardListReplyCountDTO> dtojpqlQuery =
                query.select(Projections.bean(BoardListReplyCountDTO.class,
                        board.bno,
                        board.title,
                        board.writer,
                        board.regDate,
                        reply.count().as("replyCount"))
                );

        this.getQuerydsl().applyPagination(pageable, dtojpqlQuery);

        List<BoardListReplyCountDTO> list = dtojpqlQuery.fetch();

        long totalCount = dtojpqlQuery.fetchCount();

        return new PageImpl<>(list,pageable,totalCount);
    }
}