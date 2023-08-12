package codesquad.issueTracker.comment.fixture;

import codesquad.issueTracker.comment.domain.Comment;
import codesquad.issueTracker.comment.dto.CommentRequestDto;
import codesquad.issueTracker.comment.dto.CommentResponseDto;
import codesquad.issueTracker.comment.vo.CommentUserVo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class CommentTestFixture {
    public List<CommentResponseDto> dummyCommentResponseDto() {
        return IntStream.range(1, 4)
                .mapToObj(this::makeCommentResponses)
                .collect(Collectors.toList());
    }

    private CommentResponseDto makeCommentResponses(int num) {
        Comment comment = makeComment(num);
        CommentUserVo commentUserVo = makeCommentUser();

        return CommentResponseDto.builder()
                .id((long) num)
                .content(comment.getContent())
                .writer(commentUserVo)
                .createdAt(comment.getCreatedAt())
                .build();
    }

    private Comment makeComment(int num) {
        return Comment.builder()
                .issueId(1L)
                .userId(1L)
                .content("comment content" + num)
                .createdAt(dummyLocalDateTime())
                .build();
    }

    private LocalDateTime dummyLocalDateTime() {
        return LocalDateTime.of(2023, 8, 12, 7, 23, 10);
    }

    private CommentUserVo makeCommentUser() {
        return CommentUserVo.builder()
                .name("sio")
                .profileImg("https://upload.wikimedia.org/wikipedia/commons/1/17/Enhydra_lutris_face.jpg")
                .build();
    }

    public CommentRequestDto dummyCommentRequestDto() {
        return new CommentRequestDto("post comment test1");
    }

    public Comment dummyComment() {
        return Comment.builder()
                .id(1L)
                .issueId(1L)
                .userId(1L)
                .content("dummy comment")
                .createdAt(LocalDateTime.of(2023, 8, 13, 1, 20, 10))
                .build();
    }
}
