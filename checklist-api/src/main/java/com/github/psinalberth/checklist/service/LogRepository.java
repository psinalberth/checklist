package com.github.psinalberth.checklist.service;

import org.springframework.data.repository.CrudRepository;

import com.github.psinalberth.checklist.model.Log;

public interface LogRepository extends CrudRepository<Log, Long> {

}
