package codesquad.issueTracker.comment.controller;


import static codesquad.issueTracker.global.exception.SuccessCode.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import annotation.ControllerTest;
import codesquad.issueTracker.comment.domain.Comment;
import codesquad.issueTracker.comment.dto.CommentRequestDto;
import codesquad.issueTracker.comment.dto.CommentResponseDto;
import codesquad.issueTracker.comment.service.CommentService;
import codesquad.issueTracker.comment.vo.CommentUserVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ControllerTest(CommentController.class)
class CommentControllerTest {

    private final Log log = LogFactory.getLog(CommentControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("모든 댓글 목록들을 반환한다.")
    public void getComments() throws Exception {
        //given
        given(commentService.getComments(any())).willReturn(dummyCommentResponseDto());

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/issues/{issueId}/comments", 1L));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SUCCESS.getStatus().getReasonPhrase()))
                .andExpect(jsonPath("$.message[0].id").value(1))
                .andExpect(jsonPath("$.message[0].createdAt").value(dummyLocalDateTime().toString()))
                .andExpect(jsonPath("$.message[0].content").value("comment content1"))
                .andExpect(jsonPath("$.message[0].writer.name").value(makeCommentUser().getName()))
                .andExpect(jsonPath("$.message[0].writer.profileImg").value(makeCommentUser().getProfileImg()));

    }

    private List<CommentResponseDto> dummyCommentResponseDto() {
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
                .issueId((long) num)
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

    @Test
    @DisplayName("이슈 댓글을 작성한다.")
    public void save() throws Exception {
        //given
        given(commentService.save(any(), any(), any())).willReturn(1L);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/issues/{issueId}/comments", 1L)
                .content(objectMapper.writeValueAsString(dummyCommentRequestDto()))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(SUCCESS.getStatus().getReasonPhrase()))
                .andExpect(jsonPath("$.message").value(SUCCESS.getMessage()));
    }

    private CommentRequestDto dummyCommentRequestDto() {
        return new CommentRequestDto("post comment test1");
    }
}