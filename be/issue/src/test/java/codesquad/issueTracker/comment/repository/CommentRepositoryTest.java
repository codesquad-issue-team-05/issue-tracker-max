package codesquad.issueTracker.comment.repository;

import static org.assertj.core.api.Assertions.*;

import annotation.RepositoryTest;
import codesquad.issueTracker.comment.dto.CommentRequestDto;
import codesquad.issueTracker.comment.dto.CommentResponseDto;
import codesquad.issueTracker.comment.fixture.CommentTestFixture;
import codesquad.issueTracker.user.domain.LoginType;
import codesquad.issueTracker.user.domain.User;
import codesquad.issueTracker.user.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


@RepositoryTest
class CommentRepositoryTest extends CommentTestFixture {

    private CommentRequestDto commentRequestDtoFixture;
    private List<CommentRequestDto> commentRequestDtosFixture;
    private List<CommentResponseDto> commentResponseDtosFixture;

    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.commentRepository = new CommentRepository(jdbcTemplate);
        this.userRepository = new UserRepository(jdbcTemplate);
    }

    @BeforeEach
    public void setUp() {
        commentRequestDtoFixture = dummyCommentRequestDto(1);
        commentRequestDtosFixture = dummyCommentRequestDtos();
        commentResponseDtosFixture = dummyCommentResponseDtos();
    }

    @Test
    @DisplayName("DB에 댓글 생성 데이터가 제대로 들어간다.")
    public void create() throws Exception {
        //given
        Long userId = 1L;
        Long issueId = 1L;

        //when
        Long actual = commentRepository.create(userId, issueId, commentRequestDtoFixture).get();

        //then
        assertThat(actual).isEqualTo(1L);
    }
    @Test
    @DisplayName("이슈 id에 해당하는 댓글 목록을 반환할 수 있다.")
    public void findComments() throws Exception {
        //given
        Long userId = 1L;
        Long issueId = 1L;

        User user = User.builder()
                .id(1L)
                .name("sio")
                .email("sio@gmail.com")
                .profileImg("http://image.png")
                .loginType(LoginType.GITHUB)
                .build();

        userRepository.insert(user);

        for (CommentRequestDto commentRequestDto : commentRequestDtosFixture) {
            commentRepository.create(userId, issueId, commentRequestDto);
        }
        //when
        List<CommentResponseDto> actual = commentRepository.findByIssueId(issueId);

        //then
        assertThat(actual.get(0).getId()).isEqualTo(commentResponseDtosFixture.get(0).getId());
    }
}