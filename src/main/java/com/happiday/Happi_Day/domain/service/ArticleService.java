package com.happiday.Happi_Day.domain.service;

import com.happiday.Happi_Day.domain.entity.article.Article;
import com.happiday.Happi_Day.domain.entity.article.dto.ReadOneArticleDto;
import com.happiday.Happi_Day.domain.entity.article.dto.WriteFreeArticleDto;
import com.happiday.Happi_Day.domain.repository.ArticleRepository;
import com.happiday.Happi_Day.domain.repository.ArtistRepository;
import com.happiday.Happi_Day.domain.repository.BoardRepository;
import com.happiday.Happi_Day.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final ArtistRepository artistRepository;
    private final TeamRepository teamRepository;

    public void wirteFreeArticle(WriteFreeArticleDto dto, String artists, String teams) {
        // 아티스트 목록
        List<String> artistList = Arrays.asList(artists.replace(" ", "").split("#"));
        // 팀 목록
        List<String> teamList = Arrays.asList(teams.replace(" ", "").split("#"));

        for (String artist: artistList) {
            log.info(artist);
        }
        log.info("아티스트리스트:" + artistList);
        log.info("팀리스트:" + teamList);

        // TODO 아티스트 찾아서 리스트에 저장
//        List<Board> newBoardList = new ArrayList<>();
//        // 해당 아티스트가 DB에 저장되어있는지 확인
//        // 있으면 List<Board>에 해당 아티스트 추가
//        for (String artistName : artistList) {
//            Optional<Artist> optionalArtist = artistRepository.findByName(artistName);
//            if (!optionalArtist.isEmpty()) {
//                Optional<Board> optionalBoard = boardRepository.findByArtist(optionalArtist.get());
//                if (optionalBoard.isEmpty()) {
//                    // TODO 보드 추가
//                    log.info("존재하지않는아티스트");
//                } else {
//                    newBoardList.add(optionalBoard.get());
//                }
//            }
//        }
        // TODO 팀 찾아서 리스트에 저장
//        // 해당 팀이 DB에 저장되어있는지 확인
//        for (String teamName : teamList) {
//            Optional<Team> optionalTeam = teamRepository.findByName(teamName);
//            if (!optionalTeam.isEmpty()) {
//                // 해당 팀 Board가 있는지 확인
//                // 없으면 Board 생성 / 없으면 List<Board>에 저장
//                Optional<Board> optionalBoard = boardRepository.findByTeam(optionalTeam.get());
//                if (optionalBoard.isEmpty()) {
//                    // TODO 보드 추가
//                    log.info("존재하지않는팀");
//                } else {
//                    newBoardList.add(optionalBoard.get());
//                }
//            }
//        }


        // TODO 정보 저장 : 유저, 댓글, 좋아요, 스크랩, 아티스트 리스트, 팀 리스트 추가예정
        Article newArticle = new Article();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());

        articleRepository.save(newArticle);
    }


    // 글 상세 조회
    // TODO user, board, 댓글, 좋아요, 스크랩 정보 추가 예정
    public ReadOneArticleDto readOne(Long articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        ReadOneArticleDto dto = new ReadOneArticleDto();
        dto.setTitle(optionalArticle.get().getTitle());
        dto.setContent(optionalArticle.get().getContent());

        LocalDateTime createdDate = optionalArticle.get().getCreatedAt();

        String date = createdDate.getYear()+"년 "+createdDate.getMonthValue()+"월 "+createdDate.getDayOfMonth()+"일 "+createdDate.getHour()+":"+createdDate.getMinute();
        dto.setCreatedAt(date);
        return dto;
    }
}
