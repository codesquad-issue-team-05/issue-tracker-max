package codesquad.issueTracker.comment.service;

import static org.mockito.BDDMockito.given;

import annotation.ServiceTest;
import codesquad.issueTracker.comment.dto.CommentResponseDto;
import codesquad.issueTracker.comment.fixture.CommentTestFixture;
import codesquad.issueTracker.comment.repository.CommentRepository;
import java.util.List;
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

    @InjectMocks
    CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        commentResponseDtosFixture = dummyCommentResponseDto();
    }

    @Test
    @DisplayName("댓글 목록 조회에 성공한다.")
    public void getComments() throws Exception {
        //given
        given(commentRepository.findByIssueId(1L)).willReturn(commentResponseDtosFixture);

        //when
        List<CommentResponseDto> actual = commentService.getComments(1L);

        //then
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(commentResponseDtosFixture);
    }


}