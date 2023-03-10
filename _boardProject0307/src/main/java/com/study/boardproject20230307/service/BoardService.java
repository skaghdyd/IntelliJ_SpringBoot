package com.study.boardproject20230307.service;

import com.study.boardproject20230307.dto.BoardDTO;
import com.study.boardproject20230307.entity.BoardEntity;
import com.study.boardproject20230307.entity.BoardFileEntity;
import com.study.boardproject20230307.repository.BoardFileRepository;
import com.study.boardproject20230307.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO.getBoardFile()===>>>" + boardDTO.getBoardFile());
        if (boardDTO.getBoardFile().isEmpty()) {
            //첨부 파일 없음.
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else {
            //첨부 파일 있음.
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long savedId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(savedId).get();
            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
//                MultipartFile boardFile = boardDTO.getBoardFile();
                String originalFilename = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "C:/Users/NHY/Desktop/IntelliJ_SpringBoot/_boardProject0307/src/main/resources/img/" + storedFileName;
//                String savePath = "C:/springboot_img/" + storedFileName; // 4.  springboot_img/45645656456_내사진.jpg
                boardFile.transferTo(new File(savePath));

                BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
                boardFileRepository.save(boardFileEntity);
            }
        }
    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            System.out.println("boardEntity===>>> " + boardEntity);
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            System.out.println("boardDTO===>>> " + boardDTO);
            return boardDTO;
        } else {
            return null;
        }
    }

    @Transactional
    public BoardDTO update(BoardDTO boardDTO) {
//        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
//        System.out.println("SERVICE - boardEntity ===> " + boardEntity);
//        boardRepository.save(boardEntity);

        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardDTO.getId());
        if(optionalBoardEntity.isEmpty()){
            System.out.println("update ===>>> 비어있을 수가 없는데..???");
        } else {
            BoardEntity boardEntity1 = optionalBoardEntity.get();
            boardEntity1.setBoardTitle(boardDTO.getBoardTitle());
            boardEntity1.setBoardContents(boardDTO.getBoardContents());
            System.out.println("SERVICE - boardEntity1 ===> " + boardEntity1);
            boardRepository.save(boardEntity1);
        }
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3;
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
    }
}
