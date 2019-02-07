package com.cts.payroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.payroll.bean.PayrollAppException;
import com.cts.payroll.bean.Skill;
import com.cts.payroll.dao.SkillDao;
import com.cts.payroll.repository.SkillRepository;

@Service
public class SkillService {

	private SkillDao skillDao;
	private SkillRepository skillRepository;

	@Autowired
	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@Autowired
	public void setSkillRepository(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

	@Transactional
	public List<Skill> getSkills()  {
		// return skillDao.getSkills();
		return (List<Skill>) skillRepository.findAll();
	}

	@Transactional
	public Skill getSkills(int SkillId) {
		return skillRepository.findById(SkillId).get();
	}
}
