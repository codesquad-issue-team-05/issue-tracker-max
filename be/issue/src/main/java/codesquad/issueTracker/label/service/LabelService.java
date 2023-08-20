package codesquad.issueTracker.label.service;

import codesquad.issueTracker.issue.vo.IssueLabelVo;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import codesquad.issueTracker.global.exception.CustomException;
import codesquad.issueTracker.global.exception.ErrorCode;
import codesquad.issueTracker.label.domain.Label;
import codesquad.issueTracker.label.dto.CreateLabelResponseDto;
import codesquad.issueTracker.label.dto.LabelRequestDto;
import codesquad.issueTracker.label.dto.LabelResponseDto;
import codesquad.issueTracker.label.repository.LabelRepository;
import codesquad.issueTracker.label.vo.LabelVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {
	private final LabelRepository labelRepository;

	public CreateLabelResponseDto save(LabelRequestDto labelRequestDto) {
		return CreateLabelResponseDto.from(labelRepository.insert(Label.toEntity(labelRequestDto)));
	}

	public void modify(Long labelId, LabelRequestDto labelRequestDto) {
		labelRepository.update(labelId, Label.toEntity(labelRequestDto));
	}

	public void delete(Long labelId) {
		labelRepository.delete(labelId);
	}

	public LabelResponseDto findAll() {
		List<LabelVo> labels = new ArrayList<>();
		labelRepository.findAll()
			.orElseThrow(() -> new CustomException(ErrorCode.LABEL_FIND_FAILED))
			.forEach(label ->
				labels.add(LabelVo.from(label)));
		return LabelResponseDto.of(labels,
			labelRepository.findMilestonesCount()
				.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MILESTONE)));
	}

	public Label validateLabelsId(Long label) {
		return labelRepository.findById(label).orElseThrow(() -> new CustomException(ErrorCode.LABEL_FIND_FAILED));
	}

	public Long resetLabels(Long id) {
		return labelRepository.resetIssuesLabels(id);
	}

	public Long insertIssuesLabels(Long id, Long labelId) {
		return labelRepository.insertIssuesLabels(id, labelId);
	}

	public List<IssueLabelVo> findByIssueId(Long issueId) {
		return labelRepository.findLabelsById(issueId).stream()
				.map(LabelVo::from)
				.map(IssueLabelVo::from)
				.collect(Collectors.toList());
	}
}
