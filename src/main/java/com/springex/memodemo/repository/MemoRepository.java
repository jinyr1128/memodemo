package com.springex.memodemo.repository;

import com.springex.memodemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MemoRepository extends JpaRepository<Memo,Long> {
}
