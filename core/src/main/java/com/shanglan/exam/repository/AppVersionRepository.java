package com.shanglan.exam.repository;

import com.shanglan.exam.entity.AppVersion;
import com.shanglan.exam.entity.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/7/4.
 */
public interface AppVersionRepository extends JpaRepository<AppVersion, Integer>,JpaSpecificationExecutor<ExamRecord> {

}
