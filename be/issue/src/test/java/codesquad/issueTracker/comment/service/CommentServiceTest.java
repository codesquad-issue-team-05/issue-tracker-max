package codesquad.issueTracker.comment.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import annotation.ServiceTest;
import codesquad.issueTracker.comment.domain.Comment;
import codesquad.issueTracker.comment.dto.CommentRequestDto;
import codesquad.issueTracker.comment.dto.CommentResponseDto;
import codesquad.issueTracker.comment.fixture.CommentTestFixture;
import codesquad.issueTracker.comment.repository.CommentRepository;
import codesquad.issueTracker.global.exception.CustomException;
import java.util.List;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ServiceTest
class CommentServiceTest extends CommentTestFixture {

    private final Log log = LogFactory.getLog(CommentServiceTest.class);
    private List<CommentResponseDto> commentResponseDtosFixture;
    private CommentRequestDto commentRequestDtoFixture;
    private Comment commentFixture;

    @InjectMocks
    CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        commentResponseDtosFixture = dummyCommentResponseDto();
        commentRequestDtoFixture = dummyCommentRequestDto();
        commentFixture = dummyComment();
    }

    @Test
    @DisplayName("댓글 목록 조회에 성공한다.")
    public void getComments() throws Exception {
        //given
        given(commentRepository.findByIssueId(1L)).willReturn(commentResponseDtosFixture);

        //when
        List<CommentResponseDto> actual = commentService.getComments(1L);

        //then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(commentResponseDtosFixture);
    }
    
    @Test
    @DisplayName("댓글 생성에 성공한다.")
    public void save_success() throws Exception {
        //given
        given(commentRepository.create(any(), any(), any())).willReturn(Optional.ofNullable(1L));
        
        //when
        Long actual = commentService.save(1L, 1L, commentRequestDtoFixture);
        
        //then
        assertThat(actual).isEqualTo(1L);
    }

    @Test
    @DisplayName("DB 서버 오류로 인해 댓글 생성에 실패한다.")
    public void save_fail() throws Exception {
        //given
        given(commentRepository.create(any(), any(), any())).willReturn(Optional.empty());

        //when & then
        assertThatThrownBy(() -> commentService.save(1L, 1L, commentRequestDtoFixture))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("댓글 수정에 성공한다.")
    public void update_success() throws Exception {
        //given
        given(commentRepository.findExistCommentById(1L)).willReturn(Optional.ofNullable(commentFixture));
        given(commentRepository.findById(1L)).willReturn(Optional.ofNullable(commentFixture));
        given(commentRepository.update(any(), any())).willReturn(Optional.ofNullable(1L));
        
        //when
        Long actual = commentService.modify(1L, commentRequestDtoFixture);

        //then
        assertThat(actual).isEqualTo(1L);
    }

}