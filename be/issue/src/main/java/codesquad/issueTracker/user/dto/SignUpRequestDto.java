package codesquad.issueTracker.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import codesquad.issueTracker.user.domain.LoginType;
import codesquad.issueTracker.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

	@Email(message = "유효한 이메일 주소를 입력하세요")
	private final String email;

	@Size(min = 6, max = 12, message = "비밀번호는 6자리에서 12자리까지 입력할 수 있습니다.")
	private final String password;

	@Size(min = 2, max = 12, message = "이름은 최소 2자리에서 12자리까지 입력할 수 있다.")
	private final String name;

	public static User toEntity(SignUpRequestDto signUpRequestDto, String encodedPassword, String profileImg) {
		return User.builder()
			.email(signUpRequestDto.getEmail())
			.password(encodedPassword)
			.name(signUpRequestDto.getName())
			.loginType(LoginType.LOCAL)
			.profileImg(profileImg)
			.build();
	}
}
