package com.springex.memodemo.service;

import com.springex.memodemo.dto.MemoRequestDto;
import com.springex.memodemo.dto.MemoResponseDto;
import com.springex.memodemo.entity.Memo;
import com.springex.memodemo.repository.MemoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {

        this.memoRepository = memoRepository;
    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {


        return memoRepository.findAllByOrderByModifiedAtDesc().stream().map(MemoResponseDto::new).toList();
    }

    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);
        // memo 내용 수정
        memo.update(requestDto);
        return id;
    }

    public Long deleteMemo(Long id) {

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);


        // memo 삭제
        memoRepository.delete(memo);
        return id;
    }

    private Memo findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다")
        );
    }
}
